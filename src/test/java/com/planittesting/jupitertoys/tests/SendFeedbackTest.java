package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.ContactDetails;
import com.planittesting.jupitertoys.model.pages.ConfirmationPage;
import com.planittesting.jupitertoys.model.pages.ContactPage;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.popup.ProcessingPopup;
import com.planittesting.jupitertoys.support.CsvDataProvider;
import com.planittesting.jupitertoys.support.Settings;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendFeedbackTest extends BaseTest {

    private static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData.csv";

    public static Iterator<ContactDetails> getContactDetailsFromDataSource() throws Exception {
        return CsvDataProvider.readCsv(filePath).iterator();
    }

    @ParameterizedTest(name="Submit Feedback: {0}")
    @MethodSource("getContactDetailsFromDataSource")
    @Tag("contact")
    public void sendFeedbackTestJunit(ContactDetails contactDetails) {
        //can parse csv file to a class object (ContactDetails) try apache jackson
        //create a csv parser using jackson
        if (contactDetails.getForename().equalsIgnoreCase("hong")) {
            throw new RuntimeException("failed on purpose");
        }
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(Settings.getUsername(), Settings.getPassword());
        ContactPage contactPage = homePage.navigateToContactPage();
        
        contactPage.fillInForm(contactDetails);
        contactPage.clickSubmitButton();

        new ProcessingPopup(driver).waitForProcessing();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.checkFeedbackSubmittedMessage(contactDetails);
        confirmationPage.clickLogout().clickLogout();

        assertTrue(true, "Test passed");
    }
}
