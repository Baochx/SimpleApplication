import java.util.*;

enum Color {
	Red,Yellow,Green,Blue,Pink,Brown,Purple;
}

enum Week {
	//private String str; //�������λ�ûᱨ��
	//���û�ж��ƹ��캯�������һ��ö��ֵ���治�÷ֺ�
    SUN("����", 1), MON("��һ", 2), TUES("�ܶ�", 3), WEDNES("����", 4), THURS("����", 5), FRI("����", 6), SATUR("����", 7);
    
    private String str; //����Ȩ�޿�����������
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

//enum ��������һ���̳���Enum���final��
//����ע�⣺switch��ֵ�Ƚϣ�enum�������ڲ�ͬ�����ʱע���Ƿ���д��equals��������ֵ֤�Ƚϵ���ȷ��
public class EnumTest {
	public static void main(String[] args) {
		System.out.println("Week.MON:----------");
		System.out.println("Week.MON " + Week.MON);
		System.out.println("Week.valueOf() " + Week.valueOf("MON"));
		System.out.println("Color.valueOf() " + Color.valueOf("Red")); //Red
		System.out.println("Color.Purple.ordinal() " + Color.Purple.ordinal());
		System.out.println("Color.THURS.ordinal() " + Week.THURS.ordinal());
		//���ù�����EnumSet���б���
		System.out.println("EnumSet.range():----------");
		for (Week day : EnumSet.range(Week.MON, Week.THURS))
			System.out.println(day);
		//of()�����Զ����Ӽ�
		System.out.println("EnumSet.of():----------");
		for (Week day : EnumSet.of(Week.MON, Week.THURS))
			System.out.println(day);
		//EnumMap ӳ������ö��
		System.out.println("EnumMap get():----------");
		EnumMap<Week, Color> map = new EnumMap<>(Week.class);
		for (int i = 0; i < 7; i++) 
			map.put(Week.values()[i], Color.values()[i]); // values������Enum���Ҳ�����Ӧ����JVM����������
		System.out.println(map.get(Week.SUN));
	}
}