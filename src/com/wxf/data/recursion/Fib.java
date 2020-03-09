package com.wxf.data.recursion;

/**
 * 递归求解斐波那契数列
 * 
 * @author Administrator
 * 
 */
public class Fib {

	public Fib() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过递归的方式设计斐波那契数列
	 * 
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static int fib(int n) throws Exception {
		if (n < 0)
			throw new Exception("the arguement is errored!!!");
		else if (n == 0 || n == 1)
			return n;
		else
			return fib(n - 1) + fib(n - 2);
	}

	/**
	 * 通过循环结构的设计方法，求菲波那切数列
	 * 
	 * @param n
	 * @return
	 */
	public static int fib2(int n) {
		int num1 = 0;
		int num2 = 1;
		if (n < 0)
			throw new IllegalArgumentException("the arguement is errored!!!");
		else if (n == 0 || n == 1)
			return n;
		else {
			int result = 0;
			for (int i = 2; i <= n; i++) {
				result = num1 + num2;
				num1 = num2;
				num2 = result;
			}
			return result;
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 8; i++) {
			try {
				System.out.print(fib(i) + "\t");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
