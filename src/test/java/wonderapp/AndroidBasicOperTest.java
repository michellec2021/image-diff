package wonderapp;

import base.BaseTest;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ElementUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Michelle.Chen
 */
public class AndroidBasicOperTest extends BaseTest {
    private AndroidDriver<WebElement> driver;
    private String APP_PKG = "com.remarkablefoods.consumerUAT";

    @BeforeClass
    public void setUp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel_3a_API_31_arm64-v8a");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/michelle.chen/apps/WONDER_1.49.0uatRelease.apk");
        // Open the app.
        driver = new AndroidDriver(getServiceUrl(), capabilities);
    }

    @Test
    public void testLogin() throws InterruptedException {
      /*  Proxy proxy=new Proxy();
        proxy.setHttpProxy("localhost:8080");
        WebElement element = driver.findElement(By.id("id"));
        element.sendKeys("1"+ Keys.ENTER);*/

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(MobileBy.AccessibilityId("login_button_on_welcome_page")).click();
        driver.findElement(MobileBy.AccessibilityId("login_with_email_on_login_page")).click();
        //driver.findElement(MobileBy.AccessibilityId("email_input_on_email_login_page")).sendKeys("regresssion001@chancetop.com");
        driver.findElement(MobileBy.AccessibilityId("email_input_on_email_login_page")).sendKeys("regresssion001@chancetop.com");
        Thread.sleep(1000);
        driver.findElement(MobileBy.AccessibilityId("password_input_on_email_login_page")).click();
        Thread.sleep(1000);
        driver.findElement(MobileBy.AccessibilityId("password_input_on_email_login_page")).sendKeys("pwd11111");
       /* Actions actions=new Actions(driver);
        Action keyDown=actions.keyDown(Keys.ENTER).build();
        keyDown.perform();*/

        //driver.findElement(MobileBy.AccessibilityId("login_button_on_email_login_page")).click();
        driver.findElement(MobileBy.AccessibilityId("back_button_on_allergens_page")).click();
      /*  // 卸载app后重装，driver是同一个
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_welcome_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "login_with_email_on_login_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "email_input_on_email_login_page").sendKeys("regresssion001@chancetop.com");
        ElementUtils.getElementByAccessibilityId(driver, "password_input_on_email_login_page").sendKeys("pwd11111");
        ElementUtils.getElementByAccessibilityId(driver, "login_button_on_email_login_page").click();
        ElementUtils.getElementByAccessibilityId(driver, "back_button_on_allergens_page").click();*/

       /* ElementUtils.getElementByAccessibilityId(driver,"Automation Use ONLY restaurant").click();
        Thread.sleep(5000);
        ElementUtils.waitForElementVisibility(driver, "add_to_order_button_on_carousel_image");*/
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            //close为关闭当前tab，quit为关闭当前浏览器所有tab，关闭当前session, 通知selenium grid浏览器可以使用
          /*  driver.close();
            driver.quit();*/

            //Close the app which was provided in the capabilities at session creation and quits the session.
            //driver.closeApp();
            //Terminate the particular application if it is running.
            driver.terminateApp(APP_PKG);
        }
    }
}
