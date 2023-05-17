package com.lumaserv.rocket.model;

import lombok.Getter;
import lombok.Setter;
import org.javawebstack.orm.annotation.Column;

import java.util.UUID;

@Getter
@Setter
public class ProjectNotificationChannel extends Model {

    @Column
    UUID id;
    @Column
    UUID projectId;
    @Column
    UUID notificationChannelId;

}
