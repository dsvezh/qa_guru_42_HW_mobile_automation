package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.Config;

import java.util.Locale;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static Class<? extends WebDriverProvider> getDriverClass() {
        // Сохраняем прежний способ локального запуска; deviceHost имеет приоритет.
        String host = Config.run.deviceHost();
        if (System.getProperty("deviceHost") == null && "local".equalsIgnoreCase(System.getProperty("driver"))) {
            host = "emulation";
        }
        return getDriverClass(host);
    }

    public static Class<? extends WebDriverProvider> getDriverClass(String deviceHost) {
        switch (deviceHost.toLowerCase(Locale.ROOT)) {
            case "browserstack":
                return BrowserstackDriver.class;
            case "emulation":
                return EmulationDriver.class;
            case "real":
                return RealDriver.class;
            default:
                throw new IllegalArgumentException("Неизвестный deviceHost: " + deviceHost
                        + ". Допустимые значения: browserstack, emulation, real");
        }
    }
}
