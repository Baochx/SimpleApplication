import java.util.*;

public class TryFinally {

  static int a = 0;
  int b = 0;
  
  static int f() {
    try {
      return a += 10;
    } finally {
      System.out.println("f() finally 块");
      a += 10;
    }
  }
  
  int ff(int b) {
    try {
      return b += 10;
    } finally {
      System.out.println("ff() finally 块");
      b += 10;
    }
  }
  
  int getB() {
    return b;
  }
  
  //finally 在try或者是catch中的return语句执行但尚未返回之际执行 如果有return语句则覆盖前者
  //return 返回的永远是一个值 或者一个引用的拷贝，finally中无法改变已经确定返回的拷贝值，但是可以改变拷贝指向的对象里面的内容
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
  
  //finally中的return语句覆盖try中的
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
  
  //出现异常try中的return语句就不在起作用
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
  
  //return 后面传的是值 一份引用的拷贝
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
