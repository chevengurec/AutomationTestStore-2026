package automationteststore.tests;

import automationteststore.pages.CartPage;
import automationteststore.pages.HomePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

@Epic("UI Тесты")
@Feature("Поиск и корзина")
@DisplayName("Тестирование поиска и корзины")
public class SearchAndCartTest extends BaseTest {

    @ParameterizedTest(name = "Поиск: {0}")
    @CsvSource({"shirt, 1, 2"})
    @Story("TC-02: Поиск и добавление товаров в корзину")
    @Description("Проверка поиска, добавления товаров в корзину и финальной суммы")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск товаров и проверка корзины")
    public void testSearchAndCartVerification(String searchKeyword, int index2, int index3) {
        Random random = new Random();

        int quantity2 = random.nextInt(5) + 1;
        int quantity3 = random.nextInt(5) + 1;


        new HomePage(driver, waitHelper).search(searchKeyword).selectTypeOfSort("Name A - Z").getProductByIndex(index2).setQuantity(quantity2).addToCart();

        driver.navigate().back();
        driver.navigate().back();


        CartPage cartPage = new HomePage(driver, waitHelper).search(searchKeyword).selectTypeOfSort("Name A - Z").getProductByIndex(index3).setQuantity(quantity3).addToCart();

        int cheapestIndex = cartPage.getCheapestProductIndex();
        cartPage.doubleQuantity(cheapestIndex);

        double expectedTotal = cartPage.calculateExpectedTotal();
        double actualTotal = cartPage.getActualSubTotal();

        Assertions.assertEquals(expectedTotal, actualTotal, 0.01, "Не та стоимость: " + searchKeyword);
    }

}