import java.util.*;

public class PrimeNumber {

	public static void main(String[] args) {
		test2(100);
	}
	
	//�ж�һ�����Ƿ�������
	public static boolean isPrime(int N) {
		boolean isPrime = true;
		if (N < 2 ) isPrime = false;
		if (N == 2) isPrime = true; //��С��������2
		for (int i = 2; i * i <= N; i++) //ע���ٽ�������С�ڵ���
			if (N % i == 0)  isPrime = false;
		return isPrime;
	}

	//����1��ͨ����ʽ�޳�һЩ������������������
	public static void test1(int n) {
		List<Integer> list = new ArrayList<>();
		int N = n;
		int count = 0;

		//limit�����ڱ���N������������Сֵ
		//(ͨ����ʽ6 * limit + (0~5)�����е�������ͨ�������ʽ���������6 * limit + (0,2,3,4)����������)
		int limit = (N - 1) / 6;

		//ע���ٽ�������Ȼ��<=,����limitʱ,(6 * limit + 0 + 1) = N Or (6 * limit + 1...5 + 1)����N(+1����ΪN-1��1...5��/6������),
		//����12 = 6 * 1 + 5 + 1,11 = 6 * 1 + 4 + 1��10 = 6 * 1 + 3 + 1��9 = 6 * 1 + 2 + 1��8 = 6 * 1 + 1 + 1��7 = 6 * 1 + 0 + 1��
		//��ʱ���ֻҪ����(6 * limit + 4 + 1)�� > N��Ҫ����(6 * limit + 5)��֤����Ҫ����(6 * limit + 5 + 1)��������1,2,3Ҳ�ǲ��ÿ��ǵ�
		for (int i = 1; i <= limit; i++) {
			int x1 = 6 * i + 1;
			if (isPrime(x1)) list.add(x1);
			int x2 = 6 * i + 5;
			if (isPrime(x2) && x2 <= N) list.add(x2);
		}
		list.forEach(System.out :: println);
		System.out.println("[6, " + N + "]" + "��Χ�ڵ���������" + list.size());
	}

	//����2���Կռ任ʱ��
	public static void test2(int n) {
		BitSet bits = new BitSet(n); //���ֱ��λͼ���Ѿ����Ϊtrue�ı�ʾ������������
		List<Integer> list = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (!bits.get(i)) {
				list.add(i);
				int j = i;
				for (; j <= n; j += i) 
					bits.set(j); //�����ı���һ����������
			}
		}
		list.forEach(prime -> System.out.printf("%d ", prime));
		System.out.println("[2, " + n + "]" + "��Χ�ڵ���������" + list.size());
	}

}
