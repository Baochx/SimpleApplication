
class Test {
    //调用static final并不会触发类的加载
    public static final int a = 999;
    static {
        System.out.println("static block!");
    }
}
public class ClassTest {

    public static void main(String[] args) {
        new ClassTest().test2();
    }

    //Java里面可以有static final方法但C++里面static方法不能再用const修饰
    public static final void test1() {
        System.out.println("static final!");
    }
    //Java中是值传递，Byte b传递进去的b只是一份拷贝而已，这跟其他的引用传递有点不同，Byte是不可变类，不能间接改变其内容
    public void add(Byte b) {
        b = ++b; //可以自动拆箱的
        System.out.println("add() " + b);
    }
    public void test2() {
        Byte a = 127;
        Byte b = 127;
        add(++a); //-127
        System.out.print(a + " "); //-128
        add(b); //-128
        System.out.print(b + ""); //127
    }
   
}