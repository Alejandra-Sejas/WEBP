package com.upb.tests;

import com.upb.pages.CartPage;
import com.upb.pages.InventoryPage;
import com.upb.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Agregar multiples productos al carrito.
 * Verifica que al agregar 2 productos, el badge muestre 2 y que en el carrito
 * aparezcan exactamente esos productos con los nombres correctos.
 */
public class AgregarMultiplesProductosTest extends BaseTest {

    @Test
    @DisplayName("CP04 - Agregar 2 productos al carrito refleja el conteo correcto")
    public void testAgregarMultiplesProductos() {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginAs(USUARIO, PASSWORD);
        assertTrue(inventoryPage.isLoaded());

        // 2. Agregar dos productos
        inventoryPage.addProductToCart("sauce-labs-backpack");
        assertEquals("1", inventoryPage.getCartBadgeCount(),
                "El badge deberia mostrar 1 despues del primer producto");

        inventoryPage.addProductToCart("sauce-labs-bike-light");
        assertEquals("2", inventoryPage.getCartBadgeCount(),
                "El badge deberia mostrar 2 despues del segundo producto");

        // 3. Verificar que los botones cambiaron a "Remove"
        assertEquals("Remove", inventoryPage.getButtonText("sauce-labs-backpack"),
                "El boton del Backpack deberia decir Remove");
        assertEquals("Remove", inventoryPage.getButtonText("sauce-labs-bike-light"),
                "El boton del Bike Light deberia decir Remove");

        // 4. Ir al carrito y verificar el contenido
        CartPage cartPage = inventoryPage.goToCart();
        assertTrue(cartPage.isLoaded());
        assertEquals(2, cartPage.getItemsCount(),
                "El carrito deberia tener exactamente 2 items");

        // 5. Verificar los nombres de los productos
        List<String> nombres = cartPage.getItemNames();
        assertTrue(nombres.contains("Sauce Labs Backpack"),
                "El carrito deberia contener el Sauce Labs Backpack");
        assertTrue(nombres.contains("Sauce Labs Bike Light"),
                "El carrito deberia contener el Sauce Labs Bike Light");
    }
}
