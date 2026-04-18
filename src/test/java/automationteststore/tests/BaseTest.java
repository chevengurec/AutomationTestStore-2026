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

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WaitHelper waitHelper;


    @BeforeEach
    public void setUp(TestInfo testInfo) {

        ProjectConfig config = ConfiguratorManager.getConfig(); // добавляем

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
