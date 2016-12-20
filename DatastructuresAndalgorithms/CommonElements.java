import java.util.*;

public class CommonElements {
	
	//�õݹ�ʵ�ְ�һ�������������
	public static String f(int n) {
		if (n == 0) return "";
		return n % 10 + "" + f(n/10);
	}
	
	//�������ȶ��Ĺ鲢����
	public static void merge(int[] n, int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		
		int[] L = new int[n1]; 
		int[] R = new int[n2];
		
		int i = 0, j = 0, k = l;
		while (i < n1) L[i++] = n[k++];
		while (j < n2) R[j++] = n[k++];
		
		i = j = 0;//����
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
			merge(a, l, m, r); //�ϲ����ߵĽ��
		}
	}
	
	public static void common(List<Integer> list, int[] m, int[] n) {
		int i = 0, j = 0;//����
		while (i < m.length && j < n.length)
			//���m[i]С��m����ѡ����һ��Ԫ�ؽ��бȽ�
			if (m[i] < n[j]) i++;
			else if (m[i] > n[j]) j++;
			//���ʱ������Ŀ�걣��list�����ѡ��һ�����������һ��Ԫ��
			else {
				if (!list.contains(m[i]))
					list.add(m[i]);
				if (Math.random() < 0.5) j++; //n���������һ��Ԫ��
			}
	}
	
	public static Object[] getCommon(int[] m, int[] n) { 
		List<Integer> list = new ArrayList<>();
		mergeSort(m, 0, n.length - 1); //��Χ����Ч��Χ ע��Խ�����
		mergeSort(n, 0, n.length - 1);
		common(list, m, n);
		return list.toArray();
	}

	//�õ�������������ͬ��Ԫ��
	public static Object[] test1(int[] m, int[] n) {
		return getCommon(m, n);
	}

	public static Object[] test2(int[] m, int[] n) {
		Set<Integer> set1 = new TreeSet<>();
		Set<Integer> set2 = new TreeSet<>();
		for (int i = 0; i < m.length; i++) set1.add(m[i]);
		for (int i = 0; i < n.length; i++) set2.add(n[i]);
		
		//1.����Integer[]�޷�ֱ��ת��Ϊint[],������Ҫ��ת��ΪInteger[]��ת��Ϊint[]
		/*Integer[] m1 = set1.toArray(new Integer[0]);
		Integer[] n1 = set2.toArray(new Integer[0]);
		int[] m2 = new int[m1.length];
		int[] n2 = new int[n1.length];
		for (int i = 0; i < m1.length; i++)
			m2[i] = m1[i];
		for (int i = 0; i < n1.length; i++)
			n2[i] = n1[i];*/

		//2.ֱ�Ӵ�set -> array,ע��setû��get������ԭ���ǣ�set������ģ��̶���index���ܵõ���ͬ��value
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
