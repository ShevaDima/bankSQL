package com.solvd.bank.PatternsTask.Factory;

public class VehicleFactory {
    public Vehicle getVehicle(String type) throws Exception {
        if (type == null) {
            throw new Exception("Null");
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

        throw new Exception("Null");
    }
}
