package com.solvd.bank.PatternsTask.MVC;

public class CarController {
    private Car car;
    private CarView view;

    public CarController(Car car, CarView view) {
        this.car = car;
        this.view = view;
    }

    public void setCarManufacturer(String manufacturer) {
        car.setManufacturer(manufacturer);
    }

    public String getCarManufacturer() {
        return car.getManufacturer();
    }

    public void setCarModel(String model) {
        car.setModel(model);
    }

    public String getCarModel() {
        return car.getModel();
    }

    public void updateView() {
        view.printCarDetails(car.getManufacturer(), car.getModel());
    }
}
