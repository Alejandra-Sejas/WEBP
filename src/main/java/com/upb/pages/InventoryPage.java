package com.upb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object de la pagina de inventario (productos) de SauceDemo.
 */
public class InventoryPage extends BasePage {

    // Selectores
    private final By inventoryContainer = By.id("inventory_container");
    private final By productPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By resetAppStateLink = By.id("reset_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
        // Al construirse, verifica que la pagina cargo
        waitForElement(inventoryContainer);
    }

    /**
     * Verifica que la pagina de inventario esta cargada.
     */
    public boolean isLoaded() {
        return isElementPresent(inventoryContainer);
    }

    /**
     * Agrega un producto al carrito por su id.
     * Ejemplo: "sauce-labs-backpack"
     * Espera a que el boton cambie a "Remove" para confirmar que se agrego.
     */
    public void addProductToCart(String productId) {
        By addButton = By.id("add-to-cart-" + productId);
        By removeButton = By.id("remove-" + productId);
        click(addButton);
        // Confirma que el click funciono esperando a que aparezca el boton Remove
        wait.until(ExpectedConditions.presenceOfElementLocated(removeButton));
    }

    /**
     * Devuelve el texto del boton (Add to cart o Remove) de un producto.
     */
    public String getButtonText(String productId) {
        if (isElementPresent(By.id("remove-" + productId))) {
            return getText(By.id("remove-" + productId));
        }
        return getText(By.id("add-to-cart-" + productId));
    }

    /**
     * Devuelve el numero del badge del carrito o "0" si no existe.
     */
    public String getCartBadgeCount() {
        if (isElementPresent(cartBadge)) {
            return getText(cartBadge);
        }
        return "0";
    }

    /**
     * Ordena los productos usando el dropdown.
     * Valores validos: "az", "za", "lohi", "hilo".
     */
    public void sortProductsBy(String value) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown));
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    /**
     * Devuelve una lista con los precios de los productos en el orden que aparecen.
     */
    public List<Double> getProductPrices() {
        waitForElement(productPrices);
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement el : priceElements) {
            String priceText = el.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    /**
     * Hace click en el ícono del carrito y devuelve la CartPage.
     */
    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    /**
     * Abre el menu lateral y ejecuta "Reset App State".
     */
    public void resetAppState() {
        click(menuButton);
        click(resetAppStateLink);
    }
}
