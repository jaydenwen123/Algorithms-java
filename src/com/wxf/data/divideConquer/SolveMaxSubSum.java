package com.wxf.data.divideConquer;


public class SolveMaxSubSum {

	public SolveMaxSubSum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过递归的方式解决求最大连续子序列和问题
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int solveMaxSubSum(int[] data, int low, int high) {
		// 通过分治法求解最大连续子序列和问题思路如下：
		// 1.首先从该序列的中间的某个数找起
		// 2.分为如下三种情况，第一种是最大子序列和位于该数左端
		// 在该种情况下，通过递归求和，递归出口是当划分为单个数时，如果该数大于零则返回该数，否则返回0
		// 3.第二种情况是位于该数的右端
		// 也通过递归求和，递归出口和1情况一样
		// 4.第三种情况是最大子序列中包含该数
		// 这种情况下,需要进行如下处理，首先从左半部分求出最大和，然后再右半部分求出最大和，最后相加和1,2种情况求最大值
		int leftMaxSum = 0, rightMaxSum = 0;
		int leftBorderMaxSum = 0, rightBorderMaxSum = 0;
		int leftSum = 0, rightSum = 0;
		if (low == high) {
			if (data[low] > 0) {
				return data[low];
			} else {
				return 0;
			}

		}
		int mid = (low + high) / 2;
		leftMaxSum = solveMaxSubSum(data, low, mid);
		rightMaxSum = solveMaxSubSum(data, mid + 1, high);
		// 下面解决第三种情况,此种情况下，必须从中间的数两边开始进行求和，而不能从两边网中间求和
		for (int i = mid; i >= low; i--) {
			leftSum += data[i];
			if (leftSum > leftBorderMaxSum)
				leftBorderMaxSum = leftSum;

		}
		for (int j = mid + 1; j <= high; j++) {
			rightSum += data[j];
			if (rightSum > rightBorderMaxSum)
				rightBorderMaxSum = rightSum;
		}
		return max(leftMaxSum, rightMaxSum, leftBorderMaxSum
				+ rightBorderMaxSum);
	}

	/**
	 * 找出abc三个数中的最大值
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static int max(int a, int b, int c) {
		if (a < b)
			a = b;
		return a > c ? a : c;
	}

	public static void main(String[] args) {
		int[] a = { -2, 11, -4, 13, -5, -2 };
		int[] b = { -6, 2, 4, -7, 5, 3, 2, -1, 6, -9, 10, -2 };
		System.out.println(solveMaxSubSum(a, 0, a.length - 1));
		System.out.println(solveMaxSubSum(b, 0, b.length - 1));
	}
}
