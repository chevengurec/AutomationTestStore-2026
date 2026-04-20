package automationteststore.tests;

import automationteststore.pages.CartPage;
import automationteststore.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("CASE 3")
public class CartManagmentTest extends BaseTest {

    @ParameterizedTest(name = "Добавление {0} товаров и удаление четных")
    @CsvSource({
            "5"
    })
    @DisplayName("Добавление товаров, удаление четных из корзины и проверка стоимости")
    public void checkFinalCostWithRandomChoiceFromHomePage(int quantity) {

       HomePage homePage = new HomePage(driver, waitHelper);

       CartPage cartPage = new CartPage(driver, waitHelper);

       double expectedTotal = homePage
                .addSeveralItemsToCartInSeveralQuantity(quantity)
                .openCart()
                .removeEvenProducts()
                .calculateExpectedTotal();

       double actualTotal = cartPage.getActualSubTotal();

       assertEquals(expectedTotal, actualTotal, 0.01,
                "Итоговая сумма корзины не соответствует расчётной");

    }


}
