package com.lumaserv.rocket.resource;

import com.lumaserv.rocket.model.Objective;
import org.javawebstack.webutils.Resource;

import java.sql.Timestamp;
import java.util.UUID;

public class ObjectiveResource implements Resource<Objective> {

    UUID id;
    UUID projectId;
    String name;
    UUID indicatorId;
    double value;
    Timestamp createdAt;

    public void map(Objective objective, Context context) {
        this.id = objective.getId();
        this.projectId = objective.getProjectId();
        this.name = objective.getName();
        this.indicatorId = objective.getIndicatorId();
        this.value = objective.getValue();
        this.createdAt = objective.getCreatedAt();
    }

}
