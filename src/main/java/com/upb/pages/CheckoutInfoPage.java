package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckoutInfoPage extends BasePage {

    // Selectores
    private final By pageTitle = By.className("title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
        waitForElement(pageTitle);
    }

    public boolean isLoaded() {
        return getText(pageTitle).equalsIgnoreCase("Checkout: Your Information");
    }

    public CheckoutOverviewPage fillFormAndContinue(String firstName, String lastName, String postalCode) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public boolean hasErrorMessage() {
        return isElementPresent(errorMessage);
    }
}
