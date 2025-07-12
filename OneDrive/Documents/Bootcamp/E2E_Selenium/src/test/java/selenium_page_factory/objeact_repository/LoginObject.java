package selenium_page_factory.objeact_repository;

// Update the import to match the correct package name
import src.main.selenium_page_refactory.base.BaseObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginObject extends BaseObject {
    @FindBy(xpath = "//input[@id='user-name']")
    public WebElement inputUsername;

    @FindBy(xpath = "//input[@id = 'password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//input[@id='login-button']")
    public WebElement buttonLogin;

    public LoginObject(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
