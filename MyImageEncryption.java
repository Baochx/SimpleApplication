import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MyImageEncryption {

	//加密一个目录下(除dat后缀和经解密出)的所有图片
	public static boolean encryption(String filepath) {
		//检查目录是否存在
		File f = new File(filepath);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("目录不存在！");
			return false;
		}
			
		String[] fs = f.list();

		for (String file : fs) {

			//创建输出目录
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
				continue; //已经加密过(dat格式和解密过的)的不用加密
			
			File fin = new File(filepath + "/" + file);
			if (!fin.exists()) {
				System.out.println(fin + "不存在！");
				continue;
			}

			encry(fin, fout);
		}
		return true;
	}

	//复制文件方法
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

	//解密一个目录下的所有dat后缀图片
	public static boolean decryption(String filepath) {
		//检查目录是否存在
		File f = new File(filepath);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("目录不存在！");
			return false;
		}
		
		String[] fs = f.list();

		for (String file : fs) {

			UUID u = UUID.randomUUID();
			String output = u + ".gif"; //统一解密为JPG格式
			File fout = new File(filepath + "/" + output);
			if (!fout.exists()) {
				try {
					fout.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (!file.split("\\u002e")[1].equals("txt")) continue; //只有经过加密的需要解密
			
			File fin = new File(filepath + "/" + file);
			if (!fin.exists()) {
				System.out.println(fin + " 不存在！");
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

	//删除 <filepath> 目录下所有非.txt后缀格式的文件
	public static boolean delete(String filepath) {
		//检查路径是否存在，是否是目录
		File f = new File(filepath);
		if (!f.exists() && !f.isDirectory()) {
			System.out.println("目录错误！");
			return false;
		}

		String[] fs = f.list();
		for (String file : fs) {
			//txt格式的加密图片则跳过
			if (file.split("\\u002e")[1].equals("txt")) continue;

			File fin = new File(filepath + "/" + file);
			//检查要删除的文件是否存在，理论会出现不存在的情况(代码尚未对其进行删除时文件已经被通过其他途径快速删除了)
			if (!fin.exists()) {
				System.out.println(fin + "不存在！");
				continue;
			}

			if ( fin.delete() ) System.out.printf("delete \"%s\" success!\n", fin);
		}

		return true;		
	}

	public static void main(String[] args) throws IOException {

		String directory = "G:\\斯云的异度空间\\Y图片库\\test"; //输入目标路径
		//encryption(directory); 
		//decryption(directory);
		delete(directory);
	}
}
