import java.net.*;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {

		System.out.println("172.19.91.141".getBytes()[4]);

		InetAddress net = InetAddress.getByName("siyun"); 

		System.out.println("������: " + net.getHostName());

		System.out.println("IP��ַ��" + net.getHostAddress());

		System.out.println("tosString(): " + net); //������/IP��ַ�ַ���

	}

}

