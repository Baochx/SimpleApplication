import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
/**
 * Server;
 */
public class FileServer {
 
    private static final int PORT = 3333;
    
    private ServerSocket server = null;
    private Socket client = null;
    private DataInputStream dis = null;
    private FileOutputStream fos = null;
     
    public FileServer() throws Exception {
        try {
            try {
                server = new ServerSocket(PORT);
                System.out.println("the server starting.....");
                long start = System.currentTimeMillis();
                long end = start;
                while(end - start < 180000){
                    end = System.currentTimeMillis();
                    client = server.accept();
                    dis = new DataInputStream(client.getInputStream());
                    String fileName = dis.readUTF();
                    long fileLength = dis.readLong();
                    fos = new FileOutputStream(new File("G:/test001/" + fileName));
                     
                    byte[] sendBytes = new byte[1024];
                    int transLen = 0;
                    System.out.println("----start receiving file: <" + fileName +">,the size of file: <" + fileSize(fileLength) +">----");
                    while(true){
                        int read = 0;
                        read = dis.read(sendBytes);
                        if(read == -1)
                            break;
                        transLen += read;
                        System.out.println(": " + 100 * transLen / fileLength +"%...");
                        fos.write(sendBytes,0, read);
                        fos.flush();
                    }
                    System.out.println("----receive<" + fileName + ">successfully!----");
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(dis !=null)
                    dis.close();
                if(fos !=null)
                    fos.close();
                server.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //change the size of file to K,KB,M,G...
    public String fileSize(long fileLength){
    	String sizeUnit = null;
    	long fl = fileLength;
    	int count = 0;
    	while (fl >= 1024) {
    		fl = fl / 1024;
    		count++;
    	}
    	switch(count) {
    		case 0:
    			sizeUnit = "B";
    			break;
    		case 1:
    			sizeUnit = "KB";
    			break;
    		case 2:
    			sizeUnit = "M";
    			break;
    		case 3:
    			sizeUnit = "G";
    			break;
    		case 4:
    			sizeUnit = "TB";
    			break;
    		default:
    			System.out.println("ERROR!");
    			break;
    	}
    	return fl + sizeUnit;
    }
     
    public static void main(String[] args)throws Exception {
        new FileServer();
    }
}