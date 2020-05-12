package com.planittesting.jupitertoys.model.popup;

import com.planittesting.jupitertoys.model.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasePopup extends BasePage {

    private static final By POPUP = By.className("popup");
    public final WebElement popupElement;

    public BasePopup(WebDriver driver) {
        super(driver);
        this.popupElement = driver.findElement(POPUP);
    }

    public void checkPopupTitle(String title) {
        //Assert.assertTrue(popupElement.findElement(By.tagName("h1")).getText().trim().equalsIgnoreCase(title), "title does not match");
        assertEquals(popupElement.findElement(By.tagName("h1")).getText().trim(), title, "title does not match");
    }

    public void clickButton(String buttonName) {
        List<WebElement> buttons = popupElement.findElements(By.className("btn"));
        for (WebElement button : buttons) {
            if (button.getText().trim().equalsIgnoreCase(buttonName)) {
                button.click();
                return;
            }
        }
        throw new IllegalArgumentException("Button " + buttonName + " does not exist");
    }

    public static By getPopupLocator() {return POPUP; }
}
