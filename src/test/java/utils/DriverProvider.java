package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverProvider {

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    /**
     * Obtiene la instancia de WebDriver asociada al hilo actual.
     * Cada test en ejecución paralela recibe su propio driver seguro.
     */
    public static WebDriver getDriver() {
        return threadLocal.get();
    }

    /**
     * Asigna un WebDriver al hilo actual.
     * Este método_ se usa generalmente al inicializar el driver en Hooks (@Before).
     */
    public static void setDriver(WebDriver driver) {
        threadLocal.set(driver);
    }

    /**
     * Elimina la instancia de WebDriver del hilo actual.
     * Esto evita fugas de memoria una vez que el test finaliza.
     */
    public static void removerDriver() {
        threadLocal.remove();
    }

    /**
     * Indica si es posible tomar evidencia (screenshots).
     * Para eso:
     * - Debe existir un WebDriver asociado al hilo
     * - Debe tener una sesión activa (SessionId no nula)
     *
     * @return true si la sesión está activa y se puede tomar evidencia.
     */
    public static boolean tomarEvidencia() {
        return threadLocal.get() != null && ((RemoteWebDriver) threadLocal.get()).getSessionId() != null;
    }
}
