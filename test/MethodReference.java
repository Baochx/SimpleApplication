import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MethodReference {

    public static void main(String[] args) {
        test2();   
    }

    //to use Predicate's methods:negate(), or(),and()
    public static <T> Predicate<T> as(Predicate<T> predicate) {
        return predicate;
    }

    public static void test2() {
        long result = Stream.of("A", "", "B").filter(String::isEmpty).count(); //�������þͲ�����������
        Predicate<String> pre1 = as( str -> str.length() > 1 );
        long result1 = Stream.of("AB", "", "B").filter( as(String::isEmpty).negate().and(pre1) ).count(); //�������ǿ� && ���ȴ���1 
        long result2 = Stream.of("AB", "", "B").filter( as(String::isEmpty).negate().and("AB"::equals) ).count();
        System.out.println("result: " + result);
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
    }

    public static void test1() {
        // ��������::���ù��캯��
        PersonFactory factory = new PersonFactory(Person :: new); //create a Person and pass it to Supplier
        List<Person> personList = new ArrayList<>();
        Person p1 = factory.getPerson();
        p1.setName("Bobe");
        personList.add(p1);
        Person p2 = factory.getPerson();
        p2.setName("Aames");
        personList.add(p2);
        Person p3 = factory.getPerson();
        p3.setName("Caul");
        personList.add(p3);
        Person[] persons1 = personList.toArray(new Person[0]);

        //factory.getPerson()ÿ�εõ��Ķ���һ����
        for (Person p : personList) 
            System.out.println("---: " + p);

        System.out.print("����ǰ: ");
        printArray(persons1);
        // ��������::���þ�̬����
        Arrays.sort(persons1, MethodReference :: myCompare);
        System.out.print("�����: ");
        printArray(persons1);
        System.out.println();

        Person[] persons2 = personList.toArray(new Person[0]);
        System.out.print("����ǰ: ");
        printArray(persons2);
        // ��������::���ض������ʵ������
        Arrays.sort(persons2, p1 :: compare);
        System.out.print("�����: ");
        printArray(persons2);
        System.out.println();

        Person[] persons3 = personList.toArray(new Person[0]);
        System.out.print("����ǰ: ");
        printArray(persons3);
        // ��������::�����ض����͵���������ʵ������
        Arrays.sort(persons3, Person :: compareTo);
        System.out.print("�����: ");
        printArray(persons3);
    }

    public static void printArray(Person[] persons) {
        for (Person p : persons) {
            System.out.print(p.name + "  ");
        }
        System.out.println();
    }

    public static int myCompare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }

    static class Person {

        private String name;

        public Person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int compare(Person p1, Person p2) {
            return p2.getName().compareTo(p1.getName());
        }

        public int compareTo(Person p) {
            return this.getName().compareTo(p.getName());
        }

    }

    static class PersonFactory {

        private Supplier<Person> supplier;

        public PersonFactory(Supplier<Person> supplier) {
            this.supplier = supplier;
        }

        public Person getPerson() {
            return supplier.get();
        }

    }

}
