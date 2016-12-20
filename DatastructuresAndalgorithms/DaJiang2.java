import java.util.Scanner;

public class DaJiang2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int v = scanner.nextInt(); //v 为顶点个数
		scanner.close();
		int n = 0; //n为树的层数 
		int m = 0; //叶子节点个数
		boolean full = false; //是否为满的完全二叉树
		//1.是否为满二叉树 2.层数n是多少
		for (int i = 1; i <= v; i++) {
			int temp = (1 << i)- 1; //i层满的完全二叉树的顶点个数
			if (temp == v)  {
				full = true;
				n++; 
				break;
			} else if (temp > v) {
				n++;
				break;
			}
				n++;
		}
		if (!full) {
			int last = v - ( (1 << (n - 1)) - 1); //最底层的叶子个数 = 所有的顶点个数 - 倒数第二层及以上的(是满二叉树)
			int lastFather = 0; //倒数第二层有子节点的节点数
			if ((last & 1) == 1) lastFather = last / 2 + 1; //最底层叶子数为奇数时
			else lastFather = last / 2; //最底层叶子数为偶数时
			int lastSecondLeaves = 0; //倒数第二层没有子节点的节点数
			if (2 * (n - 2)  > 1) //预防节点数为2的情况
				lastSecondLeaves = 2 * (n - 2) - lastFather;
			m = last + lastSecondLeaves;
			} else { //是满二叉树
				m = 2 * (n - 1);
			}	 
			System.out.println(n + " " + m);
	}
}