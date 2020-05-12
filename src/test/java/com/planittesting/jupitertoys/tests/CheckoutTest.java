package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.model.data.PaymentDetails;
import com.planittesting.jupitertoys.model.pages.*;
import com.planittesting.jupitertoys.model.popup.ProcessingPopup;
import com.planittesting.jupitertoys.support.ExtentManager;
import com.planittesting.jupitertoys.support.Settings;
import com.planittesting.jupitertoys.model.popup.LoginPopup;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest extends BaseTest {

    private DeliveryDetails deliveryDetails = new DeliveryDetails();
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Test
    @Tag("checkout")
    public void checkoutTestJunit() {
        ExtentManager.logStep("Login to Jupitor Toys");
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(Settings.getUsername(), Settings.getPassword());

        ExtentManager.logStep("Buy products in shop page");
        ShopPage shopPage = homePage.navigateToShopPage().waitUntilImagesDisplayed();
        List<ItemDetails> items = new ArrayList<>();
        items.add(new ItemDetails().setName("teddy bear").setQuantity("2").setPrice("$12.99"));
        items.add(new ItemDetails().setName("stuffed frog").setQuantity("1").setPrice("$10.99"));
        CartDetails cartDetails = new CartDetails().setBoughtProducts(items);
        shopPage.buyProduct(cartDetails);
        shopPage.checkCartCount(cartDetails);
        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(cartDetails);

        cartDetails.getItemByProductName("teddy bear").setQuantity("3");
        cartPage.updateQuantity(cartDetails);
        cartPage.checkCart(cartDetails);

        ExtentManager.logStep("Proceed to checkout page");
        CheckoutPage checkoutPage = cartPage.checkout();

        ExtentManager.logStep("Fill in information and checkout");
        checkoutPage.fillInForm(deliveryDetails, paymentDetails);
        checkoutPage.clickSubmitButton();

        new ProcessingPopup(driver).waitForProcessing();

        ExtentManager.logStep("Verify order submitted message");
        new ConfirmationPage(driver).checkOrderSubmittedMessage(deliveryDetails);
        assertTrue(true, "Test passed");
    }
}

