import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

	//w(A-Z,a-z,0-9,_中的任意一个); d(0-9中的任意一个); 
	//\s表示(空格、制表符、换页符); \v水平制表符; 这两个不能打印。
	//\f换页符; \t水平制表符; 这两个可以打印。
	//小数点可以匹配除了\n以外的所有字符。
	//[abc]能匹配其中的任意一个，只是一个; [^abc]匹配除abc以外的任意一个字符。
	//{3}重复匹配三次，{2,5}最少2次，最多5次，{4,}至少匹配4次
	//?: 0次或1次，相当于{0,1}; +: 1次或多次，相当于{1,}; *: 0次或是无数次
	//^: 匹配字符串开始的地方，比如: "^aaa" 在匹配 "xxx aaa xxx" 时，匹配失败，"^aaa" 在匹配 "aaa xxx" 时，成功
	//&: 规则类似上面，不过匹配的是字符串的末尾
	//\b: 匹配单词的边缘，"\\b.*" 在匹配 "@@@abc" 时，匹配到abc，这样就能匹配到单词
	//"^"，"$"，"\b"本身不会匹配任何字符，只是代表一种附加条件
	//要求它在匹配结果中所处位置的左右两边，其中一边是 "\w" 范围，另一边是 非"\w" 的范围
	//注意\w在转义的时候需要\\w
	//(): 里面的内容会被看作一个整体，|: 表示或的关系，匹配一个或者是另外一个
	//非贪婪模式: 在* + 之后再加 ? 就是尽可能地减少匹配，比如@\\w+?在匹配"abc@abc"的时候匹配"@a",而"@\\w+?@"匹配"abc@abbb@abc"时匹配到@abbb@
	//\B \S \D \W,大写代表着跟小写相反的匹配意思

	public static void main(String[] args) {
		test1();
		test2();
	}

	public static void test1() {
		System.out.println("\ntest1 begins:");
		String regular = "(\'|\")(.*?)(\'|\")"; //非贪婪匹配
		Pattern p = Pattern.compile(regular);
		Matcher m = p.matcher("\"hello\",\"world\"");
		while(m.find())
			System.out.println(m.group());
		System.out.println("test1 ends:");
	}

	public static void test2() {
		System.out.println("\ntest1 begins:");
		String regular = "do";
		Pattern p = Pattern.compile(regular);
		Matcher m = p.matcher("done do dog");
		while(m.find())
			System.out.println(m.group());
		System.out.println("test1 ends:");
	}
	
	//some examples:
	// "\d+\.?\d*" 在匹配 "It costs $12.5" 时，匹配到: 12.5
	// "go{2,8}gle" 在匹配 "Ads by goooooogle" 时，匹配到: goooooogle
	// "[^abc]" 匹配 "abc123" 时，匹配到的是1，只匹配一个字符
	// "[bcd][bcd]" 匹配 "abc123" 时，匹配到的是: bc
	// "a.\d"，在匹配 "aaa100" 时，匹配到的是: aa1
	// "\d\d"，在匹配 "abc123" 时，匹配到的是: 12
	// "￥(\d+\.?\d*)" 在匹配 "＄10.9,￥20.5" 时，匹配到的是: ￥20.5
	
	//反向引用:()里匹配到的内容可以单独读出，引用方式: \1...n，()的排序: ( 在前的先排序号
	
	//一些常用的规则
	//1.匹配整个字符串：^###$
	//2.匹配单词\b######\b
}
