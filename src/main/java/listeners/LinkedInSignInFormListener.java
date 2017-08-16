package listeners;

import driver.DriverCreator;
import org.openqa.selenium.By;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import utilities.ProjectLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by User on 15/8/2017.
 */
public class LinkedInSignInFormListener implements IInvokedMethodListener {
    private static final Logger LOGGER = ProjectLogger.getGlobalLogger().getLogger();
    private static final String FAKE_EMAIL_FOR_LINKEDIN = "klient.pochtovyy@mail.ru";
    private static final String FAKE_PASSWORD_FOR_LINKEDIN = "12WEdfvbc***";

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        try {
            if (DriverCreator.getDriver().findElement(By.xpath(".//form[@id='join-form']")).isDisplayed()) {
                DriverCreator.getDriver().findElement(By.xpath(".//a[contains(text(), 'Sign in')]")).click();
                DriverCreator.getDriver().findElement(By.xpath(".//input[@name='session_key' and @class='login-email']")).
                        sendKeys(FAKE_EMAIL_FOR_LINKEDIN);
                DriverCreator.getDriver().findElement(By.xpath(".//input[@name='session_password' and @class='login-password']")).
                        sendKeys(FAKE_PASSWORD_FOR_LINKEDIN);
                DriverCreator.getDriver().findElement(By.xpath(".//*[@id='login-submit']")).click();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.INFO, "No SIGN IN WINDOW found");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
