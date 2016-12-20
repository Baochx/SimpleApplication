//需求描述：给一个含有空格的字符串，用.表示空格，输出没有空格的字符串
//时间复杂度是O(n)，空间复杂度是O(1)

public class Trim {

	public static String trim(String str) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '.') result.append(str.charAt(i));
		}
		return result.toString();
	}
	public static void main(String...args) {
		String str = ".......123555.....heheh......";
		System.out.println(trim(str));
		//等同于使用下面的方法
		String newStr = str.replaceAll("\\u002e", "");
		System.out.println(newStr);
	}

}