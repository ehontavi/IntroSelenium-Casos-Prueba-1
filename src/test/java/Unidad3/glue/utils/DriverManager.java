package Unidad3.glue.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * DriverManager - Gestiona el ciclo de vida del WebDriver usando Singleton Pattern
 * Utiliza WebDriverManager de Boni García para la gestión automática de drivers
 */
public class DriverManager {

    private static WebDriver driver;
    private static final int IMPLICIT_WAIT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 30;

    // Constructor privado para evitar instanciación
    private DriverManager() {
    }

    /**
     * Obtiene la instancia única del WebDriver (Singleton)
     * Si no existe, inicializa Chrome por defecto
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver("chrome");
        }
        return driver;
    }

    /**
     * Inicializa el WebDriver según el navegador especificado
     * @param browser - nombre del navegador: chrome, firefox, edge
     */
    public static void initDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-infobars");
                    driver = new ChromeDriver(chromeOptions);
                    System.out.println("✅ Chrome Driver inicializado correctamente");
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    System.out.println("✅ Firefox Driver inicializado correctamente");
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    System.out.println("✅ Edge Driver inicializado correctamente");
                    break;

                default:
                    throw new IllegalArgumentException("❌ Navegador no soportado: " + browser);
            }

            // Configuraciones comunes para todos los navegadores
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
            driver.manage().deleteAllCookies();
        }
    }

    /**
     * Cierra el navegador y elimina la instancia del WebDriver
     */
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("✅ Navegador cerrado correctamente");
            } catch (Exception e) {
                System.out.println("⚠️ Error al cerrar el navegador: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}