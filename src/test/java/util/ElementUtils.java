package util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Michelle.Chen
 */
public final class ElementUtils {
    public static AndroidElement getElementByAccessibilityId(AndroidDriver<WebElement> driver, String testId) {
        return (AndroidElement) new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(testId)));
    }

    public static void waitForElementVisibility(AndroidDriver<WebElement> driver, String testId,long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
            .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(testId)));
    }

    public static void waitForElementVisibility(AndroidDriver<WebElement> driver, String testId) {
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(testId)));
    }

    public static void assertElementNotVisibility(AndroidDriver<WebElement> driver, String testId) {
        new WebDriverWait(driver, 3)
            .until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.AccessibilityId(testId)));
    }
}
