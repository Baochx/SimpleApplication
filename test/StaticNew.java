class Test1 {
	//public static Test1 t = new Test1(); //会陷入死循环
	public static Test1 t = new Test1(); //new Test1()中不用管static初始化部分，此时a的初始值为0
	public static int a = 19;

	{
		System.out.println("Test1 构造块 " + a);
	}

	static {
		System.out.println("Test1 静态块");
	}
}

public class StaticNew {
	public static int k = 0;
	public static Test1 t = new Test1();
	public static StaticNew t1 = new StaticNew("t1"); //new StaticNew("t1")中不用管static初始化部分，此时n的初始值为0
	public static StaticNew t2 = new StaticNew("t2");
	public static int i = print("i");
	public static int n = 99;
	
	public int j = print("j"); //只跟实例有关
	
	{
		print("StaticNew 构造块");
	}
	
	static {
		print("StaticNew 静态块");
	}
	
	//k, n, i 每实例一次就加1;
	public StaticNew(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++i;
		++n;
	}
	
	//k,n,i 每调用一次就加1
	public static int print(String str) {
		System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
		++n;
		return ++i;
	}
	
	public static void main(String[] args) {
		StaticNew t3 = new StaticNew("init");
	}

}
