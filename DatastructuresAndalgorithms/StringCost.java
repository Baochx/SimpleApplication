import java.util.Scanner;

//for example, abc c: cost = 2; abc cb: cost = 2 + 1 + 2
//求将第一个字符串转换为第二个字符串的代价，规则为：去除连续的字符cost为2，新增n个新的字符cost = n + 2,新增字符只能加在后面
//input: 
//line1: T 测试次数
//line2: str1
//line2_: str2
//...
//output: cost1
//        cost2
//...
public class StringCost {

	public static int getMinCost(String str1, String str2) {

		StringBuilder result = new StringBuilder(); //保存匹配信息,格式：“index times" 

		int min = 0, temp = 0, end1 = 0, end2 = 0;
		while (end1 < str1.length() - 1) {
			end2 = 0; //str2的遍历起点,清零
			end1 = str1.indexOf(str2.charAt(end2), end1); //出现匹配的位置，每次end1都是匹配后的下一个位置
			if (end1 < 0) { //没有匹配项的就不会被记录
				break; //结束匹配遍历
			}
			else {
				temp = 1; //end1是一个有效的索引，则说明至少匹配到了1个相同的字符
				result.append(end1 + " "); //记录首次匹配的位置
				for (;end1 != (str1.length() - 1) && end2 != (str2.length() - 1);) {
					if (str1.charAt(++end1) == str2.charAt(++end2)) {
						temp++; //记录从end1这个位置能连续匹配的个数
					}
					else break;
				}
				result.append(temp + " "); //记录连续匹配的个数
			}
		}
		if (result.length() == 0) return 2 + str2.length() + 2; //完全没有匹配的情况
		
		else {

			String[] str = result.toString().split(" ");
			
			//str[0], str[1] 为第一组
			String sub = str1.substring(0, Integer.parseInt(str[0])); //不包括后面
			int firstEmpty = sub.length() == 0 ? 0 : 1; //第一个匹配的开始有没有其它字符
			int lastEmpty = (str1.length() - Integer.parseInt(str[1]) - Integer.parseInt(str[0])) == 0 ? 0 : 1; //第一个匹配的后面还有没有其它字符
			
			//str2Len 表示str2还需要新增的字符数目
			int str2Len = Integer.parseInt(str[1]) == str2.length() ? 0 : ( str2.length() - Integer.parseInt(str[1]) );
			if (str2Len != 0)
				min = firstEmpty * 2 + lastEmpty * 2 + str2Len + 2;
			else min = firstEmpty * 2 + lastEmpty * 2;
			
			//如果匹配不止一组
			for (int i = 2; i < str.length; i += 2) {
				int min_ = 0;
				//能到这一步，前面一定有要连续删除的(cost=2)，后面有没有要连续删除的则不确定
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
			//第一行是测试次数 t
			String count = scan.nextLine();
			int t = Integer.parseInt(count);
			int[] result = new int[t];
			int k = 0;
			//一共t组测试
			for (int i = 0; i < t; i++) {
				String str1 = scan.nextLine();
				String str2 = scan.nextLine();
				result[k++] = getMinCost(str1, str2);
			}
			//输出t组测试结果
			for (int i = 0; i < result.length; i++) 
				System.out.println(result[i]);
			}
		scan.close();
		}

}
