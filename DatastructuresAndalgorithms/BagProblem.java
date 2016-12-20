import java.util.HashSet;
import java.util.Set;

public class BagProblem {
	int[] price; //物品价值
	int[] volume; //物品体积
	int[][] cost;
	
	
	public void compute01Bag(int n, int w) {
		price = new int[n + 1];
		volume = new int[n + 1];
		cost = new int[n+1][w+1];
		for (int i = 1; i <= n; i++) {
			price[i] = (int)(Math.random()*5) + 1; //物品价值 1~5
			volume[i] = (int)(Math.random()*3) + 1; //物品体积 1~3
		}
	}
	
	/**
	 * 动态规划问题难在状态定义和状态转移：
	 * 状态定义：
	 * 转移：
	 * 边界条件：物品数为0时，价值为0 背包为0时，价值为0
	 * 背包有可能恰好装满 也有可能无法刚好装满 取两种情况的最大值
	 * */
	public int np(int n, int w) {
		
		compute01Bag(n, w); //初始化背包
		
		//核心算法
		for (int i = 0; i <= n; i++) //物品数量
			for (int j = 0; j <= w; j++) //背包大小
				cost[i][j] = i == 0 ? 0 :  //物品数量为0，价值为0
									(j < volume[i] ? cost[i - 1][j] :  //背包大小小于该物品的体积，背包不能装这个物品
										max(cost[i - 1][j], cost[i - 1][j - volume[i]] + price[i])); //背包大小大于第i个物品的体积，
		
		return cost[n][w];
	}
	
	public int max(int m, int n) {
		if (m > n) return m;
		return n;
	}
	
	//show the data;
	public void play() {
		System.out.printf("%-5s %-5s\n","price","volume");
		for (int i = 1; i < price.length; i++) 
			System.out.printf("%-5d %-5d\n",price[i],volume[i]);

		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost[0].length; j++)
				System.out.printf("%2d ",cost[i][j]);
			System.out.println();
		}	
	}

	public static void main(String[] args) {
		MyFibonacci test = new MyFibonacci();
		System.out.println("best value: " + test.np(10, 3));;
		test.play();
		
	}
}
