package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object del formulario de informacion del checkout.
 */
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

    /**
     * Llena el formulario y hace click en Continue.
     */
    public CheckoutOverviewPage fillFormAndContinue(String firstName, String lastName, String postalCode) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    /**
     * Verifica si hay un mensaje de error visible.
     */
    public boolean hasErrorMessage() {
        return isElementPresent(errorMessage);
    }
}
