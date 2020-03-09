package com.wxf.data.withdraw;

import java.util.Arrays;

public class SolveLoad {

	public SolveLoad() {
		// TODO Auto-generated constructor stub
	}

	// ������������
	public static int maxW = 0;
	// ������С�Ľ��Ԫ�صĸ���
	public static int minV = 0;

	public static int n = 5;
	// �����
	public static int[] result = new int[n];

	/**
	 * ���װ������
	 * 
	 * @param n
	 * @param W
	 * @param w
	 * @param tw
	 * @param tc
	 * @param op
	 */
	public static void solveLoad(int n, int W, int i, int w[], int tw, int tc,
			int[] op) {

		if (i == n) {

			if (tw <= W || (tw == maxW && tc < minV)) {
				// display(op, n, w);
				// System.out.println(tw);
				maxW = tw;
				minV = tc;
				System.arraycopy(op, 0, result, 0, op.length);

			}
		} else {
			// ��ʾѡȡ�ü�װ��
			if (tw + w[i] <= W) {
				op[i] = 1;
				solveLoad(n, W, i + 1, w, tw + w[i], tc++, op);
			}
			// ��ʾ��ѡȡ�ü�װ��
			op[i] = 0;
			if (tc <= 2) {
				solveLoad(n, W, i + 1, w, tw, tc, op);
			}
		}
	}

	/**
	 * չ�ֽ��
	 * 
	 * @param op
	 * @param k
	 */
	public static void display(int[] op, int k, int[] w) {
		System.out.println(Arrays.toString(op));
		for (int i = 0; i < k; i++) {
			if (op[i] == 1) {
				System.out.print("(" + (i + 1) + "," + w[i] + ")\t");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int W = 10, tw = 0;
		int[] w = { 5, 2, 6, 4, 3 };
		int tc = 0;
		int[] op = new int[n];
		solveLoad(n, W, 0, w, tw, tc, op);
		System.out.println("���Ľ���ĸ���Ϊ��");
		System.out.println(minV + "," + maxW);
		System.out.println("��Ϊ:");
		display(result, n, w);
	}

}
