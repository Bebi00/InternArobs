package ex4;

import java.util.HashMap;
import java.util.Objects;

public class Dictionary {
    HashMap<Word,Definition> dictionary = new HashMap<>();

    public void addWord(Word w,Definition d){
        dictionary.put(w,d);
    }

    public Definition getDefinition(Word w){
        return dictionary.get(w);
    }

    public void getAllWords(){
        System.out.println("All the words in the dictionary are:");
        for(Word word:dictionary.keySet().stream().toList()){
            System.out.println(word);
        }
    }

    public void getAllDefinitions(){
        System.out.println("All the definitions in the dictionary are:");
        for(Definition definition:dictionary.values().stream().toList()){
            System.out.println(definition);
        }
    }


}
