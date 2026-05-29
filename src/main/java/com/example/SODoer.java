package com.example;

/**
 * Реализация SomeOtherInterface, выводящая "C".
 */
public class SODoer implements SomeOtherInterface {
    
    @Override
    public void doSomeOther() {
        System.out.print("C");
    }
}