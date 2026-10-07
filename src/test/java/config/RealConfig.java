package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:local.properties",
        "classpath:real.properties"
})
public interface RealConfig extends LocalConfig {
}
