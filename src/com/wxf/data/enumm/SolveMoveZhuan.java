package com.wxf.data.enumm;

public class SolveMoveZhuan {

	public SolveMoveZhuan() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �����ש����
	 * 
	 * 36���ˣ���36��ת�������е�ÿ�ο��԰�ש4�飬Ů�Ŀ��԰�ש3�飬����С�����԰�1��
	 * 
	 * Ҫ��һ�ΰ��꣬���з�����
	 * 
	 * @param n
	 * @param m
	 */
	public static void solveMoveZhuan(int n, int m) {
		for (int i = 1; i <= n / 4; i++) {
			for (int j = 1; j <= n / 3; j++) {
				int r = n - i * 4 - j * 3;
				if (r > 0 && r * 2 + i + j == m) {
					System.out.println(i + "," + j + "," + r * 2);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		solveMoveZhuan(36, 36);
	}

}
