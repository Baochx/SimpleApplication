import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class NIOTest {

	public static void main(String[] args) throws IOException {
		test3();
	}

	public static void test0() {
		String path = "C:\\Users\\chongxue\\Desktop\\test2.txt";
		File file = new File(path);
		File file1 = new File("test.txt");
		File file2 = new File("/test.txt");
		//test,��ͨ���ļ����ܷ񴴽��ļ���
		try {
			//��ֱ�Ӹ���file name����file�����ö��ⵥ������,ǰ���Ǹ�·���Ѿ�����
			RandomAccessFile ranfile = new RandomAccessFile(path, "rw"); 
			//�����·���������ļ���û�и�·��
			RandomAccessFile ranfile1 = new RandomAccessFile("test.txt", "rw"); //�ڵ�ǰ·���´��� 
			RandomAccessFile ranfile2 = new RandomAccessFile("/test.txt", "rw"); //�ڵ�ǰ·���ĸ�Ŀ¼�´���
			System.out.println("file.exists(): " + file.exists());
			System.out.println("file1.getParent(): " + file1.getParent() ); //null
			System.out.println("file2.getParent(): " + file2.getParent() ); // \
			ranfile.close();
			ranfile1.close();
			ranfile2.close();
		} catch (Exception e) {
			e.printStackTrace(); //����·��������ʱ�Ͳ������´����µ��ļ�
		}
	}
	//����NIO api�����ļ���Ч�ʻ����,ʹ�õ�apiҲ����Ӽ��,���д����������ļ��Ĵ��䣬������������������ô�죿
	//���ھ���·����ע��һ��Ҫ��֤�ļ��ĸ�·���Ǵ��ڵģ���Ȼ�ᷢ���Ҳ����ļ�·���쳣
	public static void test1() {
		String path1 = "C:\\Users\\chongxue\\Desktop\\b\\test.txt";
		String path2 = "C:\\Users\\chongxue\\Desktop\\test1.txt";
		File file1 = new File(path1);
		File file2 = new File(path2);
		System.out.println("file1's parent path: " + file1.getParent());
		System.out.println("file1.exists(): " + file1.exists());
		File parent1 = new File(file1.getParent());
		//����ļ��������Ҹ�·�������ڣ���Ӧ���ȴ�����·��
		if (!file1.exists() && !parent1.exists() ) {
			try {
				parent1.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!file1.exists()) {
			try {
				file1.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("file1's parent path: " + file2.getParent());
		System.out.println("file2.exists(): " + file2.exists());
		File parent2 = new File(file2.getParent());
		if (!file2.exists() && !parent2.exists() ) {
			try {
				parent2.mkdirs();
			} catch (Exception e) { //ע�������׳��Ĳ�����IOException
				e.printStackTrace();
			}
		}
		if (!file2.exists()) {
			try {
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//try-with-resources���Զ��ر���Դ
		try (
			RandomAccessFile ff = new RandomAccessFile(file1, "rw");
			FileChannel ffc = ff.getChannel();
			RandomAccessFile ft = new RandomAccessFile(file2, "rw");
			FileChannel tfc = ft.getChannel();
			) {
			long size = ff.length();
			System.out.println("file1.size(): " + size / 1024 + "KB");
			//ffc.transferTo(0, size, tfc);
			tfc.transferFrom(ffc, 0, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ByteBuffer��ط�������
	public static void test3() {

		ByteBuffer buffer = ByteBuffer.allocate(24);
		String path = "C:\\Users\\chongxue\\Desktop\\test1.txt";

		try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {

			FileChannel fc = raf.getChannel();
			int readBytes = fc.read(buffer); //FileChannelֻ�ܶ���ByteBuffer
			
			System.out.println("�Ѿ�����buffer: " + readBytes + " Bytes!");
			buffer.flip();
			System.out.println("buffer: " + buffer.toString());
			System.out.println("read mode: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			while (buffer.hasRemaining()) {
				//��ȡ���ĵ�ʱ��,һ������Output: ??
				System.out.print((char)buffer.get());
			}
			System.out.println();
			System.out.println("after read fully: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			buffer.clear(); //set limit = capacity, position = 0;׼��д��
			//compact()�������ǰ�δ�����ݿ�������ʼλ�ã�Ȼ���position���õ�����ĩβ
			System.out.println("after clear(): ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			//����д��
			try {
				buffer.put((byte)97);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			//buffer.flip(); //��ʱ��limit=position=0,�޷�д��
			buffer.put((byte)97);
			buffer.putChar('b');
			buffer.putChar('b'); //����ȥռ�����ֽڣ�������ʱ��ǰ�벿���ǿո�
			System.out.println("after put two characters: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			buffer.mark(); //��ס��ʱ��λ��,��Buffer�в�û��get()����
			//System.out.println("��ȡ����д������е�����: ");
			//buffer.rewind(); //position = 0; ��������ʹ��mark()+reset()���ʧЧ
			System.out.println("��ȡ��û�б����ǵ�����: ");
			while (buffer.hasRemaining()) { //hasRemaining()��ʾposition-limit֮�仹��û��
				//��ȡ���ĵ�ʱ��,һ������Output: ??
				System.out.print((char)buffer.get());
			}
			//��ʱposition����24
			System.out.println();
			buffer.reset(); //position����6
			buffer.flip(); //�л�����ģʽ��limit = position; position = 0;
			System.out.println("read mode: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			while (buffer.hasRemaining()) { //hasRemaining()��ʾposition-limit֮�仹��û��
				//��ȡ���ĵ�ʱ��,һ������Output: ??
				System.out.print((char)buffer.get());
			}
			System.out.println();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	public static void test4() {

	}

}
