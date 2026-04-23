package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected WaitHelper waitHelper;


    public BasePage(WebDriver driver, WaitHelper waitHelper) {
        this.driver = driver;
        this.waitHelper = waitHelper;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitHelper.waitForVisibility(element);
    }

    protected void click(WebElement element) {
        waitHelper.waitForClickable(element);
        element.click();
    }


    protected void fillFieldWithText(WebElement field, String text) {
        scrollToElement(field);
        waitHelper.waitForVisibility(field);
        field.clear();
        field.sendKeys(text);
    }

}
