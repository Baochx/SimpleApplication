import java.util.*;

public class MapAlg {

	public static void main(String[] args) {
		test();
	}

	//间接利用TreeMap中的红黑树来进行排序
	public static void test() {
		Map<Integer, Integer> hashMap = new HashMap<>();
		Map<Integer, List<Integer>> treeMap = new TreeMap<>((i,j) -> j - i); //List<Integer>为相同频率的随机数

		//随机得到10000个100以内的整数并统计频率，以<随机数,频率>的形式存入hashMap
		for (int i = 0; i < 10000; i++) {
			int temp = new Random().nextInt(100);
			if (!hashMap.containsKey(temp)) hashMap.put(temp, 1);
			else hashMap.put(temp, hashMap.get(temp) + 1);
		}

		//利用treeMap的特性进行排序，以<频率,随机数列表>的形式存入treeMap
		Iterator<Integer> it = hashMap.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next(); //key是统计后互不相同的随机数
			int value = hashMap.get(key); //value是统计后随机数的出现频率
			List<Integer> list = new LinkedList<>(); //LinkedList能保证顺序，插入速度也快些
			list.add(key);
			if (!treeMap.containsKey(value)) treeMap.put(value, list); 
			else treeMap.get(value).add(key); //频率相同则添加到相应的键值列表中
		}

		//遍历统计结果
		int result = 0;
		Iterator<Integer> ite = treeMap.keySet().iterator();
		while (ite.hasNext()) {
			int temp = ite.next(); //键值为出现频率
			result += temp * treeMap.get(temp).size(); //频率 * 个数，最后检查有没有统计疏漏
			System.out.print(temp + ": ");
			for (Integer i : treeMap.get(temp)) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		if (result == 10000) System.out.println("result: " + result + " 没有统计疏漏");
	}

}
