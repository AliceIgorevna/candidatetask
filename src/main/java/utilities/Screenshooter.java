package utilities;

import driver.DriverCreator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utilities.Constants.IMAGE_EXTENTION;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class Screenshooter {
    private static final Logger LOGGER = ProjectLogger.getGlobalLogger().getLogger();

    public static String captureScreenshot(String fileName) {
        String screenshotTime = Calendar.getInstance().getTime().toString().replaceAll("\\W", "_");
        String screenshotName = fileName + screenshotTime + IMAGE_EXTENTION;
        File screenshotSrc = ((TakesScreenshot) DriverCreator.getDriver()).getScreenshotAs(OutputType.FILE);
        String sourceFile = Constants.SCREENSHOT_PATH + screenshotName;
        try {
            FileUtils.copyFile(screenshotSrc, new File(sourceFile));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, "Error during screenshot taking: " + e.getMessage());
        }
        return sourceFile;
    }
}
