import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MyImageEncryption {

	//����һ��Ŀ¼��(��dat��׺�;����ܳ�)������ͼƬ
	public static boolean encryption(String filepath) {
		//���Ŀ¼�Ƿ����
		File f = new File(filepath);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("Ŀ¼�����ڣ�");
			return false;
		}
			
		String[] fs = f.list();

		for (String file : fs) {

			//�������Ŀ¼
			UUID u = UUID.randomUUID();
			String output = u + ".txt";
			File fout = new File(filepath + "/" + output);
			if (!fout.exists()) {
				try {
					fout.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (file.split("\\u002e")[1].equals("dat"))
			//|| (file.split("\\u002e")[0].length() == 34 && file.split("\\u002e")[0].charAt(8) == '-') ) 
				continue; //�Ѿ����ܹ�(dat��ʽ�ͽ��ܹ���)�Ĳ��ü���
			
			File fin = new File(filepath + "/" + file);
			if (!fin.exists()) {
				System.out.println(fin + "�����ڣ�");
				continue;
			}

			encry(fin, fout);
		}
		return true;
	}

	//�����ļ�����
	public static void encry(File fin, File fout){
		try {
			FileInputStream fis = new FileInputStream(fin);
			FileOutputStream fos = new FileOutputStream(fout);
			int i;
			while ((i = fis.read()) != -1) {
				fos.write(i + 2);
			}
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//����һ��Ŀ¼�µ�����dat��׺ͼƬ
	public static boolean decryption(String filepath) {
		//���Ŀ¼�Ƿ����
		File f = new File(filepath);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("Ŀ¼�����ڣ�");
			return false;
		}
		
		String[] fs = f.list();

		for (String file : fs) {

			UUID u = UUID.randomUUID();
			String output = u + ".gif"; //ͳһ����ΪJPG��ʽ
			File fout = new File(filepath + "/" + output);
			if (!fout.exists()) {
				try {
					fout.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (!file.split("\\u002e")[1].equals("txt")) continue; //ֻ�о������ܵ���Ҫ����
			
			File fin = new File(filepath + "/" + file);
			if (!fin.exists()) {
				System.out.println(fin + " �����ڣ�");
				continue;
			}

			try {
				FileInputStream fis = new FileInputStream(fin);
				FileOutputStream fos = new FileOutputStream(fout);
				int i;
				while ((i = fis.read()) != -1) {
					fos.write(i - 2);
				}
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	//ɾ�� <filepath> Ŀ¼�����з�.txt��׺��ʽ���ļ�
	public static boolean delete(String filepath) {
		//���·���Ƿ���ڣ��Ƿ���Ŀ¼
		File f = new File(filepath);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("Ŀ¼����");
			return false;
		}

		String[] fs = f.list();
		for (String file : fs) {
			//txt��ʽ�ļ���ͼƬ������
			if (file.split("\\u002e")[1].equals("txt")) continue;

			File fin = new File(filepath + "/" + file);
			//���Ҫɾ�����ļ��Ƿ���ڣ����ۻ���ֲ����ڵ����(������δ�������ɾ��ʱ�ļ��Ѿ���ͨ������;������ɾ����)
			if (!fin.exists()) {
				System.out.println(fin + "�����ڣ�");
				continue;
			}

			if ( fin.delete() ) System.out.printf("delete \"%s\" success!\n", fin);
		}

		return true;		
	}

	public static void main(String[] args) throws IOException {

		String directory = "G:\\˹�Ƶ���ȿռ�\\YͼƬ��\\test"; //����Ŀ��·��
		//encryption(directory); 
		//decryption(directory);
		delete(directory);
	}
}
