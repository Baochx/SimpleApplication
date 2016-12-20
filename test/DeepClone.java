class He implements Cloneable {
	private int b;
	He(int b) {
		this.b = b;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return (He)super.clone();
	}
}
public class DeepClone implements Cloneable {
	private int a;
	private He h;
	DeepClone(int a, He h) {
		this.a = a;
		this.h = h;
	}
	public He getHe() {
		return h;
	}
	public int getA() {
		return a;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new DeepClone(this.a, (He)this.getHe().clone());
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		DeepClone init = new DeepClone(10, new He(1000));
		DeepClone clone = (DeepClone)init.clone();
		System.out.println(init == clone); //克隆后对象和原对象不是同一个对象
		init.getHe().setB(2000);
		System.out.println(clone.getHe().getB()); //2000 "shadow copy",1000 "deep copy"
	}
}
