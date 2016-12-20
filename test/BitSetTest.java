import java.util.BitSet;

public class BitSetTest {
	
	
	public static void main(String[] args) {
		test1();
	}

	//valueOf()��������
	public static void test1() {
		//byte[]��Ϊ����
		byte[] bytes = {0,1,2}; // 8λ
		BitSet bits1 = BitSet.valueOf(bytes);
		System.out.print("bits1: ");
	    showBits(bits1); //С�˴洢

	    //long[]��Ϊ����
	    long[] longs = {0,0,1}; // 64λ
	    BitSet bits2 = BitSet.valueOf(longs);
		System.out.print("bits2: ");
	    showBits(bits2); //С�˴洢

	    BitSet bits3 = new BitSet();
	    for (int i = 0; i < 64; i++) 
	    	bits3.set(i); //����i��Ϊtrue,ת��Ϊ��longΪ-1

	    //set(int bitIndex, boolean value) �޸�bitIndex����ֵΪvalue
	    //set(int fromIndex, int toIndex) Ĭ��Ϊtrue
	    bits3.set(63, false); //ת��Ϊ��longΪLong.MAX_VALUE

	    //toLongArray()
	    long[] longArr = bits3.toLongArray();
	    System.out.println("longArr's length: " + longArr.length);
	    //��Ȼ�ǰ�bits��С�˴洢�������
	    for (int i = 0; i < longArr.length; i++) 
	    	System.out.print(longArr[i] + " ");
	    System.out.println();
	}

	//andNot(), xor(), and(), or()���ַ����Ĳ���
	public static void test2() {
		//���ֹ��췽��
		BitSet bits1 = new BitSet(67); //67Ϊ�Զ���ĳ�ʼֵ��Ĭ����64����������Ҳ��64
	    BitSet bits2 = new BitSet();
	    BitSet bits3 = new BitSet();
	      
		// set some bits
	     for(int i = 0; i < 16; i++) {
	        if ((i % 2) == 0) bits1.set(i);
	        if ((i % 5) != 0) bits2.set(i);
	        if ((i % 3) == 0) bits3.set(i);
	     }

	     //size()��length()����������ǰ���ǿ��ô�С��������ʵ�ʴ�С
	     System.out.println("bits1 size(): " + bits1.size()); //�����õ�λ����128�� �Ѿ�����
	     System.out.println("bits2 size(): " + bits2.size()); //�����õ�λ����64 Ĭ�ϳ�ʼֵ
	   	 System.out.println("length(): " + bits1.length()); //"logical size" ��ʵ���Ѿ��õ���λ����һ���Ӽ����������Ϊtrue��λ������+1

	   	 System.out.print("bits1: ");
	     showBits(bits1);
	     System.out.print("bits2: ");
	     showBits(bits2);
	    
	     // AND bits
	     bits1.and(bits2); // bits1��ֵ�Ѿ����ı�
	     System.out.println("After bits1 AND bits2: ");
	     System.out.print("bits1: ");
	     showBits(bits1);

	     // OR bits
	     bits1.or(bits2);
	     System.out.println("After bits1 OR bits2: ");
	     System.out.print("bits1: ");
	     showBits(bits1);

	     // andNot bits bits3��Ϊtrue��λ��bits1�����
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
