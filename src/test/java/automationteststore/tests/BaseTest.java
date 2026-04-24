package automationteststore.tests;

import automationteststore.config.ConfiguratorManager;
import automationteststore.config.ProjectConfig;
import automationteststore.utils.ScreenshotExtension;
import automationteststore.utils.WaitHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ExtendWith(ScreenshotExtension.class)
public class BaseTest {

    protected WebDriver driver;
    protected WaitHelper waitHelper;

    @BeforeEach
    public void setUp() {


        ProjectConfig config = ConfiguratorManager.getConfig();

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        String args = config.chromeArgs();

        if (args != null && !args.isBlank()) {
            for (String arg : args.split(",")) {
                options.addArguments(arg.trim());
            }
        }

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(config.homepageUrl());

        WebDriverWait pageWait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1. page loaded
        pageWait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        // 2. DOM exists
        pageWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // 3. small stabilization
        waitHelper = new WaitHelper(driver, 10);
        waitHelper.shortDelay();

        // 4. LOGS (ПОСЛЕ стабилизации!)
        System.out.println("CURRENT URL: " + driver.getCurrentUrl());
        System.out.println("TITLE: " + driver.getTitle());
        System.out.println("SORT COUNT: " + driver.findElements(By.id("sort")).size());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
