import java.util.*;

public class SortPractice {

	private static Random rand = new Random();

	public static void main(String[] args) {
		int[] arr = {5,4,3,2,1,7,3,4,6,4,5};
		mergeSort(arr, 0, arr.length - 1);
		print(arr);

	}


	//交换排序：冒泡排序 稳定
	public static void bubbleSort(int[] arr, int l, int r) {
		for (int i = l; i < r; i++) { //循环次数比长度少1
			for (int j = l; j < r - i; j++) { //第一次循环的最后是最末尾一个元素index - 1，for j + 1
				if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
			}
		}
	}

	//交换排序：快速排序 不稳定
	public static void quickSort(int[] arr, int l, int r) {
		//优化1：中轴的选择随机
		int index = l + rand.nextInt(r - l + 1); //l ~ r之间的随机数
		//System.out.println(index + " ");
		//int index = r;
		int temp = arr[index];
		int i = l, j = r;
		//等于的就放在一边
		while (i < j) {
			while (i < j && arr[i] < temp) i++;
			arr[index] = arr[i]; //arr[i] >= temp
			index = i;
			while (i < j && arr[j] >= temp) j--; 
			arr[index] = arr[j]; //arr[j] < temp
			index = j;
		}
		arr[index] = temp;
		if (i - 1 > l) quickSort(arr, l, i - 1);
		if (i + 1 < r) quickSort(arr, i + 1, r);
	}

	//交换排序：快速排序 不稳定 
	public static void quickSort_(int[] arr, int l, int r) {
		//优化1：中轴的选择随机
		int index = l + rand.nextInt(r - l + 1); //l ~ r之间的随机数
		swap(arr, index, r); //将数组最右边那个元素和随机选出的那个元素进行交换
		int temp = arr[r];
		int i = l, j = r;
		//等于的就放在一边
		while (i < j) {
			while (i < j && arr[i] < temp) i++;
			arr[j] = arr[i]; //arr[i] >= temp
			while (i < j && arr[j] >= temp) j--; 
			arr[i] = arr[j]; //arr[j] < temp
		}
		arr[j] = temp;
		if (i - 1 > l) quickSort(arr, l, i - 1);
		if (i + 1 < r) quickSort(arr, i + 1, r);
	}

	//插入排序：直接插入排序 稳定
	public static void insertSort(int[] arr, int l, int r) {
		for (int i = l; i < r; i++) {
			int j = i + 1;
			int key = arr[j];
			while (j > l && arr[j - 1] > key)  {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = key;
		}
	}

	// 插入排序：希尔排序

	// 选择排序：直接选择排序 其实也是稳定的
	public static void selectSort(int[] arr, int l, int r) {
		for (int i = l; i < r; i++) {
			int min = i;
			for (int j = i + 1; j <= r; j++) {
				if (arr[j] < arr[min]) min = j; //只选择比它小的
			}
			if (min != i) swap(arr, min, i);
		}
	}

	//选择排序：堆排序

	//归并排序
	public static void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			int q = (l + r) / 2;
			mergeSort(arr, l, q);
			mergeSort(arr, q + 1, r);
			merge(arr, l, q, r);
		}
	}

	//归并排序辅助函数
	public static void merge(int arr[], int l, int q, int r) {
		int len1 = q - l + 1; //l ~ q
		int len2 = r - q;     //q + 1 ~ r
		int[] A = new int[len1];
		int[] B = new int[len2];
		int k = l, i = 0, j = 0;;
		for (; i < len1; i++)
			A[i] = arr[k++];
		for (; j < len2; j++)
			B[j] = arr[k++];
		k = l;
		i = j = 0;
		while (i < len1 && j < len2) {
			if (A[i] <= B[j]) 
				arr[k++] = A[i++];
			else arr[k++] = B[j++];
		}
		while (i < len1) arr[k++] = A[i++];
		while (j < len2) arr[k++] = B[j++];
	}

	//基数排序 是稳定的 时间复杂度:
	public static void radixSort(int[] arr, int l, int r) {
		int length = r - l + 1;
		int[] temp = new int[length]; //辅助数组
		int[] count = new int[10];
		
		int k = 1, radix = 10;
		int max = getMax(arr, l, r);
		while ((max = max / 10) != 0) 
			k++;

		for (int j = 0; j < k; j++) {
			for (int i = 0; i < 10; i++) 
				count[i] = 0; //计数器清零

			for (int i = 0; i < length; i++)
				count[arr[i] % radix]++;

			for (int i = 1; i < 10; i++) 
				count[i] += count[i - 1]; //排序的关键

			for (int i = length - 1; i >= 0; i--)
				temp[count[arr[i] % radix]--] = arr[i]; 

			for (int i = 0; i < length; i++)
				arr[i] = temp[i];

			radix *= 10;
		}
	} 

	//get the max element in a array
	public static int getMax(int[] arr, int l, int r) {
		int max = 0;
		for (int i = l; i <= r; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	//swap method
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//print method
	public static void print(int[] arr) {
		StringBuilder sb = new StringBuilder();
		int length = arr.length;
		for (int i = 0; i < length; i++)
			sb.append(arr[i] + " ");
		sb.append("\n");
		System.out.println(sb);
	}

}