import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//给用户订单分别按价格和时间排序(以递减的顺序)并排号
public class MergeSortPractice {

	class ConsumeInfo {
		private long uid;
		private long ordered;
		private double price;
		private Date createTime;

		ConsumeInfo(long uid, double price, Date createTime) {
			this.uid = uid;
			this.price = price;
			this.createTime = createTime;
		}

		@Override
		public String toString() {
			return "uid:" + this.uid +  " ordered:" + this.ordered + " price:" + this.price + " createTime:" + this.createTime;
		}

		public double getPrice() {
			return this.price;
		}

		public Date getTime() {
			return this.createTime;
		}

		public void setOrdered(long i) {
			this.ordered = i;
		}

	}

	public void merge(List<ConsumeInfo> a, int p, int q, int r, Comparator<ConsumeInfo> comp) {
		int i, j, k,
		n1 = q - p + 1,
		n2 = r - q;
		ConsumeInfo[] L = new ConsumeInfo[n1]; 
		ConsumeInfo[] R = new ConsumeInfo[n2];
		i = j = 0;
		k = p;
	    for (; i < n1; i++) {
	    	L[i] = a.get(k++);
	    }
	    for (; j < n2; j++) {
	    	R[j] = a.get(k++);
	    }
		i = j = 0;
		k = p;
		while (i < n1 && j < n2) 
			if(comp.compare(L[i], R[j]) <= 0)
				a.set(k++, L[i++]);
			else 
				a.set(k++, R[j++]); 
		if (i < n1)
			while (i < n1) 
				a.set(k++, L[i++]);
		if (j < n2)
			while (j < n2)
				a.set(k++, R[j++]);
	}

	public void mergeSort(List<ConsumeInfo> a, int p, int r, Comparator<ConsumeInfo> comp){
		if (p < r) { 
			int q = (p + r) / 2;
			mergeSort(a, p, q, comp);
			mergeSort(a, q + 1, r, comp);
			merge(a, p, q, r, comp);
		}
	}

	public void sortByPrice(List<ConsumeInfo> list) {
		mergeSort(list, 0, list.size() - 1, 
				(ConsumeInfo o1, ConsumeInfo o2) -> ( o2.getPrice() > o1.getPrice() ? 
				1 : ( o2.getPrice() == o1.getPrice() ? 0 : -1) ) );
		//排好序后设置顺序
		for (int i = 0; i < list.size(); i++) 
			list.get(i).setOrdered(i+1);
		list.forEach( (ConsumeInfo con) -> System.out.println(con) );
	}

	public void sortByDate(List<ConsumeInfo> list) {
		mergeSort(list, 0, list.size() - 1, 
				(ConsumeInfo o1, ConsumeInfo o2) -> (o1.getTime().after(o2.getTime()) ? 
						-1 : (o1.getTime().equals(o2.getTime()) ? 0 : 1) ));
		for (int i = 0; i < list.size(); i++) 
			list.get(i).setOrdered(i+1);
		list.forEach((ConsumeInfo con) -> System.out.println(con) );
	}

	public static void main(String[] args) throws ParseException {
		MergeSortPractice msp = new MergeSortPractice();
		List<ConsumeInfo> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyy/MM/dd HH:mm:ss");
		list.add(msp.new ConsumeInfo(1, 93.6, sdf.parse("2010/05/04 12:34:23")));
		list.add(msp.new ConsumeInfo(2, 92.6, sdf.parse("2010/06/04 12:35:23")));
		list.add(msp.new ConsumeInfo(3, 95.6, sdf.parse("2010/04/04 12:34:21")));
		msp.sortByPrice(list);
		System.out.println("-------------------------------------------------------");
		msp.sortByDate(list);
	}

}

