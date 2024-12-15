package org.example.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
        private static Properties properties;

        static {
            try {
                properties = new Properties();
                // Load the config.properties file
                FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
            }
        }

        // Method to retrieve properties by key
        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
    }

