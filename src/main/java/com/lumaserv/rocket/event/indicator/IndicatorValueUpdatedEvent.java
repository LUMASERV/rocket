package com.lumaserv.rocket.event.indicator;

import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.IndicatorUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IndicatorValueUpdatedEvent extends Event {

    Indicator indicator;
    IndicatorUpdate update;
    double oldValue;

}
