package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.Settings;
import org.junit.jupiter.api.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        String username = Settings.getUsername();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(username, Settings.getUsername());
        homePage.checkLoginUser(username);
    }
}
