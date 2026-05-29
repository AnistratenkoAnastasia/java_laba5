package com.example;

public class App {
    public static void main(String[] args) {
        ConfigurationProvider providerAC = new PropertiesConfigurationProvider("AC.properties");
        Injector injectorAC = new Injector(providerAC);
        SomeBean beanAC = injectorAC.inject(new SomeBean());
        beanAC.foo();
        
        ConfigurationProvider providerBC = new PropertiesConfigurationProvider("BC.properties");
        Injector injectorBC = new Injector(providerBC);
        SomeBean beanBC = injectorBC.inject(new SomeBean());
        beanBC.foo();
    }
}