import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class LambdaTest {

	public static void print(String str) {
		System.out.println("---" + str);
	}

	//����ʹ��
	// Stream<T> filter(Predicate<? super T> predicate), �������뷵��һ������
	// Stream<T> peek(Consumer<? super T> action) //��������
	// <R> Stream<R> map(Function<? super T,? extends R> mapper) //����T����R
	public void test1() {
		Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String :: toUpperCase) //��������ʱ����ʹ�÷�static����
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());
	}

	public static void main(String[] args) {

		new LambdaTest().test1();

		//�൱�ڴ���������Ȼ������ת��Ϊ����(�ӿ�)
		Runnable run = () -> System.out.println("Lambda1!");
		new Thread(run).start();
		
		//��Ϊ�������������ʻ��ǣ�����������Ȼ������ת��Ϊ����(�ӿ�)
		new Thread(() -> System.out.println("Lambda2!")).start();
		
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		//list�е�forEach((Consumer<? super T> action)),since 1.8 from Iterable<T>
		features.forEach(n -> System.out.print(n + "    ")); //�����n����ָ�����ͣ�JVM�����ǰ������Ƶ�
		System.out.println();
		//��������(������������)��JVM�﷨��
		features.forEach(System.out :: println); //System.out -> PrintStream
		features.forEach(Lambda :: print); //print��һ���ǵ��Ǿ�̬����
		
		List<String>  person = new ArrayList<>();
		 person.add( "David");
		 person.add( "Aam");
		 person.add( "Ben");
		 //������Ϲ���Ҫ���Ԫ�ء�
		 //����ǩ������������ ����������
		 //stream():Stream<String> List<String>
		 //filter(Predicate<String> predicate):Stream<String> Stream<String>
		 //forEach(Consumer<String> consumer):void Stream<String>
		 //void	forEachOrdered(Consumer<? super T> action)
		 person.stream().filter( (n)->n.length() > 2 ).forEachOrdered( n -> System.out.println(n + "+++"));
		 
		 //�����Ҫ������Ϲ���������List
		 List<String> li = person.stream().filter( (n) -> n.length() > 2 ).collect(Collectors.toList());
		 System.out.println("collet: " + li);
		 person.stream().forEach((x) -> {
			 printperson(() -> x + "---supplier"); //calls supplier's get() and println()
		 });
	     
	     //��ӡconsumer�������Ϣ
		 Consumer<String> consumer = (n) -> System.out.println(n);
		 printperson(consumer);
	        
	     //create Test(), and calls f(), and create Super()
		 Super s = new Super(Test :: new);
		 
		 List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		 //map����������Function,�����ǶԴ���Ĳ��������޸�
		 costBeforeTax.stream().map( cost -> cost + .12 * cost ).forEach( System.out::println );
	}
	
	 private static void printperson(Consumer<String> name) {
	        System.out.println(name + "---consumer");
	 }
	
	//Supplier���ô����κβ������᷵��һ��ֵ T
	static void printperson(Supplier<String> arg) {
		System.out.println(arg.get());
	}

}

class Test {

	Test(){
		System.out.println("Test()");
	}

	void f() {
		System.out.println("hello!");
	}

}

class Super {

	Super(Supplier<Test> test) {
		test.get().f();
		System.out.println("Super(Test test)");
	}

}
