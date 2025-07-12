package src.main.selenium_page_factory.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public abstract class BaseObject {
    public WebDriver webDriver;

    public BaseObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}