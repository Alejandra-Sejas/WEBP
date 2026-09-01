package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckoutCompletePage extends BasePage {

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
