//����person��û����дclone����,HeightAndWeight������Ҫʵ��Cloneable�ӿ�,�ڽ���Person��¡ʱ��������Ϊһ�����屻���ƣ�
class HeightAndWeight implements Cloneable {

	private int height;
	private int weight;
	
	HeightAndWeight(int height, int weight) {
		this.height = height;
		this.weight = weight;
	}
	
	public void setHeigh(int height) {
		this.height = height;
	}
	
	public int getheight() {
		return this.height;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeigh() {
		return this.weight;
	}

}

public class CloneTest implements Cloneable {
	
	private int age;
	private String name;
	private HeightAndWeight heightAndWeight;
	
	CloneTest() {}
	
	CloneTest(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setHeightAndWeight(HeightAndWeight HeightAndWeight) {
		this.heightAndWeight = HeightAndWeight;
	}
	
	public HeightAndWeight getHeightAndWeight() {
		return this.heightAndWeight;
	}
	
	//��Object��clone()������protected���͵�
	//��ʵ������clone()�����Ѿ�����дΪһ�����Ʒ�����������clone()�����ĸ�Ч�ԣ�ֱ�Ӵ��ڴ渴�����ݶ������ڶ�����newһ������
	@Override
	public Object clone() {
		CloneTest ct = new CloneTest();
		ct.setAge(this.age);
		ct.setName(this.name);
		HeightAndWeight heightAndWeight = new HeightAndWeight(this.heightAndWeight.getheight(), this.heightAndWeight.getWeigh());
		//HeightAndWeight heightAndWeight = (HeightAndWeight)(this.heightAndWeight.clone()); //�е����⣿
		ct.setHeightAndWeight(heightAndWeight);
		return ct;
	}
	
	public String toString() {
		return this.getAge() + "," + this.getName() + "," + this.getHeightAndWeight().getheight() + "," + this.getHeightAndWeight().getWeigh();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {

		CloneTest person1 = new CloneTest(20, "XiaoPeng");
		person1.setHeightAndWeight(new HeightAndWeight(170, 50));
		
		CloneTest clone = (CloneTest)person1.clone(); //���û����дObject��clone��������ֻ��ǳ��������д֮����������;
		
		person1.setAge(21); //������������
		person1.setName("XiaoQiang"); //������������
		person1.getHeightAndWeight().setHeigh(165); //�����������
		person1.getHeightAndWeight().setWeight(45); //������������
		
		System.out.println(clone);
		clone.getHeightAndWeight().setHeigh(171); //�ı�clone����߲�Ӱ��person1��,��Ϊ�ı�Ķ�����ͬһ��
		System.out.println(person1);
	}
}
