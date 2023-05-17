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
public class Indicator extends Model {

    @Column
    UUID id;
    @Column
    UUID projectId;
    @Column
    String name;
    @Column
    Double value;
    @Column
    String token;
    @Column
    Timestamp createdAt;
    @Column
    Timestamp updatedAt;
    @Column
    Timestamp deletedAt;

    public Query<Objective> objectives() {
        return hasMany(Objective.class);
    }

    public Query<IndicatorUpdate> updates() {
        return hasMany(IndicatorUpdate.class);
    }

}
