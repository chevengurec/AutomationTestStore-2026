package automationteststore.utils;

import automationteststore.tests.BaseTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MyTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        System.out.println(">>> TEST FAILED: " + context.getDisplayName());

        Object testInstance = context.getRequiredTestInstance();

        if (!(testInstance instanceof BaseTest)) {
            System.out.println("Test is not instance of BaseTest");
            return;
        }

        WebDriver driver = ((BaseTest) testInstance).getDriver();

        if (driver == null) {
            System.out.println("Driver is NULL!");
            return;
        }

        try {
            // 📸 Allure (оставляем)
            AllureAttachmentManager.screenshot(driver);

            // 📁 Файл для CI
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path folder = Paths.get("target/screenshots/");
            Files.createDirectories(folder);

            String testName = context.getRequiredTestMethod().getName();
            String safeName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");

            Path targetPath = folder.resolve(safeName + ".png");

            Files.copy(
                    scrFile.toPath(),
                    targetPath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("Screenshot saved: " + targetPath.toAbsolutePath());

        } catch (Exception e) {
            System.out.println("❌ Screenshot failed:");
            e.printStackTrace();
        }
    }
}