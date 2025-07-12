package selenium_page_factory.objeact_repository;

import main.selenium_page_factory.object_repository.BaseObject;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class validateProductAddedtoCart extends BaseObject {
    @FindBy(xpath = "//div[contains(text(), 'Sauce Labs Backpack')]//ancestor::div[@class='inventory_item_description']//button[@id='remove-sauce-labs-backpack']")
    public WebElement removeItem1;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement cartQuantity;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement buttonOpenCartPage;

    public validateProductAddedtoCart(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}
