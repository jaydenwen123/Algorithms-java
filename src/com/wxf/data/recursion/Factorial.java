package com.wxf.data.recursion;

/**
 * ͨ���ݹ�����׳�
 * 
 * @author Administrator
 * 
 */
public class Factorial {

	public Factorial() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��׳˵ķ���
	 * 
	 * @param n
	 *            ����Ĳ���
	 * @return ���ص���n�Ľ׳�
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
