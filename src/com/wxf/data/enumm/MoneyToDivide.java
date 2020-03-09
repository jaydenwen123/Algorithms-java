package com.wxf.data.enumm;

import java.util.Date;

import javax.security.auth.kerberos.KerberosKey;

public class MoneyToDivide {

	public MoneyToDivide() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 解决钱币兑换问题
	 * 
	 * 通过三重循环来解决
	 * 
	 * 该算法的时间复杂度为O(n3)
	 * 
	 * @param n
	 */
	public static void solveMoneytoDivide(int n) {

		// 关于钱币兑换问题的解决思路如下：
		// 1.首先硬币只有1,2,5三种类型。将n分钱的纸币进行兑换
		// 2.可以通过解方程来实现求解，设1分的个数为x，2分的个数为y，5分的个数为z
		// 3.对应的方程为 x+2*y+5*z=n
		// 4.以上的x，y,z的范围是 （0<=x<=n,0<=y<=n/2,0<=z<=n/5）
		// 5.通过列举来求解
		int count = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n / 2; j++) {
				for (int k = 0; k <= n / 5; k++) {
					if (i + 2 * j + 5 * k == n) {
						System.out.println(i + "," + j + "," + k);
						count++;
					}
				}
			}
		}
		System.out.println("总共有" + count + "种结果");
	}

	/**
	 * 通过两重循环来解决问题 该算法的时间复杂度为O(n2)
	 * 
	 * @param n
	 */
	public static void solveMoneytoDivide2(int n) {
		int count = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n / 2; j++) {
				int r = n - i - 2 * j;
				if ((r > 0 && r % 5 == 0) || r == 0) {
					System.out.println(i + "," + j + "," + r / 5);
					count++;
				}
			}
		}
		System.out.println("总共有" + count + "种结果");
	}

	/**
	 * 改进方法
	 * 
	 * 通过一冲循环解决,效率最高
	 * 
	 * @param n
	 */
	public static void solveMoneytoDivide3(int n) {

		int count = 0;
		for (int i = 0; i <= n / 5; i++) {
			int r = n - i * 5;
			if (r == 0) {
				System.out.println(0 + "," + 0 + "," + i);
				count++;
			} else if (r > 0) {
				for (int j = 0; j <= r / 2; j++) {
					int m = r - 2 * j;
					if (m >= 0) {
						System.out.println(m + "," + j + "," + i);
						count++;
					}
				}
			}
		}
		System.out.println("总共有" + count + "种结果");
	}


	public static void main(String[] args) {

		Date time1 = new Date(System.nanoTime());
		solveMoneytoDivide(10);
		Date time2 = new Date(System.nanoTime());
		solveMoneytoDivide2(10);
		Date time3 = new Date(System.nanoTime());
		solveMoneytoDivide3(10);
		Date time4 = new Date(System.nanoTime());
		System.out.println("三种方法对比如下：");
		System.out
				.println((float) (time2.getTime() - time1.getTime()) / 1000000);
		System.out
				.println((float) (time3.getTime() - time2.getTime()) / 1000000);
		System.out
				.println((float) (time4.getTime() - time3.getTime()) / 1000000);
	}
}
