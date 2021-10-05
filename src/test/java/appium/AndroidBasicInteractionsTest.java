package appium;

import base.BaseTest;
import com.google.common.io.Resources;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ElementUtils;
import util.StopWatch;

import java.io.IOException;
import java.net.URL;

public class AndroidBasicInteractionsTest extends BaseTest {
    private AndroidDriver<WebElement> driver;
    private final String SEARCH_ACTIVITY = "com.wonder.activity.MainActivity";
    private final String ALERT_DIALOG_ACTIVITY = ".app.AlertDialogSamples";
    private final String PACKAGE = "com.remarkablefoods.consumerUAT";

    @BeforeClass
    public void setUp() throws IOException {
        URL resource = Resources.getResource("app/wonder_UAT_1.41.0.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        /*
        'deviceName' capability only affects device selection if you run the test in a cloud
        environment. It has no effect if the test is executed on a local machine.
        */
        // capabilities.setCapability("deviceName", "Android Emulator");

        /*
        It makes sense to set device udid if there are multiple devices/emulators
        connected to the local machine. Run `adb devices -l` to check which devices
        are online and what are their identifiers.
        If only one device is connected then this capability might be omitted
        */
        // capabilities.setCapability("udid", "ABCD123456789");

        /*
        It is recommended to set a full path to the app being tested.
        Appium for Android supports application .apk and .apks bundles.
        If this capability is not set then your test starts on Dashboard view.
        It is also possible to provide an URL where the app is located.
        */
        capabilities.setCapability("app", StringUtils.substringAfter(resource.getFile(), "/"));
        capabilities.setCapability("deviceName", "Pixel_3a_XL_API_29");

        /*
        By default Appium tries to autodetect the main application activity,
        but if you app's very first activity is not the main one then
        it is necessary to provide its name explicitly. Check
        https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/android/activity-startup.md
        for more details on activities selection topic.
        */
        // capabilities.setCapability("appActivity", "com.myapp.SplashActivity"));
//         capabilities.setCapability("appPackage", PACKAGE);
        // capabilities.setCapability("appWaitActivity", "com.myapp.MainActivity"));

        /*
        Appium for Android supports multiple automation backends, where
        each of them has its own pros and cons. The default one is UIAutomator2.
        */
        // capabilities.setCapability("automationName", "UIAutomator2");
        // capabilities.setCapability("automationName", "Espresso");

        /*
        There are much more capabilities and driver settings, that allow
        you to customize and tune your test to achieve the best automation
        experience. Read http://appium.io/docs/en/writing-running-appium/caps/
        and http://appium.io/docs/en/advanced-concepts/settings/
        for more details.

        Feel free to visit our forum at https://discuss.appium.io/
        if you have more questions.
        */

        driver = new AndroidDriver(getServiceUrl(), capabilities);
//        driver.removeApp(PACKAGE);
//        driver.installApp(StringUtils.substringAfter(resource.getFile(), "/"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.remarkablefoods.consumerUAT");

        }
    }

    @Test
    public void testUpgrade() {
        PageFactory.initElements(driver,null);
        driver.findElementByAccessibilityId("save_button_on_allergens_page").getAttribute("clickable");
        URL resource = Resources.getResource("app/wonder_UAT_1.43.0.apk");
        driver.installApp(StringUtils.substringAfter(resource.getFile(), "/"));
    }

    @Test
    public void testClickElement1() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        driver.startActivity(new Activity(PACKAGE, SEARCH_ACTIVITY));
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_welcome_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "login_with_email_on_login_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "email_input_on_email_login_page").sendKeys("regresssion003@chancetop.com");
        ElementUtils.getElementByAccessibilityId(driver, "password_input_on_email_login_page").sendKeys("pwd11111");
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_email_login_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "back_button_on_allergens_page").click();

        ElementUtils.waitForElementVisibility(driver, "preference button");
//        driver.resetApp();
//        ApplicationState applicationState = driver.queryAppState("com.remarkablefoods.consumerUAT");
        System.out.println("stopWatch elapsed = " + stopWatch.elapsed());
    }

    @Test
    public void testClickElement2() throws InterruptedException {
        driver.startActivity(new Activity(PACKAGE, SEARCH_ACTIVITY));
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_welcome_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "login_with_email_on_login_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "email_input_on_email_login_page").sendKeys("regresssion003@chancetop.com");
        ElementUtils.getElementByAccessibilityId(driver, "password_input_on_email_login_page").sendKeys("pwd11111");
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_email_login_page").click();
        Thread.sleep(10000);
//        driver.resetApp();
    }

    //    @Test
    public void testOpensAlert() {
        // Open the "Alert Dialog" activity of the android app
        driver.startActivity(new Activity(PACKAGE, ALERT_DIALOG_ACTIVITY));

        // Click button that opens a dialog
        AndroidElement openDialogButton = (AndroidElement) driver.findElementById("io.appium.android.apis:id/two_buttons");
        openDialogButton.click();

        // Check that the dialog is there
        AndroidElement alertElement = (AndroidElement) driver.findElementById("android:id/alertTitle");
        String alertText = alertElement.getText();
        Assert.assertEquals(alertText, "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.");
        AndroidElement closeDialogButton = (AndroidElement) driver.findElementById("android:id/button1");

        // Close the dialog
        closeDialogButton.click();
    }
}