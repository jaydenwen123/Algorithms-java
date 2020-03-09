package com.wxf.data.dynaschema;

public class SolveNumberDivide {

	public SolveNumberDivide() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 解决整数拆分问题
	 * 
	 * @param n
	 * @param k
	 */
	public static int numberDivide(int n, int k) {
		if (n == 1 || k == 1)
			return 1;

		else if (n < k)
			return numberDivide(n, n);
		else if (n == k)
			return numberDivide(n, k - 1) + 1;
		else {
			return numberDivide(n, k - 1) + numberDivide(n - k, k);
		}
	}

	// public static int[][] split(int n, int k) {
	// int[][] data = new int[10][10];
	// for (int i = 1; i <= n; i++) {
	// for (int j = 1; j <= k; j++) {
	// if (i == 1 || j == 1) {
	// data[i][j] = 1;
	// } else if (i < j) {
	// data[i][j] = data[i][i];
	// } else if (i == j) {
	// data[i][j] = data[i][j- 1] + 1;
	// } else if (i > j) {
	// data[i][j] = data[i][j - 1] + data[i - j][j];
	// }
	// }
	// }
	// return data;
	// }

	public static void display(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println(numberDivide(5, 5));
		// int[][] data = split(5, 5);
		// display(data);
	}
}
