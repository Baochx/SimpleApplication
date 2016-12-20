import java.io.*;

public class LineCounter {
	public static String filename = "C:\\Users\\chongxue\\Desktop\\BSLines";
	static int count;
	static File file;
	static FileReader fr;
	static BufferedReader br;
	static {
		file = new File(filename);
	}

	public static void main(String[] args) {
		count(file);
		System.out.println(count);
	}

	public static void count(File file) {
		File[] fs = file.listFiles();
		for (File f : fs) {
			if (f.isDirectory()) count(f);
			System.out.println(f.getName());
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				String line = null;
				while ((line = br.readLine()) != null)
				count++;
				fr.close();
				br.close();
			} catch (Exception e) {

			}
		}
	}

}