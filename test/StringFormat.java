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

	//��ӡ����׼���
	public static void test2() {
		Formatter f = new Formatter(System.out); //���캯�������������
		f.format("%-15s %5s %10s\n", "name", "age", "sex"); //-��ʾ������
		//���ֻ�����һ�θ�ʽ���ַ���������String��static����format
		String str = String.format("%#4x", 12); //�ڲ���Ȼ��ͨ��Formatterʵ�ֵ�
		System.out.println(str);
	}

	//��ӡ��ĳһ�ļ���
	public static void test3() {
		/*File f = new File("print.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/ //�ļ��������ȴ���Ҳ���Բ����ڣ������ڻ��½���������ٴ�д��Ḳ��ԭ����
		Formatter fm = null;
		try {
			fm = new Formatter("print.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Formatter	format(String format, Object... args),return type is Formatter
		fm.format("%-15s %5s %10s\n", "name", "age", "sex"); //-��ʾ������ 
		fm.flush(); //fmû�ر�Ҳ��ˢ�µĻ����������ļ�����������ݡ�(ˢ����һ����ϰ��)
		System.out.println("fm.out(): " + fm.out()); //Returns the destination for the output: java.io.BufferedWriter@4aa298b7
		System.out.println("fm: " + fm); //toString()
		System.out.println("fm.locale(): " + fm.locale()); 
		System.out.println("fm.ioException(): " + fm.ioException());
		fm.close(); //�ر�֮ǰ�Ὣ����ˢ�½��ļ�
	}

	//�ַ���ʽ
	public static void test4() {
		//����%b��ʽ
		boolean bool = true;
		System.out.format("true: %b\n", bool);
		System.out.format("null: %b\n", (Object)null);
		System.out.format("0: %b\n", 0);
 
		//.precision ����������
		System.out.format("default: %f\n", 0.3f); //Ĭ��6λ, ���㲹0
		System.out.format("default: %.2f\n", 0.3f); //Ĭ��6λ
		//flag:- and width ���
		System.out.format("%-10s %10s\n", "name", "age"); //-����룬Ĭ���Ҷ���
		System.out.format("%-10s %10s\n", "chongxue", "22");
		//%e %x % %h
		System.out.format("��ѧ������: %e\n", 100000000.0000001);
		System.out.format("-1 >>> 1 16����: %x\n", -1 >>> 1);
		System.out.format("ɢ����: %h\n", "abc");
		//System.out.format("%99: %%\n", 99);
		//flags:0(����0)�� �ո�(�����и�ǰ���ո�)��+(������ǰ���һ������)�� ( (�������������ĸ���)
		System.out.format("%04d\n", 1);
		System.out.format("% d\n", 1);
		System.out.format("%+d\n", 1);
		System.out.format("%(d\n" ,-1); //(1)��ʾ-1
		//flag:# 
		System.out.format("%x\n", 255); //ff
		System.out.format("%#x\n", 255); //0xff
		//flag:, ��ָ���
		System.out.format("%,d", 1000000); //1,000,000
	}

	//DecimalFormat
	public static void test5() {
		//NumberFormat��DecimalFormat������

		DecimalFormat df = new DecimalFormat("0.0000"); //�����ܱ�֤.ǰ��������һ��0������ .4500�����
		System.out.println(df.format(0.45)); //123.4500

		NumberFormat nf = DecimalFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2); //�ٷ�����ʽ����Ȼ����2λС��
		System.out.println(nf.format(0.11199)); //11.20%

		DecimalFormat df1 = new DecimalFormat("0.00%"); //0.��ʾ.ǰ�������и�0
		System.out.println(df1.format(0.11199)); //11.20%

		DecimalFormat df2 = new DecimalFormat("000,000.00"); //000,000��ʾÿ��3���͸�����0Ҳ������#�滻
		System.out.println(df2.format(123456.789)); //123,456.79

	}
}
