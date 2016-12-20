import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Stack;

//ʹ������ջʵ��һ������
public class MyQueue {

    private Stack<Integer> stack_in = new Stack<>();//���ջ
    private Stack<Integer> stack_out = new Stack<>();//����ջ
    
    //���
    public int enque(int data) {
        return stack_in.push(data);
    }

    public int deque() throws Exception {
        if (stack_out.empty()) {
            //��stack_in������ݷŵ�stack_out,stack_in��������ջ��Ԫ����stack_out�е�ջ��λ��
            while (!stack_in.empty()) {
                stack_out.push(stack_in.pop());
            }
        }
        if (stack_out.empty()) {
            throw new Exception("����Ϊ��");
        }
        return stack_out.pop();
    }
    
    public int size() {
        return stack_in.size() + stack_out.size();
    }
   
    public static void main(String[] args) throws Exception {

        MyQueue queue = new MyQueue();

        System.out.println("enque: " + queue.enque(1));
        System.out.println("enque: " + queue.enque(2));
        System.out.println("enque: " + queue.enque(3));
        System.out.println("size: " + queue.size());
        System.out.println("deque: " + queue.deque());
        System.out.println("size: " + queue.size());
        System.out.println("enque: " + queue.enque(4));
        System.out.println("size: " + queue.size());

        for (int i = 0; i < 3; i++)
            System.out.println("deque: " + queue.deque());
        System.out.println("size: " + queue.size()); 

    }
}