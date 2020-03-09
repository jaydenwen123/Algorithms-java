package com.wxf.data.withdraw;

public class SolveSubSum {

	public SolveSubSum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过回溯法求解子集合问题
	 * 
	 * 
	 * @param n
	 * @param i
	 * @param set
	 * @param W
	 */
	public static void solveSubSum(int n, int i, int[] set, int rw, int W,
			int sum, int[] op) {

		// 通过回溯法求解子集和问题思路如下：
		// 通过递归方法来求解
		// 1.找到递归出口
		// 递归出口为：i>n,即n个数全部搜索完，算法结束
		if (i > n) {

			if (sum == W) {
				display(set, op, n);
			}
		} else {
			// 2.找到递归体
			// 递归体：从第一个数开始搜索
			// 该数选取
			rw -= set[i];
			if (sum + set[i] <= W) {
				op[i] = 1;
				solveSubSum(n, i + 1, set, rw, W, sum + set[i], op);
			}
			if (sum + rw >= W) {
				// 该数不选取
				op[i] = 0;
				solveSubSum(n, i + 1, set, rw, W, sum, op);
			}
		}

	}

	/**
	 * 展现结果
	 * 
	 * @param w
	 * @param x
	 * @param n
	 */
	public static void display(int[] w, int[] x, int n) {
		for (int i = 1; i <= n; i++) {
			if (x[i] == 1) {
				System.out.print(w[i]+"\t");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 4, W = 31;
		int[] set = { 0, 11, 13, 24, 7 };
		int rw = 0;
		int[] op = new int[30];
		for (int j = 1; j <= n; j++) {
			rw += set[j];
		}
		solveSubSum(n, 1, set, rw, W, 0, op);
	}
}
