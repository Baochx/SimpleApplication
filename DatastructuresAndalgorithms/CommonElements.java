import java.util.*;

public class CommonElements {
	
	//用递归实现把一个整数逆序输出
	public static String f(int n) {
		if (n == 0) return "";
		return n % 10 + "" + f(n/10);
	}
	
	//以下是稳定的归并排序
	public static void merge(int[] n, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		
		int[] L = new int[n1]; 
		int[] R = new int[n2];
		
		int i = 0, j = 0, k = l;
		while (i < n1) L[i++] = n[k++];
		while (j < n2) R[j++] = n[k++];
		
		i = j = 0;//重置
		while (i < n1 && j < n2)
			if (L[i] <= R[j]) n[l++] = L[i++];
			else n[l++] = R[j++];
		
		while (i < n1) 
			n[l++] = L[i++];
		while (j < n2) 
			n[l++] = R[j++];
	}
	
	public static void mergeSort(int[] a, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(a, l, m);
			mergeSort(a, m + 1, r);
			merge(a, l, m, r); //合并两边的结果
		}
	}
	
	public static void common(List<Integer> list, int[] m, int[] n) {
		int i = 0, j = 0;//重置
		while (i < m.length && j < n.length)
			//如果m[i]小，m数组选择下一个元素进行比较
			if (m[i] < n[j]) i++;
			else if (m[i] > n[j]) j++;
			//相等时，加入目标保存list并随机选择一个数组进入下一个元素
			else {
				if (!list.contains(m[i]))
					list.add(m[i]);
				if (Math.random() < 0.5) j++; //n数组进入下一个元素
			}
	}
	
	public static Object[] getCommon(int[] m, int[] n) { 
		List<Integer> list = new ArrayList<>();
		mergeSort(m, 0, n.length - 1); //范围是有效范围 注意越界情况
		mergeSort(n, 0, n.length - 1);
		common(list, m, n);
		return list.toArray();
	}

	//得到两个数组中相同的元素
	public static Object[] test1(int[] m, int[] n) {
		return getCommon(m, n);
	}

	public static Object[] test2(int[] m, int[] n) {
		Set<Integer> set1 = new TreeSet<>();
		Set<Integer> set2 = new TreeSet<>();
		for (int i = 0; i < m.length; i++) set1.add(m[i]);
		for (int i = 0; i < n.length; i++) set2.add(n[i]);
		
		//1.由于Integer[]无法直接转换为int[],所以需要先转换为Integer[]再转换为int[]
		/*Integer[] m1 = set1.toArray(new Integer[0]);
		Integer[] n1 = set2.toArray(new Integer[0]);
		int[] m2 = new int[m1.length];
		int[] n2 = new int[n1.length];
		for (int i = 0; i < m1.length; i++)
			m2[i] = m1[i];
		for (int i = 0; i < n1.length; i++)
			n2[i] = n1[i];*/

		//2.直接从set -> array,注意set没有get方法的原因是：set是无序的，固定的index可能得到不同的value
		int len1 = set1.size();
		int len2 = set2.size();
		int[] m1 = new int[len1];
		int[] n1 = new int[len2];
		int i = 0, j = 0;
		Iterator<Integer> it1 = set1.iterator();
		while (it1.hasNext())
			m1[i++] = it1.next();
		Iterator<Integer> it2 = set2.iterator();
		while (it2.hasNext())
			n1[j++] = it2.next();

		List<Integer> list = new ArrayList<>();
		common(list, m1, n1);
		return list.toArray();
	}

	public static void main(String[] args) {
		int[] m = {1,2,4,5,3,2,34,9};
		int[] n = {2,3,5,3,2,89};
		for (Object o : test2(m, n)) System.out.print(o + " ");
		System.out.println();
	}

}
