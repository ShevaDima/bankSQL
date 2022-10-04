package com.solvd.bank.PatternsTask.AbstractFactory;

public class FlyingVehicleFactory  extends AbstractFactory{
    @Override
    public Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("jetplane")) {
            return new JetPlane();
        }
        else if (type.equalsIgnoreCase("heli")) {
            return new Heli();
        }

        return null;
    }
}
