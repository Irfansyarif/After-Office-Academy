package selenium_page_factory.objeact_repository;

import main.selenium_page_factory.object_repository.BaseObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class validateCheckoutPage extends BaseObject {
    @FindBy(xpath = "//div[@class=\\\"cart_item\\\"][1]//div[@data-test=\\\"inventory-item-name\\\"and contains(text(),item1)]")
    public WebElement Item1;

    @FindBy(xpath = "//button[@id=\\\"checkout\\\"]")
    public WebElement checkOut;

    @FindBy(xpath = "//div[@class=\\\"cart_item\\\"][1]//div[@class=\\\"inventory_item_price\\\"]")
    public WebElement itemPriceCart;

    public validateCheckoutPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}
