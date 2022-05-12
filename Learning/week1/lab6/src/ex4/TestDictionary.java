package ex4;

import java.awt.*;
import java.util.Scanner;

public class TestDictionary {
    static Dictionary dictionary = new Dictionary();
    static boolean exit=false;

    public static void main(String[] args) {

    Word word = new Word("dog");
        Definition definition = new Definition("quadruped Animal");
        dictionary.addWord(word,definition);


        while(!exit){
            printMenu();
            menuOptions();

        }
    }

    public static void printMenu(){
        System.out.println("1. Add a new word");
        System.out.println("2. Get the definition of a word");
        System.out.println("3. Get all words");
        System.out.println("4. Get all definitions");
        System.out.println("0. Exit");

        System.out.println("\nChoose an option: ");
    }

    public static void menuOptions(){
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.println("Introduce the new word: ");
                Word newWord = new Word(scanner.next());

                // consumes the '\n' character
                scanner.nextLine();
                System.out.println("Introduce the definition of the word: ");
                String description = new String(scanner.nextLine());
                Definition newDefinition = new Definition(description);
                dictionary.addWord(newWord, newDefinition);
            }
            case 2 -> {
                System.out.println("Introduce the word: ");
                System.out.println(dictionary.getDefinition(new Word(scanner.next())));
            }
            case 3 -> dictionary.getAllWords();
            case 4 -> dictionary.getAllDefinitions();
            case 0 -> exit = true;
        }
    }
}
