import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//�ؼ��ʹ��ˣ����ݸ����Ĺؼ����ҳ�������һ���ı��г��ֵĸ���

public class KeywordsFrequency {

	private Set<String> keywordsSet;

	public KeywordsFrequency(Set<String> keywordsSet) {
		this.keywordsSet = keywordsSet;
	}

	public Map<String, Integer> filter(String txt) {

		Map<String, Integer> map = new HashMap<String, Integer>(); //��Ƶͳ������
		
		int length = txt.length();
		String[] words = new String[length];
		for (int i = 0; i < length; i++) 
			words[i]  = txt.substring(i, length);

		//ÿ����һ���ؼ���ȥƥ��
		for (String keyWords : this.keywordsSet) { 
			//ƥ�����е��ַ�������(�ɴ������ı���ֶ���)
			for (int j = 0; j < length; j++) { 
				int k = 0;
				boolean find = true;
				//����ƥ�䳤��
				while (k < keyWords.length() && keyWords.charAt(k) == words[j].charAt(k)) {
					k++;
				}
				//�Ƿ���ȫƥ��
				if (k != keyWords.length()) {
					find = false;
				}
				//���ƥ��ɹ�������ӻ����޸Ĵ�Ƶ��¼
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

