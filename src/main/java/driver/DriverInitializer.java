package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static utilities.Constants.CHROME_DRIVER_PATH;
import static utilities.Constants.FIREFOX_DRIVER_PATH;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class DriverInitializer {

    private DriverInitializer() {
    }

    static WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        return driver;
    }

    static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        return new ChromeDriver();
    }
}
