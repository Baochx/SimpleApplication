//不管person有没有重写clone方法,HeightAndWeight都不需要实现Cloneable接口,在进行Person克隆时，它是作为一个整体被复制；
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
	
	//在Object中clone()方法是protected类型的
	//其实在这里clone()方法已经被改写为一个复制方法，背离了clone()方法的高效性：直接从内存复制数据而不是在堆上再new一个对象
	@Override
	public Object clone() {
		CloneTest ct = new CloneTest();
		ct.setAge(this.age);
		ct.setName(this.name);
		HeightAndWeight heightAndWeight = new HeightAndWeight(this.heightAndWeight.getheight(), this.heightAndWeight.getWeigh());
		//HeightAndWeight heightAndWeight = (HeightAndWeight)(this.heightAndWeight.clone()); //有点问题？
		ct.setHeightAndWeight(heightAndWeight);
		return ct;
	}
	
	public String toString() {
		return this.getAge() + "," + this.getName() + "," + this.getHeightAndWeight().getheight() + "," + this.getHeightAndWeight().getWeigh();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {

		CloneTest person1 = new CloneTest(20, "XiaoPeng");
		person1.setHeightAndWeight(new HeightAndWeight(170, 50));
		
		CloneTest clone = (CloneTest)person1.clone(); //如果没有重写Object的clone方法，这只是浅拷贝，重写之后就是深拷贝了;
		
		person1.setAge(21); //重新设置年龄
		person1.setName("XiaoQiang"); //重新设置名字
		person1.getHeightAndWeight().setHeigh(165); //重新设置身高
		person1.getHeightAndWeight().setWeight(45); //重新设置体重
		
		System.out.println(clone);
		clone.getHeightAndWeight().setHeigh(171); //改变clone的身高不影响person1的,因为改变的对象不是同一个
		System.out.println(person1);
	}
}
