public class StringAlg {

	public static void main(String[] args) {
		test6();
		test7(255);
	}

	//测试一个字符串是不是回文字符串(所谓回文就是正序和返序是一样的)
	public static void test1(String testStr) {
		String str = testStr;
		boolean flag = true;
		int length = str.length();
		for (int i = 0; i < length / 2; i++)
			if (str.charAt(i) != str.charAt(length - 1 - i)) {
				System.out.println("不是回文");
				flag = false;
				break;
			}
		if (flag) System.out.println("是回文");
	}

	//测试语句反转，不增加额外的空格
	public static void test2(String target) {
		System.out.println(wordsReverse(target));
	}

	// 给定一个字符串求没有在其中的字母(不区分大小写)
	public static String test3(String str) {
		StringBuilder b = new StringBuilder();
		String temp = str.toLowerCase();
		boolean[] exists = new boolean[26];
		// 统计哪些字母是已经存在str中的
		for (int i = 0; i < temp.length(); i++)
			exists[temp.charAt(i) % 97] = true;
		// 寻找不存在str中的字母
		for (int i = 0; i < 26; i++)
			if (!exists[i]) b.append((char)(i + 97));
		return b.toString();
	}

	//测试编码解码方法
	public static void test3() {
		String target = "abbbbbbbxggggshhshhhhjjjkk";
		System.out.println("编码: " + stringEncoder(target));
		System.out.println("解码: " + stringDecoder(stringEncoder(target)));
		System.out.println("原串: " + target);
	}

	//用递归实现把一个整数逆序输出
	public static String test4(int n) {
		if (n == 0) return "";
		return n % 10 + "" + test4(n/10);
	}

	public static void test5(int n) {
		System.out.printf("第%d个fabonacci是%d\n", n, fabonacci(n));
	}

	//斐波那契数列
	//利用记忆体存储 解决重叠子问题
 	// 0 1 1 2 3 5 8 f(n) = f(n - 1) + f(n - 2) (n >= 3)
 	//如果直接按照定义规则来计算的话，有些数总是被重复计算了
	private static int fabonacci(int n) {
		//n是第几个的意思
		int[] map = new int[n];
		map[0] = 0;
		map[1] = 1;
		for (int i = 2; i < n; i++)
			map[i] = map[i - 1] + map[i - 2];
		return map[n - 1];
	}

	public static void test6() {
		int a = 22;
		int b = 22;
		System.out.println(" \"22 ^ 22 =\" " + (a ^ b)); //0
		int[] m = {1, 2, 3, 4, 5, 89, 6, 7, 8};
		int[] n = {1, 2, 3, 4, 5, 6, 7, 8};
		System.out.println("n是m的子集， 寻找n比m少的唯一那个值 " + findOne(m, n)); //找到唯一不同的一个
	}

	//n是m的子集， m比n多一个元素
	private static int findOne(int[] a, int[] b) {
		int result = 0;
		for (int i = 0; i < a.length; i++) result ^= a[i];
		for (int i = 0; i < b.length; i++) result ^= b[i];
		return result;
	}
	
	public static void test7(int n) {
		System.out.printf("%d里面有%d个1: \n",n,countOne(n));
	}

	// (int)n用32位二进制表示有多少个1？
	private static int countOne(int n) {
		int count = 0; //1的个数
		while (n != 0) {
			if ((n & 1) == 1) count++;
			n >>= 1;
		}
		return count;
	}

	//编码，格式: n+char,n个字符，n可以大于10
	private static String stringEncoder(String target) {
		StringBuilder result = new StringBuilder();
		int len = target.length(); //避免每次循环都要进行计算
		int i = 0;
		char next = target.charAt(0);
		for (; i < len;) {
			int j = i; //第一次与之后的计算逻辑不一样
			int count = 1; //除了第一个字符，其它字符的首次出现均没有再进行判断，所以count的初始值为1
			char ch = next;
			while (i < len && (next = target.charAt(i++)) == ch) //注意这里的i不会接受外部循环的检查，所以要额外设置检查 
				count++;
			if (j == 0) result.append(count - 1).append(ch);
			else result.append(count).append(ch);
		}
		return result.toString();
	}


	//解码
	private static String stringDecoder(String target) {
		StringBuilder result = new StringBuilder();
		int i = 0; 
		for (;i < target.length();) {
			int j = i; //数值串起始点
			int digits = 0;
			char next = target.charAt(i++);
			while (47 < next && next < 58) {//即代表数字
				digits++;
				next = target.charAt(i++);
			}
			int count = Integer.parseInt(target.substring(j, j + digits)); //得到字符重复个数数值
			//int count = Character.digit(target.charAt(i), 10); //char -> int 但只能得到10以内的
			while (count > 0) {
				result.append(next);
				count--;
			}
		}
		return result.toString();
	}

	//语句反转
	public static String wordsReverse(String target) {
		StringBuilder result = new StringBuilder(target.length());
		String[] tokens = target.split(" ");
		for (int i = tokens.length - 1; i >= 0; i--) {
			result.append(tokens[i]);
			if (i != 0) result.append(" "); //最后一个单词后面是没有空格的，保证反转前后的长度一致;
		}
		return result.toString();
	}

}