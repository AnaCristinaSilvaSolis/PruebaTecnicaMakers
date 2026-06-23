package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logs {
    // Logger de Log4j2
    private static final Logger log = LogManager.getLogger("AUTOMATION");

    // Métodos simples
    public static void trace(String message) {
        log.trace(message);
    }

    public static void debug(String message) {
        log.debug(message);
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void warning(String message) {
        log.warn(message);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void fatal(String message) {
        log.fatal(message);
    }

    // Métodos con formato (String.format)
    public static void trace(String format, Object... args) {
        log.trace(String.format(format, args));
    }

    public static void debug(String format, Object... args) {
        log.debug(String.format(format, args));
    }

    public static void info(String format, Object... args) {
        log.info(String.format(format, args));
    }

    public static void warning(String format, Object... args) {
        log.warn(String.format(format, args));
    }

    public static void error(String format, Object... args) {
        log.error(String.format(format, args));
    }

    public static void fatal(String format, Object... args) {
        log.fatal(String.format(format, args));
    }

}
