import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTools {

	//�����޸�ĳһĿ¼�µ��ļ���ʽ������xhtml -> html(���ص�����)
	public static void rename(String filepath) throws Exception {
		String filePath = filepath;
		File f = new File(filePath);
		String[] filename = f.list();
		for (String str : filename) {
			if (str.endsWith("xhtml")) {
				String reName = str.replaceAll("x", "");
				File newFile = new File("C:\\Users\\chongxue\\Desktop\\print\\" + reName);
				if (!newFile.exists())
						newFile.createNewFile();
				//�����ʽ��utf-8��
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath + "\\" + str)), "utf-8"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
				String line = null;
				while ((line = br.readLine()) != null)
					bw.write(line);
				br.close();
				bw.close();
			}
		}
	}
	
	public static void main(String...strings) throws IOException {
		
	}
}
