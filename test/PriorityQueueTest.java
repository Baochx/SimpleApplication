import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


//T[] toArray(T[]) 是在集合Collection中定义的，所以也适合优先队列
//size(), clear(), remove(Obj), peek(), offer() 
public class PriorityQueueTest {
    
    public static void main(String[] args) {
        //优先队列自然排序测试
        Queue<Integer> queue = new PriorityQueue<>(7);

        Random rand = new Random();
        for(int i = 0; i < 7; i++){
            queue.add( new Integer( rand.nextInt(100) ) );
        }
        
        System.out.println("输出优先队列中的元素(默认升序):");
        for(int i = 0; i < 7; i++)
            System.out.println(queue.poll());
        
        //如果将优先队列用于一些自定义的数据，则需要重写compare()方法，不然无法排序
        Queue<Person> personQueue = new PriorityQueue<>(7, idComparator); //指定初始容量为7,自定义比较器idComparator
        addDataToQueue(personQueue);
       
        System.out.println("遍历输出:");
        Iterator<Person> itr = personQueue.iterator();
        while (itr.hasNext()) System.out.println(itr.next());

        System.out.println("有序输出(poll):");
        while (!personQueue.isEmpty())
            System.out.println(personQueue.poll());
    }

    //匿名Comparator实现，也可用lambda表达式实现
    public static Comparator<Person> idComparator = new Comparator<Person>(){
        @Override
        public int compare(Person p1, Person p2) {
            return p2.getId() - p1.getId(); //按从大到小的顺序
        } 
    };

    //往队列中增加测试数据
    private static void addDataToQueue(Queue<Person> personQueue) {
        Random rand = new Random();
        for(int i = 0; i < 7; i++){
            int id = rand.nextInt(100);
            personQueue.add(new Person(id, "P" + id));
        }
        // personQueue.add(null); offer方法也是一样，会抛出NullPointerException
    }
 }

class Person {

    private int id;
    private String name;

    public Person(int i, String n){
        this.id=i;
        this.name=n;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "ID:" + id  + "-Name:" + name;
    }

}