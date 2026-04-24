package automationteststore.tests;

import automationteststore.config.ConfiguratorManager;
import automationteststore.config.ProjectConfig;
import automationteststore.utils.ScreenshotExtension;
import automationteststore.utils.WaitHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

        if (System.getenv("CI") != null) {
            String[] args = config.ciArgs().split(",");
            for (String arg : args) {
                options.addArguments(arg.trim());
            }
            driver = new ChromeDriver(options);
        } else {
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(config.homepageUrl());
        waitHelper = new WaitHelper(driver, 10);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
