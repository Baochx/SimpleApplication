
//求n个数的平均值：先求和再求平均 vs 单个求平均再求和 速度？前者快
public class Test01 {
	public static void main(String[] args) {
			long[] l = new long[1000000];
			for (int i = 0; i < 1000000; i++)
				l[i] = (long)(Math.random() * 1000000);
			double result = 0;
			long start = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++)
				result += l[i];
			long end = System.currentTimeMillis();
			System.out.println((result / 1000000.0) + " time:" + (end - start));
			double result1 = 0;
			long start1 = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++)
				result1 += l[i] / 1000000.0;
			long end1 = System.currentTimeMillis();
			System.out.println(result1 + " time:" + (end1 - start1));
			
	}
}
