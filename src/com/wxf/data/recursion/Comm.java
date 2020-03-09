package com.wxf.data.recursion;

/**
 * 排列组合问题
 * 
 * @author Administrator
 * 
 */
public class Comm {

	public Comm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 数学中求组合问题的算法实现
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int comm(int n, int k) {
		if (k == 0 || k == n)
			return 1;
		else
			// 在n个人中抽取k个人
			// 进行如下的划分：
			// 首先分为第一个人是否在k个人中，如果在则只需要在剩下的n-1个人中，抽取k-1个人
			// 否则则在n-1个人中抽取k个人即可
			return comm(n - 1, k - 1) + comm(n - 1, k);
	}

	public static void main(String[] args) {

		System.out.println(comm(5, 2));
		System.out.println(comm(5, 3));
	}

}
