import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Manager {
	private static final String URL = "jdbc:mysql://localhost:3306/db_blog?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection connct;
	private static Statement state;
	private static Scanner scanner = new Scanner(System.in);
	
	static {
		try {
			Class.forName(DRIVER);
			connct = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			state = connct.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删除指定用户
	public static void deleteUserAuto() throws SQLException {
		Map<Integer, String> map = showAndGetUsers();
		System.out.println("\nPlease enter the index of user you want to delete: (enter -1 back)");
		printLine();
		int choice = Integer.valueOf(scanner.nextLine());
		if (choice != -1) {
			String username = map.get(choice);
			List<String> list = new ArrayList<String>();
			ResultSet result = state.executeQuery("select * from article where username = '" + username + "'");
			while (result.next()) {
				list.add(result.getString(2));
			}
			System.out.println("Are you sure you want to delete the user " + username + " (y/n)");
			printLine();
			String str = scanner.nextLine();
			if (str.equals("y") || str.equals("Y")) {
				Iterator<String> it = list.iterator();
				if (it == null) System.out.println(username + " has no article!");
				while (it.hasNext()) {
					deleteArticleBytitle(it.next());
				}
				int change = state.executeUpdate("delete from user where username = '" + username + "'");
				System.out.println("table user changes " + change + " rows！");
				System.out.println("delete user " + username + " successes!");
				printLine();
			}
		}		
	}
	
	public static void deleteUserControl() throws SQLException {
		outer: while (true) {
			int choice = 0;
			System.out.println("\ndeleteUser Service: 1-continue  0-back");
			printLine();
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer;
				case 1: 
					deleteUserAuto(); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	//展示并得到 user Set集合
	public static Map<Integer, String> showAndGetUsers() throws SQLException {
		ResultSet result = state.executeQuery("select * from user");
		int i = 0;
		Map<Integer, String> map = new HashMap<Integer, String>();
		printLine();
		System.out.println("All users in the db_blog: ");
		while (result.next()) {
			String username = result.getString(1);
			System.out.print(++i + " : " + username + " ");
			map.put(i, username);
			if (i % 5 == 0) System.out.println();
		}
		printLine();
		return map;
	}
	
	//手动输入article title 删除文章
	public static void deleteArticleHand() throws SQLException {
		System.out.println("Please enter the title of article you want to delete: ");
		printLine();
		String title = scanner.nextLine();
		deleteArticleBytitle(title);
	}
	
	public static void deleteArticleBytitle(String title) throws SQLException {
		int aid = queryAIdByTilte(title);
		if (aid == -1) {
			System.out.println("article " + title + " doesn't exist!");
		} else {
			System.out.println("article id = " + aid);
			int change1 = state.executeUpdate("delete from dianjiliang where aid = " + aid);
			System.out.println("table dianjiliang changes " + change1 + " rows！");
			int change2 = state.executeUpdate("delete from critique where aid = " + aid);
			System.out.println("table critique changes " + change2 + " rows！");
			int change3 = state.executeUpdate("delete from article where id = " + aid);
			System.out.println("table article changes " + change3 + " rows！");
			System.out.println("delete article <<" + title + ">> successes!");
			printLine();
		}
	}
	
	//手动输入article id 删除文章
	public static void deleteArticleAuto() throws SQLException {
		ResultSet result = null;
		String title = null;
		showArticles();
		System.out.println("\nPlease enter the id of article you want to delete: (enter -1 back)");
		printLine();
		int aid = Integer.valueOf(scanner.nextLine());
		result = state.executeQuery("select * from article where id = " + aid);
		while (result.next()) title = result.getString(2);
		if (aid != -1) {
			System.out.println("\nAre you sure you want to delete the article: " + title + " (y/n)");
			printLine();
			String str = scanner.nextLine();
			if (str.equals("y") || str.equals("Y")) {
				int change1 = state.executeUpdate("delete from dianjiliang where aid = " + aid);
				System.out.println("table dianjiliang changes " + change1 + " rows！");
				int change2 = state.executeUpdate("delete from critique where aid = " + aid);
				System.out.println("table critique changes " + change2 + " rows！");
				int change3 = state.executeUpdate("delete from article where id = " + aid);
				System.out.println("table article changes " + change3 + " rows！");	
					System.out.println("delete article " + title + " successes!");
			}
		}
	}
	
	public static void deleteArticleControl() throws SQLException {
		outer: while (true) {
			int choice = 0;
			System.out.println("\ndeleteArticle Service: 1-auto  2-hand  0-back");
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer;
				case 1: 
					deleteArticleAuto(); 
					break;
				case 2: 
					deleteArticleHand(); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	//显示所有文章
	public static void showArticles() throws SQLException {
		System.out.println("\nAll articles in the db_blog:");
		ResultSet result = state.executeQuery("select * from article");
		while (result.next()) {
			System.out.print("ID: "+ result.getInt(1) + " "); 
			System.out.print("title: "+ result.getString(2) + " "); 
			System.out.print("content_part: "+ getChineseChar(result.getString(3), 20) + " ");
			System.out.print("username: "+ result.getString(4) + " "); 
			System.out.print("date: "+ result.getDate(5) + " "); 
			System.out.print("hasRead: "+ result.getInt(6) + " "); 
			System.out.println();
		}
	}
	
	//显示所有评论者 每行最多显示5个评论者
	public static void showCritiques(int aid) throws SQLException {
		ResultSet result = null;
		Set<String> set = new HashSet<String>();
		result = state.executeQuery("select * from article where id = " + aid);
		while (result.next()) {
			System.out.println("\nAll critics in the article: " + result.getString(2));
		}
		result = state.executeQuery("select * from critique");
		while (result.next()) {
			set.add(result.getString(4));
		}
		Iterator<String> it = set.iterator();
		int i = 0;
		while (it.hasNext()) { 
			System.out.print(it.next() + " ");
			if (++i % 5 == 0) 
				System.out.println();
		}
	}
	
	//修改文章发表时间
	public static void editArticleDateHand() throws SQLException {
		System.out.println("\nPlease enter the title of article you want to edit: (enter -1 back)");
		printLine();
		String title = scanner.nextLine();
		if (title != "-1") {
			int aid = queryAIdByTilte(title);
			if (aid == -1) {
				System.out.println("article " + title + " doesn't exist!");
			} else {
				System.out.println("article id = " + aid);
				System.out.println("Please enter the date: (yyyy-MM-dd HH:mm:ss)");
				printLine();
				String date = scanner.nextLine();
				int change = state.executeUpdate("update article set date = '" + date + "' where id = " + aid);
				System.out.println("table article changes " + change + " rows！");
				System.out.println("edit article'date " + title + " successes!");
			}
		}
	}
	
	public static void editArticleDateAuto() throws SQLException {
		ResultSet result = null;
		String title = null;
		showArticles();
		System.out.println("\nPlease enter the id of article you want to edit: (enter -1 back)");
		printLine();
		int aid = Integer.valueOf(scanner.nextLine());
		result = state.executeQuery("select * from article where id = " + aid);
		while (result.next()) title = result.getString(2);
		if (aid != -1) {
			System.out.println("article id = " + aid);
			System.out.println("Please enter the date: (yyyy-MM-dd HH:mm:ss)");
			printLine();
			String date = scanner.nextLine();			
			int change = state.executeUpdate("update article set date = '" + date + "' where id = " + aid);
			System.out.println("table article changes " + change + " rows！");
			System.out.println("edit article'date " + title + " successes!");
		}
		
	}
	
	//修改文章日期的控制器
	public static void editArticleDateControl() throws SQLException {
		outer: while (true) {
			int choice = 0;
			System.out.println("\nAutoOrHand Service: 1-auto 2-hand 0-back");
			printLine();
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer;
				case 1: 
					editArticleDateAuto(); 
					break;
				case 2: 
					editArticleDateHand(); 
					break;
				default: 	
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	//查看文章评论ID(根据作者)，然后删除指定ID的评论
	public static void  deleteCritiqueOfTitleHand() throws SQLException {
		System.out.println("\nPlease enter the title of article you want to edit: ");
		printLine();
		String title = scanner.nextLine();
		int aid = queryAIdByTilte(title);
		if (aid == -1) {
			System.out.println("article " + title + " doesn't exist!"); //接下来回到选择 0 1处
		} else { //在文章存在的前提下
			deleteCritiqueOfUsernameControl(aid); //输错评论者的名字也可以循环输入;
		}
	}
	
	public static void  deleteCritiqueOfTitleAuto() throws SQLException {
		showArticles();
		System.out.println("\nPlease enter the id of article you want to edit: ");
		printLine();
		int aid = Integer.valueOf(scanner.nextLine());
		deleteCritiqueOfUsernameControl(aid); 
	}
		
	//查询删除指定文章下指定用户评论控制器
	public static void AutoOrHandControl() throws SQLException {
		outer: while (true) {
			int choice = 0;
			System.out.println("\nAutoOrHand Service: 1-auto 2-hand 0-back");
			printLine();
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer; //返回上一级
				case 1: 
					deleteCritiqueOfTitleAuto(); 
					break;
				case 2: 
					deleteCritiqueOfTitleHand(); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	//查询删除指定文章下指定用户评论控制器
	public static void deleteCritiqueOfUsernameControl(int aid) throws SQLException {
		outer: while (true) {
			int choice = 0;
			System.out.println("\ndeleteCritiqueOfUsername Service: 1-continue  0-back");
			printLine();
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer; 
				case 1: 
					deleteCritiqueByAIdAndUsername(aid); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	public static void deleteCritiqueByAIdAndUsername(int aid) throws SQLException {
		showCritiques(aid);
		System.out.println("\nPlease enter the username of critique you want to delete: ");
		printLine();
		String username = scanner.nextLine();
		if(!queryUsername(username) && !username.equals("访客")) { //评论者是存在的
			System.out.println("user " + username + " doesn't exist!"); //返回0 1 选择界面
		} else {
			showCritiqueByAIdAndUsername(aid, username); //这里的aid指的是文章id
			deleteCritiqueByIdControl(); //此处id指的是评论id
		}
	}
	

	public static void deleteCritiqueByIdControl() throws SQLException {
		outer: while (true) {
			int choice = 0;
			System.out.println("\ndeleteCritiqueById Service: 1-continue  0-back");
			printLine();
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer;
				case 1: 
					deleteCritiqueById(); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	//判断用户是否存在
	public static boolean queryUsername(String username) throws SQLException {
		String uname = null;
		ResultSet result = state.executeQuery("select * from user where username = '" +  username + "'");
		if (result.next()) 
			uname = result.getString(1);
		if (uname != null)
			return true;
		return false;
	}
	
	//根据文章标题查出文章id
	public static int queryAIdByTilte(String title) throws SQLException {
		int aid = -1; //查询失败返回-1
		ResultSet result = null;
		result = state.executeQuery("select * from article where title = '" + title + "'");
		if (result.next()) 
			aid = result.getInt(1);
		return aid;
	}
	
	//显示指定文章id和评论者的所有评论;
	public static void showCritiqueByAIdAndUsername(int aid, String username) throws SQLException {
		ResultSet result = state.executeQuery("select * from critique where aid = " + aid + " and username = '" + username + "'");
		System.out.println("\nAll the ciritique with " + username + ":\n");
		while(result.next()) {
			System.out.print("Id: " + result.getInt(1) + " ");
			System.out.print("AId: " + result.getInt(2) + " ");
			System.out.print("critique_part: " + getChineseChar(result.getString(3), 20) + " ");
			System.out.println("username: " + result.getString(4));
			System.out.println();
		}
	}
	
	//得到给定字符串中的中文字符(长度为length)
	public static String getChineseChar(String str, int length) {
		String result = "";
		int i = 0;
		Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
		int len = str.length();
		while (i < len) {
			String ch = Character.toString(str.charAt(i++));
			if (pattern.matcher(ch).matches()) {
				result += ch;
			}
		}
		if (result.length() > length) 
			result = result.substring(0, length);

		return result;
	}
	
	//根据评论id删除评论
	public static void deleteCritiqueById() throws SQLException {
		System.out.println("\nPlease chose the Id of critique you want to delete: (enter -1 back)");
		printLine();
		int id = Integer.valueOf(scanner.nextLine());
		if (id != -1) {
			int change = state.executeUpdate("delete from critique where id = " + id);
			System.out.println("table critique changes " + change + " rows！");
		}
	}
	
	//根据文章标题显示文章平论并根据评论id删除评论的接口
	public static void deleteCritiqueControl() throws SQLException { 
		outer: while (true) {
			int choice = 0;
			System.out.println("\ndeleteCritique Service: 1-continue  0-back");
			printLine();
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer;
				case 1: 
					AutoOrHandControl(); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
	}
	
	//打印分隔线
	public static void printLine() {
		int i = 0;
		System.out.println();
		while (i++ < 85)
			System.out.print("-");
	     System.out.println();
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println("Welcome to use Manager: (chose the index)");
		outer: while (true) {
			System.out.println("\n1-deleteArticle  2-showArticles  3-editArticleDate  4-deleteCritiqueService  0-exit\n"
							   + "5-deleteUser     6-showUsers");
			printLine();
			int choice = 0;
			try {
				choice = Integer.valueOf(scanner.nextLine());
			} catch (java.lang.NumberFormatException e) {
				System.out.println(e.getMessage() + "illegal!");
				System.out.println("Please try again!");
				continue;
			}
			switch (choice) {
				case 0: 
					break outer;
				case 1: 
					deleteArticleControl(); 
					break;
				case 2: 
					showArticles(); 
					break;
				case 3: 
					editArticleDateControl(); 
					break;
				case 4: 
					deleteCritiqueControl(); 
					break;
				case 5: 
					deleteUserControl(); 
					break;
				case 6: 
					showAndGetUsers(); 
					break;
				default: 
					System.out.println("There is no " + choice + " function!");
			}
		}
		System.out.println("\nbye!");
	}
	
}
