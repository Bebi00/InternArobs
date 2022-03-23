package Ex4;

public class TestMyPoint {
    public static void main(String[] args) {
        MyPoint myPoint1 = new MyPoint();
        System.out.println(myPoint1.toString());

        MyPoint myPoint2 = new MyPoint(2,2);
        System.out.println(myPoint2.toString());

        myPoint1.setXY(1,1);

        System.out.println(myPoint1.toString());

        System.out.println(myPoint1.distance(2,2));
        System.out.println(myPoint1.distance(myPoint2));

    }
}
