package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:src/main/java/config/${JAVA_ENV}.properties",
        "file:src/main/java/config/default.properties",
        "system:properties",
        "system:env"
})

public interface AppConfig extends Config {

    @DefaultValue("development")
    @Key("JAVA_ENV")

    String getEnvironment();
    @DefaultValue("https://win:w1nPorta2@affiliates.priotix.xyz")

    @Key("BASE_URL")
    String getBaseUrl();

    @Key("API_NEWS_HOST")
    String getApiNewsServiceUrl();

    @DefaultValue("https://api-admins.priotix.xyz")
    @Key("API_AUTH_URL")
    String getAuthUrl();

    @DefaultValue("https://api-files.priotix.xyz")
    @Key("API_FILES_HOST")
    String getApiFilesServiceUrl();

//    @DefaultValue("http://selenoid:4444/wd/hub/")
    @Key("SELENOID_HUB_URL")
    String getSelenoidHubUrl();

    @DefaultValue("https://api-reviews.priotix.xyz")
    @Key("API_REVIEWS_HOST")
    String getApiReviewsService();

    @Key("browser.name")
    String getBrowserName();

    @Key("chrome.browser.version")
    String getChromeBrowserVersion();
}