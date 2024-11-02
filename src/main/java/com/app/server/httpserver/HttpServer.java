package com.app.server.httpserver;

import com.app.config.ConfigBD;
import com.app.server.httpserver.config.ConfiguratioManager;
import com.app.server.httpserver.config.Configuration;
import com.app.server.httpserver.core.ServerListenerThread;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * Driver Class for the Http Server
 *
 */

@Slf4j
public class HttpServer {

    public static void main(String[] args) {
        log.info("Config BD...");
        var sessionFactory = ConfigBD.getSessionFactory();

        log.info("Server starting...");

        ConfiguratioManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfiguratioManager.getInstance().getCurrentConfiguration();

        log.info("Using port: {}", conf.getPort());
        log.info("Using webroot: {}", conf.getWebroot());


        ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
        serverListenerThread.start();
    }

}
