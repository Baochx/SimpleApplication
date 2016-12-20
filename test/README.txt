01-DateTest.java
有关Date类特性的研究


02-ExtendsConstructorTest.java
1.类继承中构造函数如何调用父类的构造函数


03-OverrideTest.java
如何才能成功地覆写父类的方法？(最好加上@Override注解)
必须遵守：
1.方法签名(方法名+参数列表)一定要完全一样，就算是具有继承关系也不能覆盖。
2.抛出的异常只能减少不能增加
可选：
1.重载的返回类型可以是父类方法返回类型的子类(要具有明确的继承关系，而不是能向上转型就行，如long, int是不可以的)
2.可以减少抛出的异常(从种类和范围上减少)，不能增加


04-OverloadTest.java
如何才能成功地重载父类的方法
final方法无法被覆写但是能够被重载


05-EnumTest.java
Java1.5 enum EnumSet以及EnumMap的探究
including:ordinal() range() of() 自定制的构造函数和方法


06-StringTest.java
有关String本身特性的一些探究
1.== 和 equals()的区别
2.Java1.7之后，intern()方法直接返回对象引用(前提:这个字符串是第一次出现)


07-FinalStatic.java
调用一个类的类变量会引发类的加载，而如果是调用静态常量则不会引发类的加载。


08-InnerClass.java
内部类之一匿名内部类的使用。


09-InetAddressTest.java
InetAddress类的使用


10-UUIDTest.java
UUID生成一串独一无二的字符串，用于重命名、区分不同的文件。


11-HashSetTest.java
new objs in set have to override the "equals()" and "hashCode()" to ensure not repeating.


12-SQLTest.java
PreparedStatement和Statement的区别

13-ClassTest.java
1.调用static final并不会触发类的加载
2.Java里面可以有static final方法但C++里面static方法不能再用const修饰
3.Java中是值传递，传递进去的Byte b虽然是一份引用拷贝，但由于Byte是包装类，不可变，所以不能间接改变其内容

14-ClassInitial.java
Java中类初始化和对象初始化的规则：
1.先加载类再创建实例。
2.先初始化类再初始化实例。
3.都是先父类再子类。
4.始化类：类变量，static块; 初始化实例：实例变量，普通初始化块，构造函数初始化语句


15-CloneTest.java
注意一个深拷贝和浅拷贝的问题：
1.成员变量中有引用的话，常规方法克隆得到的对象引用的还是原来的对象，如需不一样，则需要重写。
2.嵌套使用clone方法时要小心


16-DeepClone.java
嵌套使用clone方法实现深克隆


17-EqualsAndHashCode.java
1.Set集合不能存入重复的对象，安全基础：首先是用hashCode方法比较，再用基于值比较的equals方法，通过这两步比较，不同才允许加入


18-BitTest.java
一些实用的位移运算技巧
1.获取最大整数的三种方法
2.判断正负
3.判断奇数偶数
4.负数绝对值


19-ConcurrentHashMap.java
1.ConcurrentHashMap的5种遍历方法，elements()和values()只能得到值，其它均能得到键值对。
2.ConcurrentHashMap中的一些hash方法研究


20-InternTest.java
1.Java7之后的intern()方法，如果str是第一次出现，则返回的是本对象地址，以后引用该str也是这个地址


21-StringReflect.java
通过反射修改不可变类String对象的内容


22-CharacterEnDeTest.java
1.ASCII对照表
2.不同编码下的汉字编码
4.JVM默认编码(Charset.defaultCharset())，文件默认编码("file.encoding")，文件名默认编码("sun.jnu.encoding")
3.内存中的编码，String的getBytes(编码),new String(bytes, 解码)
5.字符流和字节流之间的转换，编码和解码：InputStreamWriter和InputStreamReader


23-ListAndArray.java
list和Array之间的转换：
1.Arrays.asList(引用数组); 如果传入的是基本数据类型数组，则整个数组被看作一个 “一维单元素” 的引用数组
2.list.toArray() 返回的是Object[]; T[] list.toArray(T[])


23-StringFormat.java
Java中的数据格式化输出
1.System.out.format()
2.Formatter's format
3.String.format()
4.NumberFormat, DecimalFormat


24-NewInterface.java
Java8接口的所有特性：
1.default方法
2.加载时机
3.成员(方法和变量)类型


25-PriorityQueueTest.java
优先队列的用法
1.新对象要自定义比较器


26-ReflectFullTest.java
1.通过反射创建新的对象
2.修改Field
3.调用方法
4.得到父类，父接口

27-LambdaTest.java
1.常用的函数式接口:Consumer, Function, Supplier, Predicate
2.流式操作，stream(), map(), peek(), filter, forEach(),forEachOrdered()


28-MethodReference.java
关于方法引用的一些实践


29-LocaleTest.java
1.Locale的三种构造方法
2.获取所有的Locales

30-InterfaceAndAbstractClass.java
1. 接口和抽象类中也是可以有main方法的，可以被JVM调用
2. static，private方法，子类和父类或者子类和接口可以同名，JVM不会误认为是重载
3. final方法则不能同名，否则会被JVM认为是企图覆盖final方法


31-BitSetTest.java
1.BitSet的基本运算：and(), or(), xor(), andNot()
2.static method: valueOf(byte[] bytes); valueOf(long[] longs);
3.toLongArray(); toByteArray(); 遵循小端存储


32-HashMapTest.java
1.key可以是null,value也可以是null


33-TryFinally.java
1.finally里面的语句总会执行，可在其中改变其它块返回的内容


34-StaticNew.java
AAAAA 对有关类初始化很重要的一些点的测试


35-RegularTest.java
AAAAA 有关正则表达式的相关知识


36-NIOTest.java
1.FileChannel:
transferTo(),transferFrom()
2.ByteBuffer
get(),put(),putChar(),clear(),compact(),mark(),reset(),rewind()


37-RandomAcessFileTest.java


38-


