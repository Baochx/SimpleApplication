import java.util.Scanner;

public class DaJiang1 {

	private static int[][] nm;

	public static void main(String...args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String line1 = scan.nextLine();
			String[] s1 = line1.split(" ");
			int n = Integer.parseInt(s1[0]);
			int m = Integer.parseInt(s1[1]);
			int q = Integer.parseInt(s1[2]);
			nm = new int[n][m]; //n * m 的矩阵 每组测试都会重新生成，虽然是static,但不会相互影响
			int[] result = new int[q]; //每次测试的结果集
			int count = 0; //求和次数
			//接下来是处理q条命令
			for (int i = 0; i < q; i++) {
				String line2 = scan.nextLine();
				String[] s2 = line2.split(" ");
				//判断是求和还是增值命令:0 增值 1 求和
				int command = Integer.parseInt(s2[0]);
				if (command == 0) {
					int x0 = Integer.parseInt(s2[1]);
					int y0 = Integer.parseInt(s2[2]);
					int x1 = Integer.parseInt(s2[3]);
					int y1 = Integer.parseInt(s2[4]);
					int k = Integer.parseInt(s2[5]);
					for (int h = x0 - 1; h <= x1 - 1; h++) {
						for (int j = y0 - 1; j <= y1 - 1; j++) {
							nm[h][j] += k;
						}
					}
				} else if (command == 1) {
					int x0 = Integer.parseInt(s2[1]);
					int y0 = Integer.parseInt(s2[2]);
					int x1 = Integer.parseInt(s2[3]);
					int y1 = Integer.parseInt(s2[4]);
					for (int h = x0 - 1; h <= x1 - 1; h++) {
						for (int j = y0 - 1; j <= y1 - 1; j++) {
							result[count] += nm[h][j];
						}
					}
					count++; //这里第一次弄错了，导致输不出结果
				} //一次查询求和
			} //q次循环

			for (int i = 0; i < count; i++) {
				System.out.println(result[i]);
			}
		}//while循环
	} //main循环

}