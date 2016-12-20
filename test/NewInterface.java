import java.util.ArrayList;
import java.util.List;


/*
 * �ӿ��п�����default,static������
 * 1.�ӿ��е����еĳ�Ա��������public static final���͵�
 * 2.���з���Ҳ����public���͵�
 * 3.�����ʱֻ����ֱ�Ӹ��ӿ�,�����ø��ӿ��е�static�����򷽷�ʱ�ż�������ӿ�
 * ������游�ӿ�����default��������ô����˳���Ǹ��ӿڵ��ӽӿ�
 * 4.�ӿ��в�����static��
 * 5.�ӿ���δʵ�ֵķ�������abstract����
 * */
interface A {

	B1 b1 = new B1();

	void f3();
	void f4();

	//static���� ���ܱ���д�����Կ���ͬ��
	static void fff() {
		//b1 = new B1(); //���ܸı䣬˵��b1��final��
		System.out.println("A.fff() static");
	}

	//default����������ʹ�ýӿڵļ��ع����ú���һ�����ȸ�����
	default void f2() {
		System.out.println("A.f2()");
	}

}

interface AA extends A {

	B b2 = new B();

	void f(); //Ĭ����public��;

	//�ӽӿ���Ҳ�ǿ���ʵ�ָ��ӿ��еĳ��󷽷���
	default void f3() {
		System.out.println("implements A's f3() in AA");
	}

	default void ff() {
		System.out.println("AA.ff()");
	}

	static void fff() {
		System.out.println("AA.fff() static");
	}

}

class B {

	static B1 b1 = new B1();

	static {
		b1 = new B1(); //static ����Ҳ�ǿ��Ըı�ģ����ܸı����final����
		System.out.println("B static block ");
	}

	public static void f1() {
		System.out.println("static B.f1()");
	}

}

class B1 {

	static {
		System.out.println("B1 static block ");
	}

	public static void f1() {
		System.out.println("static B1.f1()");
	}

}

abstract class BB implements A {

	public void f() {
		System.out.println("BB.f()");
	}

}

public class NewInterface extends BB implements AA {

	//���еĳ��󷽷�ֻҪ��������ʵ���˾���
	public void f4() {
		System.out.println("implements A's f4() in NewInterface");
	}
	
	public static void main(String[] args) {
		//֮���Բ���ʵ�ֽӿ�A�еķ���������Ϊ��ʱ�Ѿ��̳���B�е�ͬ��public����
		NewInterface nit = new NewInterface();
		nit.f(); //f()�ڸ���BB��ʵ��
		nit.ff(); //ff()��AA�е�Ĭ�Ϸ���
		//nit.fff(); //worng! static�������ھ�̬��(�޷�ͨ���������������ʵ�����ʣ�ò��û�̳У���Ȼ�����඼�ܵ��ã��̳��������Ҫ)
		//NewInterface.fff(); //wrong!
		AA.fff(); //fff()��AA�ľ�̬����
		//nit.f2();
		A.fff();
	}
	
}
