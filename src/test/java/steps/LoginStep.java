package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginStep {
    private LoginPage loginPage;

    public LoginStep(World world) {
        loginPage = new LoginPage(world.driver);
    }


    @When("El usuario completa el formulario de login con credenciales de usuario activo")
    public void completarFormularioLoginCorrecto() {
        loginPage.ingresarCredenciales("standard_user", "secret_sauce");
    }

    @And("El usuario hace clic en el boton de Login")
    public void hacerClicBotonLogin() {
        loginPage.hacerClicBotonLogin();
    }

    @Then("El usuario visualiza el logo de Swag Labs")
    public void visualizarLogo() {
        loginPage.visualizarLogo();
    }

    @When("El usuario completa el formulario de login con credenciales de usuario incorrectos")
    public void completarFormularioLoginIncorrecto() {
        loginPage.ingresarCredenciales("standard_user", "contraseniaMala");
    }


    @Then("El usuario visualiza el mensaje de error {string}")
    public void visualizarMensajeError(String message) {
        loginPage.visualizarMensajeError(message);
    }

    @When("El usuario no ingresa informacion en el campo username {string} o en el campo password {string}")
    public void ingresarInformacionEnAlgunosCampos(String username, String password) {
        loginPage.ingresarInformacionEnAlgunosCampos(username, password);
    }

    @Then("El usuario visualiza el mensaje {string} de error correspondiente")
    public void visualizarMensajeErrorCorrespondiente(String message) {
        loginPage.visualizarMensajeErrorCorrespondiente(message);
    }
}
