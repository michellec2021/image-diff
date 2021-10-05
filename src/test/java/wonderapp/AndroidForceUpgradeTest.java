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
public class AndroidForceUpgradeTest extends BaseTest {
    private AndroidDriver<WebElement> driver;
    private String APP_PKG = "com.remarkablefoods.consumerQA";
    private String APP_ACT = "com.wonder.activity.MainActivity";

    private String APP_V1_0_0 = "app/wonder_QA_1.36.0.apk";
//    private String APP_V1_0_1 = "app/wonder_UAT_1.43.0.apk";

    @BeforeClass
    public void setUp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_3a_XL_API_29");
        //capabilities.setCapability("automationName", "UiAutomator2");
        URL resource = Resources.getResource(APP_V1_0_0);
        //为什么每次都会重新安装app？Appium会先尝试先在设备上安装这个app
        capabilities.setCapability("app", StringUtils.substringAfter(resource.getFile(), "/"));
        // Open the app.
        driver = new AndroidDriver(getServiceUrl(), capabilities);
    }

    @Test
    public void testForceUpgrade() {
        driver.startActivity(new Activity(APP_PKG, APP_ACT));
        ElementUtils.waitForElementVisibility(driver, "download button", 15);
        ElementUtils.getElementByAccessibilityId(driver, "download button").click();
        ElementUtils.assertElementNotVisibility(driver, "download button");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp(APP_PKG);
        }
    }
}
