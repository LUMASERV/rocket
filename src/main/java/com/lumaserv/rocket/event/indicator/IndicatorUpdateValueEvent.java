package com.lumaserv.rocket.event.indicator;

import com.lumaserv.rocket.event.Cancellable;
import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Indicator;
import lombok.Getter;
import lombok.Setter;

@Getter
public class IndicatorUpdateValueEvent extends Event implements Cancellable {

    final Indicator indicator;
    @Setter
    double newValue;
    @Setter
    boolean cancelled;

    public IndicatorUpdateValueEvent(Indicator indicator, double newValue) {
        this.indicator = indicator;
        this.newValue = newValue;
    }

}
