import driver.DriverCreator;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.google.GoogleSearchPage;
import pages.google.GoogleSearchResultPage;
import pages.kreditech.KreditechMainPage;
import pages.social.FacebookKreditechPage;
import pages.social.LinkedInKreditechPage;
import pages.social.TwitterKreditechPage;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class KreditechTest {
    private WebDriver driver;
    private GoogleSearchPage searchPage;
    private GoogleSearchResultPage searchResultPage;
    private KreditechMainPage kreditechMainPage;
    private LinkedInKreditechPage linkedInKreditechPage;
    private FacebookKreditechPage facebookKreditechPage;
    private TwitterKreditechPage twitterKreditechPage;

    @DataProvider
    public Object[][] kreditechTestData() {
        return new Object[][]{{"kreditech", "Kreditech Holding SSL GmbH", "Kreditech", "Kreditech"}};
    }

    @Test(dataProvider = "kreditechTestData")
    public void searchKreditechInGoogle(String searchQuery, String linkedInPageHeader, String expectedFacebookCompanyName,
                                        String expectedTwitterProfileName) {
        ExtentReportListener.testLog("Search for 'kreditech'");
        searchPage.searchFor(searchQuery);
        ExtentReportListener.testLog("Click on a result which leads to 'kreditech.com'");
        searchResultPage.clickOnLinkThatLeadsToKreditech();
        ExtentReportListener.testLog("In the section 'Follow us' click on 'LinkedIn'");
        kreditechMainPage.clickLinkedInButton();
        ExtentReportListener.testLog("Ensure that opened 'LinkedIn' page is related to Kreditech");
        linkedInKreditechPage.ensureOpenedLinkedInPageIsRelatedToKreditech(linkedInPageHeader);
        ExtentReportListener.testLog("In the section 'Follow us' click on 'Facebook'");
        kreditechMainPage.clickFacebookButton();
        ExtentReportListener.testLog("Ensure that opened 'Facebook' page is related to Kreditech");
        facebookKreditechPage.ensureOpenedFacebookPageIsRelatedToKreditech(expectedFacebookCompanyName);
        ExtentReportListener.testLog("In the section 'Follow us' click on 'Twitter'");
        kreditechMainPage.clickTwitterButton();
        ExtentReportListener.testLog("Ensure that opened 'Twitter' page is related to Kreditech");
        twitterKreditechPage.ensureOpenedTwitterPageIsRelatedToKreditech(expectedTwitterProfileName);
        ExtentReportListener.testLog("Click on each navigation bar item and be sure that opened an appropriate page");
        kreditechMainPage.clickEachHeaderItemAndEnsureOpenedPageIsCorrect();
    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void runBrowser(String browser) {
        driver = DriverCreator.createWebDriver(browser);
        ExtentReportListener.assignTestAuthor("Alisa Demennikova", browser);
        DriverCreator.openUrl("https://www.google.com.ua");
        ExtentReportListener.testLog("Open https://www.google.com.ua");
        initPageElements();
    }

    @Parameters({"browser"})
    @AfterTest(alwaysRun = true)
    public void closeBrowser(String browser) {
        DriverCreator.closeBrowser(driver);
    }

    private void initPageElements() {
        searchPage = new GoogleSearchPage(driver);
        searchResultPage = new GoogleSearchResultPage(driver);
        kreditechMainPage = new KreditechMainPage(driver);
        linkedInKreditechPage = new LinkedInKreditechPage(driver);
        facebookKreditechPage = new FacebookKreditechPage(driver);
        twitterKreditechPage = new TwitterKreditechPage(driver);
    }
}
