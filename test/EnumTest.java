import java.util.*;

enum Color {
	Red,Yellow,Green,Blue,Pink,Brown,Purple;
}

enum Week {
	//private String str; //放在这个位置会报错
	//如果没有定制构造函数，最后一个枚举值后面不用分号
    SUN("周日", 1), MON("周一", 2), TUES("周二", 3), WEDNES("周三", 4), THURS("周四", 5), FRI("周五", 6), SATUR("周六", 7);
    
    private String str; //访问权限可以自由设置
    private int index;
    private Week(String str, int index) {
    	this.str = str;
    	this.index = index;
        System.out.println("Week() Constructor");
    }
    @Override
    public String toString() {
    	return index + ":" + str; 
    }
}

//enum 本质上是一个继承了Enum类的final类
//另外注意：switch是值比较，enum变量用于不同计算机时注意是否重写了equals方法，保证值比较的正确性
public class EnumTest {
	public static void main(String[] args) {
		System.out.println("Week.MON:----------");
		System.out.println("Week.MON " + Week.MON);
		System.out.println("Week.valueOf() " + Week.valueOf("MON"));
		System.out.println("Color.valueOf() " + Color.valueOf("Red")); //Red
		System.out.println("Color.Purple.ordinal() " + Color.Purple.ordinal());
		System.out.println("Color.THURS.ordinal() " + Week.THURS.ordinal());
		//利用工具类EnumSet进行遍历
		System.out.println("EnumSet.range():----------");
		for (Week day : EnumSet.range(Week.MON, Week.THURS))
			System.out.println(day);
		//of()可以自定义子集
		System.out.println("EnumSet.of():----------");
		for (Week day : EnumSet.of(Week.MON, Week.THURS))
			System.out.println(day);
		//EnumMap 映射两个枚举
		System.out.println("EnumMap get():----------");
		EnumMap<Week, Color> map = new EnumMap<>(Week.class);
		for (int i = 0; i < 7; i++) 
			map.put(Week.values()[i], Color.values()[i]); // values方法在Enum中找不到，应该是JVM“内生”的
		System.out.println(map.get(Week.SUN));
	}
}