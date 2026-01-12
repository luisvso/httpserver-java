package br.luis.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import br.luis.httpserver.config.exception.HttpException;
import br.luis.httpserver.util.json;

public class ConfigurationManager {

    private static ConfigurationManager configurationManager;
    private static Configuration configuration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null)
            configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;

        StringBuffer buffer = new StringBuffer();
        int i;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpException("The file does not exist", e);
        }

        try {
            while ((i = fileReader.read()) != -1) {
                buffer.append((char) i);
            }
        } catch (IOException e) {
            throw new HttpException(e);
        }

        JsonNode conf = null;

        try {
            conf = json.parse(buffer.toString());
        } catch (IOException e) {
            throw new HttpException("Error parsing the configuration file");
        }

        try {
            configuration = json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpException("Error Converting the configuration file into an Object");
        }

    }

    public Configuration getCurrentConfiguration() {
        if (configuration == null) {
            throw new HttpException("This configuration file does now exist");
        }

        return configuration;
    }

}
