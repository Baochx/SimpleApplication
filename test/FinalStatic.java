//ʹ��һ����ľ�̬��������������ļ���

public class FinalStatic {
	
	public static void main(String[] args) {
		System.out.println(FinalTest.b);
	}

}

class FinalTest {
	
	static final int a = 123;
	static int b = 123;
	static {
		System.out.println("Hello, I'm static block from class FinalTest");
	}

}