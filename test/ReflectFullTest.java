//import sun.misc.Unsafe;
import java.lang.reflect.*;

interface Inter1 {

}

interface Inter2 extends Inter1 {

}

class A implements Inter1, Inter2 {

	private String str;

	public A() {
		this.str = "default";
	}

	A(String str) { //public权限：getConstructor()， 所有权限均能获取：getDeclaredConstructor()
		this.str = str;
	}
	
	public String getStr() {
		return this.str;
	}

	private final int setStr(String str) {
		this.str = str;
		return 1;
	}

}

public class ReflectFullTest {

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {

		/*Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe)f.get(null);*/

		//所有直接继承的父接口，不包括祖父接口
		for (Class in : A.class.getInterfaces())
			System.out.println("Class getInterfaces(): " + in);
		//直接父类
		System.out.println("Class getSuperclass(): " + A.class.getSuperclass());	
		
		A normal = new A();
		System.out.println(normal.getStr()); //3 

		Field f = A.class.getDeclaredField("str");
		System.out.println("Field toGenericString(): " + f.toGenericString());
		System.out.println("Field getType(): " + f.getType()); //class<?>
		System.out.println("Field getGenericType(): " + f.getGenericType());
		System.out.println("Field getGenericType().getTypeName(): " + f.getGenericType().getTypeName());
		f.setAccessible(true);

		/*String str = (String)f.get(normal);
		Field f2 = String.class.getDeclaredField("value");
		f2.setAccessible(true);
		char[] ch = (char[])f2.get(str);
		for (int i = 0; i < 7; i++) ch[i] = 'h';*/

		f.set(normal, "hhhhhh"); //可以直接重新设置field的值
		System.out.println("reflect Field: " + normal.getStr()); //3 

		Method m = A.class.getDeclaredMethod("setStr", String.class);
		System.out.println("Method toString(): " + m);
		//return: private final int A.setStr(java.lang.String)
		System.out.println("Method toGenericString(): " + m.toGenericString());
		System.out.println("Method getReturnType(): " + m.getReturnType()); //int
		System.out.println("Method getParameterCount(): " + m.getParameterCount()); 
		//class java.lang.String的形式
		for (Class para : m.getParameterTypes()) 
			System.out.println("Method getParameterTypes(): " + para);
		//java.lang.String的形式
		for (Type type : m.getGenericParameterTypes()) 
			System.out.println("Method m.getGenericParameterTypes(): " + type.getTypeName());
		Type[] es = m.getGenericExceptionTypes();
		if (es != null)
			for (Type e : es) 
				System.out.println("Method m.getGenericExceptionTypes(): " + e.getTypeName());
		System.out.println("Method getName(): " + m.getName());
		System.out.println("Method invoke(具体对象，变长参数):");
		m.setAccessible(true); //这里的用法和field一样
		m.invoke(normal, "深夜代码君");
		System.out.println("reflect Method: " + normal.getStr()); //3
		System.out.println("Method isDefault(): " + m.isDefault());

		//利用无参构造函数反1射创建新实例
		A reflect = A.class.newInstance();
		System.out.println("reflect: " + reflect.getStr());

		//利用带参构造函数反射创建新实例
		Constructor<?> c = A.class.getDeclaredConstructor(String.class);
		A reflect1 = (A)c.newInstance("abcdef");
		System.out.println("reflect1: " + reflect1.getStr());

		//A aa = (A)unsafe.allocateInstance(A.class); 
		//System.out.println(aa.getA()); //0 意味着根本没用到无参构造函数
		System.out.println("normal: " + normal.getClass().getClassLoader());
		System.out.println("reflect: " + reflect.getClass().getClassLoader());
		//System.out.println("unsafe: " + aa.getClass().getClassLoader());	
		
	}
}
	
