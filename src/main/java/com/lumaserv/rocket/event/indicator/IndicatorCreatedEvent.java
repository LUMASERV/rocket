package com.lumaserv.rocket.event.indicator;

import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Indicator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IndicatorCreatedEvent extends Event {

    Indicator indicator;

}
