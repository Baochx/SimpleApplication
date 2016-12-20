import java.util.ArrayList;
import java.util.List;


/*
 * 接口中可以有default,static方法。
 * 1.接口中的所有的成员变量都是public static final类型的
 * 2.所有方法也都是public类型的
 * 3.类加载时只加载直接父接口,当调用父接口中的static变量或方法时才加载这个接口
 * 但如果祖父接口中有default方法，那么加载顺序是父接口到子接口
 * 4.接口中不能有static块
 * 5.接口中未实现的方法都是abstract方法
 * */
interface A {

	B1 b1 = new B1();

	void f3();
	void f4();

	//static方法 不能被重写，所以可以同名
	static void fff() {
		//b1 = new B1(); //不能改变，说明b1是final的
		System.out.println("A.fff() static");
	}

	//default方法的引入使得接口的加载规则变得和类一样，先父后子
	default void f2() {
		System.out.println("A.f2()");
	}

}

interface AA extends A {

	B b2 = new B();

	void f(); //默认是public的;

	//子接口中也是可以实现父接口中的抽象方法的
	default void f3() {
		System.out.println("implements A's f3() in AA");
	}

	default void ff() {
		System.out.println("AA.ff()");
	}

	static void fff() {
		System.out.println("AA.fff() static");
	}

}

class B {

	static B1 b1 = new B1();

	static {
		b1 = new B1(); //static 变量也是可以改变的，不能改变的是final变量
		System.out.println("B static block ");
	}

	public static void f1() {
		System.out.println("static B.f1()");
	}

}

class B1 {

	static {
		System.out.println("B1 static block ");
	}

	public static void f1() {
		System.out.println("static B1.f1()");
	}

}

abstract class BB implements A {

	public void f() {
		System.out.println("BB.f()");
	}

}

public class NewInterface extends BB implements AA {

	//所有的抽象方法只要最终子类实现了就行
	public void f4() {
		System.out.println("implements A's f4() in NewInterface");
	}
	
	public static void main(String[] args) {
		//之所以不用实现接口A中的方法，是因为此时已经继承了B中的同名public方法
		NewInterface nit = new NewInterface();
		nit.f(); //f()在父类BB中实现
		nit.ff(); //ff()是AA中的默认方法
		//nit.fff(); //worng! static方法属于静态绑定(无法通过子类的类名或者实例访问，貌似没继承？既然所有类都能调用，继承与否不再重要)
		//NewInterface.fff(); //wrong!
		AA.fff(); //fff()是AA的静态方法
		//nit.f2();
		A.fff();
	}
	
}
