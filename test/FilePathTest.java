import java.io.*;
public class FilePathTest {
	public static void main(String[] args) {
		createDirectory();
	}
	//����Ŀ��·�������е�Ŀ¼(���������ڵĸ�·��)
	public static void createDirectories() {
		File f = new File("C:/Users/chongxue/Desktop/abc/a"); 
		if (!f.exists())  f.mkdirs();
		System.out.println("created: " + f.exists());
	}
	//����ָ��Ŀ¼
	public static void createDirectory() {
		//�ھ���·���´�����Ŀ¼
		File f = new File("C:/Users/chongxue/Desktop/a"); 
		if (!f.exists())  f.mkdirs();
		System.out.println("created: " + f.exists());
		System.out.println(f); //output:C:\Users\chongxue\Desktop\a, ��Java��\��Ҫת�� \\
	}
	//����ָ��Ŀ¼(��·���������)�µ��ļ�
	public static void createNewFile() {
		//�ھ���·���´���
		File f = new File("C:/Users/chongxue/Desktop/a/test.text"); 
		if (!f.exists())  try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("created: " + f.exists());
	}
	//����ָ��Ŀ¼(��·���������)�µ��ļ�
	public static void createNewFile1() {
		//����/���ڸ�Ŀ¼�´������ļ�������Ŀ¼Ҳһ��
		File f = new File("/test.text"); 
		if (!f.exists())  try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("created: " + f.exists());
	}
	//����ָ��Ŀ¼(��·���������)�µ��ļ�
	public static void createNewFile2() {
		//����/���ڵ�ǰĿ¼�´������ļ�������Ŀ¼Ҳһ��
		File f = new File("test.text"); 
		if (!f.exists())  try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("created: " + f.exists());
	}
}
