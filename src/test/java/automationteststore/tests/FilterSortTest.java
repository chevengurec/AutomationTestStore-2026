package automationteststore.tests;

import java.io.File;
import automationteststore.pages.CategoryPage;
import automationteststore.pages.HomePage;
import automationteststore.pages.SubCategoryPage;
import automationteststore.utils.SortValidator;
import automationteststore.utils.WaitHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("CASE 1")
public class FilterSortTest extends BaseTest {

    @ParameterizedTest(name = "{0} → {1} → {2}")
    @CsvSource({
            "Makeup, Cheeks, Name A - Z",
            "Makeup, Cheeks, Name Z - A",
            "Makeup, Cheeks, Price Low > High",
            "Makeup, Cheeks, Price High > Low",
            "Skincare, Face, Name A - Z",
            "Skincare, Face, Price Low > High",
            "Books, Paperback, Name A - Z"
    })
    @DisplayName("Проверка сортировки товаров по имени и цене в обоих направлениях и финальной стоимости")
    public void testFilterAndSortInCategories(String category, String subCategory, String sortType) throws IOException {

        SubCategoryPage page = new HomePage(driver, waitHelper)
                .navigateToCategory(category)
                .selectSubCategory(subCategory)
                .selectTypeOfSort(sortType);

        switch (sortType) {
            case "Name A - Z" -> assertTrue(SortValidator.isSorted(page.getProductNames(), Comparator.naturalOrder()));
            case "Name Z - A" -> assertTrue(SortValidator.isSorted(page.getProductNames(), Comparator.reverseOrder()));
            case "Price Low > High" -> assertTrue(SortValidator.isSorted(page.getProductPrices(), Comparator.naturalOrder()));
            case "Price High > Low" -> assertTrue(SortValidator.isSorted(page.getProductPrices(), Comparator.reverseOrder()));
        }

        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)
                .renameTo(new File("screenshots/scr.png"));
    }
}