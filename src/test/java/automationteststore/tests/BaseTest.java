package automationteststore.tests;

import automationteststore.config.ConfiguratorManager;
import automationteststore.config.ProjectConfig;
import automationteststore.utils.WaitHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WaitHelper waitHelper;

    @BeforeEach
    public void setUp() {
        ProjectConfig config = ConfiguratorManager.getConfig();
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            // Убираем maximize() для headless
            driver = new ChromeDriver(options);
        } else {
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                System.getenv("CI") != null ? 30 : 10
        ));

        driver.get(config.homepageUrl());
        waitHelper = new WaitHelper(driver, 10);
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (driver != null) {
            driver.quit();
        }
    }
}
