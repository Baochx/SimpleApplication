package chongxue.datastructuresAndalgorithms;

import java.util.*;

public class QuickSort {
	
	public static int partition(List<Comparable> a, int p, int r) {
	Comparable x = a.get(r); //take right index as axis;
	int j, i = p;
	for (j = p; j <= r; j++)
		if (a.get(j).compareTo(x) <= 0)
		    Collections.swap(a, i++, j); //the value at i more than the x;
		return i - 1;
	} 
	
	public static int randomizedPartition(List<Comparable> a, int p, int r) {
		int i = p + (int)((double)(r - p) * Math.random());
		Collections.swap(a, i, r);
		return partition(a, p, r);
	}	

	public static void quickSort(List<Comparable> a, int p, int r){
		if (p < r) {
			int q = randomizedPartition(a, p, r);
			quickSort(a, p, q);
			quickSort(a, q + 1, r);
		}
	}
	
	public static void main(String[] args) {
		int a[] = {6,9,3,2,8,5,4,7,1};
		int size = a.length;
		ArrayList<Integer> A = new ArrayList<>();
		for (int i = 0; i < size; i++) 
			A.add(a[i]);
		quickSort((List)A, 0, size - 1);
		for (int i = 0; i < size; i++) 
			System.out.print(A.get(i)+ " ");
		    System.out.println();
	}
	
}