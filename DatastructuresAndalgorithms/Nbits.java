//1除以7的结果中的小数点以后的第n位是多少
//142 857 142 857 1.....每6位循环
//第n位小数是i = n % 6: 1:1 2:4 3:2 4:8 5:5 0:7
import java.util.*;

public class Nbits {

	public static void main(String...args) {
		for (int i = 1; i < 10; i++)
		System.out.println(nbits(i,1,7));
	}

	//假设从第一位开始循环
	public static int nbits(int nbits, int mm, int nn) {
		int n = nn;
		int m = mm;
		List<Integer> list = new ArrayList<>();
		if (m < n) 
			m *= 10;
		list.add(m / n);
		while (m != 0) {
			m = (m % 7) * 10;
			if (list.contains(m / n)) break;
			list.add(m / n);
		}
		int count = list.size();
		int i;
		if ((i = nbits % count) != 0) return list.get(i - 1);
		else return list.get(count - 1);
	}
}
