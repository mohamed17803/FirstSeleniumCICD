package factory;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptionsFactory {

    public static MutableCapabilities getOptions(String browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser parameter cannot be null");
        }

        switch (browser.trim().toLowerCase()) {
            case "chrome":
                return getChromeOptions();
            case "edge":
                return getEdgeOptions();
            case "firefox":
                return getFirefoxOptions();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--proxy-server=direct://");
        options.addArguments("--proxy-bypass-list=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        return options;
    }
    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");

        return options;
    }
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        return options;
    }
}
