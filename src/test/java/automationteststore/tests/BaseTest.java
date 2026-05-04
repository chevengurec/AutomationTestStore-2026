package automationteststore.tests;

import automationteststore.config.ConfiguratorManager;
import automationteststore.config.ProjectConfig;
import automationteststore.utils.MyTestWatcher;
import automationteststore.utils.WaitHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ExtendWith(MyTestWatcher.class)
public class BaseTest {

    protected WebDriver driver;
    protected WaitHelper waitHelper;

    public WebDriver getDriver() {
        return driver;
    }


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

        waitHelper = new WaitHelper(driver, 10);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
