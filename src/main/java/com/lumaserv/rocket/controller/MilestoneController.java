package com.lumaserv.rocket.controller;

import com.lumaserv.rocket.model.Milestone;
import com.lumaserv.rocket.model.Objective;
import com.lumaserv.rocket.model.Project;
import com.lumaserv.rocket.request.indicator.CreateMilestoneRequest;
import com.lumaserv.rocket.resource.MilestoneResource;
import com.lumaserv.rocket.resource.ObjectiveResource;
import com.lumaserv.rocket.response.Response;
import com.lumaserv.rocket.service.MilestoneService;
import com.lumaserv.rocket.service.ServiceException;
import org.javawebstack.httpserver.Exchange;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.params.Path;
import org.javawebstack.httpserver.router.annotation.verbs.Delete;
import org.javawebstack.httpserver.router.annotation.verbs.Get;
import org.javawebstack.httpserver.router.annotation.verbs.Post;
import org.javawebstack.orm.Repo;

import java.util.List;

@PathPrefix("milestone")
public class MilestoneController extends Controller {
    private MilestoneService getMilestoneService() {
        return getApp().getServices().getMilestoneService();
    }

    @Post
    public Response create(Exchange exchange, CreateMilestoneRequest request) {
        Objective objective = Repo.get(Objective.class)
                .accessible(exchange)
                .whereId(request.getObjectiveId())
                .first();

        if(objective == null) {
            return Response.error(400,"Objective not found.");
        }

        try {
            Milestone milestone = getMilestoneService().createMilestone(objective, request.getName(), request.getValue() != null ? request.getValue() : 0);
            return Response.created(MilestoneResource.class,milestone);
        } catch (ServiceException exception) {
            return Response.error(500,exception.getMessage());
        }
    }

    @Delete("{milestone:milestone}")
    public Response delete(@Path("milestone") Milestone milestone){
        try{
            getMilestoneService().deleteMilestone(milestone);
            return Response.success();
        } catch (ServiceException exception) {
            return Response.error(500,exception.getMessage());
        }
    }

    @Get("{milestone:milestone}")
    public Response get(Exchange exchange, @Path("milestone") Milestone milestone){
        return Response.success().setData(MilestoneResource.class,milestone);
    }

    @Get
    public Response getAll(){
        List<Milestone> milestoneList = Repo.get(Milestone.class).query().all();
        return Response.success().setData(MilestoneResource.class,milestoneList);
    }
}
