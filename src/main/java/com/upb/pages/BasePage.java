package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Clase base para todas las Page Objects.
 * Proporciona metodos comunes para esperar elementos y hacer acciones seguras.
 */
public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Esperamos hasta 15 segundos por cualquier elemento
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Espera a que un elemento sea clickeable y hace click.
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Espera a que un elemento sea visible y escribe texto.
     */
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Devuelve el texto de un elemento, esperando que sea visible.
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Verifica si un elemento esta presente en el DOM (sin esperar demasiado).
     */
    protected boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    /**
     * Espera a que un elemento este presente en el DOM.
     */
    protected void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
