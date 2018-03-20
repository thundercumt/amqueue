package org.amqueue.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static final String CONFIG_FILE = "amqueue.properties";
    private static final ConfigReader instance;

    static {
        ConfigReader t = null;
        try {
            t = new ConfigReader();
        } catch (IOException e) {
            t = null;
            new RuntimeException(e);
        } finally {
            instance = t;
        }
    }

    private Properties props = new Properties();

    private ConfigReader() throws IOException {
        InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        if (in == null) {
            throw new NullPointerException();
        }
        props.load(in);;
    }

    public String getPropertyAsString(String name) {
        return props.getProperty(name);
    }

    public int getPropertyAsInt(String name) {
        return Integer.parseInt(props.getProperty(name));
    }
    
    public static ConfigReader getInstance() {
        return instance;
    }
}
