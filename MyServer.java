import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;    

public class MyServer {    

    public static void main(String[] args) {            
        try {    
            MyServer server = new MyServer();    
            server.setUpServer(9090);    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }    

    public void setUpServer(int port) throws IOException {    
       try (ServerSocket server = new ServerSocket(port)) {   
           while(true) {  
               Socket client = server.accept();    
               System.out.println("�пͻ��˽������ӣ�IP: " + client.getRemoteSocketAddress());    
               processMesage(client);    
           }
       }
    }    

    private void processMesage(Socket client) throws IOException {    
        InputStream is = client.getInputStream();   
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //����˽������   
        while(true) {
            String msg;
            while ((msg = br.readLine()) != null)    
              System.out.println("������������:" + msg);      
        } 
        is.close();
        br.close();  
    }    
}    