import java.util.BitSet;

public class BitSetTest {
	
	
	public static void main(String[] args) {
		test1();
	}

	//valueOf()方法测试
	public static void test1() {
		//byte[]作为参数
		byte[] bytes = {0,1,2}; // 8位
		BitSet bits1 = BitSet.valueOf(bytes);
		System.out.print("bits1: ");
	    showBits(bits1); //小端存储

	    //long[]作为参数
	    long[] longs = {0,0,1}; // 64位
	    BitSet bits2 = BitSet.valueOf(longs);
		System.out.print("bits2: ");
	    showBits(bits2); //小端存储

	    BitSet bits3 = new BitSet();
	    for (int i = 0; i < 64; i++) 
	    	bits3.set(i); //设置i处为true,转换为的long为-1

	    //set(int bitIndex, boolean value) 修改bitIndex处的值为value
	    //set(int fromIndex, int toIndex) 默认为true
	    bits3.set(63, false); //转换为的long为Long.MAX_VALUE

	    //toLongArray()
	    long[] longArr = bits3.toLongArray();
	    System.out.println("longArr's length: " + longArr.length);
	    //依然是把bits当小端存储来计算的
	    for (int i = 0; i < longArr.length; i++) 
	    	System.out.print(longArr[i] + " ");
	    System.out.println();
	}

	//andNot(), xor(), and(), or()四种方法的测试
	public static void test2() {
		//两种构造方法
		BitSet bits1 = new BitSet(67); //67为自定义的初始值，默认是64，扩容增量也是64
	    BitSet bits2 = new BitSet();
	    BitSet bits3 = new BitSet();
	      
		// set some bits
	     for(int i = 0; i < 16; i++) {
	        if ((i % 2) == 0) bits1.set(i);
	        if ((i % 5) != 0) bits2.set(i);
	        if ((i % 3) == 0) bits3.set(i);
	     }

	     //size()和length()方法的区别：前者是可用大小，后者是实际大小
	     System.out.println("bits1 size(): " + bits1.size()); //可以用的位数：128， 已经扩容
	     System.out.println("bits2 size(): " + bits2.size()); //可以用的位数：64 默认初始值
	   	 System.out.println("length(): " + bits1.length()); //"logical size" 是实际已经用到的位数的一个子集，它是最高为true的位的索引+1

	   	 System.out.print("bits1: ");
	     showBits(bits1);
	     System.out.print("bits2: ");
	     showBits(bits2);
	    
	     // AND bits
	     bits1.and(bits2); // bits1的值已经被改变
	     System.out.println("After bits1 AND bits2: ");
	     System.out.print("bits1: ");
	     showBits(bits1);

	     // OR bits
	     bits1.or(bits2);
	     System.out.println("After bits1 OR bits2: ");
	     System.out.print("bits1: ");
	     showBits(bits1);

	     // andNot bits bits3中为true的位在bits1中清除
	     bits1.andNot(bits3);
	     System.out.println("After bits1 andNot bits2: ");
	     System.out.print("bits3: ");
	     showBits(bits3);
	     System.out.print("bits1: ");
	     showBits(bits1);

	      // XOR bits
	     bits1.xor(bits2);
	     System.out.println("After bits1 XOR bits2: ");
	     System.out.print("bits1: ");
	     showBits(bits1);
	}

	public static void showBits(BitSet bt) {
		for (int i = 0; i < bt.length(); i++) {
			if (i != 0 && (i % 4 == 0)) {
				if (i % 8 == 0) System.out.print("  ");
				else System.out.print(",");
			}
	     	System.out.printf("%d", tf(bt.get(i)) );
	     }
	     System.out.println();
	}

	public static int tf(boolean b) {
		if (b) return 1;
		else return 0;
	}
}
