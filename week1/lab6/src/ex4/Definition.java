package ex4;

import javax.swing.*;

public class Definition {
    private String description;

    public Definition(String description){
        this.description= description;
    }

    @Override
    public String toString() {
        return "Definition{" +
                "description='" + description + '\'' +
                '}';
    }
}
