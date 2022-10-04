package com.solvd.bank.PatternsTask.Factory;

public class VehicleFactory {
    public Vehicle getVehicle(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        }
        else if (type.equalsIgnoreCase("jetplane")) {
            return new JetPlane();
        }
        else if (type.equalsIgnoreCase("boat")) {
            return new Boat();
        }

        return null;
    }
}
