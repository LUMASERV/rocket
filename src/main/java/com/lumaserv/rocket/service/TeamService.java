package com.lumaserv.rocket.service;

import com.lumaserv.rocket.model.Team;

public interface TeamService {
    Team createTeam(String name) throws ServiceException;
    void deleteTeam(Team team, boolean force) throws ServiceException;
    void updateTeam(Team team, double value) throws ServiceException;
}
