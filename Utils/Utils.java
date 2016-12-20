import java.util.*;
import java.io.*;

public class Utils {

	public static void main(String[] args) throws Exception {
		test9();
	}
	
	//�����ַ����ָ�
	public static void test1() {
		String str = "aooo:abso:oo"; 
		//split�ĵײ��ǵ�����Pattern.compile(regex).split(this, limit);
		for (String s : str.split("o"))
			System.out.println(s); //ĳЩsΪ��
	}
	//���Ҵ�Сд��ĸ
	public static void test2() {
		for (int i = 65; i < 255; i++) 
			if (((i - 65) | (96 - i)) > 0)
			System.out.print((char)i); //���С��0�Ķ��Ƿ���ĸ�ַ�(������Сд)
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

	//null��"",'','\0'
	public static void test4() {
		//null������һ����ȷ���Ķ�������������һ������
		System.out.println("1�� " + (null instanceof Object)); //false
		System.out.println("2�� " + ("" instanceof Object)); //true
		char ch1 = 0; //0 ASCII
		char ch2 = '\0';
		char ch3 = '0';
		System.out.format("int: 0 = %d  '\0' = %d  '0' = %d\n", (int)ch1, (int)ch2, (int)ch3);
		System.out.format("char: 0 = %c  '\0' = %c  '0' = %c\n", ch1, ch2, ch3);
		//++ char ch4 = ''; //wrong, �����������ַ�����,��ʹ�����������丳ֵ
		//���� '\0' �� ' '�ȼۣ���Ӧ��ASCII��0; '0'��ʾ�ַ�0����Ӧ��ASCII��48; ''�ǿ��ַ����֣����������char���ͱ������г�ʼ��
		String str1 = ""; //�����ַ����ǺϷ���
		//++ char[] ch = ""; //����C++������ģ�Java��String�޷�ת��Ϊchar[]
		//++ char[] ch = {''} //wrong, ��������ַ�
		char[] ch = {str1.charAt(0)}; //ֻ�Ǵ�����һ����СΪ0���κ����ݵĿ�����
		ch[0] = str1.charAt(0); //nothing,ch�л���û������
		System.out.println("3: " + str1);
		//++ System.out.println("4: " + ch[0]); //����Խ�磬��Ϊch��û���κ�����
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
			int x1 = Integer.parseInt(tokens[0]); //ѧ��
			int x2 = Integer.parseInt(tokens[1]); //����
			result += x1 * x2;
			n += x1;
		}
		double scores = (result / (n*1.0) - 50.0) * 0.1; //�������Ϊ5
		System.out.println(scores + 0.19);
	}

	//�߳�ִ��˳��̽��
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

	//hash,��20������������ӳ�䵽0~4
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

	//hashCode()��System.identityHashCode(obj)
	public static void test8() {
		String str = new String("123");
		String str1 = new String("123");
		Object obj = new Object();
		System.out.println("str: ��д��: " + str.hashCode());
		System.out.println("str: Ĭ�Ϸ���: " + System.identityHashCode(str));
		System.out.println("str1: ��д��: " + str1.hashCode());
		System.out.println("str1: Ĭ�Ϸ���: " + System.identityHashCode(str1));
		
		//-----����������һ�µ�------------------------------------------------
		System.out.println("obj: ԭʼ����: " + obj.hashCode());
		System.out.println("obj: Ĭ�Ϸ���:  " + System.identityHashCode(obj));
	}

	public static String finalPara(final StringBuilder s) {
		//s = new StringBuilder("another!"); //wrong
		s.append("123");
		return s.toString();
	}

	//final����
	public static void test9() {
		StringBuilder s = new StringBuilder("first");
		finalPara(s);
		System.out.println(s);
	}

	//һЩ����֪ʶ����
	public static  void test10() {
		System.out.println("hehehehe\rhaha"); //�س����ص����е���λ��,haha�Ḳ�ǲ���hehe,���������hahahehe
		System.out.println("hehehehe\thaha"); //13,ˮƽ�Ʊ��ֻռ1���ַ�������ӡ��ʱ��ռ����8���ַ��Ŀռ�
		System.out.println("heheheheoooooooohaha"); 
		System.out.println("hehehehe\shaha");
	}

	//Arrays.asList(T...args)�õ���List<T>����ɾ��Ԫ��
	public static void test11() {
		String[] str = {"41", "13", "12", "21"};
		List<String> remain = Arrays.asList(str);
		remain.remove("41"); //UnsupportedOperationException
	}

}
