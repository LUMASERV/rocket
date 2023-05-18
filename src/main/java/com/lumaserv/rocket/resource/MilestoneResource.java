package com.lumaserv.rocket.resource;

import com.lumaserv.rocket.model.Milestone;
import org.javawebstack.webutils.Resource;

import java.util.Date;
import java.util.UUID;

public class MilestoneResource implements Resource<Milestone> {
    UUID id;
    UUID objectiveId;
    String name;
    double value;
    Date createdAt;

    public void map(Milestone milestone, Context context) {
        this.id = milestone.getId();
        this.objectiveId = milestone.getObjectiveId();
        this.value = milestone.getValue();
        this.createdAt = milestone.getCreatedAt();
    }
}
