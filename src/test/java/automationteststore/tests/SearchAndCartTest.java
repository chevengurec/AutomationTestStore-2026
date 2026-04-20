package automationteststore.tests;

import automationteststore.pages.CartPage;
import automationteststore.pages.HomePage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

@DisplayName("CASE 2")
public class SearchAndCartTest extends BaseTest {

    @ParameterizedTest(name = "Поиск: {0}, индексы: {1} и {2}")
    @CsvSource({
            "shirt, 1, 2"
    })
    @DisplayName("Shirt: проверка финальной стоимости")
    public void testSearchAndCartVerification(String searchKeyword, int index2, int index3) {
        Random random = new Random();

        int quantity2 = random.nextInt(5) + 1;
        int quantity3 = random.nextInt(5) + 1;


        new HomePage(driver, waitHelper)
                .search(searchKeyword)
                .selectTypeOfSort("Name A - Z")
                .getProductByIndex(index2)
                .setQuantity(quantity2)
                .addToCart();

        driver.navigate().back();
        driver.navigate().back();


        CartPage cartPage = new HomePage(driver, waitHelper)
                .search(searchKeyword)
                .selectTypeOfSort("Name A - Z")
                .getProductByIndex(index3)
                .setQuantity(quantity3)
                .addToCart();

        int cheapestIndex = cartPage.getCheapestProductIndex();
        cartPage.doubleQuantity(cheapestIndex);

        double expectedTotal = cartPage.calculateExpectedTotal();
        double actualTotal = cartPage.getActualSubTotal();

        Assertions.assertEquals(expectedTotal, actualTotal, 0.01,
                "Не та стоимость: " + searchKeyword);
    }

}