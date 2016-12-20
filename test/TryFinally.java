import java.util.*;

public class TryFinally {

  static int a = 0;
  int b = 0;
  
  static int f() {
    try {
      return a += 10;
    } finally {
      System.out.println("f() finally ��");
      a += 10;
    }
  }
  
  int ff(int b) {
    try {
      return b += 10;
    } finally {
      System.out.println("ff() finally ��");
      b += 10;
    }
  }
  
  int getB() {
    return b;
  }
  
  //finally ��try������catch�е�return���ִ�е���δ����֮��ִ�� �����return����򸲸�ǰ��
  //return ���ص���Զ��һ��ֵ ����һ�����õĿ�����finally���޷��ı��Ѿ�ȷ�����صĿ���ֵ�����ǿ��Ըı俽��ָ��Ķ������������
  public static void main(String...strings) {
    System.out.println("f() a :" + f());
    System.out.println("static a :" + a);
    TryFinally t = new TryFinally();
    System.out.println("f() a :" + t.ff(1));
    System.out.println("static a :" + t.getB());
    System.out.println("---------------------------");
    System.out.println("test1():  " + test1());
    System.out.println("---------------------------");
    System.out.println("test3(): " + test3());
    System.out.println("---------------------------");
    Map<String, String> map = getMap();
    Set<String> it = map.keySet();
    for (String key : it)
      System.out.println("key: " + key + " value: " + map.get(key));
  }
  
  //finally�е�return��串��try�е�
  public static String test1() {
        try {
            System.out.println("try block");
            return test2();
      } finally {
          System.out.println("test1() finally block");
          return "from finally";
       }
  }

  public static String test2() {
       System.out.println("from test2");
       return "test2() return";
   }
  
  //�����쳣try�е�return���Ͳ���������
  public static int test3() {
      int b = 20;
      try {
          System.out.println("try block");
          b = b / 0;
          return b += 80;
      } catch (Exception e) {
          b += 15;
          System.out.println("catch block");
      } finally {
          System.out.println("finally block");
          if (b > 25) {
              System.out.println("b > 25, b = " + b);
          }
          b += 50;
      }
      return b;
  }
  
  //return ���洫����ֵ һ�����õĿ���
  public static Map<String, String> getMap() {
      Map<String, String> map = new HashMap<>();
      Map<String, String> newMap = new HashMap<>();
      newMap.put("new", "new");
      map.put("KEY", "INIT");
       
      try {
          map.put("KEY", "TRY");
          return map;
      }
      catch (Exception e) {
          map.put("KEY", "CATCH");
      }
      finally {
          map.put("KEY", "FINALLY");
          map = newMap;
      }
      return map;
  }
  
}
