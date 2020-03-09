package com.wxf.data.enumm;

import java.util.Arrays;

public class SolvePowSet {

	/**
	 * 
	 * 求解幂集问题
	 * 
	 * 采用直接穷举法求解
	 * 
	 * @param a
	 * @param b
	 * @param n
	 */
	public static void pset(int[] a, int[] b, int n) {

		int pow = (int) Math.pow(2, n);
		for (int i = 0; i < pow; i++) {
			System.out.print("{");
			for (int k = 0; k < n; k++) {
				if (b[k] > 0)
					System.out.print(a[k]);
			}
			System.out.print("},");
			System.out.println(Arrays.toString(b));
			Change(b, n);
		}
		System.out.println();
	}

	/**
	 * @param b
	 * @param n
	 */
	private static void Change(int[] b, int n) {
		// TODO Auto-generated method stub
		for (int i = 0; i < n; i++) {
			if (b[i] > 0) {
				b[i] = 0;
			} else {
				b[i] = 1;
				break;
			}
		}
	}

	public static void main(String[] args) {
		int n = 3;
		int[] a = new int[3];
		int[] b = new int[3];

		for (int i = 0; i < n; i++) {
			a[i] = i + 1;
			b[i] = 0;
		}

		pset(a, b, n);
	}
}
