package automationteststore.pages;

import automationteststore.utils.RandomPicker;
import automationteststore.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static org.openqa.selenium.support.PageFactory.initElements;

public class SearchPage extends BasePage {
    @FindBy(xpath = "//div[@class='fixed']//a[@class='prdocutname']")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//div[@class='pricetag jumbotron']//a[@class='productcart']")
    private List<WebElement> listOfAddingButtons;

    @FindBy(xpath = "//select[@id='sort']")
    private WebElement dropDownSort;

    public SearchPage(WebDriver driver, WaitHelper waitHelper) {
        super(driver, waitHelper);
    }

    public List<WebElement> pickRandomProducts(int count) {
        return RandomPicker.getRandomElements(listOfProducts, count);
    }

    public List<WebElement> pickRandomButtons(int count) {
        return RandomPicker.getRandomElements(listOfAddingButtons, count);
    }

    public SearchPage selectTypeOfSort(String typeOfSort) {
        waitHelper.waitForVisibility(dropDownSort);
        waitHelper.waitForClickable(dropDownSort);
        Select select = new Select(dropDownSort);
        select.selectByVisibleText(typeOfSort);
        return this;
    }

    public ProductPage getProductByIndex(int index) {
        WebElement product = listOfProducts.get(index);
        click(product);
        return new ProductPage(driver, waitHelper);
    }

}

