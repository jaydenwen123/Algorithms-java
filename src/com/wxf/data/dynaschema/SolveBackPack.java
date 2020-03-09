package com.wxf.data.dynaschema;

import java.util.Arrays;

public class SolveBackPack {

	public SolveBackPack() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ȡ���ļ�ֵ
	 * 
	 * @param n
	 *            �����������n����Ʒ
	 * @param W
	 *            ��������ΪW
	 * @param w
	 *            ��ͬ��Ʒ������
	 * @param v
	 *            ��ͬ��Ʒ�ļ�ֵ
	 * @param value
	 *            ��̬�滮���Ĺ���
	 * @return
	 */
	public static int getMaxValue(int n, int W, int[] w, int[] v, int[][] value) {

		for (int i = 0; i <= n; i++)
			value[i][0] = 0;
		for (int j = 0; j <= W; j++)
			value[0][j] = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= W; j++) {
				if (j < w[i - 1]) {
					value[i][j] = value[i - 1][j];
				} else {
					int temp = value[i - 1][j];
					int cur = value[i - 1][j - w[i - 1]] + v[i - 1];
					if (temp < cur)
						value[i][j] = cur;
					else {
						value[i][j] = temp;
					}
				}
			}
		}
		System.out.println("the result:");
		display(value);
		return value[n][W];
	}

	/**
	 * ��ӡ��ά�����ֵ
	 * 
	 * @param value
	 */
	public static void display(int[][] value) {
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {
				System.out.print(value[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * ��ȡ��ѷ���������
	 * 
	 * @param n
	 * @param v
	 * @param w
	 * @param value
	 * @return
	 */
	public static int getMaxWeigh(int n, int W, int[] v, int[] w, int[] best,
			int[][] value) {
		int maxWeigh = 0;
		int k = 0;
		for (int i = value.length-1; i >0; i--) {
			if (value[i][W] != value[i - 1][W]) {
				best[i-1] = 1;
				W = W - w[i-1];
				maxWeigh+=w[i-1];
			}
		}
		return maxWeigh;
	}

	public static void main(String[] args) {
		int n = 5;
		int W = 10;
		int v[] = { 6, 3, 5, 4, 6 };
		int w[] = { 2, 2, 6, 5, 4 };
		int value[][] = new int[n + 1][W + 1];
		int val = getMaxValue(n, W, w, v, value);
		int best[]=new int[n];
		int maxWeigh=getMaxWeigh(n, W, v, w, best, value);
		System.out.println("��ѵ�����Ϊ��");
		System.out.println(maxWeigh);
		System.out.println("���ļ�ֵΪ��");
		System.out.println(val);
		System.out.println("��ѷ���Ϊ��");
		System.out.println(Arrays.toString(best));
		
	}

}
