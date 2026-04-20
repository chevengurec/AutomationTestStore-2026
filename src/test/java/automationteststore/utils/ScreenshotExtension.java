package automationteststore.utils;

import automationteststore.config.ConfiguratorManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) {
        WebDriver driver = getDriverFromContext(context);

        if (driver != null) {
            try {

                String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = testName + "_" + timestamp + ".png";


                String screenshotPath = ConfiguratorManager.getConfig().screenshotPathname();
                File targetFile = new File(screenshotPath + "/" + fileName);


                FileUtils.forceMkdirParent(targetFile);


                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshotFile, targetFile);

                System.out.println("Скриншот сохранён: " + targetFile.getAbsolutePath());


                byte[] screenshotBytes = FileUtils.readFileToByteArray(targetFile);
                Allure.addAttachment("Скриншот: " + testName, new ByteArrayInputStream(screenshotBytes));

            } catch (Exception e) {
                System.err.println("Не удалось сделать скриншот: " + e.getMessage());
            }
        }
    }

    private WebDriver getDriverFromContext(ExtensionContext context) {
        try {
            Object testInstance = context.getRequiredTestInstance();
            Field driverField = testInstance.getClass().getSuperclass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testInstance);
        } catch (Exception e) {
            System.err.println("Не удалось получить WebDriver: " + e.getMessage());
            return null;
        }
    }
}
