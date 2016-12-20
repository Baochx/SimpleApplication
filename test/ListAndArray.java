import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ListAndArray {

	private static char[] MAX_ARRAY_SIZE;

	public static void main(String...args) {
		//int[] a = new int[5]{1,2,3}; //Cannot define dimension expressions when an array initializer is provided
		String[] a = new String[]{"1", "2", "3"};

		//static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(a, 1, a, 2, 1); //Դ���飬��ʼλ�ã�Ŀ�����飬��ʼλ�ã�Ҫ���Ƶĳ���
		System.out.println("a[2]: " + a[2]); //2 a[3] = a[2]

		System.out.println("Arrays.asList(a)'s size: " + Arrays.asList(a).size()); //3, ����������int���飬Ϊ1������Ԫ��Ҫ������
		
		System.out.println("new ArrayList<String>(Arrays.asList(a)): " + new ArrayList<String>(Arrays.asList(a)).size()); 

		//��ʼ����С��10;�����ڲ��������ʼ����СΪ10,���Ƿ���list��ʱ����Ҫ�ȼ��list�е�Ԫ�ظ���
		ArrayList<String> list = new ArrayList<>(); 
		list.add("2");
		list.add("1");
		list.add("3");

		//list.add("123"); //��������������Ҳ�ܴ��벻ͬ��ֵ�����Է��Ϳ��Է�ֹ����ʱ���벻ͬ����������;
		//�������ᷢ�����档ͬʱ�����Ͳ��ܽ��������ˣ���Ϊ���������в�ͬ�����͡�

		System.out.println("list.get(0): " + list.get(0)); //1,index���Ǵ�0��ʼ��
		list.sort(null); //���Դ����Զ���ıȽ�����Ĭ��Ϊ��Ȼ˳�򣬴�С����
		System.out.println("list.get(0): " + list.get(0));

		//ArraList�̳���AbstractCollection���toString()�������ᰴ������ʽ�����
		System.out.println("list: " + list); //list�������������������
		System.out.println("list.toString(): " + list.toString()); 
		System.out.println("list.subList(0, 2): " + list.subList(0, 2));
		System.out.println("list.subList(0, 2).toString(): " + list.subList(0, 2).toString());

		System.out.println("list.subList(0, 2).toArray(): " + list.subList(0, 2).toArray()); //��������ַ
		System.out.println("list.subList(0, 2).toArray().toString(): " + list.subList(0, 2).toArray().toString());
		
		//System.out.println(list.get(-1)); // java.lang.ArrayIndexOutOfBoundsException

		System.out.println("list.size(): " + list.size()); //�õ�����ʵ��Ԫ�صĸ���
		
		/*
		private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    	}
		 * */
		
		/* public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    	}
		 * */

		list.addAll(Arrays.asList(a)); //����ΪList<String> list

		//Object[]	toArray()
		//<T> T[]	toArray(T[] a)
		System.out.println("list.toArray()[3]: " + list.toArray()[3]);
		//Object[] strs = list.toArray(); //list -> array way1
		//String[] strs = (String[])(list.toArray()); //[Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
		String[] strs = list.toArray(new String[0]); //list -> array way2
		System.out.println("strs[3] " + strs[3]); 
		System.out.println("����list: --------------------------------------");
		Iterator it = list.iterator();
		while (it.hasNext()) System.out.println(it.next());

	}
}
