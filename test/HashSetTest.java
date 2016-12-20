import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Test {
	private int test;
	
	Test(int test) {
		this.test = test;
	}

	int getTest() {
		return this.test;
	}
	/**
	 * you can try note the two override functions,comparing the test changes*/
	@Override
	public boolean equals(Object o) { //if you just override the equals, the test result doesn't change;
		if (this == o) return true;	
		if (this == null | !(o instanceof Test)) return false; 
		return this.getTest() == ((Test)o).getTest();
	}
	
	@Override
	public int hashCode() {
		return 31 * test + Integer.toString(test).hashCode(); // just one of custom hash algorithms;
	}
	
}
	
public class HashSetTest {
	
	public static int hashCode(Object a[]) {
        if (a == null)
            return 0;

        int result = 1;

        for (Object element : a)
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;
    }


	public static void main(String[] args) {
		System.out.println("String Test------------------");
		Set<String> set1 = new HashSet<>();
		String str1 = new String("1");
		String str2 = new String("1"); //though str2 is just a new object, but String override the equals() and hashCode();
		System.out.println(str1.equals(str2)); // true,so don't add the repeated element;
		set1.add(str1);
		set1.add(str2); 
		Iterator<String> it1 = set1.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next());
		}
		System.out.println("Test test------------------");
		Set<Test> set = new HashSet<>();
		Test test1 = new Test(1);
		Test test2 = new Test(1);
		System.out.println(test1.equals(test2)); // true, after overriding the equals() and hashCode(); 
		set.add(test1);
		set.add(test2);
		Iterator<Test> it = set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().getTest());
		}
	}
	
}
