package com.lumaserv.rocket.controller;

import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.Project;
import com.lumaserv.rocket.request.indicator.CreateIndicatorRequest;
import com.lumaserv.rocket.resource.IndicatorResource;
import com.lumaserv.rocket.response.Response;
import com.lumaserv.rocket.service.IndicatorService;
import com.lumaserv.rocket.service.ServiceException;
import org.javawebstack.httpserver.Exchange;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.params.Path;
import org.javawebstack.httpserver.router.annotation.verbs.Delete;
import org.javawebstack.httpserver.router.annotation.verbs.Get;
import org.javawebstack.httpserver.router.annotation.verbs.Post;
import org.javawebstack.orm.Repo;

import java.util.List;

@PathPrefix("indicators")
public class IndicatorController extends Controller {

    private IndicatorService getIndicatorService() {
        return getApp().getServices().getIndicatorService();
    }

    @Post
    public Response create(Exchange exchange, CreateIndicatorRequest request) {
        Project project = Repo.get(Project.class)
                .accessible(exchange)
                .whereId(request.getProjectId())
                .first();
        if(project == null)
            return Response.error(400, "Project not found");

        try {
            Indicator indicator = getIndicatorService().createIndicator(project, request.getName(), request.getValue() != null ? request.getValue() : 0);
            return Response.created(IndicatorResource.class, indicator);
        } catch (ServiceException exception) {
            return Response.error(500, exception.getMessage());
        }
    }

    @Delete("{indicator:indicator}")
    public Response delete(@Path("indicator") Indicator indicator) {

        try {
            getIndicatorService().deleteIndicator(indicator, true);
            return Response.success();
        } catch (ServiceException exception) {
            return Response.error(500, exception.getMessage());
        }

    }

    @Get("{indicator:indicator}")
    public Response get(Exchange exchange, @Path("indicator") Indicator indicator){
        return Response.success().setData(IndicatorResource.class, indicator);
    }

    @Get
    public Response getAll() {
        List<Indicator> indicatorList = Repo.get(Indicator.class).query().all();
        return Response.success().setData(IndicatorResource.class,indicatorList);
    }
}
