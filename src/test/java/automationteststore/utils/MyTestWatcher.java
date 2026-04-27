package automationteststore.utils;

import automationteststore.tests.BaseTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class MyTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        // Получаем экземпляр тестового класса
        Object testInstance = context.getRequiredTestInstance();

        // Проверяем, что тест наследуется от BaseTest
        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;
            WebDriver driver = baseTest.getDriver(); // Нужен геттер в BaseTest
            AllureAttachmentManager.screenshot(driver);
        }

        TestWatcher.super.testFailed(context, cause);
    }
}
