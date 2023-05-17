package com.lumaserv.rocket.service;

import com.lumaserv.rocket.model.Team;
import org.javawebstack.orm.Repo;

public class TeamServiceImpl implements TeamService{
    public Team createTeam(String name) throws ServiceException {
        if(Repo.get(Team.class).query().where("name",name).hasRecords()){
            throw new ServiceException("A team with this name already exists.");
        }

        Team team = new Team().setName(name);
        team.save();

        return team;
    }

    public void deleteTeam(Team team, boolean force) throws ServiceException {

    }

    public void updateTeam(Team team, double value) throws ServiceException {

    }
}
