package Lab_2.Ex6;

import java.util.Scanner;

public class Ex6_NonRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce the number n: ");
        int n=scanner.nextInt();
        int fact=1;
        while(n<1){

            System.out.println("The number introduced is not valid");
            n=scanner.nextInt();

        }
        for(int i=2;i<=n;i++){
            fact *=i;
        }

        System.out.println("The result is: "+fact);
    }
}
