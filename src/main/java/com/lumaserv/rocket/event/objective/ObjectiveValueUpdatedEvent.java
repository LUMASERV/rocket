package com.lumaserv.rocket.event.objective;

import com.lumaserv.rocket.event.Event;
import com.lumaserv.rocket.model.Objective;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ObjectiveValueUpdatedEvent extends Event {

    Objective objective;
    double oldValue;
    Cause cause;

    public enum Cause {
        INDICATOR,
        MANUAL
    }

}
