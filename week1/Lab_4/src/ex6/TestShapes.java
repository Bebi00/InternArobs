package ex6;

public class TestShapes {
    public static void main(String[] args) {
        Shape shape = new Shape("blue",true);
        Circle circle = new Circle(1);
        Rectangle rectangle = new Rectangle(10,10);
        Square square = new Square(5);

        System.out.println(shape.toString());
        System.out.println(circle.toString());
        System.out.println(rectangle.toString());
        System.out.println(square.toString());
    }
}
