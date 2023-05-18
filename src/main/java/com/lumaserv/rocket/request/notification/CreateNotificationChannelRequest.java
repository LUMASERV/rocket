package com.lumaserv.rocket.request.notification;

import com.lumaserv.rocket.model.NotificationChannel;
import lombok.Getter;
import org.javawebstack.abstractdata.AbstractObject;
import org.javawebstack.validator.rule.RequiredRule;
import org.javawebstack.validator.rule.StringRule;

@Getter
public class CreateNotificationChannelRequest {

    @RequiredRule
    @StringRule(min = 2, max = 50)
    String name;
    @RequiredRule
    NotificationChannel.Type type;
    @RequiredRule
    AbstractObject config;

}
