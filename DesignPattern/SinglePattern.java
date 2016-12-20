//����ģʽ�ĵ���ģʽ
public class SinglePattern {
	//������volatile���Σ���֤�ڴ�ɼ���
	private static int a;
	private  static volatile MySinglePattern singlePattern = null;
	
	private MySinglePattern() {
		a++;
	}

	public int getA() {
		return a;
	}
	
	//���μ���Ŀ�����ų���һ�μ�������֮�������������߳��Ѿ�����������д�����ڴ�
	public static MySinglePattern getMySinglePattern() {
		if (singlePattern == null) 
			synchronized (MySinglePattern.class) {
				if (singlePattern == null) //ע����һ�У������a�Ͳ�һ����1�ˣ�˵�����캯���������˶��
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
