package com.solvd.bank.PatternsTask.AbstractFactory;

public class Truck implements Vehicle {
    @Override
    public void move() {
        System.out.println("Truck drags");
    }
}
