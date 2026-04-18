package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

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

    protected void clickRandomTimes(WebElement element, int min, int max) {
        for (int i = 0, n = new Random().nextInt(max - min + 1) + min; i < n; i++) click(element);
    }

    protected void selectFromDropdown(WebElement dropdownElement, String visibleText) {
        scrollToElement(dropdownElement);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(visibleText);
    }

    protected void fillFieldWithText(WebElement field, String text) {
        scrollToElement(field);
        waitHelper.waitForVisibility(field);
        field.clear();
        field.sendKeys(text);
    }

    void refresh() {
       PageFactory.initElements(driver, this);
    }
}
