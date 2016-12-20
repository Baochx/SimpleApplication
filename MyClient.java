import java.io.*;
import java.util.*;
import java.net.*;

public class MyClient {

	public static void main(String...args) {
		try (Socket client = new Socket(InetAddress.getLocalHost(), 9090)) {
			DataOutputStream dis = new DataOutputStream(client.getOutputStream());
			Scanner scan = new Scanner(System.in);
			while (scan.hasNextLine()) {
				String msg = scan.nextLine();
				dis.write(msg.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}