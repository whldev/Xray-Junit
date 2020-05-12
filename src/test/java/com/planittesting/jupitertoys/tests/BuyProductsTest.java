package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.support.Settings;

import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.pages.ShopPage;
import org.junit.jupiter.api.Test;

public class BuyProductsTest extends BaseTest {

    private CartDetails cartDetails = new CartDetails();

    @Test
    public void buyProductsTest() {
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(Settings.getUsername(), Settings.getPassword());

        ShopPage shopPage = homePage.navigateToShopPage().waitUntilImagesDisplayed();
        shopPage.checkCartCount(cartDetails);
    }
}
