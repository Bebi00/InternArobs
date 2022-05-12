package ex2;

import java.io.*;
import java.util.Scanner;

public class CountRepetition {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce the character to be find: ");
        char character = scanner.next().toCharArray()[0];

        FileInputStream fileInputStream = new FileInputStream("src/ex2/data.txt");
        int counter=0;
        byte[] bytes = new byte[1];
        char text;

        while (fileInputStream.read(bytes) != -1) {
            text = new String(bytes).toCharArray()[0];
            if(text == character){
                counter++;
            }
        }
        System.out.println("The character "+character+" was found "+ counter + " times in the file.");
    }
}
