package chongxue.datastructuresAndalgorithms;

/*
 *This is a MergeSort Demo;
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSort{
	
	//----array----------------------------------------------------
	
	public static void merge(Comparable[] a, int p, int q, int r) {
		int i, j, k,
		n1 = p - q + 1,
		n2 = r - q;
		
		Comparable[] L = Arrays.copyOfRange(a, p, q + 1),R = Arrays.copyOfRange(a, q + 1, r + 1);
		i = j = 0;
		k = p;
		while (i < n1 && j < n2)
			if(L[i].compareTo(R[j]) < 0)
				a[k++] = L[i++];
			else 
				a[k++] = R[j++];

		if (i < n1)
			while (i < n1) 
				a[k++] = L[i++];
		if (j < n2)
			while (j < n2)
				a[k++] = R[j++];

	}
	
	//---linear container-------------------------------------------
	
	public static void merge(List<Comparable> a, int p, int q, int r) {
	int i, j, k,
	n1 = q - p + 1,
	n2 = r - q;
	Comparable[] L = new Comparable[n1]; 
	Comparable[] R = new Comparable[n2];

    for (i = 0; i < n1; i++) {
    	L[i] = a.get(p + i);
    }
    	
    for (j = 0; j < n2; j++) {
    	R[j] = a.get(q + 1 + j);
    }

	i = j = 0;
	k = p;
	while (i < n1 && j < n2)
		if(L[i].compareTo(R[j]) > 0) //descending order
			a.set(k++, L[i++]);
		else
			a.set(k++, R[j++]);

	if (i < n1)
		for (; i < n1; i++) //equivalent to: while (i < n1)
			a.set(k++, L[i]);
	
	if (j < n2)
		for (; j < n2; j++) //equivalent to: while (j < n2)
			a.set(k++, R[j]);
	}

	public static void mergeSort(List<Comparable> a, int p, int r){
		if (p < r) { //don't misss this condition
			int q = (p + r) / 2; //equivalent to: (int)Math.floor((p + r) / 2.0)
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r); //merge the part sort reuslt
		}
	}
	
	
	//---pass Outer Comparator----------------------------------
	
	public static void merge(List<Comparable> a, int p, int q, int r, Comparator comp) {
		int i, j, k,
		n1 = p - q + 1,
		n2 = r - q;
		Comparable[] L = new Comparable[n1]; 
		Comparable[] R = new Comparable[n2];

	    for (i = 0; i < n1; i++) {
	    	L[i] = a.get(p + i);
	        p++;
	    }
	    	
	    for (j = 0; j < n2; j++) {
	    	R[j] = a.get(q + 1 + j);
	    }

		i = j = 0;
		k = p;
		while (i < n1 && j < n2)
			if(comp.compare(L[i], R[j]) < 0)
				a.set(k++, L[i++]);
			else 
				a.set(k++, R[j++]);

		if (i < n1)
			while (i < n1) 
				a.set(k++, L[i++]);
		if (j < n2)
			while (j < n2)
				a.set(k++, R[j++]);
	}
	
	public static void main(String[] args) {
		int a[] = {6,9,3,2,8,5,4,7,1};
		int size = a.length;
		List<Comparable> A = new ArrayList<>(); //if < jdk7:List<Integer> A = new ArrayList<Integer>();
		StringBuilder orderResult = new StringBuilder(size * 2); //avoid creating large String instance
		for (int i = 0; i < size; i++) 
			A.add(a[i]); //Autoboxing: int -> Integer
		mergeSort(A, 0, size - 1);
		for (int i = 0; i < size - 1; i++) 
			orderResult.append(A.get(i)).append(" ");
		orderResult.append("\n");
		System.out.println(orderResult);
	}
	
}
