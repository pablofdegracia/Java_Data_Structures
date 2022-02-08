package aed.individual4;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

public class MultiSetListIterator<E> implements Iterator<E> {
  PositionList<Pair<E,Integer>> list;

  Position<Pair<E,Integer>> cursor;
  int counter; E anterior; boolean removed;
  Position<Pair<E,Integer>> prevCursor;

  public MultiSetListIterator(PositionList<Pair<E,Integer>> list) {
    this.list = list;
    if(!(list.isEmpty())) {
    this.cursor = list.first();
    this.prevCursor = list.prev(cursor);
    this.anterior= null;
    this.removed=false;
    this.counter= cursor.element().getRight(); 
    }
  }

  public boolean hasNext() {
	  /*
	  if(list.isEmpty()) return false;
	  return !(cursor==list.last()&&cursor.element().getRight()==counter);
  		*/
	  if(cursor==null) return false;
	  return cursor.element().getRight()>=1||cursor!=null;
  }
  

  public E next(){
	  E resultado=null;
	  if(list.isEmpty()||!(hasNext())) throw new NoSuchElementException();
	  
	  if(counter>=1) {
		  prevCursor=cursor;
		  counter--;
		  resultado=cursor.element().getLeft();
	  }
	  
	  if(counter==0) { 
      cursor=list.next(cursor);
      	if(hasNext())
      counter=cursor.element().getRight();
	  }
	  return resultado;
	  /*
	if(list.isEmpty()||!(hasNext())) throw new NoSuchElementException();
	counter++;
	if(hasNext()&&!removed&&counter>cursor.element().getRight()) {
		  prevCursor=cursor;
		  cursor=list.next(cursor);
		  counter=1;
	    }
	
	removed=false;
    return cursor.element().getLeft();
  */
  }
  
  public void remove() { //elimina el ultimo elemento de next();
	  if(prevCursor==null) throw new IllegalStateException();
	  if(prevCursor.element().getRight()>1) prevCursor.element().setRight(prevCursor.element().getRight()-1);
	  else {
		  list.remove(prevCursor);
	  }
	  prevCursor=null;
	  
	  /*
	  if(removed==true||cursor==list.first()&&counter==0) throw new IllegalStateException();
	  if(cursor.element().getRight()<=1) {
		  prevCursor=cursor;
		  if(hasNext())
			  cursor=list.next(cursor);
		  list.remove(prevCursor);
	  }
	  else 
	  cursor.element().setRight(cursor.element().getRight()-1);
	  
	  */
  }
}
