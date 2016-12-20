import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GradeQuerySystem {
	
	class Student {
		String name;
		int math;
		int lang;
		int count;
		int m;
		int l;
		int c;
		
		Student () {}

		Student(String name, int math, int lang, int count) {
			this.name = name;
			this.math = math;
			this.lang = lang;
			this.count = count;
		}

		public void setC(int c) {
			this.c = c;
		}

		public int getC() {
			return this.c;
		}

		public void setM(int m) {
			this.m = m;
		} 

		public int getM() {
			return this.m;
		}

		public void setL(int l) {
			this.l = l;
		}

		public int getL() {
			return this.l;
		}

		public String getName() {
			return this.name;
		}

		public int getCount() {
			return this.count;
		}

		public int getMath() {
			return this.math;
		}

		public int getLang() {
			return this.lang;
		}

		public String toString() {
			return this.name + " " + this.math + " " + this.lang + " " + this.count
					+ " " + this.m + " " + this.l + " " + this.c;
		}

	}
	
	public void sort(List<Student> list) {
		//size-学生人数
		int size = list.size();
		//stu-辅助排序数组，因为不能在原列表里进行排序
		Student[] stu = new Student[size];
		for (int i = 0; i < size; i++)
			stu[i] = list.get(i);
		
		//排列总成绩，用简单的 bubble sort,从大到小的顺序
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (stu[j].getCount() < stu[j + 1].getCount()) {
					Student temp = stu[j];
					stu[j] = stu[j + 1];
					stu[j + 1] = temp;
				}
			}
		}
		//设置排名
		stu[0].setC(1);
		for (int i = 1; i < size; i++) {
			//成绩相等，排名相同，但后面的不会连续，比如4个学生前三个学生成绩相等：1 1 1 4
			if (stu[i].getCount() == stu[i - 1].getCount()) 
				stu[i].setC(stu[i - 1].getC());
			else stu[i].setC(i + 1);
		}

		System.out.println("after sort count: ");
		printArr(stu);
		
		//排列数学成绩
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (stu[j].getMath() < stu[j + 1].getMath()) {
					Student temp = stu[j];
					stu[j] = stu[j + 1];
					stu[j + 1] = temp;
				}
			}
		}
		stu[0].setM(1);
		for (int i = 1; i < size; i++) {
			if (stu[i].getMath() == stu[i - 1].getMath()) 
				stu[i].setM(stu[i - 1].getM());
			else stu[i].setM(i + 1);
		}

		System.out.println("after sort math: ");
		printArr(stu);
		
		//排列语文
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				if (stu[j].getLang() < stu[j + 1].getLang()) {
					Student temp = stu[j];
					stu[j] = stu[j + 1];
					stu[j + 1] = temp;
				}
			}
		}
		stu[0].setL(1);
		for (int i = 1; i < size; i++) {
			if (stu[i].getLang() == stu[i - 1].getLang()) 
				stu[i].setL(stu[i - 1].getL());
			else stu[i].setL(i + 1);
		}

		System.out.println("after sort lang: ");
		printArr(stu);

		//将已经改变信息的学生数组转换为学生列表
		list.clear();
		for (int i = 0; i < size; i++)
			list.add(stu[i]);

	}

	public static void printArr(Student[] stus) {
		for (int i = 0; i < stus.length; i++)
			System.out.println(stus[i]);
	}

	public static void print(List<Student> list) {
		list.forEach((Student stu) -> System.out.println(stu));
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<Student> list = new ArrayList<>();
		GradeQuerySystem gqs = new GradeQuerySystem();
		while (scan.hasNextLine()) {
			//获取输入
			String line = scan.nextLine();
			//查询输入: LST GRADE:NAME=XIAOMING;
			if (line.split(":")[0].equals("query")) {
				String sName = line.split(":")[1].split(";")[0].split("=")[1];
				gqs.sort(list);
				System.out.println("after query list: ");
				print(list);
				Iterator<Student> it = list.iterator();
				while (it.hasNext()) {
					Student stu = it.next();
					if (sName.equals(stu.getName())) {
						System.out.println("query result: " + stu);
						break;
					}
				}
			//成绩输入:input:name=xiaoqiang,math=99,lang=100;
			} else {
					String[] s1 = line.split(":"); //先判断是什么命令s1[0]是命令, s1[1]是数据
					String[] s2 = s1[1].split(","); //s2:0-name=xiaoqiang 1-math=99 2-lang=100;
					String name = s2[0].split("=")[1];
					int math = 0;
					int lang = 0;
					if (s2[1].split("=")[0].equals("math")) {
						math = Integer.parseInt(s2[1].split("=")[1]);
						lang = Integer.parseInt(s2[2].split("=")[1].split(";")[0]);
					} else if (s2[1].split("=")[0].equals("lang")) {
						math = Integer.parseInt(s2[2].split("=")[1].split(";")[0]);
						lang = Integer.parseInt(s2[1].split("=")[1]);
					}
					list.add(gqs.new Student(name, math, lang, math + lang));
					System.out.println("input: ");
					print(list);
			}
		}
		scan.close();
	}
}
