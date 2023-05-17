package com.lumaserv.rocket.service;

import com.lumaserv.rocket.RocketApp;
import lombok.Getter;

@Getter
public class Services {

    IndicatorService indicatorService;
    ObjectiveService objectiveService;
    MilestoneService milestoneService;

    public Services(RocketApp app) {
        indicatorService = new IndicatorServiceImpl(app);
        objectiveService = new ObjectiveServiceImpl(app);
        milestoneService = new MilestoneServiceImpl(app);
    }

}
