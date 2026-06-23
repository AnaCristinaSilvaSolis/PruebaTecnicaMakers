package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverProvider;
import utils.FileManager;
import utils.Logs;

import java.time.Duration;

@Getter
@Setter
public class World {
    WebDriver driver;
    private final String mainUrl = "https://www.saucedemo.com/";


    @Before
    public void setUp() {
        Logs.debug("Inicializando el driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando la ventana");
        driver.manage().window().maximize();

        Logs.debug("Eliminando cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Guardando el driver");
        DriverProvider.setDriver(driver);

        Logs.debug("Agregando Implicit Wait");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @After
    public void tearDown(Scenario scenario) {
        Logs.debug("Tomando evidencias");
        tomarEvidencias(scenario);

        // Si el driver existe, cerrarlo correctamente para liberar recursos
        Logs.debug("Matando el driver");
        if (driver != null) {
            driver.quit();
        }
        DriverProvider.removerDriver();
    }

    @BeforeAll
    private static void beforeAll() {
        Logs.debug("Eliminando evidencias pasadas");
        FileManager.eliminarEvidenciasPasadas();
    }

    public void tomarEvidencias(Scenario scenario) {
        // Solo se toman evidencias cuando el escenario falla o es saltado.
        switch (scenario.getStatus()) {
            case FAILED, SKIPPED -> {
                // Validar si existe un driver activo para tomar evidencia
                if (DriverProvider.tomarEvidencia()) {
                    // Normaliza el nombre del escenario para usarlo como nombre de archivo
                    final var nombre = scenario.getName().toLowerCase().replace(" ", "_");

                    // Adjunta screenshot y page source en el reporte Allure
                    FileManager.tomarScreenshotAllure();
                    FileManager.obtenerPageSourceAllure();

                    // Adjunta screenshot directamente en Cucumber
                    byte[] screenshot = ((TakesScreenshot) DriverProvider.getDriver())
                            .getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot");

                    // Adjunta pageSource directamente en Cucumber
                    String pageSource = DriverProvider.getDriver().getPageSource();
                    if (pageSource != null) {
                        String formattedHtml = Jsoup.parse(pageSource).outerHtml();
                        scenario.attach(formattedHtml, "text/html", "Page Source");
                    }

                    // Guarda evidencia local en la carpeta de evidencias del proyecto
                    FileManager.tomarScreenshot(nombre);
                    FileManager.obtenerPageSource(nombre);
                }
            }
        }
    }


    @Given("Navega a la pagina de {string}")
    public void navegarAPagina(String path) {
        final var url = String.format("%s/%s", mainUrl, path);
        driver.get(url);
    }

    @Given("El usuario navega a la pagina de inicio de sesion")
    public void navegarALogin() {
        Logs.info("Navegando al index");
        driver.get(mainUrl);
    }


}
