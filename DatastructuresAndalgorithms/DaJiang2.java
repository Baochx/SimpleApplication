import java.util.Scanner;

public class DaJiang2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int v = scanner.nextInt(); //v Ϊ�������
		scanner.close();
		int n = 0; //nΪ���Ĳ��� 
		int m = 0; //Ҷ�ӽڵ����
		boolean full = false; //�Ƿ�Ϊ������ȫ������
		//1.�Ƿ�Ϊ�������� 2.����n�Ƕ���
		for (int i = 1; i <= v; i++) {
			int temp = (1 << i)- 1; //i��������ȫ�������Ķ������
			if (temp == v)  {
				full = true;
				n++; 
				break;
			} else if (temp > v) {
				n++;
				break;
			}
				n++;
		}
		if (!full) {
			int last = v - ( (1 << (n - 1)) - 1); //��ײ��Ҷ�Ӹ��� = ���еĶ������ - �����ڶ��㼰���ϵ�(����������)
			int lastFather = 0; //�����ڶ������ӽڵ�Ľڵ���
			if ((last & 1) == 1) lastFather = last / 2 + 1; //��ײ�Ҷ����Ϊ����ʱ
			else lastFather = last / 2; //��ײ�Ҷ����Ϊż��ʱ
			int lastSecondLeaves = 0; //�����ڶ���û���ӽڵ�Ľڵ���
			if (2 * (n - 2)  > 1) //Ԥ���ڵ���Ϊ2�����
				lastSecondLeaves = 2 * (n - 2) - lastFather;
			m = last + lastSecondLeaves;
			} else { //����������
				m = 2 * (n - 1);
			}	 
			System.out.println(n + " " + m);
	}
}