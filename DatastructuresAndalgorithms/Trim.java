//������������һ�����пո���ַ�������.��ʾ�ո����û�пո���ַ���
//ʱ�临�Ӷ���O(n)���ռ临�Ӷ���O(1)

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
		//��ͬ��ʹ������ķ���
		String newStr = str.replaceAll("\\u002e", "");
		System.out.println(newStr);
	}

}