import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.io.*;

//通过反射修改不可变类Strig中的内容
public class StringReflect {

	public static void main(String[] args) throws Exception {
        //getClass()的输出格式：class java.lang.String
		System.out.println("".getClass().toString().split("\\s")[1]);
        //获取类对象的方式之一
    	Class<?> c = Class.forName("".getClass().toString().split("\\s")[1]);
        //获取“定制”的构造函数
    	Constructor<?> con = c.getConstructor("".getClass());
        //创建新对象，传入构造参数
    	String obj = (String) con.newInstance("reflects");
    	System.out.println(obj);
        //获取私有的域(通过域名)，这里相当于获取“类的成员信息”
    	Field f = c.getDeclaredField("value");
        //设置访问权限为true
    	f.setAccessible(true);
        //通过传入具体的实例对象获取“对象成员信息”
    	char[] ch = (char[])f.get(obj);
        //修改对象成员信息
    	ch[0] = '6';
    	System.out.println(obj);
    	System.out.println(obj.contains("ct1"));
	}
}

//Exceptions:
//ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, 
//IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
