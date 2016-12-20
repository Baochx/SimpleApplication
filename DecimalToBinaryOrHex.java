public class DecimalToBinaryOrHex {
	
	public static void main(String...args) {
		System.out.println(decimalToHex(65535));
		System.out.println(decimalToBinary(8.8));
	}

	//10����ת��Ϊ16����
	public static String decimalToHex(int decimal) {
		int temp = decimal;
		String result = "";
		while (temp / 16 != 0) {
			result = (temp % 16 > 10 ? get(temp % 16) :  temp % 16) + result;
			temp /= 16; //������Ϊ�µı�����
		}
		return (temp > 10 ? get(temp) :  temp) + result; //����Ϊ0ʱ�������������һ��������
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

	//ʮ����ת������ ����С������
	public static String decimalToBinary(double decimal) {
		String result = "";
		String[] num = String.valueOf(decimal).split("\\u002E"); //ʵ�ĵ��ת�����u002E
		
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
			while (temp != 0 && count++ < 4) { //����Ϊ4
				result += (int)(temp * 2); //ȡ��������
				temp = temp * 2 - (int)(temp * 2); //ȡС�����ּ������˷�
			}
		}

		return result;
	}
}
