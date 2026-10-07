package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Config;
import config.LocalConfig;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalDriver implements WebDriverProvider {
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        AndroidDriver driver = new AndroidDriver(getAppiumServerUrl(config()), createOptions());
        driver.setSetting(Setting.ALLOW_INVISIBLE_ELEMENTS, true);
        return driver;
    }

    protected LocalConfig config() {
        return Config.emulation;
    }

    protected UiAutomator2Options createOptions() {
        LocalConfig config = config();
        UiAutomator2Options options = new UiAutomator2Options();

        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(config.deviceName())
                .setAppPackage(config.appPackage())
                .setAppActivity(config.appActivity())
                .setFullReset(true)
                .setDisableWindowAnimation(true)
                .setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(120));
        if (!config.platformVersion().isBlank()) {
            options.setPlatformVersion(config.platformVersion());
        }
        if (!config.deviceUdid().isBlank()) {
            options.setUdid(config.deviceUdid());
        }
        selectDevice(options);
        options.setApp(getAppPath(config));
        return options;
    }

    protected void selectDevice(UiAutomator2Options options) {
    }

    public static URL getAppiumServerUrl() {
        return getAppiumServerUrl(Config.emulation);
    }

    private static URL getAppiumServerUrl(LocalConfig config) {
        try {
            return new URL(config.appiumUrl());
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Некорректный appium.url: " + config.appiumUrl(), e);
        }
    }

    private String getAppPath(LocalConfig config) {
        Path appPath = Path.of(config.appPath()).toAbsolutePath();

        if (Files.notExists(appPath)) {
            try {
                Files.createDirectories(appPath.getParent());
                try (InputStream in = new URL(config.appUrl()).openStream()) {
                    Files.copy(in, appPath);
                }
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return appPath.toAbsolutePath().toString();
    }
}
