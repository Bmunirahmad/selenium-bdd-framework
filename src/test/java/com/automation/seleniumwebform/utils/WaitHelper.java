package com.automation.seleniumwebform.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitHelper {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;

        int timeoutInSeconds;
        try {
            String timeoutStr = ConfigReader.get("explicit.wait");
            timeoutInSeconds = Integer.parseInt(timeoutStr);
        } catch (Exception e) {
            System.out.println("WARNING: 'explicit.wait' not set in testdata.properties. Using default 10s.");
            timeoutInSeconds = 10;
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Waits for the visibility of a given WebElement.
     */
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the given element is clickable.
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the presence of an element located by a By locator.
     */
    public void waitForPresenceOfElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}