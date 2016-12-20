import java.io.*;
public class FilePathTest {
	public static void main(String[] args) {
		createDirectory();
	}
	//创建目标路径中所有的目录(包括不存在的父路径)
	public static void createDirectories() {
		File f = new File("C:/Users/chongxue/Desktop/abc/a"); 
		if (!f.exists())  f.mkdirs();
		System.out.println("created: " + f.exists());
	}
	//创建指定目录
	public static void createDirectory() {
		//在绝对路径下创建新目录
		File f = new File("C:/Users/chongxue/Desktop/a"); 
		if (!f.exists())  f.mkdirs();
		System.out.println("created: " + f.exists());
		System.out.println(f); //output:C:\Users\chongxue\Desktop\a, 在Java中\需要转义 \\
	}
	//创建指定目录(父路径必须存在)下的文件
	public static void createNewFile() {
		//在绝对路径下创建
		File f = new File("C:/Users/chongxue/Desktop/a/test.text"); 
		if (!f.exists())  try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("created: " + f.exists());
	}
	//创建指定目录(父路径必须存在)下的文件
	public static void createNewFile1() {
		//加了/是在根目录下创建新文件，创建目录也一样
		File f = new File("/test.text"); 
		if (!f.exists())  try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("created: " + f.exists());
	}
	//创建指定目录(父路径必须存在)下的文件
	public static void createNewFile2() {
		//不加/是在当前目录下创建新文件，创建目录也一样
		File f = new File("test.text"); 
		if (!f.exists())  try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("created: " + f.exists());
	}
}
