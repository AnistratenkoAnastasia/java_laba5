package com.example;

import java.lang.reflect.Field;

/**
 * Простой контейнер для внедрения зависимостей через рефлексию.
 */
public class Injector {

    private final ConfigurationProvider configurationProvider;

    /**
     * @param configurationProvider поставщик конфигурации
     */
    public Injector(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    /**
     * Внедряет зависимости в поля, помеченные @AutoInjectable.
     * @param target объект для инъекции
     * @return тот же объект с инициализированными полями
     * @throws IllegalArgumentException если target null
     * @throws RuntimeException если внедрение не удалось
     */
    public <T> T inject(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }

        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                injectField(target, field);
            }
        }
        return target;
    }

    /**
     * Внедряет зависимость в конкретное поле.
     * @param target объект-владелец
     * @param field поле для инъекции
     */
    private void injectField(Object target, Field field) {
        field.setAccessible(true);
        String interfaceName = field.getType().getName();
        String implementationName = configurationProvider.getImplementation(interfaceName);

        if (implementationName == null) {
            throw new RuntimeException("No implementation configured for: " + interfaceName);
        }

        try {
            Class<?> implementationClass = Class.forName(implementationName);
            if (!field.getType().isAssignableFrom(implementationClass)) {
                throw new RuntimeException("Class " + implementationName + " does not implement " + interfaceName);
            }
            Object instance = implementationClass.getDeclaredConstructor().newInstance();
            field.set(target, instance);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Injection failed for field: " + field.getName(), e);
        }
    }
}