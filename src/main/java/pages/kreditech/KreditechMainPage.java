package pages.kreditech;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class KreditechMainPage extends BasePage {

    public KreditechMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[@id='footer']//*[@class='social linkedin']/a")
    private WebElement linkedInSourceButton;

    @FindBy(xpath = ".//*[@id='footer']//*[@class='social facebook']/a")
    private WebElement facebookInSourceButton;

    @FindBy(xpath = ".//*[@id='footer']//*[@class='social twitter']/a")
    private WebElement twitterInSourceButton;

    //----------HEADER WEB ELEMENTS----------
    @FindBy(xpath = ".//*[@id='menu-main']//a[@title='what']")
    private WebElement whatWeDoLink;

    @FindBy(xpath = ".//*[@id='menu-main']//a[@title='who']")
    private WebElement whoWeAreLink;

    @FindBy(xpath = ".//*[@id='menu-main']//a[contains(@href, 'with-us')]")
    private WebElement workWithUsLink;

    @FindBy(xpath = ".//*[@id='menu-main']//a[@title='work']")
    private WebElement careersLink;

    @FindBy(xpath = ".//*[@id='menu-main']//a[contains(@href, 'investor')]")
    private WebElement investorRelationsLink;

    @FindBy(xpath = ".//*[@id='menu-main']//a[@title='news']")
    private WebElement pressLink;

    @FindBy(xpath = ".//*[@id='menu-main']//a[contains(@href, 'magazine')]")
    private WebElement magazineLink;

    public void clickLinkedInButton(){
        clickOnVisibleElementByJS(driver, linkedInSourceButton);
        switchDriverToSecondTabOfBrowser();
    }

    public void clickFacebookButton(){
        clickOnVisibleElementByJS(driver, facebookInSourceButton);
        waitForBrowserTabsToLoad(driver);
        switchDriverToSecondTabOfBrowser();
    }

    public void clickTwitterButton(){
        clickOnVisibleElementByJS(driver, twitterInSourceButton);
        waitForBrowserTabsToLoad(driver);
        switchDriverToSecondTabOfBrowser();
    }

    public void clickEachHeaderItemAndEnsureOpenedPageIsCorrect(){
        clickWhoWeAreAndVerify();
        clickWorkWithUsAndVerify();
        clickCareersAndVerify();
        clickInvestorRelationsAndVerify();
        clickPressAndVerify();
        clickMagazineAndVerify();
        clickWhatWeDoAndVerify();
    }

    private void clickWhatWeDoAndVerify(){
        whatWeDoLink.click();
        waitForTitleOfThePageBeLike("Kreditech – Providing access to credit for the underbanked", driver);
        verifyPageUrlContainsExpectedText("what-we-do");
    }

    private void clickWhoWeAreAndVerify(){
        clickOnVisibleElementByJS(driver, whoWeAreLink);
        waitForTitleOfThePageBeLike("Who we are - Kreditech", driver);
        verifyPageUrlContainsExpectedText("who-we-are");
    }

    private void clickWorkWithUsAndVerify(){
        clickOnVisibleElementByJS(driver, workWithUsLink);
        waitForTitleOfThePageBeLike("Work with Kreditech in Hamburg, Data Science, Engineering, Finance", driver);
        verifyPageUrlContainsExpectedText("work-with-us");
    }

    private void clickCareersAndVerify(){
        clickOnVisibleElementByJS(driver, careersLink);
        waitForTitleOfThePageBeLike("Kreditech Career Opportunities – Hamburg and worldwide", driver);
        verifyPageUrlContainsExpectedText("careers");
    }

    private void clickInvestorRelationsAndVerify(){
        clickOnVisibleElementByJS(driver, investorRelationsLink);
        waitForTitleOfThePageBeLike("Investor Relations Website – Kreditech Group Hamburg", driver);
        verifyPageUrlContainsExpectedText("investor-relations");
    }

    private void clickPressAndVerify(){
        clickOnVisibleElementByJS(driver, pressLink);
        waitForTitleOfThePageBeLike("Media & Press | Kreditech Group Website", driver);
        verifyPageUrlContainsExpectedText("press");
    }

    private void clickMagazineAndVerify(){
        clickOnVisibleElementByJS(driver, magazineLink);
        waitForTitleOfThePageBeLike("Magazine - Kreditech", driver);
        verifyPageUrlContainsExpectedText("magazine");
    }
}
