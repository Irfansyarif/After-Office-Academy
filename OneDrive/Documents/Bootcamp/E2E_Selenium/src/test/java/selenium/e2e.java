package selenium;

import java.time.Duration;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import io.cucumber.java.After;
import io.cucumber.java.lu.a.as;

public class e2e {
    public WebDriver webDriver;
    public WebDriverWait wait;
    public String item1 = "Sauce Labs Backpack";
    public String itemPrice ;
    
    

    @BeforeSuite
    public void e2eTest() throws Exception {
        // This is a placeholder for the test method.
        // You can add your test logic here.
        System.out.println("Running E2E test");
        System.setProperty("webdriver.chrome.driver",
                "chromedriver-win64\\chromedriver.exe");
             

        // Set Chrome preferences
        java.util.HashMap<String, Object> prefs = new java.util.HashMap<String, Object>();
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.credentials_enable_service", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        webDriver = new org.openqa.selenium.chrome.ChromeDriver(options);

        webDriver.get("https://www.saucedemo.com/");
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }


    

    @Test
    public void login() throws Exception {
        System.out.println("input Username");
        WebElement username = webDriver.findElement(By.id("user-name"));
        WebElement password = webDriver.findElement(By.xpath("//input[@id = 'password']"));
        WebElement buttonSignIn = webDriver.findElement(By.xpath("//input[@id = 'login-button']"));

        username.sendKeys("standard_user");
        Thread.sleep(2000);
        password.sendKeys("secret_sauce");
        Thread.sleep(2000);
        buttonSignIn.click();
        Thread.sleep(2000); 
        

        }
    

    @Test(dependsOnMethods = "login")
    public void addProductToCart() throws Exception {
        WebElement item1 = webDriver.findElement(By.xpath("//div[contains(text(), \"Sauce Labs Backpack\")]//ancestor::div[@class=\"inventory_item_description\"]//button[contains(text(),\"Add to cart\")]"));
        WebElement item1Price = webDriver.findElement(By.xpath("//div[contains(text(), \"Sauce Labs Backpack\")]//ancestor::div[@class=\"inventory_item_description\"]//div[@class=\"inventory_item_price\"]"));

        wait.until(d -> item1Price.isDisplayed());
        System.out.println("Product price: " + item1Price.getText());
        itemPrice = item1Price.getText();

        wait.until(d -> item1.isDisplayed());
        item1.click();
        System.out.println("Product added to cart");
        Thread.sleep(2000);

    }

    @Test(dependsOnMethods = "addProductToCart")
    public void validateProductaddedtoCart() throws Exception {
        WebElement removeItem1= webDriver.findElement(By.xpath("//div[contains(text(), 'Sauce Labs Backpack')]//ancestor::div[@class='inventory_item_description']//button[@id='remove-sauce-labs-backpack']"));
        WebElement cartQuantity = webDriver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
        WebElement buttonOpenCartPage = webDriver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        

       
        wait.until(d -> removeItem1.isDisplayed());
        assert removeItem1.getText().equals("Remove");

        wait.until(d -> cartQuantity.isDisplayed());
        if (cartQuantity.getText().equals("1")) {
            wait.until(d -> buttonOpenCartPage.isDisplayed());
            buttonOpenCartPage.click();
        } else {
            System.out.println("labelCart => " + cartQuantity);
            Assert.assertTrue(false, "cart label not increment");
        }

    }

     @Test(dependsOnMethods = "validateProductaddedtoCart")
    public void validateProductInCart() throws Exception {
        WebElement Item1= webDriver.findElement(By.xpath("//div[@class=\"cart_item\"][1]//div[@data-test=\"inventory-item-name\"and contains(text(),item1)]"));
        WebElement checkOut = webDriver.findElement(By.xpath("//button[@id=\"checkout\"]"));
        WebElement itemPriceCart = webDriver.findElement(By.xpath("//div[@class=\"cart_item\"][1]//div[@class=\"inventory_item_price\"]"));
        

        Thread.sleep(3000);
        wait.until(d -> Item1.isDisplayed());
        assert Item1.getText().equals(item1);

        wait.until(d -> itemPriceCart.isDisplayed());
        assert itemPriceCart.getText().equals(itemPrice);

        wait.until(d -> checkOut.isDisplayed());
        checkOut.click();

    }

    @Test(dependsOnMethods = "validateProductInCart")
    public void fillCustomerInfo() throws Exception {
        WebElement firstName = webDriver.findElement(By.xpath("//input[@id=\"first-name\"]"));
        WebElement lastName = webDriver.findElement(By.xpath("//input[@id=\"last-name\"]"));
        WebElement postalCode = webDriver.findElement(By.xpath("//input[@id=\"postal-code\"]"));
        WebElement continueButton = webDriver.findElement(By.xpath("//input[@id=\"continue\"]"));

        firstName.sendKeys("Irfan");
        Thread.sleep(2000);
        lastName.sendKeys("Anwar");
        Thread.sleep(2000);
        postalCode.sendKeys("12345");
        Thread.sleep(2000);
        continueButton.click();
    }

    @Test(dependsOnMethods = "fillCustomerInfo")
    public void validateCheckoutPage() throws Exception {
        WebElement Item1= webDriver.findElement(By.xpath("//div[@class=\"cart_item\"][1]//div[@data-test=\"inventory-item-name\"and contains(text(),item1)]"));
        WebElement FinishOrder = webDriver.findElement(By.xpath("//button[@id=\"finish\"]"));
        WebElement TotalSummary = webDriver.findElement(By.xpath("//div[@class=\"summary_subtotal_label\"]"));
        

        Thread.sleep(3000);
        wait.until(d -> Item1.isDisplayed());
        assert Item1.getText().equals(item1);

        wait.until(d -> TotalSummary.isDisplayed());
        assert TotalSummary.getText().equals("Item total: " + itemPrice);

        wait.until(d -> FinishOrder.isDisplayed());
        assert FinishOrder.getText().equals("Finish");
    }

    @AfterSuite
    public void CloseBrowser() {

        webDriver.quit();
    }
}
