package com.lumaserv.rocket.service;


import com.lumaserv.rocket.model.Milestone;
import com.lumaserv.rocket.model.Objective;


public interface MilestoneService {
    Milestone createMilestone(Objective objective, String name, double value) throws ServiceException;
    void deleteMilestone(Milestone milestone) throws ServiceException;

}
