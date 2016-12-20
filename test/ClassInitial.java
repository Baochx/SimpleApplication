/**
 * 类变量初始化和对象实例初始化是不一样的概念；
 * 创建B对象时需要初始化B对象的所有变量，那么此时B的超类A就需要先初始化了
 * 而创建B对象之前需要先加载类进来，加载的顺序也是先超类后子类。
 * */
class C {
	 int x = 7;
	 static {
			System.out.println("C static"); //没用到C类，所以不加载;
	}
}

class A 
{
	String str = "a";
	
	{
		str = "aa";
		System.out.println(str);
	}
	
	static {
		System.out.println("A static");
	}
	
	A() {
		System.out.println("A()");
	}
	
	A(String str) {
		this.str = str;
		System.out.println("A(String str) " + this.str);
	}

}

class B extends A 
{

	String str = "b";

	{
		str = "bb";
		System.out.println(str);
	}
	
	static {
		System.out.println("B static");
	}
	
	B() {
		System.out.println("B()");
	}
	
	B(String str) {
		this.str = str;
		System.out.println("B(String str) " + this.str);
		
	}
	 
} 

class SuperClass
{
	static {
		System.out.println("SuperClass static");
	}
	
    public A getObject() 
    {
       System.out.println("getObject() return A");
       return new A();
    } 
}

public class ClassInitial extends SuperClass
{
	{
		System.out.println("SubTest normal");
	}
	
	static {
		System.out.println("SubTest static");
	}
	
	ClassInitial() {
		System.out.println("SubTest()");
	}
	
	//重载的返回类型可以是父类方法返回类型的子类(要具有明确的继承关系，而不是能向上转型就行，如long, int是不可以的)
	@Override 
    public B getObject() 
    {
        System.out.println("getObject() return B");
        return new B();
    }

    public static void main (String[] args)
    {
    	System.out.println("create obj B:");
        B b = new B("bbb");
        System.out.println("call SubTest's method getObject");
        SuperClass s = new ClassInitial();
        s.getObject();
    }

}

