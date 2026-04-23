package automationteststore.config;

import org.aeonbits.owner.ConfigCache;

public class ConfiguratorManager {
    private static ProjectConfig config;

    public static ProjectConfig getConfig() {
        if (config == null) {
            config = ConfigCache.getOrCreate(ProjectConfig.class,
                    System.getenv(),
                    System.getProperties()
            );
        }
        return config;
    }
}
