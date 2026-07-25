package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void init(String browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser parameter cannot be null");
        }

        long threadId = Thread.currentThread().threadId();

        System.out.println("========================================");
        System.out.println("Requested Browser : " + browser);
        System.out.println("Thread ID         : " + threadId);
        System.out.println("OS                : " + System.getProperty("os.name"));
        System.out.println("Java Version      : " + System.getProperty("java.version"));
        System.out.println("========================================");

        WebDriver webDriver;

        switch (browser.trim().toLowerCase()) {
            case "chrome":
                System.out.println("Starting ChromeDriver...");
                WebDriverManager.chromedriver().setup(); // auto-manage Chrome driver
                webDriver = new ChromeDriver((ChromeOptions) BrowserOptionsFactory.getOptions(browser));
                break;

            case "edge":
                System.out.println("Starting EdgeDriver...");
                webDriver = startEdgeWithRetry(3, 2000); // retry logic for Edge
                break;

            case "firefox":
                System.out.println("Starting FirefoxDriver...");
                WebDriverManager.firefoxdriver().setup(); // auto-manage Firefox driver
                webDriver = new FirefoxDriver((FirefoxOptions) BrowserOptionsFactory.getOptions(browser));
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        System.out.println("Driver Started Successfully.");
        System.out.println("Browser Version : " + ((RemoteWebDriver) webDriver).getCapabilities().getBrowserVersion());

        driver.set(webDriver);
    }

    private static WebDriver startEdgeWithRetry(int maxRetries, long waitMillis) {
        int attempt = 0;
        while (true) {
            try {
                WebDriverManager.edgedriver().setup(); // auto-manage Edge driver
                return new EdgeDriver((EdgeOptions) BrowserOptionsFactory.getOptions("edge"));
            } catch (Exception e) {
                attempt++;
                System.err.println("EdgeDriver start failed (attempt " + attempt + "): " + e.getMessage());
                if (attempt >= maxRetries) {
                    throw new RuntimeException("EdgeDriver could not be started after " + maxRetries + " attempts", e);
                }
                try {
                    Thread.sleep(waitMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
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
