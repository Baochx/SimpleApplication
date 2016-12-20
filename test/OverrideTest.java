import java.util.*;
import java.io.*;

class SuperClass {
	private int a = 100;
	SuperClass() {
		print();
	}
	private void print() {
		System.out.println("SuperClass() " + a);
	}
	public final void f1() {
		System.out.println("SuperClass f1()");
	}
	protected void f2() throws Exception {
		System.out.println("SuperClass f2()");
	}
    
}

public class OverrideTest extends SuperClass {
	
	public int a  = 1000;
	//@Override //wrong 因为私有的方法根本不会被继承
	public void print() {
		System.out.println("OverrideTest print() " + this.a);
	}
	//@Override //wrong
	/*public final void f1() {
		System.out.println("OverrideTest f1()");
	}*/ //不管加不加final都会报错，JVM都会认为子类在试图覆写父类的final方法，除非方法签名不同
	//1.覆写时能扩大权限
	//2.覆写时能加上final关键字
	//3.抛出异常范围只能变小不能变大
	@Override 
	public final void f2() throws IOException {
		System.out.println("OverrideTest f2()");
	}
	//无法覆盖的几种情况：
	//1.返回类型分别为long,int
	//2.参数列表分别为ArrayList<Integer> list，List<Integer> list
	//总之返回类型和方法签名一定要相同
	public static void main(String[] args) throws Exception {
		SuperClass s = new OverrideTest();
		//s.print(); //wrong! can't access private method!
		OverrideTest s1 = new OverrideTest();
		s.f2(); //调用子类覆写后的方法
	}
}