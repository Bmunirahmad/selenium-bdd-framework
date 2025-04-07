package com.automation.seleniumwebform.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.automation.seleniumwebform.stepdefs",
                "com.automation.seleniumwebform.hooks"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-reports"
        },
        monochrome = true
)

public class TestRunner {
}