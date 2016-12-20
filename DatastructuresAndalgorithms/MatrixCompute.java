//����������˵�ǰ�� ǰһ�����������ں�һ�������� ����������Ȼ���ϵ� ���ö�����ж�
//��˺�ľ����С�������ɵ�һ��������� �����ɵڶ���������� ���ڵڶ����������ǵ�һ�������� ���Ծ����С��r x r
public class MatrixCompute {
	/**
	*initialValue ���������ʼֵ rows columns
	*/
	public static int[][] compute(int initialValue, int rows, int  columns) {
		int in = initialValue, r = rows, c = columns;
		int[][] init = new int[r][c];
		int[][] trans = new int[c][r];
		int[][] multi = new int[r][r];

		//��ʼ����������
		for (int i = 0; i < r; i ++) {
			for (int j = 0; j < c; j++) {
				init[i][j] = in++;
			}
		}

		//����ת��
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

