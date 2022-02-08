package aed.filter;

import java.util.Iterator;
import java.util.function.Predicate;
import es.upm.aedlib.indexedlist.*;

public class Utils {
  public static <E> Iterable<E> filter(Iterable<E> d, Predicate<E> pred) 
    throws IllegalStateException{
    IndexedList<E> list = new ArrayIndexedList<E>();
    Iterator<E> it = d.iterator(); //Para poder recorrer el iterable
    if(it == null)
            throw new IllegalStateException("Es nulo");

    while(it.hasNext()) {
          E x = it.next();
          if(!(x==null)&&pred.test(x))
          list.add(list.size(),x);
      }
      return list;
  }
}

