package chongxue.datastructuresAndalgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapSort {
	
	public static void heapSort(List<Comparable> a){
		buildMaxHeap(a);
		int heapSize = a.size();
		for (int i = heapSize - 1; i >= 1; i--) { //heapSize=10, max index = 9£»
			Collections.swap(a, i, 0); //swap the root and the last;
			heapSize--; //poll the last(max in the heap)
			heapiey(a, 0, heapSize); //maintain the heap
		}
	}
	
	public static void buildMaxHeap(List<Comparable> a){
		int heapSize = a.size();
		int lastParent = heapSize % 2 == 1 ? (heapSize - 2) / 2 : (heapSize - 1) / 2; //if your first index isn't 0, take another way.
		for (int i = lastParent; i >= 0; i--) 
			heapiey(a,i, heapSize);
	}
	
	public static void heapiey(List<Comparable> a, int i, int heapSize) {
		int max;
		int l = left(i); 
		int r = right(i);
		
		if (l < heapSize && a.get(l).compareTo(a.get(i)) > 0)
			max = l;
		else max = i;
		if (r < heapSize && a.get(r).compareTo(a.get(max)) > 0)
			max = r;
		if(max != i) {
			Collections.swap(a, i, max);
			heapiey(a, max, heapSize); //maintaining order: bottom-up
		}
	}
	
	//in this case, the first index is 0
	public static int left(int i) { 
		return (i + 1) * 2 - 1;
	}
	
	public static int right(int i) {
		return (i + 1) * 2;
	}
	
	public static int parent(int i) {
		if (i % 2 == 1) 
			return i / 2;
		return i / 2 - 1;
	}

	public static void main(String[] args) {
		int h[] = {0,3,2,10,12,13,14,9,8,7,5,4,1,6};
		List<Comparable> A = new ArrayList<>();
		for (int i = 0; i < h.length; i++)
			A.add(h[i]); //×Ô¶¯×°Ïä
		heapSort(A);
		System.out.println(A);
	}

}

