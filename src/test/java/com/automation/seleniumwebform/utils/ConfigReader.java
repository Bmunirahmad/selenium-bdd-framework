package com.automation.seleniumwebform.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads key-value pairs from testdata.properties into memory.
 * Used to dynamically configure test behavior.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();

    public static void loadProperties(String filePath) {
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
