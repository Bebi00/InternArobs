package Ex2;

public class Circle {
    double radius;
    String color;


    Circle(){
        this.radius = 1;
        this.color = "red";
    }
    Circle(double radius){
        this.radius = radius;
    }


    Circle(String color){
        this.color = color;
    }
    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }
}
