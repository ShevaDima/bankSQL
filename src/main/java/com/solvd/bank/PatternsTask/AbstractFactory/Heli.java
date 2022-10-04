package com.solvd.bank.PatternsTask.AbstractFactory;

public class Heli implements Vehicle {
    @Override
    public void move() {
        System.out.println("Heli flies slower");
    }
}
