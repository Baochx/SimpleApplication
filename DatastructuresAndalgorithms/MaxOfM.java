/**
 * @author Chongxue
 * 本算法用于解决在一个数组的指定范围内寻找第M大的数
 * 算法思路：
 * 1.确定好数组目标索引范围，利用快排的同时进行比较中轴索引位置是不是第M大的，是则返回，不是就继续从合适的一半继续递归
 * 2.难点在于，判断该从中轴的哪一侧开始进行寻找，中轴是第(i - l + 1)大的，并不是第i大，这点要明确，这里的i是中轴索引值
 * 3.容易混淆的是,如果 m > (i - 1 + 1) 则往右边继续递归快排 但此时的m = m - (i - l + 1)
 * 
 * 优化的地方：
 * 1.中轴选择是随机的，然后与最右边进行交换，一直选r作为中轴(等同于随机选择中轴)
 * 
 * 小坑:
 * 1.寻找第M大应该是按递减排序而不是递增
 * 2.快排递归中返回int值，每次都用一个target临时变量保存结果并返回，不能在if里面直接用return语句
 * */
public class MaxOfM {
	
	//start,end数组范围; m表示第几大(这个数可以重复但不要求原来的顺序，也就是不要求：重复时原来先出现的优先选择，因为快排是不稳定的)
	public static int maxOfM(int[] n, int start, int end, int m) throws Exception {
		if (n == null || start <= 0 || m <= 0 || end > n.length || m > (end - start + 1)) throw new Exception("Illegal Input!");
		return quick(n, start - 1, end - 1,m);
	}
	
	public static int quick(int[] n, int l, int r, int m) {
		int index = l + (int)(Math.random() * (r - l + 1)); //寻找任意位置作为轴
		swap(n, r, index);
		int temp = n[r];
		int i = l, j = r;
		while (i < j) {
			while(i < j && n[i] >= temp) i++; //大于等于的在左边
			n[j] = n[i];
			while(i < j && n[j] < temp) j--; //小于的在右边
			n[i] = n[j];
		}
		n[i] = temp;
		if ((i - l + 1) == m) return n[i];
		else if ((i - l + 1) < m) return quick(n, i + 1, r, m - (i - l + 1));
		else return quick(n, l, i - 1, m);
	}
	
	public static void swap(int[] n, int i, int j) {
		int temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}

	public static void main(String[] args) {
		int[] n = {1,2,3,4,7,6,3333,1,1,8};
		try {
			System.out.println(maxOfM(n, 2, 5, 2));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
