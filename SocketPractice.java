import java.io.*;
import java.net.*;    
    
public class SocketPractice {    
   
  public static void main(String[] args) throws Exception {
      test2();          
  } 

  public static void test1() throws Exception {

      Thread[] thread = new Thread[2];

      thread[0] = new Thread(()->{
          try {
              ServerSocket server = new ServerSocket(9090);
              Socket response = server.accept();
              System.out.println("Client: " + response.getRemoteSocketAddress());
              DataInputStream input = new DataInputStream(response.getInputStream());
              byte[] buffer = new byte[24];
              while (input.read(buffer) != -1) System.out.println("from client: " + new String(buffer));
          } catch (Exception e) {
            e.printStackTrace();
          }
          
      });

      thread[1] = new Thread(()->{
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9090);
            //
            System.out.println("getReceiveBufferSize: " + client.getReceiveBufferSize() / 1024 + "KB"); 
            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            output.write("hello!server!".getBytes());
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      });

      thread[0].start();
      thread[1].start();

      Thread.sleep(30000);

  }

  public static void test2() throws Exception {

      InetSocketAddress inet1 = new InetSocketAddress(InetAddress.getLocalHost(), 9091);
      InetSocketAddress inet2 = new InetSocketAddress(InetAddress.getLocalHost(), 9092);

      Thread[] thread = new Thread[2];

      thread[0] = new Thread(() -> {
          try {
              
              DatagramSocket client1 = new DatagramSocket(inet1);
              String message = "hello, I am client1!";
              
              //���ͱ��Ĺ��캯��
              //DatagramPacket(byte[] buf, int length, SocketAddress address) //�ɼ�offset
              //DatagramPacket(byte[] buf, int length, InetAddress address, int port) //�ɼ�offset
              
              DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, inet2);
              client1.send(packet);
              client1.setReceiveBufferSize(1024); //���ý��ջ����С
              System.out.println("getReceiveBufferSize: " + client1.getReceiveBufferSize());
          } catch (Exception e) {
              e.printStackTrace();
          }
      });
      thread[1] = new Thread(() -> {
          try {
              
              DatagramSocket client2 = new DatagramSocket(inet2);
              byte[] buffer = new byte[1024];
              
              //���ձ��ĵĹ��캯��
              //DatagramPacket(byte[] buf, int length)
              //DatagramPacket(byte[] buf, int offset, int length)
              
              DatagramPacket packet = new DatagramPacket(buffer, 1024);
              client2.receive(packet);

              //String��byte���ι��캯��
              //String(byte[] bytes) 
              //String(byte[] bytes, String charsetName)
              //String(byte[] bytes, int offset, int length) 
              //String(byte[] bytes, int offset, int length, String charset) //������ڴ�
              
              //packet.getData()�ĳ�����1024byte,��ʵ�ʽ��յ���û����ô��
              String str = new String(packet.getData(), 0, packet.getLength()); 
              System.out.println("from client1: " + str);
              System.out.println("packet.getData().length: " + packet.getData().length); //1024�ǻ������ĳ���
              System.out.println("the length of the message: " + packet.getLength()); //20 ʵ�ʽ��յĳ���
              System.out.println("the InetSocketAddress of the sender: " + packet.getSocketAddress()); //remote host 9091
              System.out.println("the InetAddress of the sender: " + packet.getAddress()); //remote host 9091
          } catch (Exception e) {
              e.printStackTrace();
          }
      });

      thread[0].start();
      thread[1].start();

      Thread.sleep(30000);

  }

}    