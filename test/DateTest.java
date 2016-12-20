import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) throws ParseException {
		String dateStr = "2016/9/27 13:39:54";
		String dateStr1 = "2016/9/28 1:39:54";
		SimpleDateFormat sdf = new SimpleDateFormat("y/M/d hh:m:s");
		Date date = sdf.parse(dateStr);
		Date date1 = sdf.parse(dateStr1);
		System.out.println("格式化当前时间：" + sdf.format(new Date()));
		System.out.println("2016/9/27 13:39:54 > 2016/9/28 1:39:54 " + date.after(date1));
	}
	
}
