import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Enumeration;

public class ConcurrentHashMapTest {

	public static void main(String...strings) {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
		map.put(1, 5);

	}
	
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
	
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); //与高16位异或
    }

    public static void print(ConcurrentHashMap<Integer, Integer> map) {
    	//Map的遍历方式1
		System.out.println("entrySet()-------------------------------------");
		Iterator<Map.Entry<Integer, Integer>> it1 = map.entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry<Integer, Integer> entry = it1.next();
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}

		//Map的遍历方式2
		System.out.println("values()-------------------------------------");
		Iterator<Integer> it2 = map.values().iterator();
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

		//Map的遍历方式3
		System.out.println("keySet()-------------------------------------");
		Iterator<Integer> it3 = map.keySet().iterator();
		while (it3.hasNext()) {
			int temp = it3.next();
			System.out.println(temp + " : " + map.get(temp));
		}

		//CurrentHashMap的遍历方式4,CurrentHashMap特有的
		System.out.println("elements()-------------------------------------");
		Enumeration<Integer> it4 = map.elements();
		while (it4.hasMoreElements()) {
			System.out.println(it4.nextElement());
		}

		//CurrentHashMap的遍历方式5，CurrentHashMap特有的
		System.out.println("keys()-------------------------------------");
		Enumeration<Integer> it5 = map.keys();
		while (it5.hasMoreElements()) {
			int temp = it5.nextElement();
			System.out.println(temp + " : " + map.get(temp));
		}
    }

}
