public class StringTest {
	//+�������ֱ����������������ʵ������������StringBuilder�ٵ�������append()����
	public static void test1() {
		System.out.println("str1 + str2: ");
		String a = "tao"+"bao";
   	 	String b = "tao";
    	String c = "bao";
    	System.out.println("from test1(), result: " + ((b + c) == a));
	}
	//Java1.7֮��intern()����ֱ�ӷ��ض�������(����ǵ�һ�γ�������ַ���)
	public static void test2() {
		String s = new String("1gg") + "1";
		s.intern();
	    String s2 = "1gg1";
	    System.out.println(s == s2);
	    //String s3 = new String("11b21"); //��������������s3.intern()�����������һ�γ���ԭ��
	    String s3 = new String("a") + new String("b"); 
	    String s4 = "ab";
	    String s3_ = s3.intern(); //"11b21"��һ�γ��֣����������һ������Ͳ��ǵ�һ�γ�����
	    //String s4 = "ab";
	   	System.out.println(s3 == s4);
	    System.out.println(s3_ == s3); //s3 s3_ s4��ָ��ͬһ����ַ
	}
	
	public static void main(String[] args) {
		test2();
	}
}