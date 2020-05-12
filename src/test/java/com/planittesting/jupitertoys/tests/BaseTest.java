package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.Browser;
import com.planittesting.jupitertoys.support.ExtentManager;
import com.planittesting.jupitertoys.support.Settings;
import com.planittesting.jupitertoys.support.jira.JiraApiServices;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @RegisterExtension
    AfterTestExtension afterTest = new AfterTestExtension();


    @BeforeAll
    public static void globalSetupJunit() {
        Settings.readSettings();
        ExtentManager.initializeReporting();
    }
    @BeforeEach
    public void setupJunit(TestInfo testInfo) {
        driver = Browser.launchBrowser();
        ExtentManager.startTest(testInfo.getDisplayName());
        afterTest.setDriver(driver);
    }

    @AfterEach
    public void teardownJunit(TestInfo testInfo) {
        String testName = testInfo.getDisplayName();
//        if(!.isSuccess()) {
//            System.out.println("*** Test execution " + testName + " failed...");
//            try {
//                ExtentManager.takeScreenshot(driver, testName);
//                ExtentManager.logException(result.getThrowable());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        driver.quit();
        //result.setAttribute("requirement", "XSI-27");
//        result.setAttribute("test", "XSI-31");
//        //update on jira
//        try {
//            JiraApiServices jiraApiServices = new JiraApiServices();
//            jiraApiServices.getTestCaseDetails("JT-2");
//            jiraApiServices.createTestCase("JT", result.getMethod().getMethodName(), "it's a new test case");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @AfterAll
    public static void globalTeardownJunit()
    {
        ExtentManager.getExtentReports().flush();
    }
}
