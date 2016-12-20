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
		//test,仅通过文件名能否创建文件？
		try {
			//能直接根据file name创建file，不用额外单独创建,前提是父路径已经存在
			RandomAccessFile ranfile = new RandomAccessFile(path, "rw"); 
			//用相对路径创建的文件，没有父路径
			RandomAccessFile ranfile1 = new RandomAccessFile("test.txt", "rw"); //在当前路径下创建 
			RandomAccessFile ranfile2 = new RandomAccessFile("/test.txt", "rw"); //在当前路径的根目录下创建
			System.out.println("file.exists(): " + file.exists());
			System.out.println("file1.getParent(): " + file1.getParent() ); //null
			System.out.println("file2.getParent(): " + file2.getParent() ); // \
			ranfile.close();
			ranfile1.close();
			ranfile2.close();
		} catch (Exception e) {
			e.printStackTrace(); //当父路径不存在时就不能重新创建新的文件
		}
	}
	//利用NIO api传输文件的效率会更高,使用的api也会更加简洁,几行代码就能完成文件的传输，但是遇到编码问题怎么办？
	//对于绝对路径，注意一定要保证文件的父路径是存在的，不然会发生找不到文件路径异常
	public static void test1() {
		String path1 = "C:\\Users\\chongxue\\Desktop\\b\\test.txt";
		String path2 = "C:\\Users\\chongxue\\Desktop\\test1.txt";
		File file1 = new File(path1);
		File file2 = new File(path2);
		System.out.println("file1's parent path: " + file1.getParent());
		System.out.println("file1.exists(): " + file1.exists());
		File parent1 = new File(file1.getParent());
		//如果文件不存在且父路径不存在，则应该先创建父路径
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
			} catch (Exception e) { //注意这里抛出的并不是IOException
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
		//try-with-resources会自动关闭资源
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
	//ByteBuffer相关方法测试
	public static void test3() {

		ByteBuffer buffer = ByteBuffer.allocate(24);
		String path = "C:\\Users\\chongxue\\Desktop\\test1.txt";

		try (RandomAccessFile raf = new RandomAccessFile(path, "rw")) {

			FileChannel fc = raf.getChannel();
			int readBytes = fc.read(buffer); //FileChannel只能读入ByteBuffer
			
			System.out.println("已经读入buffer: " + readBytes + " Bytes!");
			buffer.flip();
			System.out.println("buffer: " + buffer.toString());
			System.out.println("read mode: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			while (buffer.hasRemaining()) {
				//读取中文的时候,一个汉字Output: ??
				System.out.print((char)buffer.get());
			}
			System.out.println();
			System.out.println("after read fully: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			buffer.clear(); //set limit = capacity, position = 0;准备写入
			//compact()方法则是把未读数据拷贝到起始位置，然后把position设置到数据末尾
			System.out.println("after clear(): ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			//可以写入
			try {
				buffer.put((byte)97);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			//buffer.flip(); //此时，limit=position=0,无法写入
			buffer.put((byte)97);
			buffer.putChar('b');
			buffer.putChar('b'); //读进去占两个字节，读出的时候前半部分是空格
			System.out.println("after put two characters: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			buffer.mark(); //记住此时的位置,但Buffer中并没有get()方法
			//System.out.println("读取重新写入后所有的数据: ");
			//buffer.rewind(); //position = 0; 但这样会使得mark()+reset()组合失效
			System.out.println("读取还没有被覆盖的数据: ");
			while (buffer.hasRemaining()) { //hasRemaining()表示position-limit之间还有没有
				//读取中文的时候,一个汉字Output: ??
				System.out.print((char)buffer.get());
			}
			//此时position等于24
			System.out.println();
			buffer.reset(); //position等于6
			buffer.flip(); //切换到读模式，limit = position; position = 0;
			System.out.println("read mode: ");
			System.out.println("capacity: " + buffer.capacity() + " position: " + buffer.position() + 
				" limit: " + buffer.limit());
			while (buffer.hasRemaining()) { //hasRemaining()表示position-limit之间还有没有
				//读取中文的时候,一个汉字Output: ??
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
