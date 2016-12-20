import java.util.*;

public class WordList {
	
	public static int addWord(String[] arr) {
		int length = arr.length;
		
		//�˶�ά����ֻ�����ڸ��������������ع���
		char[][] firstAndLast = new char[length][2];
		for (int i = 0; i < length; i++) {
			firstAndLast[i][0] = arr[i].charAt(0);
			firstAndLast[i][1] = arr[i].charAt(arr[i].length() - 1);
		}
		
		//�ҵ���β��ͬ���ַ���,��Щ�ַ���Ҫ��������
		boolean[] duplicate = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (firstAndLast[i][0] == firstAndLast[i][1])  duplicate[i] = true; 
		}
		
		//����ʹ��ĳһ�������㣬ֱ���Ŷӳɹ�
		for (int i = 0; i < length; i++) {
			char head = firstAndLast[i][1]; //�����ĩβ��ĸ������
			boolean[] visited = new boolean[length]; //�������
			int count = 1; //�ɹ�����
			visited[i] = true;
			
			//ÿ�����㶼Ҫ���ȼ���Ƿ��������Ŷӵ�duplicate
			for (int k = 0; k < length; k++) {
				if (visited[k]) continue; 
				if (duplicate[k] && head == firstAndLast[k][0]) {
					head = firstAndLast[k][1]; 
					visited[k] = true; 
					count++;
				}
			}
	
			in: for (int j = 0; j < length; j++) {
				if (visited[j]) continue; //�Ѿ����ʹ��ľ�����
				if (head == firstAndLast[j][0]) { //ɨ�����ַ�������ĸ����һ����β��ĸ��ͬ����ʾ������
					head = firstAndLast[j][1]; //�µĶ���
					visited[j] = true; //�Ѿ��������ı��
					count++; //�ɹ�������1
					//ÿ�����½ڵ㣬���ȼ���Ƿ��������Ŷӵ�duplicate
					for (int k = 0; k < length; k++) {
						if (visited[k]) continue; 
						if (duplicate[k] && head == firstAndLast[k][0]) {
							head = firstAndLast[k][1]; 
							visited[k] = true; 
							count++;
						}
					}
					
					continue in; //����ɨ�������ַ���
				}
			}
			if (count == length) return 1; //����˴�ɨ��ɹ���ֱ�ӷ���1 ���򻻸��������ɨ���Ŷ�
		}
		return -1;
	}

	public static void main(String[] args) {
		//�����������β�ַ���ͬ���ַ�����ֹһ��
		String[] str = {"41", "13", "12", "21"}; //ab bb bc ca aaaa aa aji
		System.out.println(test(str));
		

	}

	public static int test(String[] words) {
		//���δ�ÿ�����ʿ�ʼ���ԣ�ֱ���ҳ�����pass���Ǹ�
		for (int i = 0; i < words.length; i++) {
			
			List<String> remain = new ArrayList<>();
			for (int j = 0; j < words.length; j++)
				remain.add(words[j]); //��δƥ��ĵ���

			remain.remove(words[i]); //�Ѿ�ƥ�䣬ɾ��
			LinkedList<String> queue = get(remain, words[i]); //���ϡ����ӹ��򡱵���һ�������б�
			System.out.println("queue: ");
			print(queue);
			while (!queue.isEmpty()) {

				String firstWord = queue.getFirst();
				System.out.println("firstWord: " + firstWord);
				while (!queue.isEmpty() && firstWord.charAt(0) == firstWord.charAt(firstWord.length() - 1)) {
					queue.removeFirst();
					remain.remove(firstWord); //�Ѿ�ƥ�䣬ɾ��
					firstWord = queue.getFirst();
				}
				remain.remove(firstWord); //�Ѿ�ƥ�䣬ɾ��
				System.out.println("remain: ");
				print(remain);
				LinkedList<String> nextQueue = get(remain, firstWord);
				System.out.println("nextQueue: " );
				print(nextQueue);
				//if (nextQueue != null) queue = nextQueue; //����Ϊ��Ҳ�᷵��һ������ʵ����
				if (!nextQueue.isEmpty()) queue = nextQueue;
				else {}

			}
			if(remain.size() == 0) return 1;
		}
		return -1;
	}

	//���ط��ϡ����ӹ��򡱵���һ�������б���β��ͬ�ķ���ǰ�棬����ƥ��
	public static LinkedList<String> get(List<String> remain, String word) {
		LinkedList<String> target = new LinkedList<>();
		char last = word.charAt(word.length() - 1);
		Iterator<String> it = remain.iterator();
		while (it.hasNext()) {
			String str = it.next();
			System.out.println("next: " + str);
			char fir = str.charAt(0);
			char las = str.charAt(str.length() - 1);
			if (fir == las) {
				target.addFirst(str);
			}
			else if (fir == last) {
				target.add(str);
			}
		}
		return target;
	}

	public static void print(List<String> str) {
		for (int i = 0; i < str.size(); i++)
			System.out.print(str.get(i));
		System.out.println();
	}

}
