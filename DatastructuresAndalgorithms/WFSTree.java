
public class WFSTree {

	private int count;

	class Node {
		private int value;
		private Node left;
		private Node right;
		

		Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		public int getValue() {
			return this.value;
		}

		public Node getLeft() {
			return this.left;
		}

		public Node getRight() {
			return this.right;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public void setRight(Node right) {
			this.right = right;
		}

	}

	public int getCount() {
		return this.count;
	}

	//create a tree with n nodes and initialvalue = init;
	public Node getTree(int init, int n) {
		int value = init;
		Node root = new Node(value, null, null);
		for (int i = 1; i < n / 2; i++) {
			value = --value;
			addNode(root, value);
		}
		int value_ = init;
		for (int i = n / 2; i <= n; i++) {
			value_ = ++value_;
			addNode(root, value_);
		}
		return root;
	}

	//add new node to the binary search tree
	public void addNode(Node root, int value) {
		int rootValue = root.getValue();
		Node pre = root;
		Node father = pre;
		boolean left = true;
		while (pre != null) {
			if (value < rootValue) {
				father = pre; //the last non-null father root
				pre = pre.getLeft(); 
			}
			else {
				father = pre;
				left = false; //is the right son node
				pre = pre.getRight();
			}
		}
		pre = new Node(value, null, null);
		if (left) father.setLeft(pre);
		else father.setRight(pre);
		count++;
	}

	public String getWFS(Node root) {

		Node[] queue = new Node[this.count]; //the queue

		StringBuilder result = new StringBuilder();
		Node re = null;
		enqueue(queue, root);
		while (size(queue) > 0) {
			re = dequeue(queue);
			result.append(re.getValue()).append(" "); //add the value to print string
			//�ּ��������1.������ұ�û�� 2.������ұ��� 3.���û���ұ�û�� 4.���û���ұ�û��
			if (re.getLeft() != null) {
				enqueue(queue, re.getLeft());
				if (re.getRight() != null) 
					enqueue(queue, re.getRight());
			} else if (re.getRight() != null) {
				enqueue(queue, re.getRight());
			}
 
		}
		return result.toString();
	}

	//ģ�������
	public Node dequeue(Node[] queue) {
		Node peek = queue[0];
		int j = 0;
		int size = size(queue); //the actual number in queue
		if (size > 1) {
			for (; j < size - 1; j++) 
				queue[j] = queue[j + 1];
			queue[j] = null;  //this the key to get the right size of queue. 
		} else if (size == 1) {
			queue[0] = null; //��ʱsize - 2 = -1 �����
		}
		return peek;
	}

	//ģ�����
	public void enqueue(Node[] queue, Node node) {
		int i = size(queue);
		queue[i] = node;
	}

	public int size(Node[] queue) {
		int size = 0;
		int i = 0;
		while (queue[i++] != null) {
			size++;
		}
		return size;
	}

	public static void main(String[] args) {
		WFSTree wfs = new WFSTree();
		Node root = wfs.getTree(8, 8);
		wfs.addNode(root, 1);
		wfs.addNode(root, 2);
		wfs.addNode(root, 3);
		System.out.println(wfs.getWFS(root));
	}

}