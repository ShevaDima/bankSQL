package com.solvd.bank.PatternsTask.AbstractFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(boolean ground) {
        if (ground) {
            return new GroundVehicleFactory();
        }
        else {
            return new FlyingVehicleFactory();
        }
    }
}
