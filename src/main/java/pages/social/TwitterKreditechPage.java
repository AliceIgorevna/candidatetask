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
public class TwitterKreditechPage extends BasePage {
    public TwitterKreditechPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//div[contains(@class, 'ProfileSidebar')]//h1[@class='ProfileHeaderCard-name']")
    private WebElement profileName;

    public void ensureOpenedTwitterPageIsRelatedToKreditech(String expectedProfileName){
        elementVisibility(profileName, driver);
        verifyProfileName(expectedProfileName);
        verifyPageUrlContainsExpectedText(KREDITECH_EXPECTED_VALUE);
        switchDriverToFirstTabOfBrowser();
        closeAllTabsOfBrowserExceptMainOne();
    }

    private void verifyProfileName(String expectedProfileName){
        Assertions.assertThat(profileName.getText()).as("Profile Name on Twitter page")
                .isEqualTo(expectedProfileName);
    }
}
