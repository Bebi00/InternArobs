package Ex4;

public class Ex4_Main {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 3, 10, 2, 5};
        int max = arr[0];
        for (int n:arr){
            if(max<n){
                max=n;
            }
        }
        System.out.println("The maximum number is: "+max);
    }
}
