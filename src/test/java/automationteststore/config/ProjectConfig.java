package automationteststore.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "system:env",
        "file:~/config.properties",
        "classpath:config.properties"
})
public interface ProjectConfig extends Config {

    @Key("homepage.url")
    String homepageUrl();

    @Key("cart.url")
    String cartUrl();

    @Key("apparel.url")
    String apparelUrl();

    @Key("shoes.url")
    String shoesUrl();

    @Key("screenshot.pathname")
    String screenshotPathname();
}
