package com.lumaserv.rocket.service;

import com.lumaserv.rocket.RocketApp;
import com.lumaserv.rocket.event.objective.*;
import com.lumaserv.rocket.model.*;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ObjectiveServiceImpl implements ObjectiveService {

    RocketApp app;

    public Objective createObjective(Project project, String name, double value) throws ServiceException {
        // Validate that there is no existing objective with this name in the same project
        if(project.objectives().where("name", name).hasRecords()) {
            throw new ServiceException("An objective with this name already exists in the project");
        }

        // Create Entry in Database
        Objective objective = new Objective()
                .setProjectId(project.getId())
                .setName(name)
                .setValue(value);
        objective.save();

        // Dispatch Event
        app.getEventBus().dispatch(new ObjectiveCreatedEvent(objective));

        return objective;
    }

    public void updateObjectiveValue(Objective objective, double value) throws ServiceException {
        updateObjectiveValue(objective, value, ObjectiveValueUpdatedEvent.Cause.MANUAL);
    }

    public void updateObjectiveValue(Objective objective, Indicator indicator) throws ServiceException {
        updateObjectiveValue(objective, indicator.getValue(), ObjectiveValueUpdatedEvent.Cause.INDICATOR);
    }

    private void updateObjectiveValue(Objective objective, double value, ObjectiveValueUpdatedEvent.Cause cause) throws ServiceException {
        double oldValue = objective.getValue();
        objective.setValue(value).save();
        app.getEventBus().dispatch(new ObjectiveValueUpdatedEvent(objective, oldValue, cause));
        List<Milestone> milestones = objective.milestones()
                .where("state", "!=", Milestone.State.REACHED)
                .where("value", "<=", value)
                .get();
        for(Milestone m : milestones) {
            app.getServices().getMilestoneService().completeMilestone(m);
        }
    }

    public void connectObjectiveIndicator(Objective objective, Indicator indicator) throws ServiceException {
        Indicator oldIndicator = objective.indicator().first();
        // Disconnect current indicator
        if(oldIndicator != null) {
            objective.setIndicatorId(null).save();
            app.getEventBus().dispatch(new ObjectiveIndicatorDisconnectedEvent(objective, indicator));
        }

        // Connect the new indicator
        objective.setIndicatorId(indicator.getId()).save();
        app.getEventBus().dispatch(new ObjectiveIndicatorConnectedEvent(objective, indicator));

        // Push value from indicator to objective
        updateObjectiveValue(objective, indicator);
    }

    public void disconnectObjectiveIndicator(Objective objective) throws ServiceException {
        Indicator indicator = objective.indicator().first();
        if(indicator == null)
            throw new ServiceException("Objective is not connected to any indicator");
        objective.setIndicatorId(null).save();
        app.getEventBus().dispatch(new ObjectiveIndicatorDisconnectedEvent(objective, indicator));
    }



    public void deleteObjective(Objective objective) throws ServiceException {
        for(Milestone milestone : objective.milestones().get()) {
            app.getServices().getMilestoneService().deleteMilestone(milestone);
        }
        objective.delete();
        app.getEventBus().dispatch(new ObjectiveDeletedEvent(objective));
    }

}
