import java.util.LinkedList;

//ǰ��������� �� ��
//�ǵݹ����˼·�����ҽڵ㡢��ڵ�ѹ��ջ��Ȼ���ӡջ��Ԫ�� 
public class PreOrder {
	
	//Ĭ�ϸ��ڵ���0(����������0��ʼ)
	public static char[] getTree() {
		char[] tree = {'A', 'B', 'C', 'D', 'E'};
		return tree;
	}

	//------��ȡ���ҽڵ㣬���ڵ���������----------
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
		int left = getLeft(present); //��ǰ�ڵ����ڵ�
		int right = getRight(present); //��ǰ�ڵ���ҽڵ�
		stack.addLast(present);
		while (!stack.isEmpty()) {
			present = stack.pollLast(); //ջ��Ԫ����Ϊ��ǰԪ��
			result.append(tree[present]); //������ڵ㡣
			left = getLeft(present); 
			right = getRight(present);
			if (left < capacity && tree[left] != 0) stack.addLast(right); //�ұ߽ڵ���ջ
			if (right < capacity && tree[right] != 0) stack.addLast(left);
		}
		return result.toString();
 	}

	public static void main(String[] args) {
		System.out.println(preOrder(getTree()));
	}

}



