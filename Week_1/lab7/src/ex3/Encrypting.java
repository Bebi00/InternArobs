package ex3;

import java.io.*;
import java.util.Scanner;

public class Encrypting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce the action to be performed (enc / dec) : ");
        String action = scanner.nextLine();
        switch (action){
            case "enc" -> {
                System.out.println("Introduce the filename to be encrypted: ");
                try {
                    String fileName = scanner.nextLine();
                    BufferedReader br = new BufferedReader(new FileReader("src/ex3/"+fileName+".dec"));
                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/ex3/"+fileName+".enc"));
                    fileModification(bw,br);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            case "dec" -> {
                System.out.println("Introduce the filename to be decrypted: ");
                try {
                    String fileName = scanner.nextLine();
                    BufferedReader br = new BufferedReader(new FileReader("src/ex3/"+fileName+".enc"));
                    BufferedWriter bw = new BufferedWriter(new FileWriter("src/ex3/"+fileName+".dec"));
                    fileModification(bw,br);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void fileModification(BufferedWriter bw,BufferedReader br) throws IOException {
        String line;
        char[] chars;
        while((line = br.readLine()) != null){
            chars = line.toCharArray();
            for(char c:chars){
                bw.write(Character.toString(++c));
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();

    }
}
