package com.lumaserv.rocket.model;

import lombok.Getter;
import lombok.Setter;
import org.javawebstack.orm.Repo;
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

    public Query<Objective> objective() {
        return belongsTo(Objective.class);
    }

    public Query<Project> project() {
        return Repo.get(Project.class).query().whereExists(Objective.class, q -> q
                .where(Project.class, "id", "=", Objective.class, "projectId")
                .whereExists(Milestone.class, q2 -> q2
                        .where(Objective.class, "id", "=", Milestone.class, "objectiveId")
                        .where(Milestone.class, "id", "=", id)
                )
        );
    }

    public enum State {
        INACTIVE,
        ACTIVE,
        REACHED
    }

}
