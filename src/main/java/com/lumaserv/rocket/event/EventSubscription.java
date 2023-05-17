package com.lumaserv.rocket.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class EventSubscription<T extends Event> {

    EventBus bus;
    Class<T> type;
    Consumer<T> callback;

    public void unsubscribe() {
        bus.unsubscribe(this);
    }

}
