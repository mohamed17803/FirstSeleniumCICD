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

        System.out.println("========================================");
        System.out.println("Requested Browser : " + browser);
        System.out.println("Thread ID         : " + Thread.currentThread().getId());
        System.out.println("OS                : " + System.getProperty("os.name"));
        System.out.println("Java Version      : " + System.getProperty("java.version"));
        System.out.println("========================================");

        WebDriver webDriver;

        switch (browser.trim().toLowerCase()) {
            case "chrome":
                System.out.println("Starting ChromeDriver...");
                webDriver = new ChromeDriver((ChromeOptions) BrowserOptionsFactory.getOptions(browser));
                break;

            case "edge":
                System.out.println("Starting EdgeDriver...");
                webDriver = new EdgeDriver((EdgeOptions) BrowserOptionsFactory.getOptions(browser));
                break;

            case "firefox":
                System.out.println("Starting FirefoxDriver...");
                webDriver = new FirefoxDriver((FirefoxOptions) BrowserOptionsFactory.getOptions(browser));
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        System.out.println("Driver Started Successfully.");
        System.out.println("Browser Version : " + ((org.openqa.selenium.remote.RemoteWebDriver) webDriver).getCapabilities().getBrowserVersion());

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
