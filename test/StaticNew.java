class Test1 {
	//public static Test1 t = new Test1(); //��������ѭ��
	public static Test1 t = new Test1(); //new Test1()�в��ù�static��ʼ�����֣���ʱa�ĳ�ʼֵΪ0
	public static int a = 19;

	{
		System.out.println("Test1 ����� " + a);
	}

	static {
		System.out.println("Test1 ��̬��");
	}
}

public class StaticNew {
	public static int k = 0;
	public static Test1 t = new Test1();
	public static StaticNew t1 = new StaticNew("t1"); //new StaticNew("t1")�в��ù�static��ʼ�����֣���ʱn�ĳ�ʼֵΪ0
	public static StaticNew t2 = new StaticNew("t2");
	public static int i = print("i");
	public static int n = 99;
	
	public int j = print("j"); //ֻ��ʵ���й�
	
	{
		print("StaticNew �����");
	}
	
	static {
		print("StaticNew ��̬��");
	}
	
	//k, n, i ÿʵ��һ�ξͼ�1;
	public StaticNew(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++i;
		++n;
	}
	
	//k,n,i ÿ����һ�ξͼ�1
	public static int print(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		return ++i;
	}
	
	public static void main(String[] args) {
		StaticNew t3 = new StaticNew("init");
	}

}
