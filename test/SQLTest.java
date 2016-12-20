import java.io.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class SQLTest {

	static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_blog?useSSL=false","root","123456");
	}

	private static void MyTest() {
		System.out.println("hello");
	}


	public static void main(String[] args) throws Exception {
        System.out.println("use PreparedStatement-------------------------------------------------------");
		PreparedStatement stmt = getConnection().prepareStatement("select * from user");
		//stmt.setString(1,"Chongxue");
		ResultSet result = stmt.executeQuery();
		System.out.println(result.getMetaData().getColumnCount()); //ResultSetMetaData
		while (result.next()) {
			String username = result.getString(1);
			String password = result.getString(2);
			String nickname = result.getString(3);
			String question = result.getString(4);
			String answer = result.getString(5);
			System.out.println(username + " " + password + " " + nickname + " " + question + " " + answer);
		}
		
        System.out.println("use Statement-------------------------------------------------------");
        Statement stmt1 = getConnection().createStatement();
        ResultSet result1 = stmt1.executeQuery("select * from user");
        while (result1.next()) {
            String username = result1.getString(1);
            String password = result1.getString(2);
            String nickname = result1.getString(3);
            String question = result1.getString(4);
            String answer = result1.getString(5);
            System.out.println(username + " " + password + " " + nickname + " " + question + " " + answer);
        }
     }

}
