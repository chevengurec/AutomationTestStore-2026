package automationteststore.pages;

import automationteststore.utils.RandomPicker;
import automationteststore.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {

    @FindBy(xpath = "//li//a[starts-with(@href, 'https://automationteststore.com/index.php?rt=product/category&path=') and not(contains(substring-after(@href, 'path='), '_')) and not(contains(substring-after(@href, 'path='), '&'))]")
    private List<WebElement> categories;
    @FindBy(xpath="//input[@id='filter_keyword']")
    private WebElement searchInput;
    @FindBy(xpath="//div[@class='button-in-search']")
    private WebElement searchButton;
    @FindBy(xpath="//a[@class='dropdown-toggle' and contains(@href, 'checkout/cart')]")
    private WebElement cartLink;

    @FindBy(xpath = "//div[@class='fixed']//a[@class='prdocutname']")
    private List<WebElement> listOfProducts;

    @FindBy(xpath = "//div[@class='pricetag jumbotron']//a[@class='productcart'][@href='#']")
    private List<WebElement> listOfAddingButtons;



    public HomePage(WebDriver driver, WaitHelper waitHelper) {

        super(driver, waitHelper);
    }

    public CategoryPage navigateToCategory(String categoryName) {
        for (WebElement categorie : categories) {
            if (categorie.getText().trim().equalsIgnoreCase(categoryName)) {
                categorie.click();
                break;
            }
        }
        return new CategoryPage(driver, waitHelper);
    }

      public SearchPage search(String keyword) {
        fillFieldWithText(searchInput, keyword);
        click(searchButton);
        return new SearchPage(driver, waitHelper);
    }


    public List<WebElement> pickRandomButtons(int count) {
        return RandomPicker.getRandomElements(listOfAddingButtons, count);
    }

    public HomePage addSeveralItemsToCartInSeveralQuantity(int numberOfItems)  {

        List<WebElement> list =  pickRandomButtons(numberOfItems);
        Random random = new Random();
        for (WebElement button : list) {
            int count = random.nextInt(10) + 1;
            while (count > 0) {
                waitHelper.waitForClickable(button);
                button.click();
                count--;
                         }
        }
        return this;
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver, waitHelper);
    }

}


