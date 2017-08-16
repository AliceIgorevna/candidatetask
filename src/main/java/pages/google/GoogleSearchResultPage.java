package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.base.BasePage;

/**
 * @author Alisa Demennikova
 *         created on 2017/08/12
 */
public class GoogleSearchResultPage extends BasePage {
    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[@id='rso']//cite[contains(text(), 'kreditech.com')]/ancestor::div/h3")
    private WebElement kreditechSearchResultLink;

    public void clickOnLinkThatLeadsToKreditech() {
        kreditechSearchResultLink.click();
    }
}
