package com.solvd.bank.PatternsTask.AbstractFactory;

public class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Car moves");
    }
}
