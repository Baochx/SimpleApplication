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
			nm = new int[n][m]; //n * m �ľ��� ÿ����Զ����������ɣ���Ȼ��static,�������໥Ӱ��
			int[] result = new int[q]; //ÿ�β��ԵĽ����
			int count = 0; //��ʹ���
			//�������Ǵ���q������
			for (int i = 0; i < q; i++) {
				String line2 = scan.nextLine();
				String[] s2 = line2.split(" ");
				//�ж�����ͻ�����ֵ����:0 ��ֵ 1 ���
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
					count++; //�����һ��Ū���ˣ������䲻�����
				} //һ�β�ѯ���
			} //q��ѭ��

			for (int i = 0; i < count; i++) {
				System.out.println(result[i]);
			}
		}//whileѭ��
	} //mainѭ��

}