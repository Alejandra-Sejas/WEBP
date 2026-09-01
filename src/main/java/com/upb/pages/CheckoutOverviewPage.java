package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckoutOverviewPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By finishButton = By.id("finish");
    private final By totalLabel = By.className("summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        waitForElement(pageTitle);
    }

    public boolean isLoaded() {
        return getText(pageTitle).equalsIgnoreCase("Checkout: Overview");
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
