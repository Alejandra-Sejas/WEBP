# WEBSP - Pruebas Automatizadas SauceDemo

Proyecto de pruebas automatizadas para la aplicacion web [SauceDemo](https://www.saucedemo.com/) usando **Selenium WebDriver + JUnit 5** con patron **Page Object Model**.

**Materia:** Certificacion II - Segundo Parcial
**Autor:** Alejandra Sejas Mendoza

## Requisitos

- JDK 21
- Maven 3.9+
- Google Chrome instalado

## Estructura del proyecto

```
WEBSP/
├── pom.xml
└── src/
    ├── main/java/com/upb/pages/     (Page Objects)
    │   ├── BasePage.java             (clase base con metodos comunes)
    │   ├── LoginPage.java
    │   ├── InventoryPage.java
    │   ├── CartPage.java
    │   ├── CheckoutInfoPage.java
    │   ├── CheckoutOverviewPage.java
    │   └── CheckoutCompletePage.java
    └── test/java/com/upb/tests/     (Tests JUnit 5)
        ├── BaseTest.java             (setup/teardown del WebDriver)
        ├── CheckoutCompletoTest.java              (CP01)
        ├── CheckoutCarritoVacioBugTest.java       (CP02 - Bug)
        ├── OrdenamientoPrecioTest.java            (CP03)
        ├── AgregarMultiplesProductosTest.java     (CP04)
        └── ResetAppStateBugTest.java              (CP05 - Bug)
```

## Escenarios automatizados

| Codigo | Escenario | Tipo |
|--------|-----------|------|
| CP01 | Flujo completo de checkout con un producto | Funcional |
| CP02 | Bug: se permite checkout con carrito vacio | Bug conocido |
| CP03 | Ordenamiento de productos por precio ascendente | Funcional |
| CP04 | Agregar multiples productos al carrito | Funcional |
| CP05 | Bug: Reset App State no restablece los botones | Bug conocido |

## Conceptos aplicados

- **Selectors:** By.id, By.className, By.cssSelector
- **Assertions:** assertEquals, assertTrue
- **Page Object Model:** BasePage + 6 Page Objects (una por cada pagina)
- **JUnit 5:** @Test, @BeforeEach, @AfterEach, @DisplayName
- **Waits explicitos:** WebDriverWait + ExpectedConditions

## Como correr

Desde IntelliJ:
- Click derecho sobre la carpeta `tests` -> Run All Tests

Desde terminal:
```
mvn test
```
