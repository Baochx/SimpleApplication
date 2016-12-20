
//�ӿںͳ�������Ҳ�ǿ�����main�����ģ����Ա�JVM����
//static��private����������͸����������ͽӿڿ���ͬ����JVM��������Ϊ������
//final��������ͬ��������ᱻJVM��Ϊ����ͼ����final����
interface Interface {

	public String str = "���ǽӿ��е�public�ֶΣ�";

	public static void staticMethod1() {
		System.out.println("2 -- Hello, I am from interface static method!");
	}

	//Ĭ����public ����Ҳֻ����public��
	default void defaultMethod1() {
		System.out.println("4 -- Hello, I am from interface default method!");
		System.out.println(str); //�Ǿ�̬�������Ե��þ�̬�ֶ�
	}

	public static void main() {
		System.out.println("1 -- Hello, I am from interface main method!");
		staticMethod1();
	}

} 

class InterfaceTest implements Interface {

	//�ǿ�����дд�ӿ�����ķ�����
	public void defaultMethod1() {
		//str = "�˴���д�ӿ��е�str"; //�޷��޸�(��Ϊ��public static final���͵�)
		System.out.println("3 -- ���Ѿ��ɹ���д��Interface�е�public default������ �̳е�str��" + str);
	}

	public static void main() {
		Interface.main();
		Interface.staticMethod1();
		new InterfaceTest().defaultMethod1();
	}

}

abstract class AbstractClass {

	protected String str = "9 -- ����AbstractClass���е�private�ֶΣ�";
	
	private static void privateMethod1() {
		System.out.println("6 -- I am from AbstractClass private static method! ");
	}

	private void f1() {
		System.out.println("11 -- I am from AbstractClass private method!");
	}

	public final void f2() {
		System.out.println("12 -- I am from AbstractClass final method!");
	}

	public static void main() {
		privateMethod1();
		System.out.println("7 -- Hello, I am from AbstractClass main method!");
	}
	
}

class AbstractClassTest extends AbstractClass {

	private void f1() {
		System.out.println("11 -- I am from AbstractClassTest private method!");
	}

	/*public final void f2() {
		System.out.println("12 -- I am from AbstractClassTest final method!");
	}*/ //�ᱻ��Ϊ����ͼ����final����

	public void modMethod1() {
		str = "8 -- �˴��޸�AbstractClass�е�str"; //�ܳɹ��޸�
		System.out.println(str);
	}

	public static void main() {
		AbstractClass.main();
		AbstractClassTest act = new AbstractClassTest();
		act.modMethod1();
		InterfaceTest.main();
	}

}

public interface InterfaceAndAbstractClass {

	public static void main(String...args) {
		AbstractClassTest.main();
	}

}