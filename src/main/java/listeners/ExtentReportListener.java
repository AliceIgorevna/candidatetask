package listeners;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;
import utilities.Screenshooter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static utilities.Constants.REPORT_PATH;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class ExtentReportListener implements IReporter, ITestListener, ISuiteListener{
    private static ExtentReports report;
    private static ExtentTest test;

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {
        report.close();
    }

    @Override
    public void onStart(ISuite iSuite) {
        report = new ExtentReports(REPORT_PATH + "ExtentReport.html", false, DisplayOrder.OLDEST_FIRST);
    }

    @Override
    public void onFinish(ISuite iSuite) {
        report.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        testLogBorder(String.format("Start to execute test %s", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String message = sumUpTestResultExtentReporter(iTestResult, LogStatus.PASS);
        test.log(LogStatus.PASS, message);
        report.endTest(test);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String message = sumUpTestResultExtentReporter(iTestResult, LogStatus.FAIL);
        test.log(LogStatus.FAIL, message);
        report.endTest(test);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String message = sumUpTestResultExtentReporter(iTestResult, LogStatus.SKIP);
        test.log(LogStatus.SKIP, message);
        report.endTest(test);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        test = report.startTest(iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public static void testLog(String message) {
        String path = Screenshooter.captureScreenshot(message.substring(0, Math.min(message.length(), 15))
                .replaceAll("\\W", ""));
        String imagePath = test.addScreenCapture(path);
        test.log(LogStatus.INFO, message, imagePath);
    }

    public static void assignTestAuthor(String author, String browser) {
        test.assignAuthor(author);
        test.setDescription(browser);
    }

    private static void testLogBorder(String message) {
        test.log(LogStatus.INFO, "//================= " + message + " =================//");
    }

    private String sumUpTestResultExtentReporter(ITestResult iTestResult, LogStatus logStatus) {
        test.getTest().setStartedTime(getTime(iTestResult.getStartMillis()));
        test.getTest().setEndedTime(getTime(iTestResult.getEndMillis()));
        for (String group : iTestResult.getMethod().getGroups()) {
            test.assignCategory(group);
        }
        String message = String.format("Test %s %sed", iTestResult.getName(), logStatus.toString().toLowerCase());
        if (iTestResult.getThrowable() != null) {
            test.log(logStatus, "StackTrace: ", iTestResult.getThrowable());
        }
        return message;
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
