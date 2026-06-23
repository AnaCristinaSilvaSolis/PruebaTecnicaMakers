package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BasePage;
import utils.Logs;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductPage extends BasePage {
    //Localizadores
    private final By messageCart = By.xpath("//span[@data-test='title']");
    private final By btnsAddCart = By.className("btn_inventory");
    private final By shoppingCart = By.xpath("//a[@data-test='shopping-cart-link']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando la página de Productos");
        Assertions.assertAll(
                () -> assertTrue(find(btnsAddCart).isDisplayed()),
                () -> assertTrue(find(btnsAddCart).isEnabled())
        );
    }

    public void agregarTodosLosProductos() {
        Logs.info("Agregando todos los productos al carrito.");

        List<WebElement> btnList = findAll(btnsAddCart);

        Logs.info("Agregando todos los productos al carrito.");
        for (var boton : btnList) {
            boton.click();
        }
    }

    public void verCarrito() {
        Logs.info("Haciendo clic al ícono del carrito");
        find(shoppingCart).click();
    }

    public void visualizarDetalleCarrito() {
        Logs.info("Verificando título de la página");
        assertTrue(find(messageCart).isDisplayed(), "El título está visible");
    }
}
