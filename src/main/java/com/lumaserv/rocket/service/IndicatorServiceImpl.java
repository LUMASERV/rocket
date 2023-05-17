package com.lumaserv.rocket.service;

import com.lumaserv.rocket.RocketApp;
import com.lumaserv.rocket.event.indicator.IndicatorCreatedEvent;
import com.lumaserv.rocket.event.indicator.IndicatorDeletedEvent;
import com.lumaserv.rocket.event.indicator.IndicatorUpdateValueEvent;
import com.lumaserv.rocket.event.indicator.IndicatorValueUpdatedEvent;
import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.IndicatorUpdate;
import com.lumaserv.rocket.model.Objective;
import com.lumaserv.rocket.model.Project;
import lombok.AllArgsConstructor;
import org.javawebstack.webutils.util.RandomUtil;

@AllArgsConstructor
public class IndicatorServiceImpl implements IndicatorService {

    RocketApp app;

    public Indicator createIndicator(Project project, String name, double value) throws ServiceException {
        // Validate that there is no existing indicator with this name in the same project
        if(project.indicators().where("name", name).hasRecords()) {
            throw new ServiceException("An indicator with this name already exists in the project");
        }

        // Create Entry in Database
        Indicator indicator = new Indicator()
                .setProjectId(project.getId())
                .setName(name)
                .setValue(value)
                .setToken(RandomUtil.string(32));
        indicator.save();

        // Create Update Entry in Database
        IndicatorUpdate update = new IndicatorUpdate()
                .setIndicatorId(indicator.getId())
                .setValue(value);
        update.save();

        // Dispatch Event
        app.getEventBus().dispatch(new IndicatorCreatedEvent(indicator));

        return indicator;
    }

    public void deleteIndicator(Indicator indicator, boolean force) throws ServiceException {
        if(!force) {
            // Check if the indicator is used by any objective
            if(indicator.objectives().hasRecords()) {
                throw new ServiceException("The indicator is in use by one ore more objectives");
            }
        } else {
            // Disconnect objectives from indicator
            for(Objective objective : indicator.objectives().get()) {
                app.getServices().getObjectiveService().disconnectObjectiveIndicator(objective);
            }
        }

        // Delete in database
        indicator.delete();

        // Dispatch event
        app.getEventBus().dispatch(new IndicatorDeletedEvent(indicator));
    }

    public void updateIndicatorValue(Indicator indicator, double value) throws ServiceException {
        // Save old value for later use in updated event
        double oldValue = indicator.getValue();

        // Cancel if value didn't change
        if(oldValue == value)
            return;

        // Dispatch update event
        IndicatorUpdateValueEvent updateEvent = new IndicatorUpdateValueEvent(indicator, value);
        app.getEventBus().dispatch(updateEvent);

        // Cancel if event has been cancelled
        if(updateEvent.isCancelled())
            return;

        // Set new value
        indicator.setValue(updateEvent.getNewValue()).save();

        // Dispatch updated event
        IndicatorUpdate update = new IndicatorUpdate()
                .setIndicatorId(indicator.getId())
                .setValue(updateEvent.getNewValue());
        update.save();
        app.getEventBus().dispatch(new IndicatorValueUpdatedEvent(indicator, update, oldValue));

        // Update objectives connected to this indicator
        for(Objective objective : indicator.objectives().get()) {
            app.getServices().getObjectiveService().updateObjectiveValue(objective, indicator);
        }
    }

}
