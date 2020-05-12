package com.planittesting.jupitertoys.model.popup;

import com.planittesting.jupitertoys.model.popup.BasePopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPopup extends BasePopup {

    private static final By USER_NAME_TEXTBOX = By.id("loginUserName"); //by id
    private static final By PASSWORD_TEXTBOX = By.id("loginPassword");

    public LoginPopup(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        popupElement.findElement(USER_NAME_TEXTBOX).sendKeys(username);
        popupElement.findElement(PASSWORD_TEXTBOX).sendKeys(password);
        clickButton("Login");
        //assert in test case
        //user assert.equal
    }

}
