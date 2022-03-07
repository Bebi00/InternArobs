package Lab_2.Ex7;

import java.util.Random;
import java.util.Scanner;

public class Ex7_Main {
    public static void main(String[] args) {
        Random random = new Random();
        int max = 10,guess;
        int n = random.nextInt(max);

        Scanner scanner = new Scanner(System.in);
        for (int tries=0;tries<3;tries++){
            System.out.println("Guess the number between 1 and "+max+" :");
            guess = scanner.nextInt();
            if(n==guess){
                System.out.println("Congrats! You won.");
                break;
            } else if( n > guess){
                System.out.println("Wrong answer, your number is too low");
            }
            else{
                System.out.println("Wrong answer, your number is too high");
            }
        }
    }
}
