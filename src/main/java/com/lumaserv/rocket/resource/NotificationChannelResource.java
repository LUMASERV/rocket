package com.lumaserv.rocket.resource;

import com.lumaserv.rocket.model.NotificationChannel;
import org.javawebstack.webutils.Resource;

import java.sql.Timestamp;
import java.util.UUID;

public class NotificationChannelResource implements Resource<NotificationChannel> {

    UUID id;
    String name;
    NotificationChannel.Type type;
    Timestamp createdAt;

    public void map(NotificationChannel notificationChannel, Context context) {
        this.id = notificationChannel.getId();
        this.name = notificationChannel.getName();
        this.type = notificationChannel.getType();
        this.createdAt = notificationChannel.getCreatedAt();
    }
}
