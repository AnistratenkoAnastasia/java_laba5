package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class PropertiesConfigurationProviderTest {
    
    @Test
    public void testGetImplementation() {
        ConfigurationProvider provider = new PropertiesConfigurationProvider("AC.properties");
        String result = provider.getImplementation("com.example.SomeInterface");
        
        assertNotNull(result);
        assertEquals("com.example.SomeImpl", result);
    }
}