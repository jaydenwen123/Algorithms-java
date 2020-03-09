package com.wxf.data.recursion;

/**
 * �ݹ����쳲���������
 * 
 * @author Administrator
 * 
 */
public class Fib {

	public Fib() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ͨ���ݹ�ķ�ʽ���쳲���������
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
	 * ͨ��ѭ���ṹ����Ʒ�������Ʋ���������
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
