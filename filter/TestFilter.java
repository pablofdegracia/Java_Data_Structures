package aed.filter;

import es.upm.aedlib.indexedlist.*;

public class TestFilter {
    private static Persona p1,p2,p3,p4,p5;
    private static IndexedList<Integer> persons = new ArrayIndexedList<Integer>();

    public static void Fill(){
        p1 = new Persona("Juan");
        p2 = null;
        p3 = new Persona("Ernesto");
        p4 = new Persona("Felipe");
        //p5 = null;

        persons.add(persons.size(), p1.getAge());
        persons.add(persons.size(), p2.getAge());
        persons.add(persons.size(), p3.getAge());
        persons.add(persons.size(), p4.getAge());
        //persons.add(persons.size(), p5.getAge());

        ////////////////////////////////

    }

    public static void Filter(){
        System.out.println(Utils.filter(persons, new GreaterThan<>(5)).toString());
    }
    public static void main(String[] args) {
        Fill();
        //System.out.println(persons.toString());
        Filter();
        
    }
}
