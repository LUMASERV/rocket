package com.lumaserv.rocket;

import com.lumaserv.rocket.event.EventBus;
import com.lumaserv.rocket.service.Services;
import lombok.Getter;
import org.javawebstack.httpserver.HTTPServer;
import org.javawebstack.webutils.config.Config;
import org.javawebstack.webutils.config.EnvFile;

import java.io.File;

@Getter
public class RocketApp {

    public static RocketApp INSTANCE;

    public static void main(String[] args) {
        INSTANCE = new RocketApp();
        INSTANCE.start();
    }

    final Config config;
    final HTTPServer server;
    final EventBus eventBus = new EventBus();
    final Services services;

    public RocketApp() {
        config = new Config().add(new EnvFile(new File(".env")).withVariables(), Config::basicEnvMapping);
        services = new Services(this);
        server = new HTTPServer()
                .port(config.getInt("http.port", 80));
    }

    public void start() {
        server.start().join();
    }

}
