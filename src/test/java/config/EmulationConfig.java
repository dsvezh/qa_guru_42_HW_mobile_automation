package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:local.properties",
        "classpath:emulation.properties"
})
public interface EmulationConfig extends LocalConfig {
    @Key("device.avd")
    @DefaultValue("")
    String avd();
}
