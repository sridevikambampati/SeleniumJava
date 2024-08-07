package packagetests;

import dev.failsafe.internal.util.Assert;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceDemoTest2 {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }
    @BeforeEach
    public void loginFlow() {
        WebElement username = driver.findElement(By.xpath("//input[@name='user-name']"));
        username.sendKeys("standard_user");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }
    @Test
    public void WebProduct() {
        driver.findElement(By.xpath("(//div[@class='inventory_item_name '])[1]"));
       WebElement addToCartButton = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
       addToCartButton.click();
       WebElement productPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
       Assertions.assertTrue(productPrice.isDisplayed());
        Assertions.assertEquals("$"
                + "29.99", productPrice.getText());
       WebElement shoppingCartContainer = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
       Assertions.assertTrue(shoppingCartContainer.isDisplayed(),"shopping crt container is not displayed");
        WebElement removeCartElement =  driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']"));
        Assertions.assertTrue(removeCartElement.isDisplayed(),"remove cart element is not displayed");
        Assertions.assertTrue(removeCartElement.isEnabled(), "remove cart element is not enabled");
    }
    @Test
    public void burgerButton() throws InterruptedException {
        Thread.sleep(2000);
        WebElement burgerButton = driver.findElement(By.id("react-burger-menu-btn"));
        burgerButton.click();
        WebElement allItems = driver.findElement(By.id("reset_sidebar_link"));
        allItems.isDisplayed();
        WebElement about = driver.findElement(By.id("about_sidebar_link"));
        about.isDisplayed();
        WebElement logout = driver.findElement(By.id("logout_sidebar_link"));
        Assertions.assertTrue(logout.isDisplayed(), "logout button is not displayed");

    }
    @AfterAll
     public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}