package com.example;

public class SomeBean {
    
    @AutoInjectable
    private SomeInterface field1;
    
    @AutoInjectable
    private SomeOtherInterface field2;
    
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
        System.out.println();
    }

    // добавила геттеры для тестов
    public SomeInterface getField1() { return field1; }
    public SomeOtherInterface getField2() { return field2; }
}