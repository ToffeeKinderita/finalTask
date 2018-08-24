package helper;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


public class WebDriverSingleton {
    private static WebDriverSingleton instance = null;
    private WebDriver driver;
    private static final Properties GLOB_PROPERTIES;
    private static final String STRATEGY;
    private static final String BROWSER;
    private static final String HUB_URL;


    static {
        GLOB_PROPERTIES = new Properties();
        InputStream inputStream = WebDriverSingleton.class.getResourceAsStream("/finalTask.properties");
        try {
            GLOB_PROPERTIES.load(inputStream);
        } catch (IOException e) {
            System.out.println("Can't load properties" + e);
        }
        STRATEGY = StringUtils.isNotEmpty(System.getProperty("strategy")) ? System.getProperty("strategy") : get("Strategy");
        BROWSER = StringUtils.isNotEmpty(System.getProperty("browser")) ? System.getProperty("browser") : get("BrowserName");
        HUB_URL = StringUtils.isNotEmpty(System.getProperty("huburl")) ? System.getProperty("huburl") : get("GridUrl");
    }

    private WebDriverSingleton() {
    }

    public WebDriver openBrowser() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(BROWSER);
        switch (STRATEGY) {
            case "local":
                if ("firefox".equals(BROWSER)) {
                    driver = new FirefoxDriver();
                    System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                } else if ("chrome".equals(BROWSER)) {
                    driver = new ChromeDriver();
                }
            case "grid":
                driver = new RemoteWebDriver(new URL(HUB_URL), caps);
            case "sauceLabs":
                driver = new RemoteWebDriver(new URL(HUB_URL), caps);
        }
        return driver;
    }

    private static String get(String propertyName) {
        return GLOB_PROPERTIES.getProperty(propertyName);
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }
}
