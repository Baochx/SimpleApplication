package chongxue.datastructuresAndalgorithms;

public class Hanoi{
	public static int count;
	
	public static void hanoi(int n, char A, char B, char C) {
		if (n == 1) {
			System.out.println(A + "¡ª¡ª>" + C);
			count++;
			return; 
		}
		hanoi(n - 1, A, C, B);
		hanoi(1, A, B, C);
		hanoi(n - 1, B, A, C);
	}
	
	public static void main(String...strings) {
		hanoi(2,'A', 'B', 'C');
		System.out.println(count);
	}
}