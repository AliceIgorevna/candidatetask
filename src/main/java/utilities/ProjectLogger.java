package utilities;

import java.io.IOException;
import java.util.logging.*;

import static utilities.Constants.LOGGER_FILE_PATH;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class ProjectLogger {
    private static final ProjectLogger GLOBAL_LOGGER = new ProjectLogger();
    private Logger logger;

    private ProjectLogger() {
        setup();
    }

    public static ProjectLogger getGlobalLogger() {
        return GLOBAL_LOGGER;
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * Get the global logger to configure it
     * Suppress the logging output to the console
     * Create an HTML formatted logging file
     */
    private void setup() {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        Formatter formatterHTML = new HtmlLoggerFormatter();
        FileHandler fileHTML;

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers.length != 0) {
            if (handlers[0] instanceof ConsoleHandler) {
                rootLogger.removeHandler(handlers[0]);
            }
        }
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        try {
            fileHTML = new FileHandler(LOGGER_FILE_PATH, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to create log files!");
        }

        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}
