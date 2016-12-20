import java.util.Formatter;
import java.io.*;
import java.text.*;

public class StringFormat {
	
	public static void main(String[] args) {
		test5();
	}

	public static void test1() {
		int x = 10;
		float y = 90.1f;
		System.out.format("%d %f \n", x, y); //Java 1.5
		System.out.printf("%d %f \n", x, y); //Java 1.5, PrintStream
	}

	//打印到标准输出
	public static void test2() {
		Formatter f = new Formatter(System.out); //构造函数传入输出介质
		f.format("%-15s %5s %10s\n", "name", "age", "sex"); //-表示向左靠齐
		//如果只是输出一次格式化字符串，可用String的static方法format
		String str = String.format("%#4x", 12); //内部依然是通过Formatter实现的
		System.out.println(str);
	}

	//打印到某一文件中
	public static void test3() {
		/*File f = new File("print.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/ //文件可以事先存在也可以不存在，不存在会新建，如存在再次写入会覆盖原来的
		Formatter fm = null;
		try {
			fm = new Formatter("print.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Formatter	format(String format, Object... args),return type is Formatter
		fm.format("%-15s %5s %10s\n", "name", "age", "sex"); //-表示向左靠齐 
		fm.flush(); //fm没关闭也不刷新的话，不会向文件里面输出内容。(刷新是一个好习惯)
		System.out.println("fm.out(): " + fm.out()); //Returns the destination for the output: java.io.BufferedWriter@4aa298b7
		System.out.println("fm: " + fm); //toString()
		System.out.println("fm.locale(): " + fm.locale()); 
		System.out.println("fm.ioException(): " + fm.ioException());
		fm.close(); //关闭之前会将缓存刷新进文件
	}

	//字符格式
	public static void test4() {
		//关于%b格式
		boolean bool = true;
		System.out.format("true: %b\n", bool);
		System.out.format("null: %b\n", (Object)null);
		System.out.format("0: %b\n", 0);
 
		//.precision 浮点数精度
		System.out.format("default: %f\n", 0.3f); //默认6位, 不足补0
		System.out.format("default: %.2f\n", 0.3f); //默认6位
		//flag:- and width 宽度
		System.out.format("%-10s %10s\n", "name", "age"); //-左对齐，默认右对齐
		System.out.format("%-10s %10s\n", "chongxue", "22");
		//%e %x % %h
		System.out.format("科学记数法: %e\n", 100000000.0000001);
		System.out.format("-1 >>> 1 16进制: %x\n", -1 >>> 1);
		System.out.format("散列码: %h\n", "abc");
		//System.out.format("%99: %%\n", 99);
		//flags:0(补充0)， 空格(正数有个前导空格)，+(结果总是包括一个符号)， ( (用括号括起来的负数)
		System.out.format("%04d\n", 1);
		System.out.format("% d\n", 1);
		System.out.format("%+d\n", 1);
		System.out.format("%(d\n" ,-1); //(1)表示-1
		//flag:# 
		System.out.format("%x\n", 255); //ff
		System.out.format("%#x\n", 255); //0xff
		//flag:, 组分隔符
		System.out.format("%,d", 1000000); //1,000,000
	}

	//DecimalFormat
	public static void test5() {
		//NumberFormat是DecimalFormat的子类

		DecimalFormat df = new DecimalFormat("0.0000"); //这样能保证.前面至少有一个0，避免 .4500的情况
		System.out.println(df.format(0.45)); //123.4500

		NumberFormat nf = DecimalFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2); //百分制形式下仍然保留2位小数
		System.out.println(nf.format(0.11199)); //11.20%

		DecimalFormat df1 = new DecimalFormat("0.00%"); //0.表示.前面至少有个0
		System.out.println(df1.format(0.11199)); //11.20%

		DecimalFormat df2 = new DecimalFormat("000,000.00"); //000,000表示每隔3个就隔开，0也可以用#替换
		System.out.println(df2.format(123456.789)); //123,456.79

	}
}
