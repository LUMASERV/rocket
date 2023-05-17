package com.lumaserv.rocket.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventBus {

    final List<EventSubscription<?>> subscriptions = new ArrayList<>();

    public synchronized <T extends Event> EventSubscription<T> subscribe(Class<T> type, Consumer<T> callback) {
        EventSubscription<T> subscription = new EventSubscription<>(this, type, callback);
        subscriptions.add(subscription);
        return subscription;
    }

    public synchronized void unsubscribe(EventSubscription<?> subscription) {
        subscriptions.remove(subscription);
    }

    public <T extends Event> T dispatch(T event) {
        for(EventSubscription<?> s : new ArrayList<>(subscriptions)) {
            if(s.getType().isAssignableFrom(event.getClass())) {
                EventSubscription<T> sub = (EventSubscription<T>) s;
                sub.getCallback().accept(event);
                if(event instanceof Cancellable && ((Cancellable) event).isCancelled())
                    break;
            }
        }
        return event;
    }

}
