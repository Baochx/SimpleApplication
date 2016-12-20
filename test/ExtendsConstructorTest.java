//默认无参构造函数在不显示声明一个无参构造函数时才会自动提供
//如果父类已经显示声明了有参构造函数，那么子类需要显示调用父类的有参构造函数
class Super {
	Super(int i) {
		System.out.println("Super(int i) " + i);
	}
}

public class ExtendsConstructorTest extends Super {
	ExtendsConstructorTest(int i) {
		super(i);
		System.out.println("ExtendsConstructorTest(int i) " + i);
	}
	
	void f1() {
		System.out.println("f1()");
	}
	
	void f2() {
		this.f1();
		System.out.println("f2()");
	}

	public static void main(String[] args) {
		new ExtendsConstructorTest(1).f2();
	}

}
