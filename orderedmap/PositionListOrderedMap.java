package aed.orderedmap;

import java.util.Comparator;
import java.util.Iterator;

import es.upm.aedlib.Entry;
import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

public class PositionListOrderedMap<K,V> implements OrderedMap<K,V> {
	private Comparator<K> cmp;
	private PositionList<Entry<K,V>> elements;

	/* Acabar de codificar el constructor */
	public PositionListOrderedMap(Comparator<K> cmp) 
	{
		this.cmp = cmp;
		this.elements = new NodePositionList<Entry<K,V>>();
	}

	/* Ejemplo de un posible método auxiliar: */

	/* If key is in the map, return the position of the corresponding
	 * entry.  Otherwise, return the position of the entry which
	 * should follow that of key.  If that entry is not in the map,
	 * return null.  Examples: assume key = 2, and l is the list of
	 * keys in the map.  For l = [], return null; for l = [1], return
	 * null; for l = [2], return a ref. to '2'; for l = [3], return a
	 * reference to [3]; for l = [0,1], return null; for l = [2,3],
	 * return a reference to '2'; for l = [1,3], return a reference to
	 * '3'. */

	private Position<Entry<K,V>> keyPosition(K key) 
	{
		if (key == null) throw new IllegalArgumentException();

		Position<Entry<K, V>> resultado = null;
		for(Position<Entry<K, V>> cursor = elements.first(); cursor != null; cursor = elements.next(cursor))
		{
			if(cmp.compare(key, cursor.element().getKey()) == 0)
			{
				resultado = cursor;
			}
		}
		return resultado;
	}

	private Position<Entry<K,V>> findKeyPlace(K key) 
	{
		if (key == null) throw new IllegalArgumentException();

		Position<Entry<K, V>> resultado = null;

		if (elements.isEmpty())
		{
			return resultado;
		}

		else 
		{
			boolean encontrado = false;

			for (Position<Entry<K,V>> cursor = elements.first(); cursor != null && !encontrado; 
					cursor = elements.next(cursor))
			{
				if (cmp.compare(key, cursor.element().getKey()) == 0 || this.cmp.compare(key, cursor.element().getKey()) < 0)
				{
					resultado = cursor;
					encontrado = true;
				}
			}
			return resultado;
		}	
	}


	/* Podéis añadir más métodos auxiliares */
	private  boolean PosContieneClave(K key, Position<Entry<K, V>> pos)
	{
		return pos.element().getKey().equals(key); 
	}

	private Entry<K, V> entryONUll(Position<Entry<K, V>> pos)
	{
		return pos.element();
	}

	public boolean containsKey(K key) 
	{
		if (key == null) throw new IllegalArgumentException();

		boolean esta = false;
		for (Position<Entry<K, V>> cursor = elements.first(); cursor != null; cursor = elements.next(cursor))
		{
			if (cursor.element().getKey().equals(key))
			{
				esta = true;
			}
		}
		return esta;
	}


	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException();

		else if (keyPosition(key) == null) 
		{
			return null;
		}

		else
		{			
			return keyPosition(key).element().getValue();
		}
	}

	public V put(K key, V value) 
	{
		if (key == null) throw new IllegalArgumentException();

		Entry<K,V> res= new EntryImpl(key, value);
		V resultado = null;

		if (elements.isEmpty())
		{
			elements.addFirst (res);
		}

		else if (keyPosition(key) != null)
		{
			resultado = keyPosition(key).element().getValue();
			elements.set(keyPosition(key), res);
		}

		else if(findKeyPlace(key) != null)
		{
			elements.addBefore(findKeyPlace(key), res);
		}

		else
		{
			elements.addLast(res);
		}

		return resultado;
	}

	public V remove(K key) 
	{
		if (key == null) throw new IllegalArgumentException();
		V value = null;

		if (keyPosition(key) != null) 
		{
			value = keyPosition(key).element().getValue();
			elements.remove(keyPosition(key));
		}

		return value;
	}

	public int size() {
		int cont = 0;
		Position<Entry<K, V>> cursor = elements.first();
		while (cursor != null)
		{
			if (cursor.element() != null)
			{
				cont++;
				cursor = elements.next(cursor);
			}
		}
		return cont;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Return the entry with the greatest key less than or equal to the
	 * argument key, or null if no such entry exists.
	 * @return the entry with the greatest key less than or equal to the
	 * argument key.
	 * @throws IllegalArgumentException if the key is invalid (e.g., the null value).
	 */

	public Entry<K,V> floorEntry(K key)
	{
		if (key == null) throw new IllegalArgumentException();

		else if(elements.isEmpty())
		{
			return null;
		}

		else
		{
			Entry<K, V> resultado = null;
			for (Position<Entry<K, V>> cursor = elements.first(); cursor != null; cursor = elements.next(cursor))
			{
				if(cmp.compare(cursor.element().getKey(), key) <= 0)
				{
					resultado = cursor.element();
				}
			}
			return resultado;
		}
	}

	public Entry<K,V> ceilingEntry(K key) 
	{

		if (key == null) throw new IllegalArgumentException();

		else if (elements.isEmpty())
		{
			return null;
		}

		else
		{
			boolean enc = false;
			Entry<K, V> resultado = null;

			for (Position<Entry<K, V>> cursor = elements.first(); cursor != null && !enc; cursor = elements.next(cursor))
			{
				if(cmp.compare(cursor.element().getKey(), key) >= 0)
				{
					resultado = cursor.element();
					enc = true;
				}
			}
			return resultado;
		}
	}

	public Iterable<K> keys() {
		PositionList<K> aux= new NodePositionList<K>();
		Iterator<Entry<K, V>> it = elements.iterator();
		IndexedList<K> resultado = new ArrayIndexedList<K>();

		if(elements.isEmpty()==false) {
			aux.addFirst(elements.first().element().getKey());
			while(it.hasNext())
				aux.addLast(it.next().getKey());


			Iterator<K> it2 = aux.iterator();
			resultado.add(resultado.size(), aux.first().element());
			while(it2.hasNext())
			{
				int i=resultado.size()-1; boolean esta=false;
				K key= it2.next();
				if(resultado.indexOf(key)!=-1)
					esta=true;
				//ahora que sabemos que no est� recorremos el bucle
				//para saber donde meterlo

				while(i>0&&!esta&&cmp.compare(key, resultado.get(i))<0) {
					i--;
				}
				if(esta==false)
					resultado.add(i,key);
			}
		}
		return resultado;
	}

	public String toString() {
		return elements.toString();
	}
}