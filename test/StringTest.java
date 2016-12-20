public class StringTest {
	//+如果不是直接连接两个常量，实际上是先生成StringBuilder再调用它的append()方法
	public static void test1() {
		System.out.println("str1 + str2: ");
		String a = "tao"+"bao";
   	 	String b = "tao";
    	String c = "bao";
    	System.out.println("from test1(), result: " + ((b + c) == a));
	}
	//Java1.7之后，intern()方法直接返回对象引用(如果是第一次出现这个字符串)
	public static void test2() {
		String s = new String("1gg") + "1";
		s.intern();
	    String s2 = "1gg1";
	    System.out.println(s == s2);
	    //String s3 = new String("11b21"); //创建了两个对象，s3.intern()将不会满足第一次出现原则
	    String s3 = new String("a") + new String("b"); 
	    String s4 = "ab";
	    String s3_ = s3.intern(); //"11b21"第一次出现，如果这句和下一句调换就不是第一次出现了
	    //String s4 = "ab";
	   	System.out.println(s3 == s4);
	    System.out.println(s3_ == s3); //s3 s3_ s4都指向同一个地址
	}
	
	public static void main(String[] args) {
		test2();
	}
}