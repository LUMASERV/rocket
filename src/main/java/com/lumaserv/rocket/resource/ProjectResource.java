package com.lumaserv.rocket.resource;

import com.lumaserv.rocket.model.Project;
import org.javawebstack.webutils.Resource;

public class ProjectResource implements Resource<Project> {

    String name;

    public void map(Project project, Context context) {
        this.name = project.getName();
    }
}
