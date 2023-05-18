package com.lumaserv.rocket.service;

import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.Objective;
import com.lumaserv.rocket.model.Project;

public interface ObjectiveService {

    Objective createObjective(Project project, String name, double value) throws ServiceException;

    void updateObjectiveValue(Objective objective, double value) throws ServiceException;

    void updateObjectiveValue(Objective objective, Indicator indicator) throws ServiceException;

    void connectObjectiveIndicator(Objective objective, Indicator indicator) throws ServiceException;

    void disconnectObjectiveIndicator(Objective objective) throws ServiceException;

    void deleteObjective(Objective objective) throws ServiceException;

}
