package com.planittesting.jupitertoys.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static WebDriver driver;

    public static WebDriver getBrowserInstance() { return driver; }

    public static WebDriver launchBrowser() {
        switch (Settings.getBrowser()) {
            case "chrome" :
                ChromeOptions options = new ChromeOptions();

                if (Settings.isHeadless()) {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }

                if (Settings.isRemote_execution()) {
                    try {
                        driver = new RemoteWebDriver(new URL(Settings.getSelenium_grid_url()), options);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    driver = new ChromeDriver(options);
                }
                break;

            case "firefox" :
                driver = new FirefoxDriver();
                break;

            default:
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.navigate().to(Settings.getUrl());
        driver.manage().timeouts().implicitlyWait(Settings.getImplicitly_wait(), TimeUnit.SECONDS);
        return driver;
    }
}
