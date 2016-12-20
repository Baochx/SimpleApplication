import java.util.*;
import java.io.*;

class SuperClass {
	private int a = 100;
	SuperClass() {
		print();
	}
	private void print() {
		System.out.println("SuperClass() " + a);
	}
	public final void f1() {
		System.out.println("SuperClass f1()");
	}
	protected void f2() throws Exception {
		System.out.println("SuperClass f2()");
	}
    
}

public class OverrideTest extends SuperClass {
	
	public int a  = 1000;
	//@Override //wrong ��Ϊ˽�еķ����������ᱻ�̳�
	public void print() {
		System.out.println("OverrideTest print() " + this.a);
	}
	//@Override //wrong
	/*public final void f1() {
		System.out.println("OverrideTest f1()");
	}*/ //���ܼӲ���final���ᱨ��JVM������Ϊ��������ͼ��д�����final���������Ƿ���ǩ����ͬ
	//1.��дʱ������Ȩ��
	//2.��дʱ�ܼ���final�ؼ���
	//3.�׳��쳣��Χֻ�ܱ�С���ܱ��
	@Override 
	public final void f2() throws IOException {
		System.out.println("OverrideTest f2()");
	}
	//�޷����ǵļ��������
	//1.�������ͷֱ�Ϊlong,int
	//2.�����б�ֱ�ΪArrayList<Integer> list��List<Integer> list
	//��֮�������ͺͷ���ǩ��һ��Ҫ��ͬ
	public static void main(String[] args) throws Exception {
		SuperClass s = new OverrideTest();
		//s.print(); //wrong! can't access private method!
		OverrideTest s1 = new OverrideTest();
		s.f2(); //�������าд��ķ���
	}
}