package chongxue.datastructuresAndalgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InsertionSort {

	//array
	public static void insertSort(Comparable[] a){
		int i, j, n = a.length;
		Comparable key;
		for (i = 1; i < n; i++) {
			key = a[i];
			j = i - 1;
			while (j >= 0 && (a[j].compareTo(key) > 0)) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
	}

	public static void insertSort1(List<Comparable> a) {
		int i ,j, n = a.size();
		Comparable key;
		for (i = 1; i < n; i++) {
			key = a.get(i);
			j = i - 1;
			while (j >= 0 && (a.get(j).compareTo(key) > 0)) {
				a.set(j + 1,a.get(j));
				j--;
			}
			a.set(j + 1, key);
		}
	}
	
	public static void insertSort2(List<Comparable> a) {
		int i ,j, n = a.size();
		Comparable key;
		for (i = 1; i < n; i++) {
			key = a.get(i);
			j = i - 1;
			while (j >= 0 && (a.get(j).compareTo(key) > 0)) 
				j--;
			Collections.rotate(a.subList(j + 1, i + 1), 1);
		}
	}

	//可以从外部传入比较器
	public static void insertSort(List<Comparable> a, Comparator comp) {
		int i ,j, n = a.size();
		Comparable key;
		for (i = 1; i < n; i++) {
			key = a.get(i);
			j = i - 1;
			while (j >= 0 && comp.compare(a.get(j), key) > 0)
				j--;
			Collections.rotate(a.subList(j + 1, i + 1), 1);	
		}
	}
	
	public static void main(String...strings) {
		Integer a[] = {6,9,3,2,8,5,4,7,1};
		int size = a.length;
		List<Comparable> A = new ArrayList<>(); //if < jdk7:List<Integer> A = new ArrayList<Integer>();
		StringBuilder orderResult = new StringBuilder(size * 2); //avoid creating large String instance
		for (int i = 0; i < size; i++) 
			A.add(a[i]); 
		
		insertSort(a);
		for (int e : a) orderResult.append(e).append(" ");
		System.out.println(orderResult);
		//insertSort1(A);
		insertSort2(A);
		System.out.println(A);
	}
	
}
