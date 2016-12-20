public class BitTest {
	public static void main(String[] args) {
		test6();
	}
	//负数在计算机中是以补码的形式保存的
	public static void test1() {
		//得到最大整数的三种方式
		System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
		System.out.println("-1 >>> 1:          " + (-1 >>> 1)); //无符号循环右移，高位补0
		System.out.println("((1 << 31) - 1):   " + ((1 << 31) - 1));
		System.out.println("((1 << 31) + 1):   " + ((1 << 31) + 1));
		System.out.println("(1 << 31):         " + (1 << 31));
		System.out.println("(1 << 31):         " + Integer.toBinaryString(1 << 31));
		System.out.println("((1 << 31) + 1):   " + Integer.toBinaryString((1 << 31) + 1));
		System.out.println("-1:                " + Integer.toBinaryString(-1)); //-1在计算机中的补码表示形式
		System.out.println("Integer.MAX_VALUE: " + Integer.toBinaryString(Integer.MAX_VALUE));
	}

	//右移相当于除以2，左移相当于乘以2
	public static void test2() {
		System.out.println(-15 >> 1); //入5反而变小了
		System.out.println(-15 << 1);
	}

	//求一个负数的绝对值:减1再取反
	public static void test3() {
		int a = -100;
		a = ~(a - 1); //此时符号位被改变为1 
		System.out.println(a);
	}

	//判断一个数的正负
	public static void test4() {
		int a = 100;
		boolean negative = (a >> 31 & 1) == 1 ? true : false;
		System.out.println("负数？ " + negative);
	}

	//判断一个数的奇偶
	public static void test5() {
		int a = 100;
		boolean odd = (a & 1) == 1 ? true : false;
		System.out.println("奇数？ " + odd);
	}
	
	//byte,short 转换为16进制输出时，需要对符号位进行处理，char因为是无符号型，不需要处理
	//Integer: static String	toHexString(int i)
	public static void test6() {
		byte b = (byte)0x8f; //是一个负数
		System.out.println("处理符号位之前：" + Integer.toHexString(b)); //符号不变，转换为int时前面填充1
		System.out.println("处理符号位之后：" + Integer.toHexString(b & 0xff)); //符号位变为0,short:& 0xffff
	}
}