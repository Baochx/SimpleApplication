import java.util.*;

public class MapAlg {

	public static void main(String[] args) {
		test();
	}

	//�������TreeMap�еĺ��������������
	public static void test() {
		Map<Integer, Integer> hashMap = new HashMap<>();
		Map<Integer, List<Integer>> treeMap = new TreeMap<>((i,j) -> j - i); //List<Integer>Ϊ��ͬƵ�ʵ������

		//����õ�10000��100���ڵ�������ͳ��Ƶ�ʣ���<�����,Ƶ��>����ʽ����hashMap
		for (int i = 0; i < 10000; i++) {
			int temp = new Random().nextInt(100);
			if (!hashMap.containsKey(temp)) hashMap.put(temp, 1);
			else hashMap.put(temp, hashMap.get(temp) + 1);
		}

		//����treeMap�����Խ���������<Ƶ��,������б�>����ʽ����treeMap
		Iterator<Integer> it = hashMap.keySet().iterator();
		while (it.hasNext()) {
			int key = it.next(); //key��ͳ�ƺ󻥲���ͬ�������
			int value = hashMap.get(key); //value��ͳ�ƺ�������ĳ���Ƶ��
			List<Integer> list = new LinkedList<>(); //LinkedList�ܱ�֤˳�򣬲����ٶ�Ҳ��Щ
			list.add(key);
			if (!treeMap.containsKey(value)) treeMap.put(value, list); 
			else treeMap.get(value).add(key); //Ƶ����ͬ����ӵ���Ӧ�ļ�ֵ�б���
		}

		//����ͳ�ƽ��
		int result = 0;
		Iterator<Integer> ite = treeMap.keySet().iterator();
		while (ite.hasNext()) {
			int temp = ite.next(); //��ֵΪ����Ƶ��
			result += temp * treeMap.get(temp).size(); //Ƶ�� * �������������û��ͳ����©
			System.out.print(temp + ": ");
			for (Integer i : treeMap.get(temp)) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		if (result == 10000) System.out.println("result: " + result + " û��ͳ����©");
	}

}
