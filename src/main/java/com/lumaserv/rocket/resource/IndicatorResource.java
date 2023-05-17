package com.lumaserv.rocket.resource;

import com.lumaserv.rocket.model.Indicator;
import org.javawebstack.webutils.Resource;

import java.util.Date;
import java.util.UUID;

public class IndicatorResource implements Resource<Indicator> {

    UUID id;
    UUID projectId;
    String name;
    double value;
    Date createdAt;

    public void map(Indicator indicator, Context context) {
        this.id = indicator.getId();
        this.projectId = indicator.getProjectId();
        this.name = indicator.getName();
        this.value = indicator.getValue();
        this.createdAt = indicator.getCreatedAt();
    }

}
