import java.util.LinkedList;
import java.nio.charset.*;
import java.util.*;
import java.io.*;

public class CharacterEnDecodeTest {
	
	public static void main(String...strings) { 
		test3();
	}
	//输出所有的ASCII
	//A-Z:65-90 a-z:97-122 0-9:48-57
	//有些字符不能正常打印
	public static void test1() {
		for (int i = 0; i < 256; i++) {
			if (i % 10 == 0) System.out.println();
			System.out.printf("(%d):%c  ", i, (char)i);
		}
	}

	//Java中的char类型占几个字符？
	//英文字符都是占用1Byte，汉子跟编码有关
	public static void test2() {
		char ch = 'a';
		char ch1 = '中';
		System.out.println(" 'a'占用的字节数：" + ("" + ch).getBytes().length);
		System.out.println(" '中'占用的字节数(GBK)：" + ("" + ch1).getBytes().length);
		System.out.println("系统采用的编码格式：" + Charset.defaultCharset()); //GBK(汉字内码扩展规范) JVM默认编码
		try {
			//以“UTF-8”编码读入byte数组，encoding
			System.out.println(" '中'占用的字节数(UTF-8)：" + ("" + ch1).getBytes("UTF-8").length); 
			System.out.println(" '中'占用的字节数(UTF-16)：" + ("" + ch1).getBytes("UTF-16").length);
			System.out.println(" '中'占用的字节数(iso8859-1)：" + ("" + ch1).getBytes("iso8859-1").length);
			//以“UTF-8”编码读入新建字符串,decoding
			System.out.println("用UTF-8来解码GBK格式的汉字：" + new String(("" + ch1).getBytes(), "UTF-8"));
			System.out.println("用iso-8859-1来解码GBK格式的汉字：" + new String(("" + ch1).getBytes("iso8859-1"), "iso8859-1"));
			System.out.println("用UTF-8来解码GBK格式的英文字符：" + new String(("" + ch).getBytes(), "UTF-8"));
			System.out.println("GBK: " + Arrays.toString(("" + ch1).getBytes()));
			System.out.println("UTF-8: " + Arrays.toString(("" + ch1).getBytes("UTF-8")));
			System.out.println("UTF-16: " + Arrays.toString(("" + ch1).getBytes("UTF-16")));
			System.out.println("iso8859-1: " + Arrays.toString(("" + ch1).getBytes("iso8859-1")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	//字符->字节：BufferedWriter(InputStreamWriter(FileOutputStream(filename)), encoder));
	//字节->字符：BufferedReader(InputStreamReader(FileInputStream(filename)), decoder));

	//设置：System.setProperty("", "");
	public static void test3() {
		System.out.println(System.getProperty("file.encoding")); //GBK main方法所在文件采用的字符编码,影响文件内容
		System.out.println(System.getProperty("sun.jnu.encoding")); //GBK 文件名字编码，影响文件名
		System.setProperty("sun.jnu.encoding", "utf-8");
		System.out.println(System.getProperty("sun.jnu.encoding")); //utf-8
	}
}
