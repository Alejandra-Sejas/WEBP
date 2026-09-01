package com.upb.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase base para todos los tests.
 * Se encarga de inicializar y cerrar el WebDriver en cada test.
 */
public class BaseTest {

    protected WebDriver driver;

    protected static final String BASE_URL = "https://www.saucedemo.com/";
    protected static final String USUARIO = "standard_user";
    protected static final String PASSWORD = "secret_sauce";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Opciones generales para evitar popups molestos
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        // Deshabilita las funcionalidades de contraseñas de Chrome
        // (esto quita el popup de "contraseña comprometida" que aparece con secret_sauce)
        options.addArguments("--disable-features=PasswordCheck,PasswordLeakDetection,AutofillServerCommunication");
        options.addArguments("--disable-save-password-bubble");

        // Configuracion via preferencias del perfil
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("autofill.profile_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Deshabilita la barra amarilla "Chrome esta siendo controlado por software automatizado"
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}