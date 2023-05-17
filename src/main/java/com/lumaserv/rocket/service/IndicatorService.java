package com.lumaserv.rocket.service;

import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.Project;

public interface IndicatorService {

    Indicator createIndicator(Project project, String name, double value) throws ServiceException;
    void deleteIndicator(Indicator indicator, boolean force) throws ServiceException;
    void updateIndicatorValue(Indicator indicator, double value) throws ServiceException;

}
