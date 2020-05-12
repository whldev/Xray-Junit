package com.planittesting.jupitertoys.support;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static String url;
    private static String browser;
    private static Integer implicitly_wait;
    private static boolean remote_execution;
    private static String selenium_grid_url;
    private static boolean headless;
    private static String username;
    private static String password;
    private static String api_endpoint;

    private static final String propertyFilePath = "config.properties";

    public static void readSettings() {
        Properties properties = new Properties();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = properties.getProperty("url", "http://jupiter.cloud.planittesting.com");
        browser = properties.getProperty("browser", "chrome");
        implicitly_wait = Integer.parseInt(properties.getProperty("implicitlyWait", "20"));
        remote_execution = Boolean.parseBoolean(properties.getProperty("remote_execution", "false"));
        headless = Boolean.parseBoolean(properties.getProperty("headless", "false"));
        username = properties.getProperty("username", "asd");
        password = properties.getProperty("password", "letmein");
        api_endpoint = properties.getProperty("api_endpoint");

        if (remote_execution) {
            selenium_grid_url = properties.getProperty("selenium_grid_url");
        }
    }

    public static String getUrl() { return url; }

    public static String getBrowser() { return browser; }

    public static Integer getImplicitly_wait() { return implicitly_wait; }

    public static boolean isRemote_execution() { return remote_execution; }

    public static String getSelenium_grid_url() { return selenium_grid_url; }

    public static boolean isHeadless() { return headless; }

    public static String getUsername() { return username; }

    public static String getPassword() { return password; }

    public static String getApi_endpoint() { return api_endpoint; }
}
