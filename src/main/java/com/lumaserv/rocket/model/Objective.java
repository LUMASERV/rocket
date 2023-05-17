package com.lumaserv.rocket.model;

import lombok.Getter;
import lombok.Setter;
import org.javawebstack.orm.annotation.Column;
import org.javawebstack.orm.annotation.Dates;
import org.javawebstack.orm.annotation.SoftDelete;
import org.javawebstack.orm.query.Query;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Dates
@SoftDelete
public class Objective extends Model {

    @Column
    UUID id;
    @Column
    UUID projectId;
    @Column
    String name;
    @Column
    UUID indicatorId;
    @Column
    Double value;
    @Column
    Timestamp createdAt;
    @Column
    Timestamp updatedAt;
    @Column
    Timestamp deletedAt;

    public Query<Project> project() {
        return belongsTo(Project.class);
    }

    public Query<Indicator> indicator() {
        return belongsTo(Indicator.class);
    }

    public Query<Milestone> milestones() {
        return hasMany(Milestone.class);
    }

}
