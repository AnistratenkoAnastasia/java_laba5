package com.example;

/**
 * Реализация SomeInterface, выводящая "B".
 */
public class OtherImpl implements SomeInterface {
    
    @Override
    public void doSomething() {
        System.out.print("B");
    }
}