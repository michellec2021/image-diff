package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    private static AppiumDriverLocalService service;

//    @BeforeSuite

    /**
     * 本地用nodejs启动appium server
     */
    public void globalSetup() throws IOException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    //    @AfterSuite
    public void globalTearDown() {
        if (service != null) {
            service.stop();
        }
    }

    public URL getServiceUrl() {
        try {
            return new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new IllegalStateException("service url error");
        }
    }

}
