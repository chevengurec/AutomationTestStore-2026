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

        // просто читаем всё из конфига
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
        pageWait.until(webDriver ->
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        waitHelper = new WaitHelper(driver, 10);

        pageWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        waitHelper = new WaitHelper(driver, 10);

        waitHelper.shortDelay();
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
