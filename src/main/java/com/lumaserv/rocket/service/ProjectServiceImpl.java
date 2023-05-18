package com.lumaserv.rocket.service;

import com.lumaserv.rocket.RocketApp;
import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.Objective;
import com.lumaserv.rocket.model.Project;
import lombok.AllArgsConstructor;
import org.javawebstack.orm.Repo;

@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    RocketApp app;


    public Project createProject(String name) throws ServiceException {
        if (Repo.get(Project.class).query().where("name", name).hasRecords()) {
            throw new ServiceException("A project with this name already exists.");
        }

        Project project = new Project().setName(name);
        project.save();

        return project;
    }

    public void deleteProject(Project project, boolean force) throws ServiceException {
        if (!force) {
            if (project.objectives().hasRecords()) {
                throw new ServiceException("The project still has one or more objectives.");
            } else if (project.indicators().hasRecords()) {
                throw new ServiceException("The project still has one or more indicators.");
            }


        } else {
            for (Indicator indicator : project.indicators().get()) {
                app.getServices().getIndicatorService().deleteIndicator(indicator, true);
            }

            for (Objective objective : project.objectives().get()) {
                app.getServices().getObjectiveService().disconnectObjectiveIndicator(objective);
                app.getServices().getObjectiveService().deleteObjective(objective);
            }
        }
    }
}
