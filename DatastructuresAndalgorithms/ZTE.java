import java.util.*;

public class ZTE {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,3,2,2,5,6,6,1};
		int[] target = frequencySort(arr);
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
		for (int i = 0; i < target.length; i++)
			System.out.print(target[i] + " ");
	}

	public static int[] frequencySort(int arr[]) {
		int len = arr.length;
		int[] target = new int[len];
		int index = 0;
		Map<Integer, Integer> map = new LinkedHashMap<>(); //ensure the insert order;
		for (int i = 0; i < len; i++) {
			int temp = arr[i];
			if (map != null && map.containsKey(temp)) 
				map.put(temp, map.get(temp) + 1);
			else map.put(temp, 1);
		}
		while (!map.isEmpty()) {
			Iterator< Map.Entry<Integer, Integer> > it  = map.entrySet().iterator();
			Map.Entry<Integer, Integer> entry = it.next();
			int maxVal = entry.getKey();
			int maxFre = entry.getValue();
			//find the highest frequency.
			while (it.hasNext()) {
				Map.Entry<Integer, Integer> entry_ = it.next();
				int key = entry_.getKey();
				int value = entry_.getValue();
				if (value > maxFre) {
					maxVal = key;
					maxFre = value;
				}
			}
			map.remove(maxVal); //remove the highest frequency at present;
			int i = index;
			for (; i < (index + maxFre); i++) 
				target[i] = maxVal;
			index =  i; //reset the k;
		}
		return target;
	}
}