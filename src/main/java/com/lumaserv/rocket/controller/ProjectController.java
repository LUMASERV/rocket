package com.lumaserv.rocket.controller;


import com.lumaserv.rocket.model.Project;
import com.lumaserv.rocket.request.indicator.CreateProjectRequest;
import com.lumaserv.rocket.resource.ProjectResource;
import com.lumaserv.rocket.response.Response;
import com.lumaserv.rocket.service.ProjectService;
import com.lumaserv.rocket.service.ServiceException;
import org.javawebstack.httpserver.Exchange;
import org.javawebstack.httpserver.router.annotation.PathPrefix;
import org.javawebstack.httpserver.router.annotation.params.Path;
import org.javawebstack.httpserver.router.annotation.verbs.Delete;
import org.javawebstack.httpserver.router.annotation.verbs.Get;
import org.javawebstack.httpserver.router.annotation.verbs.Post;
import org.javawebstack.orm.Repo;

import java.util.List;

@PathPrefix("projects")
public class ProjectController extends Controller {

    private ProjectService getProjectService(){
        return getApp().getServices().getProjectService();
    }

    @Post
    public Response create(Exchange exchange, CreateProjectRequest request){
        try {
            Project project = getProjectService().createProject(request.getName());
            return Response.created(ProjectResource.class,project);
        } catch (ServiceException exception) {
            return Response.error(500,exception.getMessage());
        }
    }

    @Delete("{project:project}")
    public Response delete(@Path("project") Project project){
        try {
            getProjectService().deleteProject(project,true);
            return Response.success();
        } catch (ServiceException exception) {
            return Response.error(500,exception.getMessage());
        }
    }

    @Get("{project:project}")
    public Response get(Exchange exchange, @Path("project") Project project) {
        return Response.success().setData(ProjectResource.class,project);
    }

    @Get
    public Response getAll() {
        List<Project> projectList = Repo.get(Project.class).query().all();
        return Response.success().setData(ProjectResource.class, projectList);
    }
}
