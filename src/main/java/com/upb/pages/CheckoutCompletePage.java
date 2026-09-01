package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object de la pagina final del checkout (Thank you for your order).
 */
public class CheckoutCompletePage extends BasePage {

    // Selectores
    private final By pageTitle = By.className("title");
    private final By completeHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        waitForElement(pageTitle);
    }

    public boolean isLoaded() {
        return getText(pageTitle).equalsIgnoreCase("Checkout: Complete!");
    }

    public String getHeaderMessage() {
        return getText(completeHeader);
    }
}
