package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.popup.LogoutPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseJupiterToysPage extends BasePage {

    private static final By HOME_BUTTON = By.id("nav-home");
    private static final By SHOP_BUTTON = By.id("nav-shop");
    private static final By CONTACT_BUTTON = By.id("nav-contact");
    private static final By LOGIN_BUTTON = By.id("nav-login");
    private static final By LOGOUT_BUTTON = By.id("nav-logout");
    private static final By CART_BUTTON = By.id("nav-cart");
    private static final By CART_COUNT = By.className("cart-count");
    private static final By USER_BUTTON = By.id("nav-user");

    public BaseJupiterToysPage(WebDriver driver) {
        super(driver);
    }

    public HomePage navigateToHomePage() {
        click(HOME_BUTTON);
        return new HomePage(driver);
    }

    public ShopPage navigateToShopPage() {
        click(SHOP_BUTTON);
        return new ShopPage(driver);
    }

    public ContactPage navigateToContactPage() {
        click(CONTACT_BUTTON);
        return new ContactPage(driver);
    }

    public LoginPopup navigateToLoginPage() {
        click(LOGIN_BUTTON);
        return new LoginPopup(driver);
    }

    public CartPage navigateToCartPage() {
        click(CART_BUTTON);
        return new CartPage(driver);
    }

    public void checkCartCount(CartDetails cartDetails) {
        assertEquals(driver.findElement(CART_BUTTON).findElement(CART_COUNT).getText(), cartDetails.getCartCount(), "Cart count does not match");
    }

    public void checkLoginUser(String expectedUserName) {
        assertEquals(driver.findElement(USER_BUTTON).getText().trim(), expectedUserName, "Login user name does not match");
    }

    public void clickButton(String buttonName) {
        List<WebElement> buttons = driver.findElements(By.className("btn"));
        for (WebElement button : buttons) {
            if (button.getText().trim().equalsIgnoreCase(buttonName)) {
                button.click();
                return;
            }
        }
        throw new IllegalArgumentException("Button " + buttonName + " does not exist");
    }

    public LogoutPopup clickLogout() {
        click(LOGOUT_BUTTON);
        return new LogoutPopup(driver);
    }

}
