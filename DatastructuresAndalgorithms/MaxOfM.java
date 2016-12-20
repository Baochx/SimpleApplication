/**
 * @author Chongxue
 * ���㷨���ڽ����һ�������ָ����Χ��Ѱ�ҵ�M�����
 * �㷨˼·��
 * 1.ȷ��������Ŀ��������Χ�����ÿ��ŵ�ͬʱ���бȽ���������λ���ǲ��ǵ�M��ģ����򷵻أ����Ǿͼ����Ӻ��ʵ�һ������ݹ�
 * 2.�ѵ����ڣ��жϸô��������һ�࿪ʼ����Ѱ�ң������ǵ�(i - l + 1)��ģ������ǵ�i�����Ҫ��ȷ�������i����������ֵ
 * 3.���׻�������,��� m > (i - 1 + 1) �����ұ߼����ݹ���� ����ʱ��m = m - (i - l + 1)
 * 
 * �Ż��ĵط���
 * 1.����ѡ��������ģ�Ȼ�������ұ߽��н�����һֱѡr��Ϊ����(��ͬ�����ѡ������)
 * 
 * С��:
 * 1.Ѱ�ҵ�M��Ӧ���ǰ��ݼ���������ǵ���
 * 2.���ŵݹ��з���intֵ��ÿ�ζ���һ��target��ʱ���������������أ�������if����ֱ����return���
 * */
public class MaxOfM {
	
	//start,end���鷶Χ; m��ʾ�ڼ���(����������ظ�����Ҫ��ԭ����˳��Ҳ���ǲ�Ҫ���ظ�ʱԭ���ȳ��ֵ�����ѡ����Ϊ�����ǲ��ȶ���)
	public static int maxOfM(int[] n, int start, int end, int m) throws Exception {
		if (n == null || start <= 0 || m <= 0 || end > n.length || m > (end - start + 1)) throw new Exception("Illegal Input!");
		return quick(n, start - 1, end - 1,m);
	}
	
	public static int quick(int[] n, int l, int r, int m) {
		int index = l + (int)(Math.random() * (r - l + 1)); //Ѱ������λ����Ϊ��
		swap(n, r, index);
		int temp = n[r];
		int i = l, j = r;
		while (i < j) {
			while(i < j && n[i] >= temp) i++; //���ڵ��ڵ������
			n[j] = n[i];
			while(i < j && n[j] < temp) j--; //С�ڵ����ұ�
			n[i] = n[j];
		}
		n[i] = temp;
		if ((i - l + 1) == m) return n[i];
		else if ((i - l + 1) < m) return quick(n, i + 1, r, m - (i - l + 1));
		else return quick(n, l, i - 1, m);
	}
	
	public static void swap(int[] n, int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}

	public static void main(String[] args) {
		int[] n = {1,2,3,4,7,6,3333,1,1,8};
		try {
			System.out.println(maxOfM(n, 2, 5, 2));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
