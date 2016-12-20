package chongxue.datastructuresAndalgorithms;

import java.util.Collections;
import java.util.ArrayList;

public class PriorityQueue {
	private ArrayList<Comparable> heap;
	private int heapSize;
	
	public PriorityQueue(){
		heapSize = 0;
		heap = new ArrayList<>(); 
	}
	
	public void enQueue(Comparable e){
		int i = heapSize++; //first time: i = 0; don't be blinded;
		heap.add(e); //index = i - 1
		while (i > 0 && heap.get(i).compareTo(heap.get(HeapSort.parent(i))) > 0) {
			Collections.swap(heap, HeapSort.parent(i), i);
			i = HeapSort.parent(i); 
		}
	}
	
	public Comparable deQueue() {
		if (heapSize < 1)
			return null;
		Comparable top = heap.get(0);
		heapSize--;
		heap.set(0, heap.get(heapSize));
		heap.remove(heapSize);
		HeapSort.heapiey(heap, 0, heapSize);
		return top;
	}
	
	public boolean empty(){
		return heapSize <= 0;
	}

	public static void main(String[] args) {
		int[] a = {6,5,9,8,79,3,1,2,56,9,8};
		PriorityQueue q = new PriorityQueue();
		for (int i = 0; i < a.length; i++)
			q.enQueue(a[i]);
		while(!q.empty())
			System.out.print(q.deQueue() + " ");
	}

}

