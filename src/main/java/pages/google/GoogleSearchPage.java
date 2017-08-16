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
public class GoogleSearchPage extends BasePage {
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//button[@name='btnG' and @type='submit']/following-sibling::*//input[@type='text']")
    private WebElement searchInput;


    @FindBy(xpath = ".//input[@class='lsb' and @type='button']")
    private WebElement searchButton;

    public void searchFor(String value){
        searchInput.sendKeys(value);
        searchButton.click();
    }
}
