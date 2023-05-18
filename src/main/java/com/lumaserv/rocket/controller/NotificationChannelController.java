package com.lumaserv.rocket.controller;

import com.lumaserv.rocket.model.NotificationChannel;
import com.lumaserv.rocket.request.notification.CreateNotificationChannelRequest;
import com.lumaserv.rocket.resource.NotificationChannelResource;
import com.lumaserv.rocket.response.Response;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.verbs.Delete;
import org.javawebstack.httpserver.router.annotation.verbs.Get;
import org.javawebstack.httpserver.router.annotation.verbs.Post;
import org.javawebstack.orm.Repo;

import java.util.List;

@PathPrefix("notification-channels")
public class NotificationChannelController extends Controller {

    @Get
    public Response list() {
        List<NotificationChannel> channels = Repo.get(NotificationChannel.class).query().get();

        return Response.success().setData(NotificationChannelResource.class, channels);
    }

    @Post
    public Response create(CreateNotificationChannelRequest request) {
        if (Repo.get(NotificationChannel.class).query().where("name", request.getName()).hasRecords()) {
            return Response.error(400, "A notification channel with this name already exists");
        }
        NotificationChannel channel = new NotificationChannel()
                .setName(request.getName())
                .setType(request.getType())
                .setConfig(request.getConfig());
        channel.save();
        return Response.created(NotificationChannelResource.class, channel);
    }

    @Get("{notificationchannel:channel}")
    public Response get(NotificationChannel channel) {
        return Response.success().setData(NotificationChannelResource.class, channel);
    }

    @Delete("{notificationchannel:channel}")
    public Response delete(NotificationChannel channel) {
        return Response.error(501, "Not Implemented");
    }

}
