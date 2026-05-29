package com.example;

/**
 * Пример бина с внедряемыми зависимостями.
 */
public class SomeBean {
    
    @AutoInjectable
    private SomeInterface field1;
    
    @AutoInjectable
    private SomeOtherInterface field2;
    
    /**
     * Вызывает методы внедрённых зависимостей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
        System.out.println();
    }

    public SomeInterface getField1() { return field1; }
    public SomeOtherInterface getField2() { return field2; }
}