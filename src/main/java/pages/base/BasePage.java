package pages.base;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public abstract class BasePage {
    private static final String WINDOW_HANDLE_PROPERTY_NAME = "current.window.handle";
    private static final long WAITING_TIME_IN_MILLISECONDS = 5000;
    private static final int FIRST_BROWSER_TAB_INDEX = 0;
    private static final int SECOND_BROWSER_TAB_INDEX = 1;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void switchDriverToSecondTabOfBrowser() {
        String handleName = new ArrayList<>(driver.getWindowHandles()).get(SECOND_BROWSER_TAB_INDEX);
        driver.switchTo().window(handleName);
        System.setProperty(WINDOW_HANDLE_PROPERTY_NAME, handleName);
    }

    protected void switchDriverToFirstTabOfBrowser() {
        String handleName = new ArrayList<>(driver.getWindowHandles()).get(FIRST_BROWSER_TAB_INDEX);
        driver.switchTo().window(handleName);
        System.setProperty(WINDOW_HANDLE_PROPERTY_NAME, handleName);
    }


    protected void closeAllTabsOfBrowserExceptMainOne() {
        String originalHandle = new ArrayList<>(driver.getWindowHandles()).get(FIRST_BROWSER_TAB_INDEX);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    protected void verifyPageUrlContainsExpectedText(String expectedText) {
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl).as("Current URL that is opened").containsSequence(expectedText);
    }

    protected static void elementVisibility(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, WAITING_TIME_IN_MILLISECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    protected static void waitForTitleOfThePageBeLike(String title, WebDriver driver) {
        new WebDriverWait(driver, WAITING_TIME_IN_MILLISECONDS).until(ExpectedConditions.titleIs(title));
    }

    protected void waitForBrowserTabsToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME_IN_MILLISECONDS);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    protected void clickOnVisibleElementByJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
}
