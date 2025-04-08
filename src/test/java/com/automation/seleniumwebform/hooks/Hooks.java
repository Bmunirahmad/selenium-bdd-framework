package com.automation.seleniumwebform.hooks;

import com.automation.seleniumwebform.base.BaseTest;
import com.automation.seleniumwebform.utils.ConfigReader;
import com.automation.seleniumwebform.utils.ScreenshotUtil;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Global hooks to set up and tear down WebDriver,
 * initialize config, generate reports and handle screenshots.
 */
public class Hooks extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    @BeforeAll
    public static void beforeAll() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/TestReport_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println(">>> [HOOK] Before hook is being triggered for scenario: " + scenario.getName());
        ConfigReader.loadProperties("src/test/resources/config/testdata.properties");
        initializeDriver();
        driver.get(ConfigReader.get("base.url"));
        logger.info("Navigated to: " + ConfigReader.get("base.url"));
        scenarioTest = extent.createTest(scenario.getName());
    }

    @AfterStep
    public void logStepResult(Scenario scenario) {
        if (scenario.isFailed()) {
            scenarioTest.fail("Step failed.");
        } else {
            scenarioTest.pass("Step passed.");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String path = ScreenshotUtil.captureScreenshot(scenario.getName());
            scenarioTest.fail("Failed: " + scenario.getName())
                    .addScreenCaptureFromPath(path);
        } else {
            scenarioTest.pass("Passed: " + scenario.getName());
        }
        quitDriver();
        logger.info("Browser closed");
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }
}