package helper;

import config.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Factory {

    public static WebDriver createWebDriver() throws MalformedURLException, InterruptedException {
        try {
            final WebDriver driver;
            AppConfig appConfig = ConfigFactory.create(AppConfig.class);
            if (appConfig.getEnvironment().equals("development")) {
                Log.warn("Run on 'development'");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else {
                Log.warn("ENVIRONMENT" + appConfig.getEnvironment());
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", appConfig.getBrowserName());
                capabilities.setCapability("browserVersion", appConfig.getChromeBrowserVersion());
                capabilities.setCapability("selenoid:options", Map.of("enableVNC", true, "enableVideo", true));
                Log.warn("Browser Name" + appConfig.getBrowserName());
                Log.warn("Browser Version" + appConfig.getChromeBrowserVersion());
                Log.warn("Selenoid URL" + appConfig.getSelenoidHubUrl());
                driver = new RemoteWebDriver(new URL("http://selenoid:4444/wd/hub/"), capabilities);
            }
            return driver;
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}