package com.solvd.bank.PatternsTask.Factory;

public class FactoryDemo {
    public static void main(String[] args) {
        VehicleFactory vehicleFactory = new VehicleFactory();

        Vehicle vehicle1 = vehicleFactory.getVehicle("car");
        vehicle1.move();

        Vehicle vehicle2 = vehicleFactory.getVehicle("jetplane");
        vehicle2.move();

        Vehicle vehicle3 = vehicleFactory.getVehicle("boat");
        vehicle3.move();

    }
}
