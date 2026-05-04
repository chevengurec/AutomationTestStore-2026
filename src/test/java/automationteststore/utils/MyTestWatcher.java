package automationteststore.utils;

import automationteststore.tests.BaseTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class MyTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                // Оставляем ваш Allure (для отчетов)
                AllureAttachmentManager.screenshot(driver);

                // ДОБАВЛЯЕМ сохранение в файл для GitHub
                try {
                    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    java.nio.file.Path folder = java.nio.file.Paths.get("target/screenshots/");
                    java.nio.file.Files.createDirectories(folder); // создаем папку, если нет
                    java.nio.file.Files.copy(scrFile.toPath(), folder.resolve(context.getDisplayName() + ".png"));
                } catch (Exception e) {
                    System.out.println("Не удалось сохранить скриншот: " + e.getMessage());
                }
            }
        }
    }
}
