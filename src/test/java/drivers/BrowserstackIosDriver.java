package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Config;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackIosDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();
        Map<String, Object> browserStackOptions = new HashMap<>();

        browserStackOptions.put("userName", Config.browserStack.user());
        browserStackOptions.put("accessKey", Config.browserStack.key());
        browserStackOptions.put("projectName", Config.browserStack.projectName());
        browserStackOptions.put("buildName", Config.browserStack.buildName());
        browserStackOptions.put("sessionName", "ios_test");

        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:deviceName", Config.browserStack.iosDeviceName());
        caps.setCapability("appium:platformVersion", Config.browserStack.iosOsVersion());
        caps.setCapability("appium:app", Config.browserStack.iosApp());
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("bstack:options", browserStackOptions);

        try {
            return new IOSDriver(new URL(Config.browserStack.hubUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
