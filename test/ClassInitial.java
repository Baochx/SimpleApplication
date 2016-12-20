/**
 * �������ʼ���Ͷ���ʵ����ʼ���ǲ�һ���ĸ��
 * ����B����ʱ��Ҫ��ʼ��B��������б�������ô��ʱB�ĳ���A����Ҫ�ȳ�ʼ����
 * ������B����֮ǰ��Ҫ�ȼ�������������ص�˳��Ҳ���ȳ�������ࡣ
 * */
class C {
	 int x = 7;
	 static {
			System.out.println("C static"); //û�õ�C�࣬���Բ�����;
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
	
	//���صķ������Ϳ����Ǹ��෽���������͵�����(Ҫ������ȷ�ļ̳й�ϵ��������������ת�;��У���long, int�ǲ����Ե�)
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

