import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ListAndArray {

	private static char[] MAX_ARRAY_SIZE;

	public static void main(String...args) {
		//int[] a = new int[5]{1,2,3}; //Cannot define dimension expressions when an array initializer is provided
		String[] a = new String[]{"1", "2", "3"};

		//static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(a, 1, a, 2, 1); //源数组，起始位置，目标数组，起始位置，要复制的长度
		System.out.println("a[2]: " + a[2]); //2 a[3] = a[2]

		System.out.println("Arrays.asList(a)'s size: " + Arrays.asList(a).size()); //3, 如果放入的是int数组，为1，数组元素要是引用
		
		System.out.println("new ArrayList<String>(Arrays.asList(a)): " + new ArrayList<String>(Arrays.asList(a)).size()); 

		//初始化大小是10;是它内部的数组初始化大小为10,但是访问list的时候，需要先检查list中的元素个数
		ArrayList<String> list = new ArrayList<>(); 
		list.add("2");
		list.add("1");
		list.add("3");

		//list.add("123"); //不声明容器类型也能存入不同的值，所以泛型可以防止编译时存入不同的数据类型;
		//编译器会发出警告。同时这样就不能进行排序了，因为容器里面有不同的类型。

		System.out.println("list.get(0): " + list.get(0)); //1,index还是从0开始的
		list.sort(null); //可以传入自定义的比较器，默认为自然顺序，从小到大。
		System.out.println("list.get(0): " + list.get(0));

		//ArraList继承了AbstractCollection里的toString()方法，会按特殊样式输出。
		System.out.println("list: " + list); //list索引输出整个集合内容
		System.out.println("list.toString(): " + list.toString()); 
		System.out.println("list.subList(0, 2): " + list.subList(0, 2));
		System.out.println("list.subList(0, 2).toString(): " + list.subList(0, 2).toString());

		System.out.println("list.subList(0, 2).toArray(): " + list.subList(0, 2).toArray()); //输出数组地址
		System.out.println("list.subList(0, 2).toArray().toString(): " + list.subList(0, 2).toArray().toString());
		
		//System.out.println(list.get(-1)); // java.lang.ArrayIndexOutOfBoundsException

		System.out.println("list.size(): " + list.size()); //得到的是实际元素的个数
		
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

		list.addAll(Arrays.asList(a)); //参数为List<String> list

		//Object[]	toArray()
		//<T> T[]	toArray(T[] a)
		System.out.println("list.toArray()[3]: " + list.toArray()[3]);
		//Object[] strs = list.toArray(); //list -> array way1
		//String[] strs = (String[])(list.toArray()); //[Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
		String[] strs = list.toArray(new String[0]); //list -> array way2
		System.out.println("strs[3] " + strs[3]); 
		System.out.println("遍历list: --------------------------------------");
		Iterator it = list.iterator();
		while (it.hasNext()) System.out.println(it.next());

	}
}
