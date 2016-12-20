import java.util.Scanner;

//for example, abc c: cost = 2; abc cb: cost = 2 + 1 + 2
//�󽫵�һ���ַ���ת��Ϊ�ڶ����ַ����Ĵ��ۣ�����Ϊ��ȥ���������ַ�costΪ2������n���µ��ַ�cost = n + 2,�����ַ�ֻ�ܼ��ں���
//input: 
//line1: T ���Դ���
//line2: str1
//line2_: str2
//...
//output: cost1
//        cost2
//...
public class StringCost {

	public static int getMinCost(String str1, String str2) {

		StringBuilder result = new StringBuilder(); //����ƥ����Ϣ,��ʽ����index times" 

		int min = 0, temp = 0, end1 = 0, end2 = 0;
		while (end1 < str1.length() - 1) {
			end2 = 0; //str2�ı������,����
			end1 = str1.indexOf(str2.charAt(end2), end1); //����ƥ���λ�ã�ÿ��end1����ƥ������һ��λ��
			if (end1 < 0) { //û��ƥ����ľͲ��ᱻ��¼
				break; //����ƥ�����
			}
			else {
				temp = 1; //end1��һ����Ч����������˵������ƥ�䵽��1����ͬ���ַ�
				result.append(end1 + " "); //��¼�״�ƥ���λ��
				for (;end1 != (str1.length() - 1) && end2 != (str2.length() - 1);) {
					if (str1.charAt(++end1) == str2.charAt(++end2)) {
						temp++; //��¼��end1���λ��������ƥ��ĸ���
					}
					else break;
				}
				result.append(temp + " "); //��¼����ƥ��ĸ���
			}
		}
		if (result.length() == 0) return 2 + str2.length() + 2; //��ȫû��ƥ������
		
		else {

			String[] str = result.toString().split(" ");
			
			//str[0], str[1] Ϊ��һ��
			String sub = str1.substring(0, Integer.parseInt(str[0])); //����������
			int firstEmpty = sub.length() == 0 ? 0 : 1; //��һ��ƥ��Ŀ�ʼ��û�������ַ�
			int lastEmpty = (str1.length() - Integer.parseInt(str[1]) - Integer.parseInt(str[0])) == 0 ? 0 : 1; //��һ��ƥ��ĺ��滹��û�������ַ�
			
			//str2Len ��ʾstr2����Ҫ�������ַ���Ŀ
			int str2Len = Integer.parseInt(str[1]) == str2.length() ? 0 : ( str2.length() - Integer.parseInt(str[1]) );
			if (str2Len != 0)
				min = firstEmpty * 2 + lastEmpty * 2 + str2Len + 2;
			else min = firstEmpty * 2 + lastEmpty * 2;
			
			//���ƥ�䲻ֹһ��
			for (int i = 2; i < str.length; i += 2) {
				int min_ = 0;
				//�ܵ���һ����ǰ��һ����Ҫ����ɾ����(cost=2)��������û��Ҫ����ɾ������ȷ��
				lastEmpty = (str1.length() - Integer.parseInt(str[i + 1]) - Integer.parseInt(str[i])) == 0 ? 0 : 1;
				str2Len = Integer.parseInt(str[1]) == str2.length() ? 0 : ( str2.length() - Integer.parseInt(str[i + 1]) );
				if (str2Len != 0)
					min_ = 2 + lastEmpty * 2 + str2Len + 2;
				else min_ = firstEmpty * 2 + lastEmpty * 2;
				min = min < min_ ? min : min_;
			}
		}
		
		return min;
	}
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			//��һ���ǲ��Դ��� t
			String count = scan.nextLine();
			int t = Integer.parseInt(count);
			int[] result = new int[t];
			int k = 0;
			//һ��t�����
			for (int i = 0; i < t; i++) {
				String str1 = scan.nextLine();
				String str2 = scan.nextLine();
				result[k++] = getMinCost(str1, str2);
			}
			//���t����Խ��
			for (int i = 0; i < result.length; i++) 
				System.out.println(result[i]);
			}
		scan.close();
		}

}
