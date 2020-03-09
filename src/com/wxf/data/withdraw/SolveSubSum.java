package com.wxf.data.withdraw;

public class SolveSubSum {

	public SolveSubSum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ͨ�����ݷ�����Ӽ�������
	 * 
	 * 
	 * @param n
	 * @param i
	 * @param set
	 * @param W
	 */
	public static void solveSubSum(int n, int i, int[] set, int rw, int W,
			int sum, int[] op) {

		// ͨ�����ݷ�����Ӽ�������˼·���£�
		// ͨ���ݹ鷽�������
		// 1.�ҵ��ݹ����
		// �ݹ����Ϊ��i>n,��n����ȫ�������꣬�㷨����
		if (i > n) {

			if (sum == W) {
				display(set, op, n);
			}
		} else {
			// 2.�ҵ��ݹ���
			// �ݹ��壺�ӵ�һ������ʼ����
			// ����ѡȡ
			rw -= set[i];
			if (sum + set[i] <= W) {
				op[i] = 1;
				solveSubSum(n, i + 1, set, rw, W, sum + set[i], op);
			}
			if (sum + rw >= W) {
				// ������ѡȡ
				op[i] = 0;
				solveSubSum(n, i + 1, set, rw, W, sum, op);
			}
		}

	}

	/**
	 * չ�ֽ��
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
