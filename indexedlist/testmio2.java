package aed.indexedlist;
import es.upm.aedlib.indexedlist.*;

public class testmio2 {
    public static IndexedList<Integer> a,b,c,d,e;
    public static int[] b1,c1,d1,e1;
    private static void cargar(){
        a= new ArrayIndexedList<Integer>();
        b= new ArrayIndexedList<Integer>();
        b.add(0,1);

        c= new ArrayIndexedList<Integer>();
        c.add(0,1);
        c.add(1,4);
        c.add(2,3);

        d= new ArrayIndexedList<Integer>();
        d.add(0,1);
        d.add(1,4);
        d.add(2,3);
        d.add(3,2);

        e= new ArrayIndexedList<Integer>();
        e.add(0,1);
        e.add(1,4);
        e.add(2,3);
        e.add(3,4);
        e.add(4,4);
        e.add(5,3);
    }

    private static boolean prueba(){
        return Utils_porro.deleteRepeated(a).equals(a)&&
        //Utils.deleteRepeated(b).equals(b)&&
        //Utils_porro.deleteRepeated(c).equals(c)&&
        Utils_porro.deleteRepeated(e).equals(c);
    }
    public static void main(String[] args) {
        cargar();
        System.out.println(e.hashCode());
        System.out.println(c.hashCode());
        System.out.println(a.hashCode());
        System.out.println(prueba());
    }
}
