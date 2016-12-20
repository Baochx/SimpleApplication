public class DecimalToBinaryOrHex {
	
	public static void main(String...args) {
		System.out.println(decimalToHex(65535));
		System.out.println(decimalToBinary(8.8));
	}

	//10进制转换为16进制
	public static String decimalToHex(int decimal) {
		int temp = decimal;
		String result = "";
		while (temp / 16 != 0) {
			result = (temp % 16 > 10 ? get(temp % 16) :  temp % 16) + result;
			temp /= 16; //将商作为新的被除数
		}
		return (temp > 10 ? get(temp) :  temp) + result; //当商为0时，余数就是最后一个被除数
	}

	public static String get(int i) {
		switch (i) {
			case 10: 
				return "a";
			case 11: 
				return "b";
			case 12: 
				return "c";
			case 13: 
				return "d";
			case 14: 
				return "e";
			case 15: 
				return "f";
		}
		return "";
	}

	//十进制转二进制 包括小数部分
	public static String decimalToBinary(double decimal) {
		String result = "";
		String[] num = String.valueOf(decimal).split("\\u002E"); //实心点的转义就是u002E
		
		if (num[0].length() != 0) {
			int temp = Integer.parseInt(num[0]); //or valueOf(num[0])
			while (temp / 2 != 0) {
				result = temp % 2 + result;
				temp /= 2;
			}
		result = temp + result;
		}
		
		if (num[1].length() != 0) {
			double temp = Double.valueOf("0." + num[1]);
			result += "."; 
			int count = 0;
			while (temp != 0 && count++ < 4) { //精度为4
				result += (int)(temp * 2); //取整数部分
				temp = temp * 2 - (int)(temp * 2); //取小数部分继续做乘法
			}
		}

		return result;
	}
}
