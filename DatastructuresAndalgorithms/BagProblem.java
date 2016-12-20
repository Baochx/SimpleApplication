import java.util.HashSet;
import java.util.Set;

public class BagProblem {
	int[] price; //��Ʒ��ֵ
	int[] volume; //��Ʒ���
	int[][] cost;
	
	
	public void compute01Bag(int n, int w) {
		price = new int[n + 1];
		volume = new int[n + 1];
		cost = new int[n+1][w+1];
		for (int i = 1; i <= n; i++) {
			price[i] = (int)(Math.random()*5) + 1; //��Ʒ��ֵ 1~5
			volume[i] = (int)(Math.random()*3) + 1; //��Ʒ��� 1~3
		}
	}
	
	/**
	 * ��̬�滮��������״̬�����״̬ת�ƣ�
	 * ״̬���壺
	 * ת�ƣ�
	 * �߽���������Ʒ��Ϊ0ʱ����ֵΪ0 ����Ϊ0ʱ����ֵΪ0
	 * �����п���ǡ��װ�� Ҳ�п����޷��պ�װ�� ȡ������������ֵ
	 * */
	public int np(int n, int w) {
		
		compute01Bag(n, w); //��ʼ������
		
		//�����㷨
		for (int i = 0; i <= n; i++) //��Ʒ����
			for (int j = 0; j <= w; j++) //������С
				cost[i][j] = i == 0 ? 0 :  //��Ʒ����Ϊ0����ֵΪ0
									(j < volume[i] ? cost[i - 1][j] :  //������СС�ڸ���Ʒ���������������װ�����Ʒ
										max(cost[i - 1][j], cost[i - 1][j - volume[i]] + price[i])); //������С���ڵ�i����Ʒ�������
		
		return cost[n][w];
	}
	
	public int max(int m, int n) {
		if (m > n) return m;
		return n;
	}
	
	//show the data;
	public void play() {
		System.out.printf("%-5s %-5s\n","price","volume");
		for (int i = 1; i < price.length; i++) 
			System.out.printf("%-5d %-5d\n",price[i],volume[i]);

		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++)
				System.out.printf("%2d ",cost[i][j]);
			System.out.println();
		}	
	}

	public static void main(String[] args) {
		MyFibonacci test = new MyFibonacci();
		System.out.println("best value: " + test.np(10, 3));;
		test.play();
		
	}
}
