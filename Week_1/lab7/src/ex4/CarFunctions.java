package ex4;

import java.io.*;
import java.util.ArrayList;

public class CarFunctions {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/ex4/cars.txt"));
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/ex4/cars.txt"));
    static int carCount;

    public CarFunctions() throws IOException {
    }

    public Car createCar(String model, double price) {
        carCount++;
        System.out.println("A new Car has been created.");
        return new Car(model, price);
    }

    public void saveCar(Car car) throws IOException {
        oos.writeObject(car);
        oos.flush();
        System.out.println("The car was saved.");
    }

    public Car loadCar(int carNumber) throws IOException, ClassNotFoundException {
        for (int i = 0; i < carCount; i++) {
            Object object = ois.readObject();
            if (i == carNumber-1) {
                return (Car) object;
            }
        }
        return null;

    }

    public ArrayList<Car> viewCars() throws IOException, ClassNotFoundException {
        try {
            ois = new ObjectInputStream(new FileInputStream("src/ex4/cars.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Car> carArrayList = new ArrayList<>();
        Object object;
        for (int i = 0; i < carCount; i++) {
            object = ois.readObject();
            carArrayList.add((Car) object);
        }

        return carArrayList;
    }

}
