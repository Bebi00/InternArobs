package ex4;

import java.io.IOException;

public class TestCar {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CarFunctions carFunctions = new CarFunctions();
        carFunctions.saveCar(carFunctions.createCar("ferrari",200));
        carFunctions.saveCar(carFunctions.createCar("volvo",30));
        System.out.println(carFunctions.loadCar(2).toString());

        for (Car car:carFunctions.viewCars()){
            System.out.println(car.toString());
        }
    }
}
