package Ex1;

public class TestRobot {

    public static void main(String[] args) {
        Robot robot = new Robot();
        System.out.println(robot.toString());
        robot.change(5);
        System.out.println(robot.toString());

    }
}
