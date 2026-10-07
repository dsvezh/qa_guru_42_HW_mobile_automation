package drivers;

import config.Config;
import config.RealConfig;
import io.appium.java_client.android.options.UiAutomator2Options;

public class RealDriver extends LocalDriver {
    @Override
    protected RealConfig config() {
        return Config.real;
    }

    @Override
    protected void selectDevice(UiAutomator2Options options) {
        if (config().deviceUdid().isBlank()) {
            throw new IllegalArgumentException("Для стенда real укажите -Ddevice.udid=<серийный номер из adb devices>");
        }
    }
}
