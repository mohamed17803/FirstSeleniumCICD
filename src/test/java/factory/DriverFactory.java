package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void init(String browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser parameter cannot be null");
        }

        WebDriver webDriver;
        switch (browser.trim().toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver((ChromeOptions) BrowserOptionsFactory.getOptions(browser));
                break;
            case "edge":
                webDriver = new EdgeDriver((EdgeOptions) BrowserOptionsFactory.getOptions(browser));
                break;
            case "firefox":
                webDriver = new FirefoxDriver((FirefoxOptions) BrowserOptionsFactory.getOptions(browser));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.set(webDriver);
    }

    public static WebDriver get() {
        return driver.get();
    }

    public static void quit() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}
