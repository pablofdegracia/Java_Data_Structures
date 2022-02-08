package aed.pqueue;

import java.util.Arrays;
import es.upm.aedlib.Entry;

import es.upm.aedlib.priorityqueue.*;

public class Utils {
	
	public static void sort(int[] arr) {
		PriorityQueue<Integer,Integer> pq = 
				new HeapPriorityQueue<>();
		for (int i=0; i<arr.length; i++)
			pq.enqueue(arr[i], null);
		for (int i=0; i<arr.length; i++) {
			Entry<Integer,Integer> entry = pq.dequeue();
			arr[i] = entry.getKey();
		}
	}

	public static void main(String[] args) {
		int[] testArr = new int[] {1,1,2,20,3,6,2,1,3,4,6,7};
		System.out.println("The array "+Arrays.toString(testArr));
		sort(testArr);
		System.out.println(" is sorted "+Arrays.toString(testArr));
	}

}
