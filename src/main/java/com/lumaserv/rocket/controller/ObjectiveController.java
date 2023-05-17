package com.lumaserv.rocket.controller;

import com.lumaserv.rocket.model.Objective;
import com.lumaserv.rocket.model.Project;
import com.lumaserv.rocket.request.indicator.CreateObjectiveRequest;
import com.lumaserv.rocket.resource.IndicatorResource;
import com.lumaserv.rocket.resource.ObjectiveResource;
import com.lumaserv.rocket.response.Response;
import com.lumaserv.rocket.service.IndicatorService;
import com.lumaserv.rocket.service.ObjectiveService;
import com.lumaserv.rocket.service.ServiceException;
import org.javawebstack.httpserver.Exchange;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.params.Path;
import org.javawebstack.httpserver.router.annotation.verbs.Delete;
import org.javawebstack.httpserver.router.annotation.verbs.Get;
import org.javawebstack.httpserver.router.annotation.verbs.Post;
import org.javawebstack.orm.Repo;

import java.util.List;


@PathPrefix("objectives")
public class ObjectiveController extends Controller {
    private ObjectiveService getObjectiveService() {
        return getApp().getServices().getObjectiveService();
    }
    @Post
    public Response create(Exchange exchange, CreateObjectiveRequest request) {
        Project project = Repo.get(Project.class)
                .accessible(exchange)
                .whereId(request.getProjectId())
                .first();
        if(project == null)
            return Response.error(400, "Project not found.");

        try {
            Objective objective = getObjectiveService().createObjective(project, request.getName(), request.getValue() != null ? request.getValue() : 0);
            return Response.created(ObjectiveResource.class, objective);
        } catch (ServiceException exception) {
            return Response.error(500, exception.getMessage());
        }
    }

    @Delete("{objective:objective}")
    public Response delete(@Path("objective") Objective objective){
        try{
            getObjectiveService().deleteObjective(objective);
            return Response.success();
        } catch(ServiceException exception){
            return Response.error(500, exception.getMessage());
        }
    }

    @Get("{objective:objective}")
    public Response get(Exchange exchange,@Path("objective") Objective objective){
        return Response.success().setData(ObjectiveResource.class,objective);
    }

    @Get
    public Response getAll(){
        List<Objective> objectiveList = Repo.get(Objective.class).query().all();
        return Response.success().setData(ObjectiveResource.class,objectiveList);
    }
}
