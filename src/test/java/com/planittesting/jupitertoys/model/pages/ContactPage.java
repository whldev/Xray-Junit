package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.ContactDetails;
import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import com.planittesting.jupitertoys.model.data.PaymentDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BaseJupiterToysPage{

    private static final By FORENAME_INPUT = By.id("forename");
    private static final By SURNAME_INPUT = By.id("surname");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By TELEPHONE_INPUT = By.id("telephone");
    private static final By MESSAGE_INPUT = By.id("message");

    public ContactPage(WebDriver driver) { super(driver); }


    public void enterForename(String forename) {
        driver.findElement(FORENAME_INPUT).sendKeys(forename);
    }

    public void enterSurname (String surname) {
        driver.findElement(SURNAME_INPUT).sendKeys(surname);
    }

    public void enterEmail (String email) { driver.findElement(EMAIL_INPUT).sendKeys(email); }

    public void enterTelephone (String telephone) { driver.findElement(TELEPHONE_INPUT).sendKeys(telephone); }

    public void enterMessage (String message) { driver.findElement(MESSAGE_INPUT).sendKeys(message); }


    public ConfirmationPage clickSubmitButton() {
        clickButton("Submit");
        return new ConfirmationPage(driver);
    }

    public void fillInForm(ContactDetails contactDetails) {
        enterForename(contactDetails.getForename());
        enterSurname(contactDetails.getSurname());
        enterEmail(contactDetails.getEmail());
        enterTelephone(contactDetails.getTelephone());
        enterMessage(contactDetails.getMessage());
    }
}
