import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Stack;

//使用两个栈实现一个队列
public class MyQueue {

    private Stack<Integer> stack_in = new Stack<>();//入队栈
    private Stack<Integer> stack_out = new Stack<>();//出队栈
    
    //入队
    public int enque(int data) {
        return stack_in.push(data);
    }

    public int deque() throws Exception {
        if (stack_out.empty()) {
            //把stack_in里的数据放到stack_out,stack_in中最先入栈的元素在stack_out中的栈顶位置
            while (!stack_in.empty()) {
                stack_out.push(stack_in.pop());
            }
        }
        if (stack_out.empty()) {
            throw new Exception("队列为空");
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