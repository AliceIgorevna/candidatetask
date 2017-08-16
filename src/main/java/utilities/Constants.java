package utilities;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class Constants {
    //BROWSER DRIVER PATH
    public static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe";
    public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe";

    //LOGGER
    public static final String LOGGER_FILE_PATH = System.getProperty("user.dir") + "/ExtentReport/Logging.html";

    //REPORTS PATH
    public static final String REPORT_PATH = System.getProperty("user.dir") + "/ExtentReport/";

    //SCREENSHOT CONSTANTS
    public static final String RELATIVE_PROJECT_PATH_WITH_BACKSLASHES = System.getProperty("user.dir").replaceAll("\\\\", "/");
    public static final String SCREENSHOT_PATH = RELATIVE_PROJECT_PATH_WITH_BACKSLASHES + "/ExtentReport/screenshots/";
    public static final String IMAGE_EXTENTION = ".png";

    //EXPECTED VALUES

    public static final String KREDITECH_EXPECTED_VALUE = "kreditech";
}
