package com.automation.seleniumwebform.pageobjects;

import com.automation.seleniumwebform.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.seleniumwebform.utils.ConfigReader;

import java.io.File;

/**
 * Page Object Model class for Selenium Web Form interactions.
 */
public class WebFormPage {

    private WebDriver driver;
    private WaitHelper waitHelper;

    // === Page Elements ===
    @FindBy(id = "my-text-id")
    private WebElement textInput;

    @FindBy(name = "my-password")
    private WebElement passwordInput;

    @FindBy(name = "my-textarea")
    private WebElement textArea;

    @FindBy(name = "my-file")
    private WebElement fileInput;

    @FindBy(css = "button")
    private WebElement submitButton;

    @FindBy(id = "message")
    private WebElement confirmationMessage;

    @FindBy(name = "my-disabled")
    private WebElement disabledInput;

    @FindBy(name = "my-check")
    private WebElement defaultCheckbox;

    @FindBy(id = "my-radio-2")
    private WebElement defaultRadio;

    @FindBy(id = "my-radio-1")
    private WebElement altRadio;

    @FindBy(name = "my-readonly")
    private WebElement readonlyInput;

    @FindBy(linkText = "Return to index")
    private WebElement returnLink;

    @FindBy(name = "my-colors")
    private WebElement colorPicker;

    @FindBy(name = "my-date")
    private WebElement dateInput;

    @FindBy(name = "my-range")
    private WebElement rangeInput;





    // === Constructor ===
    public WebFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    // === Actions ===

    public void fillForm() {
        waitHelper.waitForElementToBeVisible(textInput);
        textInput.sendKeys(ConfigReader.get("username"));

        waitHelper.waitForElementToBeVisible(passwordInput);
        passwordInput.sendKeys(ConfigReader.get("password"));

        waitHelper.waitForElementToBeVisible(textArea);
        textArea.sendKeys(ConfigReader.get("message"));

        waitHelper.waitForElementToBeVisible(colorPicker);
        colorPicker.sendKeys(ConfigReader.get("color"));

        waitHelper.waitForElementToBeVisible(dateInput);
        dateInput.sendKeys(ConfigReader.get("date"));

        waitHelper.waitForElementToBeVisible(rangeInput);
        rangeInput.sendKeys(ConfigReader.get("range"));

        waitHelper.waitForElementToBeVisible(fileInput);
        String path = new File(ConfigReader.get("filepath")).getAbsolutePath();
        fileInput.sendKeys(path);
    }

    public void submitForm() {
        waitHelper.waitForElementToBeClickable(submitButton);
        submitButton.click();
    }

    public boolean isConfirmationDisplayed() {
        waitHelper.waitForElementToBeVisible(confirmationMessage);
        return confirmationMessage.isDisplayed();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public boolean isDisabled() {
        return !disabledInput.isEnabled();
    }

    public boolean isCheckboxSelected() {
        return defaultCheckbox.isSelected();
    }

    public boolean isRadioSelected() {
        return defaultRadio.isSelected();
    }

    public void selectDefaultRadio() {
        if (!defaultRadio.isSelected()) {
            defaultRadio.click();
        }
    }

    public boolean isAltRadioSelected() {
        return altRadio.isSelected();
    }

    public boolean isReadonly() {
        return readonlyInput.getAttribute("readonly") != null;
    }

    public void clickReturnLink() {
        waitHelper.waitForElementToBeClickable(returnLink);
        returnLink.click();
    }
}