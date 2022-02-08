package aed.hashtable;

import java.util.Iterator;
import java.util.Vector;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.InvalidKeyException;

/**
 * A hash table implementing using open addressing to handle key collisions.
 */
public class HashTable<K,V> implements Map<K,V> {
	Entry<K, V>[] buckets;
	int size;

	public HashTable(int initialSize) 
	{
		buckets = createArray(initialSize);
		size = 0;
	}

	public boolean containsKey(Object arg0) throws InvalidKeyException {

		int i = 0; 
		boolean found = false; 
		while(i < buckets.length && !found) 
		{
			if(buckets[i] != null) 
			{
				if(buckets[i].getKey().equals(arg0))
					found=true;
			}
			i++;
		}
		return found;
	}
	
	public V put(K key, V value) throws InvalidKeyException 
	{
		if (buckets[ index(key)] == null) 
		{

			buckets[ index(key)] = new EntryImpl<>(key, value);
			size++;
			return null;
		}

		if (buckets[ index(key)].getKey().equals(key)) 
		{

			V valorOld = buckets[ index(key)].getValue();

			buckets[ index(key)] = new EntryImpl<>(key, value);
			return valorOld;
		}

		int indice =  rehashIndice(index(key), key);
		if (indice == -1) {      
			rehash();   
			indice = index(key);
			if (buckets[indice] != null) 
			{
				if (buckets[indice].getKey().equals(key)) 
				{
					V valorOld = buckets[indice].getValue();
					buckets[indice] = new EntryImpl<>(key, value);
					return valorOld;
				}
				indice = rehashIndice(indice, key);
			}
		}

		if (buckets[indice] != null) 
		{
			V valorOld = buckets[indice].getValue();
			buckets[indice] = new EntryImpl<>(key, value);
			return valorOld;
		}

		buckets[indice] = new EntryImpl<>(key, value);
		size++;
		return null;
	}


	public V get(K key) throws InvalidKeyException 
	{    
		int indice = index(key);   
		if (buckets[indice] == null) 
		{
			return null;
		}

		if (buckets[indice].getKey().equals(key))
		{
			return buckets[indice].getValue();
		}

		indice = search(key, indice);
		if (indice != -1) 
		{
			return buckets[indice].getValue();
		} 
		return null;
	}


	public V remove(K key) throws InvalidKeyException 
	{  
		if (buckets[index(key)] == null) 
		{
			return null;
		}

		if (buckets[index(key)].getKey().equals(key)) 
		{  
			V value = buckets[index(key)].getValue();
			buckets[index(key)] = null;
			size--;      
			seguirOk(index(key));
			return value;
		}

		int indice = search(key, index(key));
		if ((indice != -1) && buckets[indice].getKey().equals(key)) 
		{  
			V value = buckets[indice].getValue();
			buckets[indice] = null;
			size--;
			seguirOk(indice);
			return value;
		}  
		return null;
	}


	public Iterable<K> keys() 
	{    
		Vector<K> result = new Vector<K>();
		for (Entry<K, V> entries : buckets) 
		{   
			if (entries != null) 
			{
				result.add(entries.getKey());
			}
		}
		return result;
	}

	public Iterable<Entry<K, V>> entries()
	{  
		Vector<Entry<K, V>> result = new Vector<>();
		for (Entry<K, V> entries : buckets) 
		{  
			if (entries != null) 
			{
				result.add(entries);
			}
		}
		return result;
	}

	public int size()
	{
		return size;
	}


	public boolean isEmpty() 
	{
		return size == 0;
	}

	
	public Iterator<Entry<K, V>> iterator() 
	{
		return entries().iterator();
	}

	// Métodos auxiliares empleados:

	private int index(K key) 
	{
		return Math.abs(key.hashCode()) % buckets.length;
	}

	private boolean isFull() {
		return size == buckets.length;
	}
	private int rehashIndice(int indice, K key) 
	{   
		if (isFull()) //está lleno
		{
			return -1;
		}

		int nuevoIndice = (indice + 1) % buckets.length;
		while ((nuevoIndice != indice) && (buckets[nuevoIndice] != null)) 
		{

			if (buckets[nuevoIndice].getKey().equals(key)) 
			{
				break;
			}
			nuevoIndice = (nuevoIndice + 1) % buckets.length;
		}

		if (nuevoIndice == indice) 
		{
			return -1;
		} 

		else 
		{
			return nuevoIndice;
		}
	}


	private int search (K key, int indice) 
	{
		int indiceFinal = (indice + 1) % buckets.length;
		while (indiceFinal != indice) 
		{

			if (buckets[indiceFinal] != null) 
			{

				if (buckets[indiceFinal].getKey().equals(key)) 
				{
					break;
				}
			}
			indiceFinal = (indiceFinal + 1) % buckets.length;
		}

		if (indiceFinal != indice) 
		{
			return indiceFinal;
		}  
		return -1; //si están todos llenos -
	}


	@SuppressWarnings("unchecked")
	private void rehash () 
	{

		Entry<K, V>[] old = buckets;   
		buckets = new Entry[old.length * 2];
		size = 0;  
		for (int i=0; i <old.length; i++) 
		{
			put(old[i].getKey(), old[i].getValue());
		}
	}


	private void seguirOk(int indice) 
	{

		int indice1 = (indice + 1) % buckets.length;
		int hueco = indice;
		while (indice1 != indice)
		{

			if (buckets[indice1] != null) 
			{
				if (index(buckets[indice1].getKey()) == hueco) 
				{

					buckets[hueco] = buckets[indice1];
					hueco = indice1;
				}
			}

			indice1 = (indice1 + 1) % buckets.length;
		}
	}

	@SuppressWarnings("unchecked")
	private Entry<K, V>[] createArray(int size) 
	{
		return (Entry<K, V>[]) new Entry[size];
	}



}