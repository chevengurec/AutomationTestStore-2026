package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends  BasePage {

    @FindBy(id = "product_quantity")
    private WebElement quantityInput;

    @FindBy(xpath = "//a[@class='cart']")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver, WaitHelper waitHelper) {
        super(driver, waitHelper);
    }

    public ProductPage setQuantity(int quantity) {
        waitHelper.waitForVisibility(quantityInput);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        return this;
    }

    public CartPage addToCart() {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        waitHelper.waitForClickable(addToCartButton);
        click(addToCartButton);
        return new CartPage(driver, waitHelper);
    }
}
