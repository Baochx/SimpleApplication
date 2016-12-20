import java.lang.reflect.Field;

/* 
 * 总结：
 * 1：Java1.7中，"string"方式新建字符串时先在字符串池中查找，有则直接返回其引用，没有则将地址复制进常量池;
 *:2：new出的String对象通过intern()方法放入常量池，如果已经有返回池中引用地址，否则返回对象原地址。
 * String str = new String("我是计算233"); String str_ = str.intern();这2条语句包括了1和2两种情况 str_ == str,false。
 * */

public class InternTest {

	public static void main(String[] args) throws Exception {

		String str = new String("我是计算233"); //"我是计算233"首次出现被加入常量池中，地址addr1
		String str_ = str.intern(); //str_地址addr1, str地址addr2
		System.out.println("不满足首次出现，所以对象不是同一个：" + str == str_); //false

		char[] cha = {'我','是','计','算','2','3','3'};
		String str3 = new String(cha); //str3的值 不会因为反射的改变而改变，因为它的值没有指向"我是计算233"
		String str4 = new String("我是计算233"); //为什么会跟着变？"我是计算233"指向了常量池中的引用
		
		/*
		 * 构造函数源码： 
		public String(String original) {
        this.value = original.value; //内部的值指向的是同一块地址 这里value代表一个地址
        this.hash = original.hash;
    	}*/

		String str1 = str.intern(); //常量池中已经有，返回str1是常量池中的(地址addr1)
		System.out.println(str1 == "我是计算233"); //true
		System.out.println(str1 == str); //false 内部的value是指向同一块内存，但它们各自的引用指向的地址不一样
		
		System.out.println();
		System.out.println("str: " + str + " " + "str1: " + str1);
		Class<?> c = Class.forName("java.lang.String");
		Field f = c.getDeclaredField("value"); //value域是private的
    	f.setAccessible(true);
    	char[] ch = (char[])f.get(str); //public Object get(Object obj)
    	ch[0] = '你';
    	System.out.println("str: " + str + "  str1: " + str1 + "  str4: " + str4); //你是计算233:你是计算233 说明指向的是同一块地址啊
    	System.out.println("str3 == str1 " + (str3 == str1)); //false
    	System.out.println("str3: " + str3);
    	
    	String s1 = new String("kvill");  //注意      "kvill" 这一步已经将"kvill"放入常量池了s1.intern()不满足第一次原则 
    	System.out.println( s1 == s1.intern() );  //false
    	
    	char[] test = {'k','v','i','l','u'};  
    	String s2 = new String(test);  
    	System.out.println(s2 == s2.intern()); //true

	}
}
