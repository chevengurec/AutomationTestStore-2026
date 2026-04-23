package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


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

