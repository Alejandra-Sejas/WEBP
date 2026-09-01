package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class CartPage extends BasePage {

    private final By cartTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
        // Espera a que el titulo cargue
        waitForElement(cartTitle);
    }

    public boolean isLoaded() {
        return getText(cartTitle).equalsIgnoreCase("Your Cart");
    }

    public int getItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        List<WebElement> nameElements = driver.findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement el : nameElements) {
            names.add(el.getText());
        }
        return names;
    }

    /**
     * Hace click en el boton Checkout.
     */
    public CheckoutInfoPage clickCheckout() {
        click(checkoutButton);
        return new CheckoutInfoPage(driver);
    }

    /**
     * Hace click en el boton Continue Shopping.
     */
    public void clickContinueShopping() {
        click(continueShoppingButton);
    }
}
