package com.lumaserv.rocket.controller;


import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.Project;
import com.lumaserv.rocket.request.indicator.IndicatorWebhookRequest;
import com.lumaserv.rocket.resource.IndicatorResource;
import com.lumaserv.rocket.response.Response;
import com.lumaserv.rocket.service.ServiceException;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.params.Path;
import org.javawebstack.httpserver.router.annotation.verbs.Post;
import org.javawebstack.orm.Repo;

@PathPrefix("indicator-webhook")
public class IndicatorWebhookController extends Controller {

    @Post("{token}")
    public Response update(IndicatorWebhookRequest request, @Path("token")String token){
        Indicator indicator = Repo.get(Indicator.class).where("token",token).first();
        if(indicator == null) {
            return Response.error(404,"Webhook not found.");
        }
        try {
            getApp().getServices().getIndicatorService().updateIndicatorValue(indicator, request.getValue());
        } catch (ServiceException exception) {
            return Response.error(500, exception.getMessage());
        }
        return Response.success();
    }
}
