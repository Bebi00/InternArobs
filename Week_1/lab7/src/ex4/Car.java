package ex4;

import java.io.Serializable;

public class Car implements Serializable {
    private String model;
    private double price;

    Car(String model,double price){
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
