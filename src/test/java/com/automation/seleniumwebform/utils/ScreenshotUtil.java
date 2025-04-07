package com.automation.seleniumwebform.utils;

import com.automation.seleniumwebform.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil extends BaseTest {

    public static String captureScreenshot(String scenarioName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destPath = "screenshots/" + scenarioName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            File dest = new File(destPath);
            FileUtils.copyFile(src, dest);
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}