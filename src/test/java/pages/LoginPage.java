package pages;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;
import utils.Logs;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage extends BasePage {

    //Localizadores
    private final By logoApp = By.className("app_logo");
    private final By inputUsername = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By btnLogin = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando la página de Login");
        Assertions.assertAll(
                () -> assertTrue(find(inputUsername).isDisplayed()),
                () -> assertTrue(find(inputPassword).isDisplayed()),
                () -> assertTrue(find(btnLogin).isDisplayed())
        );

    }

    public void ingresarCredenciales(String username, String password) {
        Logs.info("Escribiendo el username");
        find(inputUsername).sendKeys(username);

        Logs.info("Escribiendo el password");
        find(inputPassword).sendKeys(password);
    }

    public void hacerClicBotonLogin() {
        Logs.info("Haciendo clic al botón de Login");
        find(btnLogin).click();
    }

    public void visualizarLogo() {
        Logs.info("Verificando logo de la compañía");
        assertTrue(find(logoApp).isDisplayed(), "El logo está visible");
    }

    public void visualizarMensajeError(String messageExpected) {
        Logs.info("Verificando mensaje de error");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(errorMessage).isDisplayed(), "El mensaje está visible"),
                () -> Assertions.assertEquals(messageExpected, find(errorMessage).getText())
        );
    }

    public void ingresarInformacionEnAlgunosCampos(String username, String password) {
        Logs.info("Escribiendo el username");
        find(inputUsername).sendKeys(username);

        Logs.info("Escribiendo el password");
        find(inputPassword).sendKeys(password);
    }

    @Then("El usuario visualiza el mensaje {string} de error correspondiente")
    public void visualizarMensajeErrorCorrespondiente(String messageExpected) {
        Logs.info("Verificando mensaje de error");
        Assertions.assertAll(
                () -> Assertions.assertTrue(find(errorMessage).isDisplayed(), "El mensaje está visible"),
                () -> Assertions.assertEquals(messageExpected, find(errorMessage).getText())
        );
    }
}
