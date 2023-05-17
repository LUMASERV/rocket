package com.lumaserv.rocket.model;

import lombok.Getter;
import lombok.Setter;
import org.javawebstack.abstractdata.AbstractObject;
import org.javawebstack.orm.annotation.Column;
import org.javawebstack.orm.annotation.Dates;
import org.javawebstack.orm.annotation.SoftDelete;
import org.javawebstack.webutils.modelbind.ModelBind;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Dates
@SoftDelete
@ModelBind("notificationchannel")
public class NotificationChannel extends Model {

    @Column
    UUID id;
    @Column
    String name;
    @Column
    Type type;
    @Column
    AbstractObject config;
    @Column
    Timestamp createdAt;
    @Column
    Timestamp updatedAt;
    @Column
    Timestamp deletedAt;

    public enum Type {
        SLACK
    }

}
