package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(Paths.get("src/test/resources/config.properties").toAbsolutePath().toString());
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getBaseUrl() {
        return properties.getProperty("speed.base.url");
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public String getScreenshotDirectory() {
        return properties.getProperty("screenshot.directory");
    }

    public String getClientUsername() {
        return properties.getProperty("client.username");
    }

    public String getClientPassword() {
        return properties.getProperty("client.password");
    }

    public String getAdminUsername() {
        return properties.getProperty("admin.username");
    }

    public String getAdminPassword() {
        return properties.getProperty("admin.password");
    }

    public String sendEmailReport() {
        return properties.getProperty("send.email");
    }

    public String getOperatorUsername() {
        return properties.getProperty("interconnectionOperator.username");
    }

    public String getBasePasword() {
        return properties.getProperty("base.password");
    }
}
