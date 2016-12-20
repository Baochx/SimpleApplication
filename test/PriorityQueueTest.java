import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


//T[] toArray(T[]) ���ڼ���Collection�ж���ģ�����Ҳ�ʺ����ȶ���
//size(), clear(), remove(Obj), peek(), offer() 
public class PriorityQueueTest {
    
    public static void main(String[] args) {
        //���ȶ�����Ȼ�������
        Queue<Integer> queue = new PriorityQueue<>(7);

        Random rand = new Random();
        for(int i = 0; i < 7; i++){
            queue.add( new Integer( rand.nextInt(100) ) );
        }
        
        System.out.println("������ȶ����е�Ԫ��(Ĭ������):");
        for(int i = 0; i < 7; i++)
            System.out.println(queue.poll());
        
        //��������ȶ�������һЩ�Զ�������ݣ�����Ҫ��дcompare()��������Ȼ�޷�����
        Queue<Person> personQueue = new PriorityQueue<>(7, idComparator); //ָ����ʼ����Ϊ7,�Զ���Ƚ���idComparator
        addDataToQueue(personQueue);
       
        System.out.println("�������:");
        Iterator<Person> itr = personQueue.iterator();
        while (itr.hasNext()) System.out.println(itr.next());

        System.out.println("�������(poll):");
        while (!personQueue.isEmpty())
            System.out.println(personQueue.poll());
    }

    //����Comparatorʵ�֣�Ҳ����lambda���ʽʵ��
    public static Comparator<Person> idComparator = new Comparator<Person>(){
        @Override
        public int compare(Person p1, Person p2) {
            return p2.getId() - p1.getId(); //���Ӵ�С��˳��
        } 
    };

    //�����������Ӳ�������
    private static void addDataToQueue(Queue<Person> personQueue) {
        Random rand = new Random();
        for(int i = 0; i < 7; i++){
            int id = rand.nextInt(100);
            personQueue.add(new Person(id, "P" + id));
        }
        // personQueue.add(null); offer����Ҳ��һ�������׳�NullPointerException
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