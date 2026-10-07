package config;

import org.aeonbits.owner.ConfigFactory;

public final class Config {

    private Config() {
    }

    public static final RunConfig run = ConfigFactory.create(RunConfig.class, System.getProperties());
    public static final EmulationConfig emulation = ConfigFactory.create(EmulationConfig.class, System.getProperties());
    public static final RealConfig real = ConfigFactory.create(RealConfig.class, System.getProperties());

    public static final BrowserStackConfig browserStack =
            ConfigFactory.create(
                    BrowserStackConfig.class,
                    System.getProperties()
            );
}
