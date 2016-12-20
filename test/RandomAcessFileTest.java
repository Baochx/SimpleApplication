import java.io.IOException;
import java.io.RandomAccessFile;

public class MyRandomAcessFile {
	
	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("C:\\Users\\chongxue\\Desktop\\test1.txt", "rw");  
		
		file.write("Hello World".getBytes());  
		file.seek(0);// 把文件指针位置设置到文件起始处  
		  
		 // 以下从file文件中读数据，要注意文件指针的位置  
		System.out.println((char)file.read());  
		  
		file.seek(0);  //指针回到文件起始位置
		int len = (int)file.length();//取得文件长度（字节数）  
		byte[] b = new byte[len];  
		file.readFully(b);

		file.write(b); 
		file.write(b);

		file.seek(0);
		System.out.println((char)file.read());  
		System.out.println((char)file.read());

		file.seek(0);
		file.skipBytes(1); //跳过1个字节
		System.out.println(file.readLine());
		System.out.println(file.readLine());
		file.close();

		//test1.txt: Hello WorldHello WorldHello World
		//Output:
		//H
		//H
		//e
		//ello WorldHello WorldHello World
		//null
	}
}
