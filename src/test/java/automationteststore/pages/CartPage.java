package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//h1[@class='heading1']//span[@class='maintext']")
    private WebElement cartHeading;

    @FindBy(xpath = "//table[@class='table table-striped table-bordered']//tr/td[@class='align_right'][1]")
    private List<WebElement> unitPrices;
    @FindBy(xpath = "//input[contains(@name, 'quantity[')]")
    private List<WebElement> quantityInputs;

    @FindBy(xpath = "//table/tbody/tr/td[position()=6 and @class='align_right']")
    private List<WebElement> totalPrices;

    @FindBy(xpath = "//td[contains(., 'Sub-Total')]/following-sibling::td/span[contains(@class, 'bold')]")
    private WebElement subTotalPrice;

    @FindBy(xpath = "//a[contains(@class, 'btn-default')][contains(@href, 'remove')]")
    private List<WebElement> listOfRemoveButtons;

    public CartPage(WebDriver driver, WaitHelper waitHelper) {
        super(driver, waitHelper);
    }

     public int getCheapestProductIndex() {
        double minPrice = Double.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < unitPrices.size(); i++) {
            String priceText = unitPrices.get(i).getText().replace("$", "").replace("€", "");
            double price = Double.parseDouble(priceText);
            if (price < minPrice) {
                minPrice = price;
                minIndex = i;
            }
        }
        return minIndex;
    }

    public CartPage doubleQuantity(int rowIndex) {
        int currentQty = Integer.parseInt(quantityInputs.get(rowIndex).getAttribute("value"));
        quantityInputs.get(rowIndex).clear();
        quantityInputs.get(rowIndex).sendKeys(String.valueOf(currentQty * 2));
        return this;
    }

    public double calculateExpectedTotal() {
        double total = 0;
        for (int i = 0; i < totalPrices.size(); i++) {
            String priceText = totalPrices.get(i).getText().replace("$", "").replace("€", "");
            double price = Double.parseDouble(priceText);
            total += price;
        }
    return total;
    }
    public double getActualSubTotal() {
        String totalText = subTotalPrice.getText()
                .replace("$", "")
                .replace("€", "")
                .replace(",", "");
        return Double.parseDouble(totalText);
    }

    public CartPage removeEvenProducts()  {
        if (!listOfRemoveButtons.isEmpty()) {
            for (int i = listOfRemoveButtons.size() - 1; i >= 0; i--) {
                if (i % 2 != 0) {
                    waitHelper.waitForClickable(listOfRemoveButtons.get(i));
                    click(listOfRemoveButtons.get(i));
                }
            }
        }
        return this;
    }
}
