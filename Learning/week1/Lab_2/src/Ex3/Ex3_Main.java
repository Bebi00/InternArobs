package Ex3;

import java.util.Scanner;

public class Ex3_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce the starting number of the interval: ");
        int start = scanner.nextInt();
        System.out.println("Introduce the final number of the interval: ");
        int end = scanner.nextInt();

        System.out.println("The primes number in the interval ["+start+";"+end+"] are: ");
        for (int nr=start+1;nr<end;nr++){
            boolean isPrime = true;
            for (int div=2;div<nr/2+1;div++){
                if(nr%div==0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                System.out.println(nr+", ");
            }

        }
    }
}
