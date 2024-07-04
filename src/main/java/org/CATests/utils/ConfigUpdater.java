package org.CATests.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigUpdater {
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties"; // Ensure this is the correct path

    public static void updateConfig(Map<String, String> data) {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            // Load existing properties
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE_PATH)) {
            // Update properties with data from the Excel row
            for (Map.Entry<String, String> entry : data.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }

            // Save properties to the config file
            properties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}