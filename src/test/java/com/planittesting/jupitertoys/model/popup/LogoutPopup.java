package com.planittesting.jupitertoys.model.popup;

import com.planittesting.jupitertoys.model.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class LogoutPopup extends BasePopup {

    public LogoutPopup(WebDriver driver) {
        super(driver);
    }

    public HomePage clickLogout() {
        clickButton("Logout");
        return new HomePage(driver);
    }
}
