package utils;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    // Carpeta donde se guardarán las evidencias de cada ejecución
    private static final String evidencePath = "evidencias";

    /**
     * Toma un screenshot del navegador y lo guarda como archivo PNG.
     *
     * @param screenshotName Nombre de la carpeta donde se almacenará el screenshot.
     */
    public static void tomarScreenshot(String screenshotName) {
        Logs.debug("Tomando Screenshot");
        // Obtiene la imagen en memoria desde el navegador
        final var screenshotFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);

        // Ruta final del archivo
        final var path = String.format("%s/%s/screenshot.png", evidencePath, screenshotName);

        try {
            // Copia el archivo temporal a la ruta final
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error tomar screenshot: %s", ioException.getLocalizedMessage());
        }
    }

    /**
     * Obtiene el Page Source actual del navegador y lo guarda como archivo HTML.
     * Usa Jsoup para formatear el HTML y hacerlo más legible.
     *
     * @param fileName Carpeta donde se guardará el archivo.
     */
    public static void obtenerPageSource(String fileName) {
        Logs.debug("Tomando page source");
        // Construcción del path del archivo final
        final var path = String.format("%s/%s/pageSource.html", evidencePath, fileName);

        try {
            final var file = new File(path);

            Logs.debug("Creando los directorios padres si es que no existen");
            final var fileWrite = new FileWriter(file);

            // Obtiene el HTML actual del navegador
            final var pageSource = DriverProvider.getDriver().getPageSource();
            // Convierte el HTML en un formato legible con Jsoup
            final var parsedPageSource = (pageSource != null) ? Jsoup.parse(pageSource).toString() : "";

            // Escribe el contenido en el archivo
            fileWrite.write(parsedPageSource);
            fileWrite.close();

        } catch (IOException ioException) {
            Logs.error("Error tomar page source: %s", ioException.getLocalizedMessage());
        }
    }

    /**
     * Elimina la carpeta completa de evidencias antes de iniciar una nueva ejecución.
     * Esto evita acumular archivos antiguos.
     */
    public static void eliminarEvidenciasPasadas() {
        try {
            Logs.debug("Borrando las carpetas de evidencias");
            // Elimina recursivamente la carpeta "evidencias"
            FileUtils.deleteDirectory(new File(evidencePath));
        } catch (IOException ioException) {
            Logs.error("Error al borrar la evidencia anterior: %s", ioException.getLocalizedMessage());
        }
    }

    /**
     * Adjunta una captura de pantalla en el reporte Allure.
     * Devuelve el screenshot como un arreglo de bytes.
     */
    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] tomarScreenshotAllure() {
        Logs.debug("Tomando screenshot para Allure");
        // Allure solo soporta el formato en bytes
        return ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.BYTES);
    }


    /**
     * Adjunta el Page Source actual al reporte Allure.
     * Se usa Jsoup para formatearlo correctamente como texto.
     */
    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String obtenerPageSourceAllure() {
        Logs.debug("Tomando PageSource para Allure");
        final var pageSource = DriverProvider.getDriver().getPageSource();
        // Retorna HTML legible para incluirlo en el reporte
        return (pageSource != null) ? Jsoup.parse(pageSource).toString() : "";
    }
}
