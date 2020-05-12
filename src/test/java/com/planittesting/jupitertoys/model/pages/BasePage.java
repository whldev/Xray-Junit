package com.planittesting.jupitertoys.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public void click(By elemLocator) {
        //waitUntilDisplayed(elemLocator).click();
        driver.findElement(elemLocator).click();
    }



    public WebElement waitUntilDisplayed(By elemLocator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elemLocator));
    }

}