package packagetests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.*;

public class SauceDemoTest extends BaseTest {


    @Test
    public void testLogin() {
        try {
            driver.get("https://www.saucedemo.com");
            test.log(Status.INFO, "Navigated to SauceDemo");
            driver.findElement(name("user-name")).sendKeys("standard_user");
            driver.findElement(xpath("//input[@name='password']")).sendKeys("secret_sauce");
            driver.findElement(xpath("//input[@id='login-button']")).click();

            // Verify login is successful
            boolean isLoggedIn = driver.findElements(cssSelector(".inventory_list")).size() > 0;
            Assertions.assertTrue(isLoggedIn, "Login failed!");

            test.log(Status.PASS, "Login test executed successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed due to exception: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testProductDetails() {
        try {
            driver.get("https://www.saucedemo.com");
            test.log(Status.INFO, "Navigated to SauceDemo");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.findElement(xpath("(//div[@data-test='inventory-item-name'])[1]")).click();
            WebElement addToCartButton = driver.findElement(xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
            addToCartButton.click();

            Assertions.assertTrue(addToCartButton.isDisplayed(), "addToCartButton is not displayed");
            Assertions.assertTrue(addToCartButton.isEnabled(), "addToCartButton is not enable");
           boolean isPriceBarDisplayed = driver.findElement(xpath("(//div[@class='pricebar'])[1]")).isDisplayed();
           Assertions.assertTrue(isPriceBarDisplayed, "price bar is not displayed");

            test.log(Status.PASS, "product details test executed successfully");
        }
        catch (Exception e) {
            test.log(Status.FAIL, "test fail due to exception" +e.getMessage());
            throw e;
        }
    }



//    @AfterMethod
//    public void tearDownTest(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            test.log(Status.FAIL, "Test failed");
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            test.log(Status.PASS, "Test passed");
//        } else if (result.getStatus() == ITestResult.SKIP) {
//            test.log(Status.SKIP, "Test skipped");
//        }
//
//        if (driver != null) {
//            driver.quit();
//        }
//
//        // Flush after each test method
//        extent.flush();
//    }
}
