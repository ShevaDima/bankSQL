package com.solvd.bank.PatternsTask.AbstractFactory;

public class AbstractFactoryDemo {
    public static void main(String[] args) throws Exception {
        AbstractFactory groundFactory = FactoryProducer.getFactory(true);

        Vehicle vehicle1 = groundFactory.getVehicle("car");
        vehicle1.move();

        Vehicle vehicle2 = groundFactory.getVehicle("truck");
        vehicle2.move();


        AbstractFactory flyingFactory = FactoryProducer.getFactory(false);

        Vehicle vehicle3 = flyingFactory.getVehicle("jetplane");
        vehicle3.move();

        Vehicle vehicle4 = flyingFactory.getVehicle("heli");
        vehicle4.move();
    }
}
