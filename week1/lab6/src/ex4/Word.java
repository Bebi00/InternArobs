package ex4;

import java.util.Objects;

public class Word {
    private String name;

    public Word(String name){
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Word word){
            return this.name.equals(word.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                '}';
    }
}
