package com.lumaserv.rocket.model;

import lombok.Getter;
import lombok.Setter;
import org.javawebstack.orm.annotation.Column;
import org.javawebstack.orm.annotation.Dates;
import org.javawebstack.orm.annotation.SoftDelete;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Dates
@SoftDelete
public class Milestone extends Model {

    @Column
    UUID id;
    @Column
    UUID objectiveId;
    @Column
    State state;
    @Column
    Double value;
    @Column
    Timestamp missedAt;
    @Column
    Timestamp reachedAt;
    @Column
    Timestamp estimatedAt;
    @Column
    Timestamp createdAt;
    @Column
    Timestamp updatedAt;
    @Column
    Timestamp deletedAt;



    public enum State {
        INACTIVE,
        ACTIVE,
        REACHED
    }

}
