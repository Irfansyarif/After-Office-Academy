package selenium_page_factory.objeact_repository;

// Update the import to match the correct package name

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import src.main.selenium_page_factory.base.BaseObject;

public class AddProductToCart extends BaseObject {
    @FindBy(xpath = "//div[contains(text(), \\\"Sauce Labs Backpack\\\")]//ancestor::div[@class=\\\"inventory_item_description\\\"]//button[contains(text(),\\\"Add to cart\\\")]")
    public WebElement item1;

    @FindBy(xpath = "//div[contains(text(), \\\"Sauce Labs Backpack\\\")]//ancestor::div[@class=\\\"inventory_item_description\\\"]//div[@class=\\\"inventory_item_price\\\"]")
    public WebElement item1Price;

    public AddProductToCart(WebDriver webDriver) {
        super(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
