package packagetests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.By.*;

public class SauceDemoTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        extent = ExtentManager.getInstance();
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        test = ExtentManager.createTest("SauceDemo Test", "Testing SauceDemo login functionality");
    }

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
            Assert.assertTrue(isLoggedIn, "Login failed!");

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

            Assert.assertTrue(addToCartButton.isDisplayed(), "addToCartButton is not displayed");
            Assert.assertTrue(addToCartButton.isEnabled(), "addToCartButton is not enable");
           boolean isPriceBarDisplayed = driver.findElement(xpath("(//div[@class='pricebar'])[1]")).isDisplayed();
           Assert.assertTrue(isPriceBarDisplayed, "price bar is not displayed");

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

    @AfterClass
    public void tearDown() {
        // Flush at the end of the test class
        extent.flush();
        driver.quit();
    }
}
