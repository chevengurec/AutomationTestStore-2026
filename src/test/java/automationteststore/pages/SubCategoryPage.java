package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryPage extends BasePage {

    @FindBy(xpath = "//div[@class='fixed']//a[@class='prdocutname']")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//div[@class='pricetag jumbotron']//div[@class='oneprice' or @class='pricenew']")
    private List<WebElement> listOfPrices;

    @FindBy(xpath = "/div[@class='pricetag jumbotron']//a[@class='productcart']//i[@class='fa fa-cart-plus fa-fw']")
    private List<WebElement> listOfAddingButtons;
    @FindBy(xpath = "//select[@id='sort']")
    private WebElement dropDownSort;

    public SubCategoryPage(WebDriver driver, WaitHelper waitHelper) {

        super(driver, waitHelper);
    }

    public SubCategoryPage selectTypeOfSort(String typeOfSort) {
        waitHelper.waitForVisibility(dropDownSort);
        waitHelper.waitForClickable(dropDownSort);
        Select select = new Select(dropDownSort);
        select.selectByVisibleText(typeOfSort);
        return this;
    }

    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement product : listOfProducts) {
            names.add(product.getText());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement price : listOfPrices) {
            prices.add(Double.parseDouble(price.getText()
                    .replace("$", "")
                    .replace("€", "")));
        }
        return prices;
    }

}



