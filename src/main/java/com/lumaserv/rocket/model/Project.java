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
public class Project extends Model {

    @Column
    UUID id;
    @Column
    String name;
    @Column
    Timestamp createdAt;
    @Column
    Timestamp updatedAt;
    @Column
    Timestamp deletedAt;

    public Query<Objective> objectives() {
        return hasMany(Objective.class);
    }

    public Query<Indicator> indicators() {
        return hasMany(Indicator.class);
    }

}
