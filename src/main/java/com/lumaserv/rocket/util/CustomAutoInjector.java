package com.lumaserv.rocket.util;

import org.javawebstack.httpserver.Exchange;
import org.javawebstack.httpserver.router.RouteAutoInjector;

import java.util.Map;

public class CustomAutoInjector implements RouteAutoInjector {

    public Object getValue(Exchange exchange, Map<String, Object> map, Class<?> type) {
        if(type.getPackage().getName().startsWith("com.lumaserv.rocket.request")) {
            return exchange.body(type);
        }
        return null;
    }

}
