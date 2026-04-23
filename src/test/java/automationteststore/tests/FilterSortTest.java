package automationteststore.tests;

import automationteststore.pages.HomePage;
import automationteststore.pages.SubCategoryPage;
import automationteststore.utils.SortValidator;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI Тесты")
@Feature("Сортировка товаров")
@DisplayName("Тестирование сортировки в категориях")
public class FilterSortTest extends BaseTest {

    @Epic("UI Тесты")
    @Feature("Сортировка товаров")
    @Story("TC-01: Сортировка товаров по имени и цене")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка сортировки товаров по имени A-Z")
    @DisplayName("Сортировка по имени A-Z")
    @ParameterizedTest(name = "{0} → {1}")
    @CsvSource({
            "Makeup, Cheeks",
            "Skincare, Face",
            "Books, Paperback"
    })
    public void testSortByNameAsc(String category, String subCategory) {

        SubCategoryPage page = new HomePage(driver, waitHelper)
                .navigateToCategory(category)
                .selectSubCategory(subCategory)
                .selectTypeOfSort("Name A - Z");

        assertTrue(
                SortValidator.isSorted(page.getProductNames(), Comparator.naturalOrder())
        );
    }

    @Epic("UI Тесты")
    @Feature("Сортировка товаров")
    @Story("TC-01: Сортировка товаров по имени и цене")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка сортировки товаров по имени Z-A")
    @DisplayName("Сортировка по имени Z-A")
    @ParameterizedTest(name = "{0} → {1}")
    @CsvSource({
            "Makeup, Cheeks"
    })
    public void testSortByNameDesc(String category, String subCategory) {

        SubCategoryPage page = new HomePage(driver, waitHelper)
                .navigateToCategory(category)
                .selectSubCategory(subCategory)
                .selectTypeOfSort("Name Z - A");

        assertTrue(
                SortValidator.isSorted(page.getProductNames(), Comparator.reverseOrder())
        );
    }

    @Epic("UI Тесты")
    @Feature("Сортировка товаров")
    @Story("TC-01: Сортировка товаров по имени и цене")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка сортировки товаров по цене по возрастанию")
    @DisplayName("Сортировка по цене Low → High")
    @ParameterizedTest(name = "{0} → {1}")
    @CsvSource({
            "Makeup, Cheeks",
            "Skincare, Face"
    })
    public void testSortByPriceAsc(String category, String subCategory) {

        SubCategoryPage page = new HomePage(driver, waitHelper)
                .navigateToCategory(category)
                .selectSubCategory(subCategory)
                .selectTypeOfSort("Price Low > High");

        assertTrue(
                SortValidator.isSorted(page.getProductPrices(), Comparator.naturalOrder())
        );
    }

    @Epic("UI Тесты")
    @Feature("Сортировка товаров")
    @Story("TC-01: Сортировка товаров по имени и цене")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка сортировки товаров по цене по убыванию")
    @DisplayName("Сортировка по цене High → Low")
    @Test
    public void testSortByPriceDesc() {

        SubCategoryPage page = new HomePage(driver, waitHelper)
                .navigateToCategory("Makeup")
                .selectSubCategory("Cheeks")
                .selectTypeOfSort("Price High > Low");

        assertTrue(
                SortValidator.isSorted(page.getProductPrices(), Comparator.reverseOrder())
        );
    }
}