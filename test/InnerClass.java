public class InnerClass {
	
	private int a = 89;
	
	public static void main(String...args) {
		int a = 900;
		new B() {
			private int a = 100;
			public void f3(int a) {
				System.out.println("anonymous class's method f3(): a = " + this.a); //�����ڲ���Ҳ����thisָ��ģ����Լ����ڲ������п���
				test(new B()); //�෽�������������κεط�����
			}
		}.f3(a); //���õ��Ǿֲ�����a	 
	}

	public static void test(B b) {
		b.f2();
	}
}

class B {
	public void f2() {
		System.out.println("B f2()");
	}
}
