package com.lumaserv.rocket.service;

import com.lumaserv.rocket.RocketApp;
import com.lumaserv.rocket.event.milestone.MilestoneDeletedEvent;
import com.lumaserv.rocket.event.milestone.MilestoneReachedEvent;
import com.lumaserv.rocket.model.Milestone;
import com.lumaserv.rocket.model.NotificationChannel;
import com.lumaserv.rocket.model.Objective;
import com.lumaserv.rocket.model.Project;
import com.lumaserv.rocket.util.SlackWebhook;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class MilestoneServiceImpl implements MilestoneService {

    RocketApp app;

    public void deleteMilestone(Milestone milestone) throws ServiceException {
        milestone.delete();
        app.getEventBus().dispatch(new MilestoneDeletedEvent(milestone));
    }

    public void completeMilestone(Milestone milestone) throws ServiceException {
        Objective objective = milestone.objective().first();
        Project project = objective.project().first();
        milestone
                .setState(Milestone.State.REACHED)
                .setReachedAt(Timestamp.from(Instant.now()))
                .save();
        app.getEventBus().dispatch(new MilestoneReachedEvent(milestone));
        List<NotificationChannel> channels = project.notificationChannels().get();
        channels.forEach(c -> {
            switch (c.getType()) {
                case SLACK: {
                    SlackWebhook webhook = new SlackWebhook(c.getConfig().string("url"));
                    webhook.send(new SlackWebhook.Message()
                            .text("*Milestone Reached :rocket:*\n" + objective.getName() + "\n" + objective.getValue() + " / " + milestone.getValue())
                    );
                    break;
                }
            }
        });
    }

    public Milestone createMilestone(Objective objective, String name, double value) throws ServiceException {
        return null;
    }
}
