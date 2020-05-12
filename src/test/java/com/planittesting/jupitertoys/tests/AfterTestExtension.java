package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.ExtentManager;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AfterTestExtension implements AfterTestExecutionCallback {

    private WebDriver driver;

    public void afterTestExecution(ExtensionContext extensionContext) {
        String methodName = extensionContext.getDisplayName();
        if (extensionContext.getExecutionException().isPresent()) {
            ExtentManager.logException(extensionContext.getExecutionException().get());
            try {
                ExtentManager.takeScreenshot(driver, methodName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDriver(WebDriver driver)
    {
        this.driver = driver;
    }
}

