package com.wxf.data.enumm;

public class findNumber {

	public findNumber() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 求一个四位数，首先该四位数的千位上的数字和百位上的数字未知，只知道视为和个位上的 数字为1
	 * 和2，有知道这个书减去7然后可以被7整除，将去8可以被8整除，将去9可以被9整除
	 *
	 * @return
	 */
	public static int find() {
		int num = -1;
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				int n = 1000 * i + 100 * j + 12;
				if ((n - 7) % 7 == 0 && (n - 8) % 8 == 0 && (n - 9) % 9 == 0) {
					num = n;
					break;
				}
			}
		}
		return num;
	}

	public static void main(String[] args) {
		System.out.println("求出来的该数为：");
		System.out.println(find());
	}

}
