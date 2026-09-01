package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object de la pagina de login de SauceDemo.
 */
public class LoginPage extends BasePage {

    // Selectores de la pagina
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Metodo de conveniencia: hace login completo y devuelve la siguiente pagina.
     */
    public InventoryPage loginAs(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return new InventoryPage(driver);
    }
}
