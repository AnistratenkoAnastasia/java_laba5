package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Реализация ConfigurationProvider, читающая маппинги из .properties файла.
 */
public class PropertiesConfigurationProvider implements ConfigurationProvider {

    private final Properties properties;

    /**
     * Загружает конфигурацию из ресурса classpath.
     * @param resourceName имя файла конфигурации
     * @throws RuntimeException если файл не найден или не читается
     */
    public PropertiesConfigurationProvider(String resourceName) {
        this.properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null)
                throw new RuntimeException("Configuration file not found: " + resourceName);
            this.properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    @Override
    public String getImplementation(String interfaceName) {
        return this.properties.getProperty(interfaceName);
    }
}