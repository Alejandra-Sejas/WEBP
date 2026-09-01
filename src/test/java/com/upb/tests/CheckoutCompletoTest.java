package com.upb.tests;

import com.upb.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CP01 - Flujo completo de checkout con un producto.
 * Verifica el "happy path" de compra: agregar producto, ir al carrito,
 * llenar informacion, revisar y finalizar la compra.
 */
public class CheckoutCompletoTest extends BaseTest {

    @Test
    @DisplayName("CP01 - Flujo completo de checkout termina en Thank you for your order")
    public void testCheckoutCompleto() {
        // 1. Hacer login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginAs(USUARIO, PASSWORD);
        assertTrue(inventoryPage.isLoaded(), "La pagina de inventario deberia cargarse");

        // 2. Agregar un producto al carrito
        inventoryPage.addProductToCart("sauce-labs-backpack");
        assertEquals("1", inventoryPage.getCartBadgeCount(),
                "El badge deberia mostrar 1 despues de agregar un producto");

        // 3. Ir al carrito
        CartPage cartPage = inventoryPage.goToCart();
        assertTrue(cartPage.isLoaded(), "La pagina del carrito deberia cargarse");
        assertEquals(1, cartPage.getItemsCount(),
                "El carrito deberia contener 1 item");

        // 4. Iniciar checkout y llenar formulario
        CheckoutInfoPage infoPage = cartPage.clickCheckout();
        assertTrue(infoPage.isLoaded(), "La pagina de informacion deberia cargarse");

        CheckoutOverviewPage overviewPage = infoPage.fillFormAndContinue(
                "Alejandra", "Sejas", "12345");
        assertTrue(overviewPage.isLoaded(), "La pagina de overview deberia cargarse");

        // 5. Finalizar la compra
        CheckoutCompletePage completePage = overviewPage.clickFinish();
        assertTrue(completePage.isLoaded(), "La pagina final deberia cargarse");

        // 6. Verificar el mensaje de confirmacion
        assertEquals("Thank you for your order!", completePage.getHeaderMessage(),
                "El mensaje de confirmacion deberia aparecer");
    }
}
