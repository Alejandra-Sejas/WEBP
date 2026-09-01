package com.upb.tests;

import com.upb.pages.InventoryPage;
import com.upb.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verificacion del bug: Reset App State no restablece los botones.
 *
 * bug del primer parcial L (Severidad 3):
 * "El sidebar Reset App State no funciona correctamente".
 * Cuando se agregan productos al carrito y luego se ejecuta "Reset App State",
 * el badge del carrito SI se limpia (va a 0), pero los botones de los productos
 * agregados siguen mostrando "Remove" en lugar de volver a "Add to cart".
 * Este test es de regresion: confirma que el bug se sigue reproduciendo.
 */
public class ResetAppStateBugTest extends BaseTest {

    @Test
    @DisplayName("CP05 - BUG: Reset App State limpia el badge pero deja los botones en Remove")
    public void testResetAppStateNoRestableceBotones() {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginAs(USUARIO, PASSWORD);
        assertTrue(inventoryPage.isLoaded());

        // 2. Agregar un producto al carrito
        inventoryPage.addProductToCart("sauce-labs-backpack");
        assertEquals("1", inventoryPage.getCartBadgeCount(),
                "El badge deberia mostrar 1 despues de agregar el producto");
        assertEquals("Remove", inventoryPage.getButtonText("sauce-labs-backpack"),
                "El boton deberia estar en Remove");

        // 3. Ejecutar Reset App State desde el menu lateral
        inventoryPage.resetAppState();

        // 4. Comportamiento CORRECTO: el badge se limpio
        assertEquals("0", inventoryPage.getCartBadgeCount(),
                "El badge del carrito deberia estar en 0 despues del reset");

        // 5. BUG CONFIRMADO: el boton NO regreso a "Add to cart" Lo esperado seria que dijera "Add to cart", pero sigue en "Remove".
        String textoBoton = inventoryPage.getButtonText("sauce-labs-backpack");
        assertEquals("Remove", textoBoton,
                "BUG CONFIRMADO: el boton sigue en Remove despues del reset (deberia estar en Add to cart)");
    }
}
