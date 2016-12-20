//懒汉模式的单例模式
public class SinglePattern {
	//必须用volatile修饰，保证内存可见性
	private static int a;
	private  static volatile MySinglePattern singlePattern = null;
	
	private MySinglePattern() {
		a++;
	}

	public int getA() {
		return a;
	}
	
	//两次检查的目的是排除第一次检查和锁定之间有其其它的线程已经创建单例并写入了内存
	public static MySinglePattern getMySinglePattern() {
		if (singlePattern == null) 
			synchronized (MySinglePattern.class) {
				if (singlePattern == null) //注释这一行，输出的a就不一定是1了，说明构造函数被调用了多次
					singlePattern = new MySinglePattern();
			}
		return singlePattern;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			new Thread(
				() -> {
					System.out.println( Thread.currentThread().getName() + " : " 
						+ MySinglePattern.getMySinglePattern().getA() );
				}, "Thread " + i 
				).start();
	}

}
