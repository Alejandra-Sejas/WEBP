package com.upb.tests;

import com.upb.pages.CartPage;
import com.upb.pages.CheckoutInfoPage;
import com.upb.pages.InventoryPage;
import com.upb.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CP02 - Verificacion del bug: se permite hacer checkout con el carrito vacio.
 *
 * BUG REPORTADO EN EL PRIMER PARCIAL (Severidad 3):
 * "Se puede realizar el Checkout con el carrito vacio".
 *
 * El sistema deberia bloquear el checkout si no hay productos en el carrito
 * y mostrar un mensaje de error, pero permite avanzar sin problema.
 *
 * Este test es un test de regresion: verifica que el bug sigue reproduciendose.
 * Si en el futuro arreglan el bug, este test fallara y sabremos que debe actualizarse.
 */
public class CheckoutCarritoVacioBugTest extends BaseTest {

    @Test
    @DisplayName("CP02 - BUG: el sistema permite hacer checkout con el carrito vacio")
    public void testCheckoutConCarritoVacio() {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginAs(USUARIO, PASSWORD);

        // 2. Verificar que el carrito esta vacio
        assertEquals("0", inventoryPage.getCartBadgeCount(),
                "El carrito deberia estar vacio al inicio");

        // 3. Ir directamente al carrito sin agregar productos
        CartPage cartPage = inventoryPage.goToCart();
        assertTrue(cartPage.isLoaded(), "La pagina del carrito deberia cargarse");
        assertEquals(0, cartPage.getItemsCount(),
                "El carrito deberia tener 0 items");

        // 4. Hacer click en Checkout con el carrito vacio (aqui deberia bloquearse)
        CheckoutInfoPage infoPage = cartPage.clickCheckout();

        // 5. Confirmar el BUG: el sistema PERMITIO avanzar al formulario
        //    El comportamiento correcto seria mostrar un mensaje de error.
        assertTrue(infoPage.isLoaded(),
                "BUG CONFIRMADO: el sistema avanzo al checkout con carrito vacio");
    }
}
