//两个矩阵相乘的前提 前一个的列数等于后一个的行数 本题中是天然符合的 不用额外的判断
//相乘后的矩阵大小：行数由第一个矩阵决定 列数由第二个矩阵决定 由于第二个的列数是第一个的行数 所以矩阵大小：r x r
public class MatrixCompute {
	/**
	*initialValue 增量矩阵初始值 rows columns
	*/
	public static int[][] compute(int initialValue, int rows, int  columns) {
		int in = initialValue, r = rows, c = columns;
		int[][] init = new int[r][c];
		int[][] trans = new int[c][r];
		int[][] multi = new int[r][r];

		//初始化增量矩阵
		for (int i = 0; i < r; i ++) {
			for (int j = 0; j < c; j++) {
				init[i][j] = in++;
			}
		}

		//矩阵转置
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < r; j++) {
				trans[i][j] = init[j][i];
 			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < r; j++) {
				int e = 0;
				for (int k = 0; k < c; k++) {
					e += init[i][k] * trans[k][j];
				}
				multi[i][j] = e;
			}
		}
		return multi; 
	}

	public static void show(int[][] multi) {
		for (int i = 0; i < multi.length; i++) {
			for (int j = 0; j < multi[0].length; j++) {
				System.out.print(multi[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		show(compute(1, 3, 3));
	}
}

