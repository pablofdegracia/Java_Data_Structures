package aed.indexedlist;
import es.upm.aedlib.indexedlist.*;

public class Utils_porro {
  
  public static <E> IndexedList<Integer> deleteRepeated(IndexedList<E> l) {
    Integer[] aux= new Integer[l.size()];
    IndexedList<Integer> copia= new ArrayIndexedList<Integer>((ArrayIndexedList<Integer>)l);
    IndexedList<Integer> result= new ArrayIndexedList<Integer>();
    //Chequea si la lista está vacía
      if(l.hashCode()!=0){
        int i=0; 
        //Llena el array de enteros
        for(E e : l) {
          aux[i]=e.hashCode();
          i++;}
        
        //Ordena el array de menor a mayor  
        
        for(int j=0; j<aux.length; j++) {
          for(int k=j+1; k<l.size(); k++) {
            if(aux[j]>aux[k]){
              int temp=aux[j];
              aux[j]=aux[k];
              aux[k]=temp;
            }
          }
        }
        
        //Chequea los numeros que se repiten
        int c=1; result.add(result.size(),aux[0]);
        while(c<aux.length) {
          if(aux[c-1]!=aux[c]){
            result.add(result.size(),aux[c]);
          }
          c++;
        }
      }
      
      return result; 
    }
  }
