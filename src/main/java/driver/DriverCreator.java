package driver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class DriverCreator {
    private static WebDriver driver;

    private DriverCreator() {
    }

    public static WebDriver createWebDriver(String browserName) {
        if (browserName.equalsIgnoreCase(BrowserKind.firefox.toString())) {
            driver = DriverInitializer.createFirefoxDriver();
        } else if (browserName.equalsIgnoreCase(BrowserKind.chrome.toString())) {
            driver = DriverInitializer.createChromeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser value!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void openUrl(String url) {
        driver.get(url);
    }

    public static void closeBrowser(WebDriver driver) {
        driver.quit();
    }

    public enum BrowserKind {
        firefox, chrome
    }
}
