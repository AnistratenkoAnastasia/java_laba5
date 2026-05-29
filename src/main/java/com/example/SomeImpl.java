package com.example;

/**
 * Реализация SomeInterface, выводящая "A".
 */
public class SomeImpl implements SomeInterface {
    
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}