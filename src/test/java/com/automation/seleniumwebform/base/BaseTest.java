package com.automation.seleniumwebform.base;

import com.automation.seleniumwebform.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * Abstract base class for initializing WebDriver based on config.
 * Applies implicit wait and maximizes the browser.
 */
public abstract class BaseTest {
    protected static WebDriver driver;

    public void initializeDriver() {
        if (driver == null) {
            String browser = ConfigReader.get("browser");

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }

            driver.manage().window().maximize();
            int implicitWait = Integer.parseInt(ConfigReader.get("implicit.wait"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    // ✅ New static accessor for WebFormSteps
    public static WebDriver getStaticDriver() {
        return driver;
    }
}