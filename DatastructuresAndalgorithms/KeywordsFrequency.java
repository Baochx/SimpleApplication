import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//关键词过滤：根据给定的关键词找出它们在一段文本中出现的概率

public class KeywordsFrequency {

	private Set<String> keywordsSet;

	public KeywordsFrequency(Set<String> keywordsSet) {
		this.keywordsSet = keywordsSet;
	}

	public Map<String, Integer> filter(String txt) {

		Map<String, Integer> map = new HashMap<String, Integer>(); //词频统计容器
		
		int length = txt.length();
		String[] words = new String[length];
		for (int i = 0; i < length; i++) 
			words[i]  = txt.substring(i, length);

		//每次用一个关键词去匹配
		for (String keyWords : this.keywordsSet) { 
			//匹配所有的字符串数组(由待过滤文本拆分而成)
			for (int j = 0; j < length; j++) { 
				int k = 0;
				boolean find = true;
				//计算匹配长度
				while (k < keyWords.length() && keyWords.charAt(k) == words[j].charAt(k)) {
					k++;
				}
				//是否完全匹配
				if (k != keyWords.length()) {
					find = false;
				}
				//如果匹配成功，则添加或者修改词频记录
				if (find) {
					if (map.containsKey(keyWords)) map.put(keyWords, map.get(keyWords) + 1);
					else map.put(keyWords, 1);
				} 
			}
 		}

 		return map;
 	} 

	public static void main(String...args) {
		 Set<String> set = new HashSet<String>();
		 set.add("ab");
		 set.add("bc");
		 set.add("nn");
		 set.add("hh");
		 KeywordsFrequency f = new KeywordsFrequency(set);
		 Map<String, Integer> map = f.filter("abdfbckllkkkhghbcvnjjab nnjhhjbghhabjjkk");
		 Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		 while (it.hasNext()) {
			 Map.Entry<String, Integer> next = it.next();
			 System.out.println(next.getKey() + " " + next.getValue());
		 }
	}

}

