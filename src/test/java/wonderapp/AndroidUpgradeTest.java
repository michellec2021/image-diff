package wonderapp;

import base.BaseTest;
import com.google.common.io.Resources;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ElementUtils;

import java.io.IOException;
import java.net.URL;

/**
 * @author Michelle.Chen
 */
public class AndroidUpgradeTest extends BaseTest {
    private AndroidDriver<WebElement> driver;
    private String APP_PKG = "com.remarkablefoods.consumerUAT";
    private String APP_ACT = "com.wonder.activity.MainActivity";

    private String APP_V1_0_0 = "app/wonder_UAT_1.43.0.apk";
    private String APP_V1_0_1 = "app/wonder_UAT_1.44.0.apk";

    @BeforeClass
    public void setUp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_3a_XL_API_29");
        //capabilities.setCapability("automationName", "UiAutomator2");
        URL resource = Resources.getResource(APP_V1_0_1);
        //为什么每次都会重新安装app？Appium会先尝试先在设备上安装这个app
        capabilities.setCapability("app", StringUtils.substringAfter(resource.getFile(), "/"));
        // Open the app.
        driver = new AndroidDriver(getServiceUrl(), capabilities);
    }

    @Test
    public void testLoginStateAfterUpgrade() throws InterruptedException {
        driver.removeApp(APP_PKG);
        String appUpgradeVersion = StringUtils.substringAfter(Resources.getResource(APP_V1_0_0).getFile(), "/");
        driver.installApp(appUpgradeVersion);
//        Thread.sleep(10000000);
//        driver.launchApp();
//        Thread.sleep(1000000);
//
//        driver.startActivity(new Activity(APP_PKG, APP_ACT));
        driver.launchApp();
        // 卸载app后重装，driver是同一个
        Thread.sleep(10000);
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_welcome_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "login_with_email_on_login_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "email_input_on_email_login_page").sendKeys("regresssion003@chancetop.com");
        ElementUtils.getElementByAccessibilityId(driver, "password_input_on_email_login_page").sendKeys("pwd11111");
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_email_login_page").click();
        ElementUtils.waitForElementVisibility(driver, "preference button");

        driver.terminateApp(APP_PKG);
        driver.installApp(appUpgradeVersion);
        Activity activity = new Activity(APP_PKG, APP_ACT);
        driver.startActivity(activity);
        ElementUtils.waitForElementVisibility(driver, "preference button");
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
//        if (driver != null) {
//            driver.terminateApp(APP_PKG);
//        }
    }
}
