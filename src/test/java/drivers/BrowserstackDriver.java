package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Config;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver implements WebDriverProvider {
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();
        Map<String, Object> browserStackOptions = new HashMap<>();
        browserStackOptions.put("userName", Config.browserStack.user());
        browserStackOptions.put("accessKey", Config.browserStack.key());
        browserStackOptions.put("projectName", Config.browserStack.projectName());
        browserStackOptions.put("buildName", Config.browserStack.buildName());
        browserStackOptions.put("sessionName", "android_search_test");
        browserStackOptions.put("appiumVersion", Config.browserStack.appiumVersion());

        caps.setCapability("platformName", "android");
        caps.setCapability("deviceName", Config.browserStack.androidDeviceName());
        caps.setCapability("platformVersion", Config.browserStack.androidOsVersion());
        caps.setCapability("app", Config.browserStack.androidApp());
        caps.setCapability("automationName", "UIAutomator2");
        caps.setCapability("bstack:options", browserStackOptions);

        try {
            return new RemoteWebDriver(
                    new URL(Config.browserStack.hubUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
