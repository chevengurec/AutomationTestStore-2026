package automationteststore.pages;

import automationteststore.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class CategoryPage extends BasePage {

    @FindBy(xpath = "//li[@class='col-md-2 col-sm-2 col-xs-6 align_center']")
    protected List<WebElement> subCategories;
    public CategoryPage(WebDriver driver, WaitHelper waitHelper) {
        super(driver, waitHelper);
    }

    public SubCategoryPage selectSubCategory(String subCategoryName) {

        for (WebElement subCategory : subCategories) {
            if (subCategory.getText().trim().equalsIgnoreCase(subCategoryName)) {
                subCategory.click();
                break;
            }
        }
        return new SubCategoryPage(driver, waitHelper);
    }
}
