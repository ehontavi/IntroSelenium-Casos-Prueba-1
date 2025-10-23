package Unidad3.hooks;

import Unidad3.glue.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Hooks - Contiene métodos que se ejecutan antes y después de cada escenario
 * Gestiona la inicialización del driver y captura de screenshots
 */
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n========================================");
        System.out.println("🚀 INICIANDO ESCENARIO");
        System.out.println("========================================");
        System.out.println("📝 Nombre: " + scenario.getName());
        System.out.println("🏷️  Tags: " + scenario.getSourceTagNames());
        System.out.println("========================================\n");

        // Inicializar el driver (Chrome por defecto)
        // Puedes cambiar a "firefox" o "edge" si lo necesitas
        DriverManager.initDriver("chrome");
    }

    @After
    public void tearDown(Scenario scenario) {

        System.out.println("\n========================================");
        System.out.println("🏁 FINALIZANDO ESCENARIO");
        System.out.println("========================================");

        // Obtener el driver desde el DriverManager
        WebDriver driver = DriverManager.getDriver();

        // Capturar screenshot si el driver está disponible
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                if (scenario.isFailed()) {
                    // Screenshot para escenarios fallidos
                    scenario.attach(screenshot, "image/png", "❌ ERROR - " + scenario.getName());
                    System.out.println("📸 Screenshot de ERROR capturado");
                    System.out.println("❌ Estado: FAILED");
                } else {
                    // Screenshot para escenarios exitosos
                    scenario.attach(screenshot, "image/png", "✅ PASSED - " + scenario.getName());
                    System.out.println("📸 Screenshot de ÉXITO capturado");
                    System.out.println("✅ Estado: PASSED");
                }
            } catch (Exception e) {
                System.out.println("⚠️ No se pudo capturar screenshot: " + e.getMessage());
            }
        }

        // Cerrar el navegador
        DriverManager.quitDriver();

        System.out.println("========================================\n");
    }

    /**
     * Hook que se ejecuta solo para escenarios con el tag @smoke
     */
    @Before("@Smoke")
    public void setUpSmoke() {
        System.out.println("🔥 Ejecutando test de SMOKE");
    }

    /**
     * Hook que se ejecuta solo para escenarios con el tag @regression
     */
    @Before("@regression")
    public void setUpRegression() {
        System.out.println("🔄 Ejecutando test de REGRESSION");
    }
}