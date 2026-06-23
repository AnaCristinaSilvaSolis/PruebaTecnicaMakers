package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // --- Métodos comunes, NO abstractos ---
    public WebDriver getDriver() {
        return driver;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    public void waitPage(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // --- Métodos que deben ser implementados por cada página ---
    public abstract void verifyPage();

    protected void esperar(long tiempoMs) {
        try {
            Thread.sleep(tiempoMs);
        } catch (InterruptedException interruptedException) {

        }
    }
}
