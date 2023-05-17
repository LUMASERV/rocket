package com.lumaserv.rocket.controller;

import com.lumaserv.rocket.RocketApp;

public abstract class Controller {

    protected RocketApp getApp() {
        return RocketApp.INSTANCE;
    }

}
