package com.lumaserv.rocket.event.objective;

import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Indicator;
import com.lumaserv.rocket.model.Objective;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ObjectiveIndicatorConnectedEvent extends Event {

    Objective objective;
    Indicator indicator;

}
