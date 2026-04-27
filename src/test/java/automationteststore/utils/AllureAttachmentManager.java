package automationteststore.utils;

import automationteststore.tests.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureAttachmentManager {

    @Attachment(value="Screenshot", type="image/png")
    public static byte[] screenshot(WebDriver driver) {  // ← принимает driver как параметр
        if (driver != null) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return new byte[0];
    }
}
