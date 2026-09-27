package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:local.properties",
        "classpath:config.properties"
})
public interface BrowserStackConfig extends Config {

    @Key("browserstack.user")
    String user();

    @Key("browserstack.key")
    String key();

    @Key("browserstack.hubUrl")
    String hubUrl();

    @Key("android.deviceName")
    String androidDeviceName();

    @Key("android.osVersion")
    String androidOsVersion();

    @Key("android.app")
    String androidApp();

    @Key("ios.deviceName")
    String iosDeviceName();

    @Key("ios.osVersion")
    String iosOsVersion();

    @Key("ios.app")
    String iosApp();

    @Key("appium.version")
    String appiumVersion();

    @Key("project.name")
    String projectName();

    @Key("build.name")
    String buildName();
}
