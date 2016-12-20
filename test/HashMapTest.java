import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapTest {

	public static void main(String[] args) {
		test();
	}

	//HashMap����nullΪ��ֵ������Ϊһ������value
	public static void test() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(null, 1);
		map.put(1, null);
		map.put(2, null);
		//map.put(2, 3);
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Map.Entry<Integer, Integer>> it = set.iterator();
		System.out.println(map.get(null));
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> entry = it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		//��ʱ����ж��Ƿ����һ����ֵ
		//1,�����ַ���������nullΪvalue��ʱ��ͻ��������
		if (map.get(3) == null) System.out.println("3 doesn't exists!");
		//2
		if (!map.containsKey(3)) System.out.println("3 doesn't exists!");
	}

}
