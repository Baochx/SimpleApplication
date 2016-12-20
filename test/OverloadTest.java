import java.io.*;

class SuperClass {
	
	protected void f1(int a) {
		System.out.println("SuperClass f1()");
	}
	public final void f2() throws IOException {
		System.out.println("SuperClass final f2()");
	}
}
public class OverloadTest extends SuperClass {
	//Overloadʱ�����¼�final����
	public final int f1(int a, int b) {
		System.out.println("OverloadTest f1()");
		return 1;
	}
	//����JVM�Ͳ�����Ϊ��������ͼ���Ǹ����final��������Ϊ��������
	//Overloadʱ�����׳�����Χ���쳣�����Ǹı䷵������
	public final int f2(int a) throws Exception { 
		System.out.println("OverloadTest final f2()");
		return 0;
	}
	public static void main(String[] args) throws Exception {
		SuperClass s1 = new OverloadTest();
		//s1.f1(1,2); //wrong s1���õ����Լ���f1���������޷����������е�f1�·���
		OverloadTest s2 = new OverloadTest();
		s2.f1(1); //���ôӸ���̳е�
		s2.f1(1,2); //�����Լ����ص�
		s2.f2(); //�ܹ��̳�final������ֻ���޷�����final����
		s2.f2(1);
	}
}