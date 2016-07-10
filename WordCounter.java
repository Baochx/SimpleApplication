import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
	
	public static Map<String, Integer> getWordMap(String fileName) {
		Map<String, Integer> wordMap = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(fileName);
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));) {
			
			String lines = null;
			Pattern pattern = Pattern.compile("(\\s+|\\p{Punct})+");
			Pattern pattern2 = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）-——+|{}【】‘；：”“’。，、？\t\0]");
			while ((lines = br.readLine()) != null) {
				String[] words = pattern.split(lines);
				for (String word : words) {
					Matcher matcher = pattern2.matcher(word);
					word = matcher.replaceAll("").trim().toLowerCase();
					if (wordMap.containsKey(word)) wordMap.put(word, wordMap.get(word) + 1);
					else wordMap.put(word, 1);
				}
			}
			
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		
		return wordMap;
	}
	
	public static List<Entry<String, Integer>> sortByValueInDecreasingOrder(Map<String, Integer> wordMap) {
		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue().compareTo(o1.getValue()));
			}
		});
		return list;
}
	
	public static void main(String[] args) {
		System.out.println("Please enter the file path: ");
		try (Scanner scan = new Scanner(System.in);) {
			String fileName = scan.nextLine();
			List<Entry<String, Integer>> list = sortByValueInDecreasingOrder(getWordMap(fileName));
			Iterator<Entry<String, Integer>> it = list.iterator();
			while (it.hasNext()) {
				Entry<String, Integer> entry = it.next();
				System.out.println(entry.getKey() + " " + entry.getValue());
			}
		}	
   }
	
} 