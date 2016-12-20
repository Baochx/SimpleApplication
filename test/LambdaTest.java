import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class LambdaTest {

	public static void print(String str) {
		System.out.println("---" + str);
	}

	//复合使用
	// Stream<T> filter(Predicate<? super T> predicate), 根据输入返回一个布尔
	// Stream<T> peek(Consumer<? super T> action) //消费输入
	// <R> Stream<R> map(Function<? super T,? extends R> mapper) //输入T返回R
	public void test1() {
		Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String :: toUpperCase) //方法引用时可以使用非static方法
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());
	}

	public static void main(String[] args) {

		new LambdaTest().test1();

		//相当于创建匿名类然后向上转型为父类(接口)
		Runnable run = () -> System.out.println("Lambda1!");
		new Thread(run).start();
		
		//作为方法参数，本质还是：创建匿名类然后向上转型为父类(接口)
		new Thread(() -> System.out.println("Lambda2!")).start();
		
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		//list中的forEach((Consumer<? super T> action)),since 1.8 from Iterable<T>
		features.forEach(n -> System.out.print(n + "    ")); //这里的n不用指明类型，JVM会根据前面进行推导
		System.out.println();
		//方法引用(类名，方法名)，JVM语法糖
		features.forEach(System.out :: println); //System.out -> PrintStream
		features.forEach(Lambda :: print); //print不一定非得是静态方法
		
		List<String>  person = new ArrayList<>();
		 person.add( "David");
		 person.add( "Aam");
		 person.add( "Ben");
		 //输出符合过滤要求的元素。
		 //方法签名：返回类型 方法所属类
		 //stream():Stream<String> List<String>
		 //filter(Predicate<String> predicate):Stream<String> Stream<String>
		 //forEach(Consumer<String> consumer):void Stream<String>
		 //void	forEachOrdered(Consumer<? super T> action)
		 person.stream().filter( (n)->n.length() > 2 ).forEachOrdered( n -> System.out.println(n + "+++"));
		 
		 //如果需要保存符合过滤条件的List
		 List<String> li = person.stream().filter( (n) -> n.length() > 2 ).collect(Collectors.toList());
		 System.out.println("collet: " + li);
		 person.stream().forEach((x) -> {
			 printperson(() -> x + "---supplier"); //calls supplier's get() and println()
		 });
	     
	     //打印consumer对象的信息
		 Consumer<String> consumer = (n) -> System.out.println(n);
		 printperson(consumer);
	        
	     //create Test(), and calls f(), and create Super()
		 Super s = new Super(Test :: new);
		 
		 List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		 //map方法参数是Function,作用是对传入的参数进行修改
		 costBeforeTax.stream().map( cost -> cost + .12 * cost ).forEach( System.out::println );
	}
	
	 private static void printperson(Consumer<String> name) {
	        System.out.println(name + "---consumer");
	 }
	
	//Supplier不用传入任何参数但会返回一个值 T
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
