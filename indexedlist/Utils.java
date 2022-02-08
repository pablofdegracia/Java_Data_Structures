package aed.indexedlist;
import es.upm.aedlib.indexedlist.*;

public class Utils {
  
  public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {
    //Crea una copia de la lista orginal para porder modificarla
    IndexedList<E> result= new ArrayIndexedList<E>((ArrayIndexedList<E>)l);
    //Chequea si la lista está vacía
      if(l.hashCode()!=0){
        for(int i=0; i<result.size(); i++) {
          for(int j=1+i; j<result.size(); j++){
    /*recorre la lista comparando un elemento con el resto a su derecha
    En el caso de que se repita ese número se elimina el clon más a la derecha
    y la lista retrocede para correguir el cambio de longitud
    */
            if(result.get(i).hashCode()==(result.get(j).hashCode())) {
              result.removeElementAt(j);
              j--;
            }
          }
        }
      }
      return result; 
    }
  }
