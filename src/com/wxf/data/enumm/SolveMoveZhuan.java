package com.wxf.data.enumm;

public class SolveMoveZhuan {

	public SolveMoveZhuan() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 解决搬砖问题
	 * 
	 * 36个人，搬36快转，其中男的每次可以搬砖4块，女的可以搬砖3块，两个小孩可以搬1块
	 * 
	 * 要求一次搬完，进行分人数
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
