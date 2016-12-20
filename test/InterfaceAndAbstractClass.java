
//接口和抽象类中也是可以有main方法的，可以被JVM调用
//static，private方法，子类和父类或者子类和接口可以同名，JVM不会误认为是重载
//final方法则不能同名，否则会被JVM认为是企图覆盖final方法
interface Interface {

	public String str = "我是接口中的public字段！";

	public static void staticMethod1() {
		System.out.println("2 -- Hello, I am from interface static method!");
	}

	//默认是public 方法也只能是public的
	default void defaultMethod1() {
		System.out.println("4 -- Hello, I am from interface default method!");
		System.out.println(str); //非静态方法可以调用静态字段
	}

	public static void main() {
		System.out.println("1 -- Hello, I am from interface main method!");
		staticMethod1();
	}

} 

class InterfaceTest implements Interface {

	//是可以重写写接口里面的方法的
	public void defaultMethod1() {
		//str = "此处改写接口中的str"; //无法修改(因为是public static final类型的)
		System.out.println("3 -- 我已经成功重写了Interface中的public default方法！ 继承的str：" + str);
	}

	public static void main() {
		Interface.main();
		Interface.staticMethod1();
		new InterfaceTest().defaultMethod1();
	}

}

abstract class AbstractClass {

	protected String str = "9 -- 我是AbstractClass类中的private字段！";
	
	private static void privateMethod1() {
		System.out.println("6 -- I am from AbstractClass private static method! ");
	}

	private void f1() {
		System.out.println("11 -- I am from AbstractClass private method!");
	}

	public final void f2() {
		System.out.println("12 -- I am from AbstractClass final method!");
	}

	public static void main() {
		privateMethod1();
		System.out.println("7 -- Hello, I am from AbstractClass main method!");
	}
	
}

class AbstractClassTest extends AbstractClass {

	private void f1() {
		System.out.println("11 -- I am from AbstractClassTest private method!");
	}

	/*public final void f2() {
		System.out.println("12 -- I am from AbstractClassTest final method!");
	}*/ //会被认为是企图覆盖final方法

	public void modMethod1() {
		str = "8 -- 此处修改AbstractClass中的str"; //能成功修改
		System.out.println(str);
	}

	public static void main() {
		AbstractClass.main();
		AbstractClassTest act = new AbstractClassTest();
		act.modMethod1();
		InterfaceTest.main();
	}

}

public interface InterfaceAndAbstractClass {

	public static void main(String...args) {
		AbstractClassTest.main();
	}

}