package com.wxf.data.withdraw;

import java.util.Arrays;

public class SolveFullPerm {

	public SolveFullPerm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 解决全排列问题
	 * 
	 * @param data
	 */
	public static void solveFullPerm(Object[] data, int k, int n) {

		// 根据回溯法求解全排列问题的思路如下：
		// 采用递归的思想进行求解假设f(s,k,n)表示的是求解s[k...n-1]子序列的全排列则
		// 1.递归体为：当k<n-1时，f(s,k+1,n)是小问题，前者是大问题，对于该问题进行将
		// s[k]和s[k...n-1]中的元素交换的办法
		// 2.递归出口为：当k=n-1时，输出所有的元素产生的一种全排列
		Object temp;
		if (k == n - 1) {
			System.out.println(Arrays.toString(data));
		} else {
			for (int i = k; i < n; i++) {
				temp = data[i];
				data[i] = data[k];
				data[k] = temp;
				solveFullPerm(data, k + 1, n);
				temp = data[i];
				data[i] = data[k];
				data[k] = temp;
			}

		}
	}

	public static void main(String[] args) {
		Integer[] data = { 1, 2, 3, 5 };
		System.out.println("通过回溯法解决全排列的问题：");
		solveFullPerm(data, 0, data.length);
	}
}
