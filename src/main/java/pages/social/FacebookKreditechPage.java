package pages.social;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

import static utilities.Constants.KREDITECH_EXPECTED_VALUE;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class FacebookKreditechPage extends BasePage {
    public FacebookKreditechPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[@id='entity_sidebar']//h1")
    private WebElement pageCompanyName;

    public void ensureOpenedFacebookPageIsRelatedToKreditech(String expectedCompanyName){
        elementVisibility(pageCompanyName, driver);
        verifyCompanyName(expectedCompanyName);
        verifyPageUrlContainsExpectedText(KREDITECH_EXPECTED_VALUE);
        switchDriverToFirstTabOfBrowser();
        closeAllTabsOfBrowserExceptMainOne();
    }

    private void verifyCompanyName(String expectedCompanyName){
        Assertions.assertThat(pageCompanyName.getText()).as("Company Name on Facebook page")
                .isEqualTo(expectedCompanyName);
    }
}
