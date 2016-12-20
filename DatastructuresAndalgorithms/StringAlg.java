public class StringAlg {

	public static void main(String[] args) {
		test6();
		test7(255);
	}

	//����һ���ַ����ǲ��ǻ����ַ���(��ν���ľ�������ͷ�����һ����)
	public static void test1(String testStr) {
		String str = testStr;
		boolean flag = true;
		int length = str.length();
		for (int i = 0; i < length / 2; i++)
			if (str.charAt(i) != str.charAt(length - 1 - i)) {
				System.out.println("���ǻ���");
				flag = false;
				break;
			}
		if (flag) System.out.println("�ǻ���");
	}

	//������䷴ת�������Ӷ���Ŀո�
	public static void test2(String target) {
		System.out.println(wordsReverse(target));
	}

	// ����һ���ַ�����û�������е���ĸ(�����ִ�Сд)
	public static String test3(String str) {
		StringBuilder b = new StringBuilder();
		String temp = str.toLowerCase();
		boolean[] exists = new boolean[26];
		// ͳ����Щ��ĸ���Ѿ�����str�е�
		for (int i = 0; i < temp.length(); i++)
			exists[temp.charAt(i) % 97] = true;
		// Ѱ�Ҳ�����str�е���ĸ
		for (int i = 0; i < 26; i++)
			if (!exists[i]) b.append((char)(i + 97));
		return b.toString();
	}

	//���Ա�����뷽��
	public static void test3() {
		String target = "abbbbbbbxggggshhshhhhjjjkk";
		System.out.println("����: " + stringEncoder(target));
		System.out.println("����: " + stringDecoder(stringEncoder(target)));
		System.out.println("ԭ��: " + target);
	}

	//�õݹ�ʵ�ְ�һ�������������
	public static String test4(int n) {
		if (n == 0) return "";
		return n % 10 + "" + test4(n/10);
	}

	public static void test5(int n) {
		System.out.printf("��%d��fabonacci��%d\n", n, fabonacci(n));
	}

	//쳲���������
	//���ü�����洢 ����ص�������
 	// 0 1 1 2 3 5 8 f(n) = f(n - 1) + f(n - 2) (n >= 3)
 	//���ֱ�Ӱ��ն������������Ļ�����Щ�����Ǳ��ظ�������
	private static int fabonacci(int n) {
		//n�ǵڼ�������˼
		int[] map = new int[n];
		map[0] = 0;
		map[1] = 1;
		for (int i = 2; i < n; i++)
			map[i] = map[i - 1] + map[i - 2];
		return map[n - 1];
	}

	public static void test6() {
		int a = 22;
		int b = 22;
		System.out.println(" \"22 ^ 22 =\" " + (a ^ b)); //0
		int[] m = {1, 2, 3, 4, 5, 89, 6, 7, 8};
		int[] n = {1, 2, 3, 4, 5, 6, 7, 8};
		System.out.println("n��m���Ӽ��� Ѱ��n��m�ٵ�Ψһ�Ǹ�ֵ " + findOne(m, n)); //�ҵ�Ψһ��ͬ��һ��
	}

	//n��m���Ӽ��� m��n��һ��Ԫ��
	private static int findOne(int[] a, int[] b) {
		int result = 0;
		for (int i = 0; i < a.length; i++) result ^= a[i];
		for (int i = 0; i < b.length; i++) result ^= b[i];
		return result;
	}
	
	public static void test7(int n) {
		System.out.printf("%d������%d��1: \n",n,countOne(n));
	}

	// (int)n��32λ�����Ʊ�ʾ�ж��ٸ�1��
	private static int countOne(int n) {
		int count = 0; //1�ĸ���
		while (n != 0) {
			if ((n & 1) == 1) count++;
			n >>= 1;
		}
		return count;
	}

	//���룬��ʽ: n+char,n���ַ���n���Դ���10
	private static String stringEncoder(String target) {
		StringBuilder result = new StringBuilder();
		int len = target.length(); //����ÿ��ѭ����Ҫ���м���
		int i = 0;
		char next = target.charAt(0);
		for (; i < len;) {
			int j = i; //��һ����֮��ļ����߼���һ��
			int count = 1; //���˵�һ���ַ��������ַ����״γ��־�û���ٽ����жϣ�����count�ĳ�ʼֵΪ1
			char ch = next;
			while (i < len && (next = target.charAt(i++)) == ch) //ע�������i��������ⲿѭ���ļ�飬����Ҫ�������ü�� 
				count++;
			if (j == 0) result.append(count - 1).append(ch);
			else result.append(count).append(ch);
		}
		return result.toString();
	}


	//����
	private static String stringDecoder(String target) {
		StringBuilder result = new StringBuilder();
		int i = 0; 
		for (;i < target.length();) {
			int j = i; //��ֵ����ʼ��
			int digits = 0;
			char next = target.charAt(i++);
			while (47 < next && next < 58) {//����������
				digits++;
				next = target.charAt(i++);
			}
			int count = Integer.parseInt(target.substring(j, j + digits)); //�õ��ַ��ظ�������ֵ
			//int count = Character.digit(target.charAt(i), 10); //char -> int ��ֻ�ܵõ�10���ڵ�
			while (count > 0) {
				result.append(next);
				count--;
			}
		}
		return result.toString();
	}

	//��䷴ת
	public static String wordsReverse(String target) {
		StringBuilder result = new StringBuilder(target.length());
		String[] tokens = target.split(" ");
		for (int i = tokens.length - 1; i >= 0; i--) {
			result.append(tokens[i]);
			if (i != 0) result.append(" "); //���һ�����ʺ�����û�пո�ģ���֤��תǰ��ĳ���һ��;
		}
		return result.toString();
	}

}