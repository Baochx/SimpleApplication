import java.util.LinkedList;
import java.nio.charset.*;
import java.util.*;
import java.io.*;

public class CharacterEnDecodeTest {
	
	public static void main(String...strings) { 
		test3();
	}
	//������е�ASCII
	//A-Z:65-90 a-z:97-122 0-9:48-57
	//��Щ�ַ�����������ӡ
	public static void test1() {
		for (int i = 0; i < 256; i++) {
			if (i % 10 == 0) System.out.println();
			System.out.printf("(%d):%c  ", i, (char)i);
		}
	}

	//Java�е�char����ռ�����ַ���
	//Ӣ���ַ�����ռ��1Byte�����Ӹ������й�
	public static void test2() {
		char ch = 'a';
		char ch1 = '��';
		System.out.println(" 'a'ռ�õ��ֽ�����" + ("" + ch).getBytes().length);
		System.out.println(" '��'ռ�õ��ֽ���(GBK)��" + ("" + ch1).getBytes().length);
		System.out.println("ϵͳ���õı����ʽ��" + Charset.defaultCharset()); //GBK(����������չ�淶) JVMĬ�ϱ���
		try {
			//�ԡ�UTF-8���������byte���飬encoding
			System.out.println(" '��'ռ�õ��ֽ���(UTF-8)��" + ("" + ch1).getBytes("UTF-8").length); 
			System.out.println(" '��'ռ�õ��ֽ���(UTF-16)��" + ("" + ch1).getBytes("UTF-16").length);
			System.out.println(" '��'ռ�õ��ֽ���(iso8859-1)��" + ("" + ch1).getBytes("iso8859-1").length);
			//�ԡ�UTF-8����������½��ַ���,decoding
			System.out.println("��UTF-8������GBK��ʽ�ĺ��֣�" + new String(("" + ch1).getBytes(), "UTF-8"));
			System.out.println("��iso-8859-1������GBK��ʽ�ĺ��֣�" + new String(("" + ch1).getBytes("iso8859-1"), "iso8859-1"));
			System.out.println("��UTF-8������GBK��ʽ��Ӣ���ַ���" + new String(("" + ch).getBytes(), "UTF-8"));
			System.out.println("GBK: " + Arrays.toString(("" + ch1).getBytes()));
			System.out.println("UTF-8: " + Arrays.toString(("" + ch1).getBytes("UTF-8")));
			System.out.println("UTF-16: " + Arrays.toString(("" + ch1).getBytes("UTF-16")));
			System.out.println("iso8859-1: " + Arrays.toString(("" + ch1).getBytes("iso8859-1")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	//�ַ�->�ֽڣ�BufferedWriter(InputStreamWriter(FileOutputStream(filename)), encoder));
	//�ֽ�->�ַ���BufferedReader(InputStreamReader(FileInputStream(filename)), decoder));

	//���ã�System.setProperty("", "");
	public static void test3() {
		System.out.println(System.getProperty("file.encoding")); //GBK main���������ļ����õ��ַ�����,Ӱ���ļ�����
		System.out.println(System.getProperty("sun.jnu.encoding")); //GBK �ļ����ֱ��룬Ӱ���ļ���
		System.setProperty("sun.jnu.encoding", "utf-8");
		System.out.println(System.getProperty("sun.jnu.encoding")); //utf-8
	}
}
