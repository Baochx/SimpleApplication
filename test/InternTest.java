import java.lang.reflect.Field;

/* 
 * �ܽ᣺
 * 1��Java1.7�У�"string"��ʽ�½��ַ���ʱ�����ַ������в��ң�����ֱ�ӷ��������ã�û���򽫵�ַ���ƽ�������;
 *:2��new����String����ͨ��intern()�������볣���أ�����Ѿ��з��س������õ�ַ�����򷵻ض���ԭ��ַ��
 * String str = new String("���Ǽ���233"); String str_ = str.intern();��2����������1��2������� str_ == str,false��
 * */

public class InternTest {

	public static void main(String[] args) throws Exception {

		String str = new String("���Ǽ���233"); //"���Ǽ���233"�״γ��ֱ����볣�����У���ַaddr1
		String str_ = str.intern(); //str_��ַaddr1, str��ַaddr2
		System.out.println("�������״γ��֣����Զ�����ͬһ����" + str == str_); //false

		char[] cha = {'��','��','��','��','2','3','3'};
		String str3 = new String(cha); //str3��ֵ ������Ϊ����ĸı���ı䣬��Ϊ����ֵû��ָ��"���Ǽ���233"
		String str4 = new String("���Ǽ���233"); //Ϊʲô����ű䣿"���Ǽ���233"ָ���˳������е�����
		
		/*
		 * ���캯��Դ�룺 
		public String(String original) {
        this.value = original.value; //�ڲ���ֵָ�����ͬһ���ַ ����value����һ����ַ
        this.hash = original.hash;
    	}*/

		String str1 = str.intern(); //���������Ѿ��У�����str1�ǳ������е�(��ַaddr1)
		System.out.println(str1 == "���Ǽ���233"); //true
		System.out.println(str1 == str); //false �ڲ���value��ָ��ͬһ���ڴ棬�����Ǹ��Ե�����ָ��ĵ�ַ��һ��
		
		System.out.println();
		System.out.println("str: " + str + " " + "str1: " + str1);
		Class<?> c = Class.forName("java.lang.String");
		Field f = c.getDeclaredField("value"); //value����private��
    	f.setAccessible(true);
    	char[] ch = (char[])f.get(str); //public Object get(Object obj)
    	ch[0] = '��';
    	System.out.println("str: " + str + "  str1: " + str1 + "  str4: " + str4); //���Ǽ���233:���Ǽ���233 ˵��ָ�����ͬһ���ַ��
    	System.out.println("str3 == str1 " + (str3 == str1)); //false
    	System.out.println("str3: " + str3);
    	
    	String s1 = new String("kvill");  //ע��      "kvill" ��һ���Ѿ���"kvill"���볣������s1.intern()�������һ��ԭ�� 
    	System.out.println( s1 == s1.intern() );  //false
    	
    	char[] test = {'k','v','i','l','u'};  
    	String s2 = new String(test);  
    	System.out.println(s2 == s2.intern()); //true

	}
}
