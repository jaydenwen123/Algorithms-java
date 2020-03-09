package com.wxf.data.recursion;

public class Gcd {

	public Gcd() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 计算最大公约数的函数
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static int gcd(int n, int m) {
		int max = 0;
		if (m == 0)
			return n;
		else if (n < m) {
			return gcd(m, n);
		} else
			return gcd(m, n % m);
	}

	public static void main(String[] args) {

		System.out.println(gcd(12, 18));
	}
}
