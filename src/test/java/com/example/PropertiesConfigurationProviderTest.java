package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Тесты для PropertiesConfigurationProvider.
 */
public class PropertiesConfigurationProviderTest {
    
    /**
     * Проверка получения имени реализации из конфигурации.
     */
    @Test
    public void testGetImplementation() {
        ConfigurationProvider provider = new PropertiesConfigurationProvider("AC.properties");
        String result = provider.getImplementation("com.example.SomeInterface");
        
        assertNotNull(result);
        assertEquals("com.example.SomeImpl", result);
    }
}