package automationteststore.utils;

import automationteststore.tests.BaseTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class MyExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()) {
            // Получаем экземпляр тестового класса
            Object testInstance = extensionContext.getRequiredTestInstance();

            // Если тест наследуется от BaseTest
            if (testInstance instanceof BaseTest) {
                BaseTest baseTest = (BaseTest) testInstance;
                WebDriver driver = baseTest.getDriver(); // Нужно добавить геттер в BaseTest
                AllureAttachmentManager.screenshot(driver);
            }
        }
    }
}
