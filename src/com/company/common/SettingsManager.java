package com.company.common;

import java.io.*;
import java.util.Properties;

public class SettingsManager {
    private static SettingsManager INSTANCE = null;

    private SettingsManager() {}

    public static SettingsManager getInstance() {
        if (INSTANCE == null) {
            synchronized(SettingsManager.class) {
                INSTANCE = new SettingsManager();
            }
        }
        return INSTANCE;
    }

    public Properties getProperties() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();

        fis = new FileInputStream("src/com/company/resources/config.properties");
        property.load(fis);

        return property;
    }

}
