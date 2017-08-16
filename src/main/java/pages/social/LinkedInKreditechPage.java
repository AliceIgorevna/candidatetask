package pages.social;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class LinkedInKreditechPage extends BasePage {
    private static final String FAKE_EMAIL_FOR_LINKEDIN = "klient.pochtovyy@mail.ru";
    private static final String FAKE_PASSWORD_FOR_LINKEDIN = "12WEdfvbc***";
    private static final String HREF_ATTRIBUTE_NAME = "href";

    public LinkedInKreditechPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[@id='stream-promo-top-bar']//div[@class='header']/a")
    private WebElement companyLogoLink;

    @FindBy(xpath = ".//*[@id='stream-promo-top-bar']//div[@class='content-wrapper']/h1")
    private WebElement companyHeader;

    public void ensureOpenedLinkedInPageIsRelatedToKreditech(String expectedPageHeader){
        elementVisibility(companyHeader, driver);
        verifyHeaderOfTheCompany(expectedPageHeader);
        verifyLogoLinkLeadsToCreditech();
        switchDriverToFirstTabOfBrowser();
        closeAllTabsOfBrowserExceptMainOne();
    }

    private void verifyHeaderOfTheCompany(String expectedPageHeader){
        Assertions.assertThat(companyHeader.getText()).as("Company Header").isEqualTo(expectedPageHeader);
    }

    private void verifyLogoLinkLeadsToCreditech(){
        Assertions.assertThat(companyLogoLink.getAttribute(HREF_ATTRIBUTE_NAME)).as("Kreditech Logo link")
                .containsSequence("company/kreditech");
    }
}
