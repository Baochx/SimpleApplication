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
	//Overload时可以新加final修饰
	public final int f1(int a, int b) {
		System.out.println("OverloadTest f1()");
		return 1;
	}
	//这样JVM就不会以为子类在试图覆盖父类的final方法，因为这是重载
	//Overload时可以抛出更大范围的异常或者是改变返回类型
	public final int f2(int a) throws Exception { 
		System.out.println("OverloadTest final f2()");
		return 0;
	}
	public static void main(String[] args) throws Exception {
		SuperClass s1 = new OverloadTest();
		//s1.f1(1,2); //wrong s1调用的是自己的f1方法，而无法调用子类中的f1新方法
		OverloadTest s2 = new OverloadTest();
		s2.f1(1); //调用从父类继承的
		s2.f1(1,2); //调用自己重载的
		s2.f2(); //能够继承final方法，只是无法覆盖final方法
		s2.f2(1);
	}
}