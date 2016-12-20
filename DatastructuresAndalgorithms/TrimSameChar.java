import java.util.Scanner;

//将第一个字符串中不存在于第二个字符串中的字符输出
public class TrimSameChar {
	private Main1() {}
	public static void main(String[] args) {
			String m = "12334";
			String n = "34";
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < m.length(); i++) {
				boolean find = false;
				char chm = m.charAt(i);
				for (int j = 0; j < n.length(); j++) {
					if (chm == n.charAt(j)) {
						find = true;
						break;
					}
				}
				if (!find) str.append(chm);
			}
			System.out.print(str);
	}
}
