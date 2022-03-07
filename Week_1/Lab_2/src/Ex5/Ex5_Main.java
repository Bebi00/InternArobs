package Ex5;

import java.util.Random;

public class Ex5_Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        System.out.println("The original array is: ");
        for(int i=0;i<arr.length;i++){
            arr[i]=random.nextInt(100);
            System.out.print(""+arr[i] +", ");
        }

        int temp;
        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("\nThe sorted array is: ");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+", ");
        }
    }
}
