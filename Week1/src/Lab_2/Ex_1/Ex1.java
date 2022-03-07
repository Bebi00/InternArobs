package Lab_2.Ex_1;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Ex1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce the first number: ");
        float firstNumber = scanner.nextFloat();

        System.out.println("Introduce the second number: ");
        float secondNumber = scanner.nextFloat();

        System.out.println("The maximum number is "+Math.max(firstNumber,secondNumber));
    }

}
