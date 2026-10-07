package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:local.properties"})
public interface RunConfig extends Config {
    @Key("deviceHost")
    @DefaultValue("browserstack")
    String deviceHost();
}
