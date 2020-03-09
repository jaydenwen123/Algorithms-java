package com.wxf.data.recursion;

/**
 * 通过递归来求阶乘
 * 
 * @author Administrator
 * 
 */
public class Factorial {

	public Factorial() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 求阶乘的方法
	 * 
	 * @param n
	 *            传入的参数
	 * @return 返回的是n的阶乘
	 */
	public static long factorial(int n) {
		if (n < 0)
			throw new IllegalArgumentException("the arguement is error:" + n);
		else if (n == 0)
			return 1;
		else
			return n * factorial(n - 1);
	}

	
	
	public static void main(String[] args) {
		int n = 5;
		System.out.println(factorial(n));

	}

}
