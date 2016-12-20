public class InnerClass {
	
	private int a = 89;
	
	public static void main(String...args) {
		int a = 900;
		new B() {
			private int a = 100;
			public void f3(int a) {
				System.out.println("anonymous class's method f3(): a = " + this.a); //匿名内部类也是有this指针的，在自己的内部方法中可用
				test(new B()); //类方法可以在类内任何地方调用
			}
		}.f3(a); //调用的是局部变量a	 
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
