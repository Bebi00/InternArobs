package ex5;

import ex1.Circle;

public class Cylinder extends Circle {
    private double height=1;

    public Cylinder(){}
    public Cylinder(double radius){
        super(radius);
    }
    public Cylinder(double radius,double height){
        super(radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        double pi=3.1415;
        return 2*pi*this.getRadius()*height+2*super.getArea();
    }

    public double getVolume(){
        return super.getArea()*height;
    }
}
