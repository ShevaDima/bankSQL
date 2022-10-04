package com.solvd.bank.PatternsTask.AbstractFactory;

public class GroundVehicleFactory extends AbstractFactory{
    @Override
    public Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        }
        else if (type.equalsIgnoreCase("truck")) {
            return new Truck();
        }

        return null;
    }
}
