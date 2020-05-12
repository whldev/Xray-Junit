package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import com.planittesting.jupitertoys.model.data.PaymentDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BaseJupiterToysPage{

    private static final By FORENAME_INPUT = By.id("forename");
    private static final By SURNAME_INPUT = By.id("surname");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By TELEPHONE_INPUT = By.id("telephone");
    private static final By ADDRESS_TEXTAREA = By.id("address");
    private static final By CARD_TYPE_SELECT = By.id("cardType");
    private static final By CARD_NUMBER_INPUT = By.id("card");

    public CheckoutPage(WebDriver driver) { super(driver); }


    public void enterForename(String forename) {
        driver.findElement(FORENAME_INPUT).sendKeys(forename);
    }

    public void enterSurname (String surname) {
        driver.findElement(SURNAME_INPUT).sendKeys(surname);
    }

    public void enterEmail (String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void enterTelephone (String telephone) {
        driver.findElement(TELEPHONE_INPUT).sendKeys(telephone);
    }

    public void enterAddress (String address) {
        driver.findElement(ADDRESS_TEXTAREA).sendKeys(address);
    }

    public void selectCardType (String cardType) {
        Select cardTypeSelect = new Select(driver.findElement(CARD_TYPE_SELECT));
        if (cardType.equalsIgnoreCase("Visa")) {
            cardTypeSelect.selectByValue("Visa");
        }
        else if (cardType.equalsIgnoreCase("Mastercard")) {
            cardTypeSelect.selectByValue("Mastercard");
        }
        else {
            throw new IllegalArgumentException("Card type does not exist");
        }
    }

    public void enterCardNumber (String cardNumber) {
        driver.findElement(CARD_NUMBER_INPUT).sendKeys(cardNumber);
    }

    public ConfirmationPage clickSubmitButton() {
        clickButton("Submit");
        return new ConfirmationPage(driver);
    }

    public void fillInForm(DeliveryDetails deliveryDetails, PaymentDetails paymentDetails) {
        enterForename(deliveryDetails.getForename());
        enterSurname(deliveryDetails.getSurname());
        enterEmail(deliveryDetails.getEmail());
        enterAddress(deliveryDetails.getAddress());
        selectCardType(paymentDetails.getCardType());
        enterCardNumber(paymentDetails.getCardNumber());
    }





}
