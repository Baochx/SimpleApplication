import java.util.*;

public class PrimeNumber {

	public static void main(String[] args) {
		test2(100);
	}
	
	//判断一个数是否是素数
	public static boolean isPrime(int N) {
		boolean isPrime = true;
		if (N < 2 ) isPrime = false;
		if (N == 2) isPrime = true; //最小的素数是2
		for (int i = 2; i * i <= N; i++) //注意临界条件是小于等于
			if (N % i == 0)  isPrime = false;
		return isPrime;
	}

	//方法1：通过公式剔除一些不可能是素数的数字
	public static void test1(int n) {
		List<Integer> list = new ArrayList<>();
		int N = n;
		int count = 0;

		//limit是用于遍历N以内素数的最小值
		//(通过公式6 * limit + (0~5)，所有的数都能通过这个公式计算出，但6 * limit + (0,2,3,4)都不是素数)
		int limit = (N - 1) / 6;

		//注意临界条件依然是<=,等于limit时,(6 * limit + 0 + 1) = N Or (6 * limit + 1...5 + 1)等于N(+1是因为N-1，1...5是/6的余数),
		//比如12 = 6 * 1 + 5 + 1,11 = 6 * 1 + 4 + 1，10 = 6 * 1 + 3 + 1，9 = 6 * 1 + 2 + 1，8 = 6 * 1 + 1 + 1，7 = 6 * 1 + 0 + 1，
		//此时最后只要考虑(6 * limit + 4 + 1)会 > N，要进行(6 * limit + 5)验证，不要考虑(6 * limit + 5 + 1)，余数是1,2,3也是不用考虑的
		for (int i = 1; i <= limit; i++) {
			int x1 = 6 * i + 1;
			if (isPrime(x1)) list.add(x1);
			int x2 = 6 * i + 5;
			if (isPrime(x2) && x2 <= N) list.add(x2);
		}
		list.forEach(System.out :: println);
		System.out.println("[6, " + N + "]" + "范围内的素数个数" + list.size());
	}

	//方法2：以空间换时间
	public static void test2(int n) {
		BitSet bits = new BitSet(n); //数字标记位图，已经标记为true的表示不可能是素数
		List<Integer> list = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (!bits.get(i)) {
				list.add(i);
				int j = i;
				for (; j <= n; j += i) 
					bits.set(j); //素数的倍数一定不是素数
			}
		}
		list.forEach(prime -> System.out.printf("%d ", prime));
		System.out.println("[2, " + n + "]" + "范围内的素数个数" + list.size());
	}

}
