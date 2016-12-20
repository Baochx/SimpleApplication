public class BitTest {
	public static void main(String[] args) {
		test6();
	}
	//�����ڼ���������Բ������ʽ�����
	public static void test1() {
		//�õ�������������ַ�ʽ
		System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
		System.out.println("-1 >>> 1:          " + (-1 >>> 1)); //�޷���ѭ�����ƣ���λ��0
		System.out.println("((1 << 31) - 1):   " + ((1 << 31) - 1));
		System.out.println("((1 << 31) + 1):   " + ((1 << 31) + 1));
		System.out.println("(1 << 31):         " + (1 << 31));
		System.out.println("(1 << 31):         " + Integer.toBinaryString(1 << 31));
		System.out.println("((1 << 31) + 1):   " + Integer.toBinaryString((1 << 31) + 1));
		System.out.println("-1:                " + Integer.toBinaryString(-1)); //-1�ڼ�����еĲ����ʾ��ʽ
		System.out.println("Integer.MAX_VALUE: " + Integer.toBinaryString(Integer.MAX_VALUE));
	}

	//�����൱�ڳ���2�������൱�ڳ���2
	public static void test2() {
		System.out.println(-15 >> 1); //��5������С��
		System.out.println(-15 << 1);
	}

	//��һ�������ľ���ֵ:��1��ȡ��
	public static void test3() {
		int a = -100;
		a = ~(a - 1); //��ʱ����λ���ı�Ϊ1 
		System.out.println(a);
	}

	//�ж�һ����������
	public static void test4() {
		int a = 100;
		boolean negative = (a >> 31 & 1) == 1 ? true : false;
		System.out.println("������ " + negative);
	}

	//�ж�һ��������ż
	public static void test5() {
		int a = 100;
		boolean odd = (a & 1) == 1 ? true : false;
		System.out.println("������ " + odd);
	}
	
	//byte,short ת��Ϊ16�������ʱ����Ҫ�Է���λ���д���char��Ϊ���޷����ͣ�����Ҫ����
	//Integer: static String	toHexString(int i)
	public static void test6() {
		byte b = (byte)0x8f; //��һ������
		System.out.println("�������λ֮ǰ��" + Integer.toHexString(b)); //���Ų��䣬ת��Ϊintʱǰ�����1
		System.out.println("�������λ֮��" + Integer.toHexString(b & 0xff)); //����λ��Ϊ0,short:& 0xffff
	}
}