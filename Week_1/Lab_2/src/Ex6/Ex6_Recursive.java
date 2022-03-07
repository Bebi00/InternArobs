package Ex6;

import java.util.Scanner;

public class Ex6_Recursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce the number n: ");
        int n=scanner.nextInt();
        while(n<1) {

            System.out.println("The number introduced is not valid");
            n = scanner.nextInt();

        }
        int fact = factorial(n);
        System.out.println("The result is: "+fact);
    }

    public static int factorial(int n){
        if(n>1){
            return n*factorial(n-1);
        }
        else{
            return 1;
        }
    }
}
