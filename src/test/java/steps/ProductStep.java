package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductPage;

public class ProductStep {
    private ProductPage productPage;

    public ProductStep(World world) {
        productPage = new ProductPage(world.driver);
    }

    @When("El usuario agrega todos los productos al carrito")
    public void agregarTodosLosProductos() {
        productPage.agregarTodosLosProductos();
    }

    @And("El usuario hace clic en el icono del carrito")
    public void verCarrito() {
        productPage.verCarrito();
    }

    @Then("El usuario visualiza el detalle del carrito")
    public void visualizarDetalleCarrito() {
        productPage.visualizarDetalleCarrito();
    }
}
