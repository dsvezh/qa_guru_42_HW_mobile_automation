package drivers;

import config.Config;
import config.EmulationConfig;
import io.appium.java_client.android.options.UiAutomator2Options;

public class EmulationDriver extends LocalDriver {
    @Override
    protected EmulationConfig config() {
        return Config.emulation;
    }

    @Override
    protected void selectDevice(UiAutomator2Options options) {
        if (config().deviceUdid().isBlank() && !config().avd().isBlank()) {
            options.setAvd(config().avd());
        }
    }
}
