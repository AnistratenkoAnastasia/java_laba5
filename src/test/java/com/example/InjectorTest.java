package com.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class InjectorTest {
    
    @Test
    public void testInject_AC_Config() {
        ConfigurationProvider provider = new PropertiesConfigurationProvider("AC.properties");
        Injector injector = new Injector(provider);
        SomeBean bean = injector.inject(new SomeBean());
        
        assertNotNull(bean.getField1());
        assertNotNull(bean.getField2());
        assertTrue(bean.getField1() instanceof SomeImpl);
        assertTrue(bean.getField2() instanceof SODoer);
    }
    
    @Test
    public void testInject_BC_Config() {
        ConfigurationProvider provider = new PropertiesConfigurationProvider("BC.properties");
        Injector injector = new Injector(provider);
        SomeBean bean = injector.inject(new SomeBean());
        
        assertNotNull(bean.getField1());
        assertTrue(bean.getField1() instanceof OtherImpl);
    }
}