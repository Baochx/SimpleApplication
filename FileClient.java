import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
 
/**
 * Client;
 */
public class FileClient{
     
    private static final String SERVER_IP = "172.18.41.169";
    private static final int SERVER_PORT = 3333;
     
    private Socket client;
    private FileInputStream fis;
    private DataOutputStream dos;
     
    public FileClient(){
        try {
            try {
                client = new Socket(SERVER_IP, SERVER_PORT);
                System.out.println("The file path:");
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                File file = new File(str);
                fis = new FileInputStream(file);
                dos = new DataOutputStream(client.getOutputStream());
                 
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
                 
                byte[] sendBytes = new byte[1024];
                int length = 0;
                while((length = fis.read(sendBytes,0, sendBytes.length)) > 0){
                    dos.write(sendBytes,0, length);
                    dos.flush();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(fis !=null)
                    fis.close();
                if(dos !=null)
                    dos.close();
                	client.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args)throws Exception {
        new FileClient();
    }
}
