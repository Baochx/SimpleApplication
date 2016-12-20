import java.util.*;
import java.text.*;

public class LocaleTest {

	public static void main(String[] args) {
		test3();
	}

	//��ѯ�ض����ҵ�Locale������ǲ�ס����������Ҫ��ѯ�������ҵĻ�
	public static void print() {
		Locale[] locales = Locale.getAvailableLocales();
		System.out.printf("language:       Country:        Variant:       Name:\n");
		for (Locale locale : locales) {
			if (locale.getDisplayName().contains("�Ĵ�����")) //ѡ���Դ�ӡ
			System.out.printf("%-15s %-15s %-15s %s\n", locale.getLanguage(), locale.getCountry(), 
				locale.getDisplayVariant(), locale.getDisplayName());
		}
	}

	public static void test1() {
		Locale locale = Locale.getDefault();
		Formatter f = new Formatter(System.out); 
		f.format(locale, "%s %s %s %s\n", locale.getLanguage(), locale.getCountry(), 
			locale.getDisplayCountry(), locale.toLanguageTag());
		//zh CN �й� zh-CN --getDisplayName()��getDisplayCountry()�����ֵһ��

		Locale japanese = new Locale("ja", "JP", "JP");
    	f.format(japanese, "%s %s %s %s\n", japanese.getLanguage(), japanese.getCountry(), 
			japanese.getDisplayCountry(), japanese.toLanguageTag());
	}

	//reset default locale
	public static void test2() {
		System.out.println("1: " + Locale.getDefault());
		Locale.setDefault(new Locale("jp", "JP", "JP")); //##_##_$%%, ������ٲ�����ʾ����
		System.out.println("2: " + Locale.getDefault());
		System.out.println("3: " + new Date()); //�ƺ��޸�default��ʱ�����ʾû��ʲôӰ��
	}

	//ʱ���ʽ������
	public static void test3() {
		Locale.setDefault(new Locale("en", "US"));
		Locale locale = Locale.getDefault();
		Calendar cal = Calendar.getInstance(locale);
    	DateFormat df = DateFormat.getDateTimeInstance(
        			DateFormat.FULL, DateFormat.FULL, locale);
   		String date1 = df.format(cal.getTime()); //������Date, getTime()���صĲ�����Date
		System.out.println("FULL STYLE: " + date1);
		DateFormat df1 = DateFormat.getInstance();
		String date2 = df1.format(cal.getTime());
		System.out.println("SHORT STYLE: " + date2);
	}
}
