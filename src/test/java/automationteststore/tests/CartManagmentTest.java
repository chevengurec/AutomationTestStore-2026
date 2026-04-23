package automationteststore.tests;

import automationteststore.pages.CartPage;
import automationteststore.pages.HomePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Epic("UI Тесты")
@Feature("Управление корзиной")
@DisplayName("Тестирование корзины")
public class CartManagmentTest extends BaseTest {

    @ParameterizedTest(name = "Добавление {0} товаров")
    @CsvSource({"5"})
    @Story("TC-03: Добавление случайных товаров и удаление чётных")
    @Description("Проверка корзины при добавлении случайного количества случайных товаров и последующем удалении чётных позиций")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Добавление и удаление товаров из корзины")
    public void checkFinalCostWithRandomChoiceFromHomePage(int quantity) {

        HomePage homePage = new HomePage(driver, waitHelper);

        CartPage cartPage = new CartPage(driver, waitHelper);

        double expectedTotal = homePage.addSeveralItemsToCartInSeveralQuantity(quantity).openCart().removeEvenProducts().calculateExpectedTotal();

        double actualTotal = cartPage.getActualSubTotal();

        assertEquals(expectedTotal, actualTotal, 0.01, "Итоговая сумма корзины не соответствует расчётной");

    }


}
