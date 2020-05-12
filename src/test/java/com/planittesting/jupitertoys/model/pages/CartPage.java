package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.model.table.Table;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage extends BaseJupiterToysPage {

    private static final By CART_ITEMS_TABLE = By.className("cart-items");
    private static final By PRODUCT_QUANTITY_TEXTBOX = By.cssSelector("input[name=\"quantity\"]");
    private static final By TOTAL_PRICE_TEXT = By.className("total");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private boolean isProductPresent(Table cartTable, String productName) {
        return cartTable.isItemPresent("Item", productName);
    }

    private String getProductPrice(Table cartTable, String productName) {
        WebElement cell = cartTable.getCell("Item", productName, "Price");
        return cell.getText().trim();
    }

    private String getProductQuantity(Table cartTable, String productName) {
        WebElement cell = cartTable.getCell("Item", productName, "Quantity");
        return cell.findElement(PRODUCT_QUANTITY_TEXTBOX).getAttribute("value");
    }

    private void setProductQuantity(Table cartTable, String productName, String productQuantity) {
        WebElement cell = cartTable.getCell("Item", productName, "Quantity");
        WebElement productQuantityTextBox = cell.findElement(PRODUCT_QUANTITY_TEXTBOX);
        productQuantityTextBox.clear();
        productQuantityTextBox.sendKeys(productQuantity);
    }

    private String getProductSubtotal(Table cartTable, String productName) {
        WebElement cell = cartTable.getCell("Item", productName, "Subtotal");
        return cell.getText().trim().replaceAll("[^\\d.]", "");
    }

    private String getTotalPrice(Table cartTable) {
        return cartTable.getTableElement().findElement(TOTAL_PRICE_TEXT).getText().split(" ")[1];
    }

//    private boolean isProductPriceCorrect(Table cartTable, String productName, String expectedCurrency, String expectedPrice) {
//        Pattern pattern = Pattern.compile("(.)(\\d.*)");
//        WebElement cell = cartTable.getCell("Item", productName, "Price");
//        Matcher matcher = pattern.matcher(cell.getText().trim());
//        Assert.assertTrue(matcher.find());
//        boolean isPriceUnitCorrect = matcher.group(1).equals(expectedCurrency);
//        boolean isPriceCorrect = matcher.group(2).equals(expectedPrice);
//        return isPriceCorrect && isPriceCorrect;
//    }
//
//    private boolean isProductQuantityCorrect(Table cartTable, String productName, String expectedProductQuantity) {
//        WebElement cell = cartTable.getCell("Item", productName, "Quantity");
//        return cell.findElement(PRODUCT_QUANTITY_TEXTBOX).getAttribute("value").equals(expectedProductQuantity);
//    }
//
//    private boolean isSubtotalCorrect(Table cartTable, String productName, String expectedPriceUnit, String expectedSubtotal) {
//        Pattern pattern = Pattern.compile("(.)(\\d.*)");
//        WebElement cell = cartTable.getCell("Item", productName, "Subtotal");
//        Matcher matcher = pattern.matcher(cell.getText().trim());
//        matcher.find();
//        boolean isPriceUnitCorrect = matcher.group(1).equals(expectedPriceUnit);
//        boolean isPriceCorrect = matcher.group(2).equals(expectedSubtotal);
//        return isPriceCorrect && isPriceCorrect;
//    }

//    private boolean isTotalPriceCorrect(Table cartTable, String expectedTotalPrice) {
//        String totalPrice = cartTable.getTableElement().findElement(TOTAL_PRICE_TEXT).getText().split(" ")[1];
//        return totalPrice.equals(expectedTotalPrice);
//    }


    public void checkCart(CartDetails cartDetails) {
        Table cartTable = new Table(driver.findElement(CART_ITEMS_TABLE));
        assertEquals(cartDetails.getBoughtProducts().size(), cartTable.getTableRows().size(), "the number of items in cart does not match the number of items bought");
        //use soft assert
        SoftAssertions softAssert = new SoftAssertions();

        for(ItemDetails itemDetails : cartDetails.getBoughtProducts()) {
            String productName = itemDetails.getName();
            softAssert.assertThat(getProductPrice(cartTable, productName).equals(itemDetails.getPrice())).as("Product " + productName + " price matched").isTrue();
            softAssert.assertThat(getProductQuantity(cartTable,productName).equals(itemDetails.getQuantity())).as("Product " + productName + " quantity matched").isTrue();
            softAssert.assertThat(getProductSubtotal(cartTable,productName).equals(itemDetails.getSubtotal())).as("Product " + productName + " subtotal does not match").isTrue();
            softAssert.assertThat(getTotalPrice(cartTable).equals(cartDetails.getTotalPrice())).as("Total price does not match").isTrue();
            softAssert.assertAll();
        }
    }

    public CheckoutPage checkout() {
        clickButton("Check Out");
        return new CheckoutPage(driver);
    }

    public void updateQuantity(CartDetails cartDetails) {
        Table cartTable = new Table(driver.findElement(CART_ITEMS_TABLE));
        for(ItemDetails itemDetails : cartDetails.getBoughtProducts()) {
            String productName = itemDetails.getName();
            String productQuantity = itemDetails.getQuantity();
            if (!getProductQuantity(cartTable,productName).equals(productQuantity)) {
                setProductQuantity(cartTable, productName, productQuantity);
            }
        }
    }
}
