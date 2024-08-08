package packagetests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    WebDriver driver;
    static ExtentReports extent;
    ExtentTest test;

    @BeforeAll
    public static void setup() {
        extent = ExtentManager.getInstance();
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-first-run");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        test = ExtentManager.createTest("SauceDemo Test", "Testing SauceDemo login functionality");
    }

    @AfterAll
    public static void extentFlush() {
        // Flush at the end of the test class
        extent.flush();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
