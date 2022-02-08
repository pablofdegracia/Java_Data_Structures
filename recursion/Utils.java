package aed.recursion;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.positionlist.*;


public class Utils {

  private static int absoluteValue(int a) {
	  return (a < 0) ? -a : a;
  }
  
  private static int cociente(int a, int b) {
	   return (a+b)/2;
  }
  public static int multiply(int a, int b) {
	  /* ALGORTIMO
      i=0 -> a;b; sum=b;
      i=1 -> a/2; b*2; if(a%2!=0) -> sum=sum+b
      (...)
      i=FIN -> a=1; b
    */
	  
	if(a<0 && b < 0) {
		a=absoluteValue(a); b=absoluteValue(b);
		}
	
    if(a==1) {
    	return b;
    }
    if(a%2!=0)
      return (b+multiply(a/2 ,b*2));
    else{
      return multiply(a/2 ,b*2);

    }
  }

  public static <E extends Comparable<E>> int findBottom(IndexedList<E> l) {
	  if(l.get(0).compareTo(l.get(1))<=0) return 0;
	  if(l.get(l.size()-1).compareTo(l.get(l.size()-2))<=0) return l.size()-1;
	  if(l.isEmpty()) return -1;
	  else
		  return  findBottomRec(l, 0, l.size());
  }
  
  private static <E extends Comparable<E>> int 
  findBottomRec(IndexedList<E> l, int start, int end) {
	  int middle= cociente(start, end); 
	  if(l.size()==1) 
		  return start;
	  if(l.size()==2) {
	      if((l.get(start).compareTo(l.get(end)))<=0) return start;
		  else return end;}
	  if(l.get(middle).compareTo(l.get(middle-1))<=0&&l.get(middle).compareTo(l.get(middle+1))<=0)
	  	return  middle;
	  else {
		  return findBottomRec(l, start, end-1);}
  }

  public static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
	joinMultiSets(NodePositionList<Pair<E,Integer>> l1,
			NodePositionList<Pair<E,Integer>> l2) 
	{

		if (l1 == null || l2==null)
			return null;
		else
		{
			NodePositionList<Pair<E, Integer>> res = new NodePositionList<Pair<E, Integer>>();
			joinMultiSetsRec (l1, l2, l1.first(), l2.first(), res);
			return res;
		}

	}

	private static <E extends Comparable<E>> NodePositionList<Pair<E,Integer>>
	joinMultiSetsRec(NodePositionList<Pair<E,Integer>> l1,
			NodePositionList<Pair<E,Integer>> l2, Position<Pair<E,Integer>> cursor1, 
			Position<Pair<E,Integer>> cursor2, 
			NodePositionList<Pair<E,Integer>> res) 
	{

		if (cursor1 != null || cursor2 != null)
		{
			if (cursor1 == null)
			{
				res.addLast(cursor2.element());
				cursor2 = l2.next(cursor2);
			}
			else if (cursor2 == null)
			{
				res.addLast(cursor1.element());
				cursor1 = l1.next(cursor1);
			}
			else if (cursor1.element().getLeft().compareTo(cursor2.element().getLeft()) < 0)
			{
				res.addLast(cursor1.element());
				cursor1 = l1.next(cursor1);
			}	
			else if(cursor1.element().getLeft().compareTo(cursor2.element().getLeft()) == 0)
			{
				int indice = cursor1.element().getRight() + cursor2.element().getRight();
				cursor1.element().setRight(indice);
				res.addLast(cursor1.element());
				cursor1 = l1.next(cursor1);
				cursor2 = l2.next(cursor2);
			}
			else
			{ 
				res.addLast(cursor2.element());
				cursor2 = l2.next(cursor2);
			}
			return joinMultiSetsRec (l1,l2,cursor1, cursor2, res);		   
		} 
		return res;
	}
}
