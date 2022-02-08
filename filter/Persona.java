package aed.filter;
import java.util.*;

public class Persona{
    private String name;
    private int age;

    public Persona(String name) {
        this.name = name;
        Random rand = new Random();
        age= rand.nextInt(10); 
    }

    public String toString() {
        return name + ";" +age;
    }

    public int getAge() {
        return age;
    }
}