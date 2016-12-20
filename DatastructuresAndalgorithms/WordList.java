import java.util.*;

public class WordList {
	
	public static int addWord(String[] arr) {
		int length = arr.length;
		
		//此二维数组只是用于辅助分析，可以重构掉
		char[][] firstAndLast = new char[length][2];
		for (int i = 0; i < length; i++) {
			firstAndLast[i][0] = arr[i].charAt(0);
			firstAndLast[i][1] = arr[i].charAt(arr[i].length() - 1);
		}
		
		//找到首尾相同的字符串,这些字符串要优先连接
		boolean[] duplicate = new boolean[length];
		for (int i = 0; i < length; i++) {
			if (firstAndLast[i][0] == firstAndLast[i][1])  duplicate[i] = true; 
		}
		
		//轮流使用某一个当顶点，直到排队成功
		for (int i = 0; i < length; i++) {
			char head = firstAndLast[i][1]; //顶点的末尾字母当顶点
			boolean[] visited = new boolean[length]; //遍历标记
			int count = 1; //成功个数
			visited[i] = true;
			
			//每个顶点都要优先检查是否有满足排队的duplicate
			for (int k = 0; k < length; k++) {
				if (visited[k]) continue; 
				if (duplicate[k] && head == firstAndLast[k][0]) {
					head = firstAndLast[k][1]; 
					visited[k] = true; 
					count++;
				}
			}
	
			in: for (int j = 0; j < length; j++) {
				if (visited[j]) continue; //已经访问过的就跳过
				if (head == firstAndLast[j][0]) { //扫到的字符串首字母和上一个的尾字母相同，表示能连接
					head = firstAndLast[j][1]; //新的顶点
					visited[j] = true; //已经遍历过的标记
					count++; //成功个数加1
					//每当有新节点，优先检查是否有满足排队的duplicate
					for (int k = 0; k < length; k++) {
						if (visited[k]) continue; 
						if (duplicate[k] && head == firstAndLast[k][0]) {
							head = firstAndLast[k][1]; 
							visited[k] = true; 
							count++;
						}
					}
					
					continue in; //继续扫描所有字符串
				}
			}
			if (count == length) return 1; //如果此次扫描成功，直接返回1 否则换个顶点继续扫描排队
		}
		return -1;
	}

	public static void main(String[] args) {
		//特殊情况：首尾字符相同的字符串不止一个
		String[] str = {"41", "13", "12", "21"}; //ab bb bc ca aaaa aa aji
		System.out.println(test(str));
		

	}

	public static int test(String[] words) {
		//依次从每个单词开始尝试，直到找出可以pass的那个
		for (int i = 0; i < words.length; i++) {
			
			List<String> remain = new ArrayList<>();
			for (int j = 0; j < words.length; j++)
				remain.add(words[j]); //尚未匹配的单词

			remain.remove(words[i]); //已经匹配，删除
			LinkedList<String> queue = get(remain, words[i]); //符合“连接规则”的下一个单词列表
			System.out.println("queue: ");
			print(queue);
			while (!queue.isEmpty()) {

				String firstWord = queue.getFirst();
				System.out.println("firstWord: " + firstWord);
				while (!queue.isEmpty() && firstWord.charAt(0) == firstWord.charAt(firstWord.length() - 1)) {
					queue.removeFirst();
					remain.remove(firstWord); //已经匹配，删除
					firstWord = queue.getFirst();
				}
				remain.remove(firstWord); //已经匹配，删除
				System.out.println("remain: ");
				print(remain);
				LinkedList<String> nextQueue = get(remain, firstWord);
				System.out.println("nextQueue: " );
				print(nextQueue);
				//if (nextQueue != null) queue = nextQueue; //就算为空也会返回一个链表实例的
				if (!nextQueue.isEmpty()) queue = nextQueue;
				else {}

			}
			if(remain.size() == 0) return 1;
		}
		return -1;
	}

	//返回符合“连接规则”的下一个单词列表：首尾相同的放在前面，优先匹配
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
