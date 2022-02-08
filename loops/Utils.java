package aed.loops;

public class Utils {
  public static int maxNumRepeated(Integer[] a, Integer elem)  {
    int result_aux=0;
    
    for (int i = 0; i < a.length; i++) {
      boolean ok = true; int result = 0;
      if(a[i].equals(elem)){
        result++; int j=i+1;
        while(j<a.length &&ok){
          if(a[j].equals(elem)==false){
            ok=false; 
          }
          else{j++; result++;}
        }
      }
      if(result>result_aux)result_aux=result;
    }
    return result_aux;
  }
}
