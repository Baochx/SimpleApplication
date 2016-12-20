import java.util.*;
import java.io.*;

public class Utils {

	public static void main(String[] args) throws Exception {
		test9();
	}
	
	//测试字符串分隔
	public static void test1() {
		String str = "aooo:abso:oo"; 
		//split的底层是调用了Pattern.compile(regex).split(this, limit);
		for (String s : str.split("o"))
			System.out.println(s); //某些s为空
	}
	//查找大小写字母
	public static void test2() {
		for (int i = 65; i < 255; i++) 
			if (((i - 65) | (96 - i)) > 0)
			System.out.print((char)i); //结果小于0的都是非字母字符(包括大小写)
	}

	//use break label print 9 * 9 chart
	public static void test3() {
		outer: for (int i = 1; i < 10; i++) {
            for(int j = 1; j < 10; j++) {
            if(j > i) { 
                System.out.println(); 
                continue outer; 
            }
            if (i * j < 10) System.out.print(j + " * " + i + " =  " + (i * j) + "  "); 
            else System.out.print(j + " * " + i + " = " + (i * j) + "  "); 
            }
       }
	}

	//null和"",'','\0'
	public static void test4() {
		//null代表着一个不确定的对象，所以它不是一个对象
		System.out.println("1： " + (null instanceof Object)); //false
		System.out.println("2： " + ("" instanceof Object)); //true
		char ch1 = 0; //0 ASCII
		char ch2 = '\0';
		char ch3 = '0';
		System.out.format("int: 0 = %d  '\0' = %d  '0' = %d\n", (int)ch1, (int)ch2, (int)ch3);
		System.out.format("char: 0 = %c  '\0' = %c  '0' = %c\n", ch1, ch2, ch3);
		//++ char ch4 = ''; //wrong, 不能声明空字符文字,即使接下来你会对其赋值
		//所以 '\0' 和 ' '等价，对应的ASCII是0; '0'表示字符0，对应的ASCII是48; ''是空字符文字，不能用其给char类型变量进行初始化
		String str1 = ""; //而空字符串是合法的
		//++ char[] ch = ""; //这是C++中允许的，Java中String无法转换为char[]
		//++ char[] ch = {''} //wrong, 不允许空字符
		char[] ch = {str1.charAt(0)}; //只是创建了一个大小为0无任何内容的空数组
		ch[0] = str1.charAt(0); //nothing,ch中还是没有内容
		System.out.println("3: " + str1);
		//++ System.out.println("4: " + ch[0]); //数组越界，因为ch中没有任何内容
	}

	//grade calculator
	public static void test5() throws Exception {
		String filePath = "C:\\Users\\chongxue\\Desktop\\score.txt";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		int n = 0;
		int result = 0;
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] tokens = line.split(" ");
			int x1 = Integer.parseInt(tokens[0]); //学分
			int x2 = Integer.parseInt(tokens[1]); //绩点
			result += x1 * x2;
			n += x1;
		}
		double scores = (result / (n*1.0) - 50.0) * 0.1; //绩点最高为5
		System.out.println(scores + 0.19);
	}

	//线程执行顺序探究
	public static void test6() throws Exception {
		System.out.println("start");
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				System.out.println("step2");
			}
		}); 
		thread1.start();
		System.out.println("step1");
		Thread thread2 = new Thread(new Runnable(){
			public void run() {
				System.out.println("step3");
			}
		});
		thread2.start();
		thread2.join();
		System.out.println("end");
	}

	//hash,将20个浮点数均匀映射到0~4
	public static void test7() {
		for (int i = 0; i < 20; i++)
			System.out.print((new Float(i * 0.1).hashCode() & 0x7fffffff) % 5 + " ");
	}

	/*
	* Output:
	* start
	* step1
	* step2
	* step3
    * end
	*/

	//hashCode()和System.identityHashCode(obj)
	public static void test8() {
		String str = new String("123");
		String str1 = new String("123");
		Object obj = new Object();
		System.out.println("str: 重写后: " + str.hashCode());
		System.out.println("str: 默认方法: " + System.identityHashCode(str));
		System.out.println("str1: 重写后: " + str1.hashCode());
		System.out.println("str1: 默认方法: " + System.identityHashCode(str1));
		
		//-----两个方法是一致的------------------------------------------------
		System.out.println("obj: 原始方法: " + obj.hashCode());
		System.out.println("obj: 默认方法:  " + System.identityHashCode(obj));
	}

	public static String finalPara(final StringBuilder s) {
		//s = new StringBuilder("another!"); //wrong
		s.append("123");
		return s.toString();
	}

	//final参数
	public static void test9() {
		StringBuilder s = new StringBuilder("first");
		finalPara(s);
		System.out.println(s);
	}

	//一些基本知识测试
	public static  void test10() {
		System.out.println("hehehehe\rhaha"); //回车，回到本行的首位置,haha会覆盖部分hehe,最终输出：hahahehe
		System.out.println("hehehehe\thaha"); //13,水平制表符只占1个字符，但打印的时候占据了8个字符的空间
		System.out.println("heheheheoooooooohaha"); 
		System.out.println("hehehehe\shaha");
	}

	//Arrays.asList(T...args)得到的List<T>不能删除元素
	public static void test11() {
		String[] str = {"41", "13", "12", "21"};
		List<String> remain = Arrays.asList(str);
		remain.remove("41"); //UnsupportedOperationException
	}

}
