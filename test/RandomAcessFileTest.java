import java.io.IOException;
import java.io.RandomAccessFile;

public class MyRandomAcessFile {
	
	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("C:\\Users\\chongxue\\Desktop\\test1.txt", "rw");  
		
		file.write("Hello World".getBytes());  
		file.seek(0);// ���ļ�ָ��λ�����õ��ļ���ʼ��  
		  
		 // ���´�file�ļ��ж����ݣ�Ҫע���ļ�ָ���λ��  
		System.out.println((char)file.read());  
		  
		file.seek(0);  //ָ��ص��ļ���ʼλ��
		int len = (int)file.length();//ȡ���ļ����ȣ��ֽ�����  
		byte[] b = new byte[len];  
		file.readFully(b);

		file.write(b); 
		file.write(b);

		file.seek(0);
		System.out.println((char)file.read());  
		System.out.println((char)file.read());

		file.seek(0);
		file.skipBytes(1); //����1���ֽ�
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
