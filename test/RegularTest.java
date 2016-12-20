import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

	//w(A-Z,a-z,0-9,_�е�����һ��); d(0-9�е�����һ��); 
	//\s��ʾ(�ո��Ʊ������ҳ��); \vˮƽ�Ʊ��; ���������ܴ�ӡ��
	//\f��ҳ��; \tˮƽ�Ʊ��; ���������Դ�ӡ��
	//С�������ƥ�����\n����������ַ���
	//[abc]��ƥ�����е�����һ����ֻ��һ��; [^abc]ƥ���abc���������һ���ַ���
	//{3}�ظ�ƥ�����Σ�{2,5}����2�Σ����5�Σ�{4,}����ƥ��4��
	//?: 0�λ�1�Σ��൱��{0,1}; +: 1�λ��Σ��൱��{1,}; *: 0�λ���������
	//^: ƥ���ַ�����ʼ�ĵط�������: "^aaa" ��ƥ�� "xxx aaa xxx" ʱ��ƥ��ʧ�ܣ�"^aaa" ��ƥ�� "aaa xxx" ʱ���ɹ�
	//&: �����������棬����ƥ������ַ�����ĩβ
	//\b: ƥ�䵥�ʵı�Ե��"\\b.*" ��ƥ�� "@@@abc" ʱ��ƥ�䵽abc����������ƥ�䵽����
	//"^"��"$"��"\b"������ƥ���κ��ַ���ֻ�Ǵ���һ�ָ�������
	//Ҫ������ƥ����������λ�õ��������ߣ�����һ���� "\w" ��Χ����һ���� ��"\w" �ķ�Χ
	//ע��\w��ת���ʱ����Ҫ\\w
	//(): ��������ݻᱻ����һ�����壬|: ��ʾ��Ĺ�ϵ��ƥ��һ������������һ��
	//��̰��ģʽ: ��* + ֮���ټ� ? ���Ǿ����ܵؼ���ƥ�䣬����@\\w+?��ƥ��"abc@abc"��ʱ��ƥ��"@a",��"@\\w+?@"ƥ��"abc@abbb@abc"ʱƥ�䵽@abbb@
	//\B \S \D \W,��д�����Ÿ�Сд�෴��ƥ����˼

	public static void main(String[] args) {
		test1();
		test2();
	}

	public static void test1() {
		System.out.println("\ntest1 begins:");
		String regular = "(\'|\")(.*?)(\'|\")"; //��̰��ƥ��
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
	// "\d+\.?\d*" ��ƥ�� "It costs $12.5" ʱ��ƥ�䵽: 12.5
	// "go{2,8}gle" ��ƥ�� "Ads by goooooogle" ʱ��ƥ�䵽: goooooogle
	// "[^abc]" ƥ�� "abc123" ʱ��ƥ�䵽����1��ֻƥ��һ���ַ�
	// "[bcd][bcd]" ƥ�� "abc123" ʱ��ƥ�䵽����: bc
	// "a.\d"����ƥ�� "aaa100" ʱ��ƥ�䵽����: aa1
	// "\d\d"����ƥ�� "abc123" ʱ��ƥ�䵽����: 12
	// "��(\d+\.?\d*)" ��ƥ�� "��10.9,��20.5" ʱ��ƥ�䵽����: ��20.5
	
	//��������:()��ƥ�䵽�����ݿ��Ե������������÷�ʽ: \1...n��()������: ( ��ǰ���������
	
	//һЩ���õĹ���
	//1.ƥ�������ַ�����^###$
	//2.ƥ�䵥��\b######\b
}
