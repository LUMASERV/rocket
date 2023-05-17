package com.lumaserv.rocket.service;

import com.lumaserv.rocket.model.Project;

public interface ProjectService {
    Project createProject(String name) throws ServiceException;
    void deleteProject(Project project, boolean force) throws ServiceException;
}
