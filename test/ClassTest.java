
class Test {
    //����static final�����ᴥ����ļ���
    public static final int a = 999;
    static {
        System.out.println("static block!");
    }
}
public class ClassTest {

    public static void main(String[] args) {
        new ClassTest().test2();
    }

    //Java���������static final������C++����static������������const����
    public static final void test1() {
        System.out.println("static final!");
    }
    //Java����ֵ���ݣ�Byte b���ݽ�ȥ��bֻ��һ�ݿ������ѣ�������������ô����е㲻ͬ��Byte�ǲ��ɱ��࣬���ܼ�Ӹı�������
    public void add(Byte b) {
        b = ++b; //�����Զ������
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