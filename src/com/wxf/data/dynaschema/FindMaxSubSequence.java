package com.wxf.data.dynaschema;


public class FindMaxSubSequence {

	public FindMaxSubSequence() {
		// TODO Auto-generated constructor stub
	}

	// 求解最长公共子序列问题的思路如下：
	// 1.首先根据两个序列a和b，然后求出改子序列的长度
	// 具体做法如下：
	// a。首先对两个序列进行判断，如果两个序列的最后一个元素相等，则该元素可定是公共子序列的元素，
	// 问题转化为从两个序列中的剩下的元素中求最长公共子序列的问题
	// b。如果两个子序列的最后一个元素不相等。并且公共子序列的最后一个元素等于a序列的最后一个元素，
	// 则只需要从该序列的中剩下的元素中找最长子序列
	// c。返回来，最后一个元素等于b的最后一个元素，进行类推

	// 求解问题通过递归的思路求解
	// 1.递归出口
	// 递归出口是如果i=0 或者 j=0,则长度为零
	// 2、递归体
	// 递归体是：当两个元素最后元素相等时，长度加1
	// 否则，找出最大的子序列长度即可

	// 2.其次是求其子序列，通过数组来存储

	/**
	 * 
	 * 求最大公共子序列的长度
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getMaxSubSequeLength(String a, String b, int[][] length) {
		int alen = a.length();
		int blen = b.length();
		// 对于第一种情况
		for (int i = 0; i < alen + 1; i++)
			length[i][0] = 0;
		for (int j = 0; j < blen + 1; j++)
			length[0][j] = 0;
		for (int i = 1; i <= alen; i++) {
			for (int j = 1; j <= blen; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1))
					// 处理第二种情况
					length[i][j] = length[i - 1][j - 1] + 1;
				else {
					// 处理第三种情况
					if (length[i][j - 1] >= length[i - 1][j]) {
						length[i][j] = length[i][j - 1];
					} else {
						length[i][j] = length[i - 1][j];
					}
				}
			}
		}
		return length[alen][blen];
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String getMaxSubSequence(String a, String b) {

		int i = a.length(), j = b.length();
		int[][] length = new int[i + 1][j + 1];
		int len = getMaxSubSequeLength(a, b, length);
		char[] subSeque = new char[len];
		int k = subSeque.length;
		while (k > 0) {
			if (length[i][j] == length[i][j - 1])
				j--;
			else if (length[i][j] == length[i - 1][j])
				i--;
			else {
				subSeque[--k] = a.charAt(i - 1);
				i--;
				j--;
			}
		}
		return new String(subSeque);
	}

	public static void main(String[] args) {
		String a = "abcbdb";
		String b = "accbbabdbb";
		System.out.println(getMaxSubSequence(a, b));
	}
}
