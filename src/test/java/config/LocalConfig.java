package config;

import org.aeonbits.owner.Config;

public interface LocalConfig extends Config {
    @Key("appium.url")
    String appiumUrl();

    @Key("device.name")
    String deviceName();

    @Key("device.platformVersion")
    @DefaultValue("")
    String platformVersion();

    @Key("device.udid")
    @DefaultValue("")
    String deviceUdid();

    @Key("app.path")
    String appPath();

    @Key("app.url")
    String appUrl();

    @Key("app.package")
    String appPackage();

    @Key("app.activity")
    String appActivity();
}
