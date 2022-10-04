package com.solvd.bank.PatternsTask.MVC;

public class MvcDemo {
    public static void main(String[] args) {
        Car car = retriveCarFromDatabase();

        CarView view = new CarView();

        CarController controller = new CarController(car, view);

        controller.updateView();

        controller.setCarModel("Supra");

        controller.updateView();
    }

    private static Car retriveCarFromDatabase() {
        Car car = new Car();
        car.setManufacturer("Toyota");
        car.setModel("Camry");
        return car;
    }
}
