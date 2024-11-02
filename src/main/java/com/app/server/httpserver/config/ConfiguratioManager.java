package com.app.server.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.app.server.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfiguratioManager {

    private static ConfiguratioManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfiguratioManager() {
    }

    public static ConfiguratioManager getInstance() {
        if(myConfigurationManager == null) {
            myConfigurationManager = new ConfiguratioManager();
        }
        return myConfigurationManager;
    }

    /**
     * Used to load a configuration file by the path provided
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while((i = fileReader.read()) != -1) {
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing configuration file", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing configuration file, internal", e);
        }
    }

    /**
     * Returns the current loaded configuration
     */
    public Configuration getCurrentConfiguration() {
        if(myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration Set");
        }
        return myCurrentConfiguration;
    }

}
