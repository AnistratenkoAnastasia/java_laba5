package com.example;

/**
 * Точка входа в приложение. Демонстрирует работу DI-контейнера
 * с двумя разными конфигурациями (AC и BC).
 */
public class App {
    
    /**
     * Запускает приложение: создаёт инжекторы, внедряет зависимости
     * в SomeBean и вызывает метод foo().
     * @param args аргументы командной строки
     */
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