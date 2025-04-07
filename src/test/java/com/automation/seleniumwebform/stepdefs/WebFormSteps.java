package com.automation.seleniumwebform.stepdefs;

import com.automation.seleniumwebform.base.BaseTest;
import com.automation.seleniumwebform.pageobjects.WebFormPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Step definitions implementing behavior described in .feature file.
 */
public class WebFormSteps {
    WebFormPage webFormPage;
    WebDriver driver = BaseTest.getStaticDriver(); // ✅ Access driver statically

    @Given("the user navigates to the Selenium Web Form page")
    public void setupPage() {
        webFormPage = new WebFormPage(driver);
    }

    @When("the user fills in all required fields")
    public void fillAllFields() {
        webFormPage.fillForm();
    }

    @And("submits the form")
    public void submitsForm() {
        webFormPage.submitForm();
    }

    @Then("a confirmation message is displayed")
    public void confirmMessage() {
        Assert.assertTrue(webFormPage.getConfirmationMessage().contains("Received"));
    }

    @When("the user checks the disabled input field")
    public void checkDisabled() {
        Assert.assertTrue(webFormPage.isDisabled());
    }

    @Then("the field should be disabled")
    public void verifyDisabled() {
        Assert.assertTrue(webFormPage.isDisabled());
    }

    @Then("the default checkbox should not be selected")
    public void checkboxDefault() {
        Assert.assertFalse(webFormPage.isCheckboxSelected());
    }

    @Then("the default radio should not be selected")
    public void radioDefault() {
        Assert.assertFalse(webFormPage.isRadioSelected());
    }

    @When("the user selects the default radio button")
    public void selectRadio() {
        webFormPage.selectDefaultRadio();
    }

    @Then("the previously selected radio button should be deselected")
    public void deselectPrevious() {
        Assert.assertFalse(webFormPage.isAltRadioSelected());
    }

    @Then("the readonly input field should not be editable")
    public void readonlyFieldCheck() {
        Assert.assertTrue(webFormPage.isReadonly());
    }

    @When("the user clicks on the return to index link")
    public void clickReturnLink() {
        webFormPage.clickReturnLink();
    }

    @Then("the user should be navigated to {string}")
    public void verifyRedirection(String expectedUrl) {
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
    }
}