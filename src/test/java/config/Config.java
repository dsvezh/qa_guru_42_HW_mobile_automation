package config;

import org.aeonbits.owner.ConfigFactory;

public final class Config {

    private Config() {
    }

    public static final BrowserStackConfig browserStack =
            ConfigFactory.create(
                    BrowserStackConfig.class,
                    System.getProperties()
            );
}
