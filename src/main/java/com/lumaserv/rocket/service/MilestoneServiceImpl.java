package com.lumaserv.rocket.service;

import com.lumaserv.rocket.RocketApp;
import com.lumaserv.rocket.event.milestone.MilestoneDeletedEvent;
import com.lumaserv.rocket.model.Milestone;
import com.lumaserv.rocket.model.Objective;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MilestoneServiceImpl implements MilestoneService {

    RocketApp app;

    public void deleteMilestone(Milestone milestone) throws ServiceException {
        milestone.delete();
        app.getEventBus().dispatch(new MilestoneDeletedEvent(milestone));
    }

    public Milestone createMilestone(Objective objective, String name, double value) throws ServiceException {
        return null;
    }
}
