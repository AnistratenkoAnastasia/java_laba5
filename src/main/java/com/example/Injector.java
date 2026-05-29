package com.example;

import java.lang.reflect.Field;


public class Injector {

    private final ConfigurationProvider configurationProvider;

    public Injector(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

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