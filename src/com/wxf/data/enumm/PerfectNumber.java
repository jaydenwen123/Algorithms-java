package com.wxf.data.enumm;

public class PerfectNumber {

	/**
	 * 打印出从n1到n2之间的全部完全数
	 * 
	 * @param n1
	 * @param n2
	 */
	public static void printPerfectNumber(int n1, int n2) {
		// 思路如下：
		// 1.首先，需要从n1 到n2逐个进行遍历
		// 2.然后找出每个数其全部的因数
		// 3.然后将其所有的因数相加，在和该数进行比较，如果相等进行输出
		if (n1 < 0 || n2 < 0 || n1 > n2)
			return;
		for (int i = n1; i <= n2; i++) {
			int sum = 0;
			// 获取某个数的全部因子的思路如下：
			// 1.首先某个数m的全部因子全部在1-m/2之间
			// 2.从1到m/2之间遍历，如果能被m整除，则进行相加否则跳过找下一个
			for (int j = 1; j <= i / 2; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum == i)
				System.out.print(i + "\n");
		}

	}

	/**
	 * 计算某个数的全部因数
	 * 
	 * @param n
	 */
	public static void getFactor(int n) {
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0)
				System.out.print(i + "\t");
		}
	}

	public static void main(String[] args) {

		printPerfectNumber(2, 1000);
		getFactor(28);

	}

}
