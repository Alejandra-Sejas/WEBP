package com.upb.tests;

import com.upb.pages.InventoryPage;
import com.upb.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CP03 - Ordenamiento de productos por precio ascendente (low to high).
 * Verifica que al aplicar el filtro, los productos aparecen ordenados
 * de menor a mayor precio.
 */
public class OrdenamientoPrecioTest extends BaseTest {

    @Test
    @DisplayName("CP03 - Ordenar productos de menor a mayor precio funciona correctamente")
    public void testOrdenamientoPorPrecioAscendente() {
        // 1. Login
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.loginAs(USUARIO, PASSWORD);
        assertTrue(inventoryPage.isLoaded());

        // 2. Aplicar filtro de ordenamiento por precio ascendente
        inventoryPage.sortProductsBy("lohi");

        // 3. Obtener la lista de precios en el orden actual
        List<Double> precios = inventoryPage.getProductPrices();

        // 4. Verificar que la lista tiene productos
        assertTrue(precios.size() > 0, "Deberia haber al menos un producto");

        // 5. Verificar que cada precio es menor o igual al siguiente
        for (int i = 0; i < precios.size() - 1; i++) {
            double actual = precios.get(i);
            double siguiente = precios.get(i + 1);
            assertTrue(actual <= siguiente,
                    "El precio $" + actual + " deberia ser menor o igual a $" + siguiente);
        }

        // 6. Validacion adicional: el primer producto debe ser el mas barato ($7.99)
        assertEquals(7.99, precios.get(0), 0.001,
                "El primer producto deberia ser el mas barato ($7.99)");
    }
}
