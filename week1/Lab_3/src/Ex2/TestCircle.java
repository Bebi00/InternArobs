package Ex2;

public class TestCircle {
    public static void main(String[] args) {
        Circle circle1 = new Circle();
        System.out.println("Radius: "+circle1.getRadius()+", Color: "+circle1.getColor());

        Circle circle2 = new Circle(20);
        System.out.println("Radius: "+circle2.getRadius()+", Color: "+circle2.getColor());

        Circle circle3 = new Circle("blue");
        System.out.println("Radius: "+circle3.getRadius()+", Color: "+circle3.getColor());

    }
}
