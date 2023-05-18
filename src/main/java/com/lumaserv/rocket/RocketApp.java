package com.lumaserv.rocket;

import com.lumaserv.rocket.controller.Controller;
import com.lumaserv.rocket.event.EventBus;
import com.lumaserv.rocket.model.Model;
import com.lumaserv.rocket.service.Services;
import com.lumaserv.rocket.util.CustomAutoInjector;
import com.lumaserv.rocket.util.DefaultAccessible;
import lombok.Getter;
import org.javawebstack.httpserver.HTTPServer;
import org.javawebstack.orm.Accessible;
import org.javawebstack.orm.ORM;
import org.javawebstack.orm.ORMConfig;
import org.javawebstack.orm.exception.ORMConfigurationException;
import org.javawebstack.orm.mapper.AbstractDataTypeMapper;
import org.javawebstack.orm.wrapper.MySQL;
import org.javawebstack.orm.wrapper.SQL;
import org.javawebstack.webutils.config.Config;
import org.javawebstack.webutils.config.EnvFile;
import org.javawebstack.webutils.middleware.SerializedResponseTransformer;
import org.javawebstack.webutils.modelbind.ModelBindParamTransformer;

import java.io.File;

@Getter
public class RocketApp {

    public static RocketApp INSTANCE;

    public static void main(String[] args) throws ORMConfigurationException {
        INSTANCE = new RocketApp();
        INSTANCE.start();
    }

    final Config config;
    final HTTPServer server;
    final EventBus eventBus = new EventBus();
    final Services services;

    public RocketApp() throws ORMConfigurationException {
        config = new Config().add(new EnvFile(new File(".env")).withVariables(), Config::basicEnvMapping);
        services = new Services(this);
        setupDatabase();
        server = new HTTPServer()
                .port(config.getInt("http.port", 80))
                .routeParamTransformer(new ModelBindParamTransformer())
                .controller(Controller.class, Controller.class.getPackage())
                .routeAutoInjector(new CustomAutoInjector())
                .responseTransformer(new SerializedResponseTransformer());
    }

    public void start() {
        server.start().join();
    }

    public void setupDatabase() throws ORMConfigurationException {
        SQL sql = new MySQL(
                config.get("database.host"),
                config.getInt("database.port", 3306),
                config.get("database.name"),
                config.get("database.username"),
                config.get("database.password")
        );
        ORMConfig ormConfig = new ORMConfig()
                .setDefaultSize(50)
                .addTypeMapper(new AbstractDataTypeMapper());

        Accessible accessible = new DefaultAccessible();
        ORM.register(Model.class.getPackage(), sql, ormConfig);
        ORM.getRepos().forEach(r -> r.setAccessible(accessible));
        ORM.autoMigrate();
    }
}
