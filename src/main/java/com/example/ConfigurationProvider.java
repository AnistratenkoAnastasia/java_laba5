package com.example;

/**
 * Интерфейс для получения маппинга интерфейсов на реализации.
 */
public interface ConfigurationProvider {
    
    /**
     * Возвращает имя класса-реализации для заданного интерфейса.
     * @param interfaceName полное имя интерфейса
     * @return имя класса реализации или null
     */
    String getImplementation(String interfaceName);
}