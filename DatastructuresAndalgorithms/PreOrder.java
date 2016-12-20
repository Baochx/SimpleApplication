import java.util.LinkedList;

//前序遍历：根 左 右
//非递归遍历思路：将右节点、左节点压入栈，然后打印栈顶元素 
public class PreOrder {
	
	//默认根节点是0(数组索引从0开始)
	public static char[] getTree() {
		char[] tree = {'A', 'B', 'C', 'D', 'E'};
		return tree;
	}

	//------获取左右节点，父节点索引函数----------
	public static int getLeft(int i) {
		return 2 * i + 1;
	}

	public static int getRight(int i) {
		return 2 * i + 2;
	}

	public static int root(int node) {
		return (node - 1) / 2;
	}

	//non-recursive
	public static String preOrder(char[] tree) {
		LinkedList<Integer> stack = new LinkedList<>(); //LinkedList implements Deque
		StringBuilder result = new StringBuilder(); //the print_string
		int capacity = tree.length; //the border of the tree
		int present = 0; //0 is root
		int left = getLeft(present); //当前节点的左节点
		int right = getRight(present); //当前节点的右节点
		stack.addLast(present);
		while (!stack.isEmpty()) {
			present = stack.pollLast(); //栈顶元素作为当前元素
			result.append(tree[present]); //输出根节点。
			left = getLeft(present); 
			right = getRight(present);
			if (left < capacity && tree[left] != 0) stack.addLast(right); //右边节点入栈
			if (right < capacity && tree[right] != 0) stack.addLast(left);
		}
		return result.toString();
 	}

	public static void main(String[] args) {
		System.out.println(preOrder(getTree()));
	}

}



