package com.wxf.data.divideConquer;

public class FindMidNumber {

	/**
	 * 于寻找两个等长有序序列的中位数
	 * 
	 * 递归分治策略
	 * 
	 * @param a
	 * @param b
	 * @param low1
	 * @param low2
	 * @param high1
	 * @param high2
	 * @return
	 */
	public static int findMidNumber(int[] a, int[] b, int low1, int low2,
			int high1, int high2) {
		// 关于寻找两个等长有序序列的中位数的思路如下：
		// 1.首先判断数组a和b的元素的个数是奇数还是偶数，如果是奇数则mid=(s+t)/2;
		// 否则偶数为mid=(s+t)/2和(s+t)/2+1
		// 2.其次如果二者相等，则返回，否则如果a[mid]>b[mid]，则舍掉a的上半部分和b的下半部分，
		// 3.否则舍掉a的下半部分和b的上半部分。
		// 4.依次递归查找，知道数据元素的个数为1时，算法结束
		if (high1 == low1 && high2 == low2) {
			return a[low1] < b[low2] ? a[low1] : b[low2];
		} else {
			int mid1 = (high1 + low1) / 2, mid2 = (high2 + low2) / 2;
			if (a[mid1] == b[mid2])
				return a[mid1];
			if (a[mid1] < b[mid2]) {

				return findMidNumber(a, b, getPostPart(low1, high1), low2,
						high1, getPrePart(low2, high2));
			} else {
				return findMidNumber(a, b, low1, getPostPart(low2, high2),
						getPrePart(low1, high1), high2);
			}
		}

	}

	/**
	 * 循环结构的寻找中位数算法
	 * 
	 * @param a
	 * @param low1
	 * @param high1
	 * @param b
	 * @param low2
	 * @param high2
	 * @return
	 */
	public static int findMidNumber(int[] a, int low1, int high1, int[] b,
			int low2, int high2) {
		while (low1 != high1 && low2 != high2) {
			int mid1 = (low1 + high1) / 2;
			int mid2 = (low2 + high2) / 2;
			if (a[mid1] == b[mid2])
				return a[mid1];
			else if (a[mid1] < b[mid2]) {
				low1 = getPostPart(low1, high1);
				high2 = getPrePart(low2, high2);
			} else {
				high1 = getPrePart(low1, high1);
				low2 = getPostPart(low2, high2);
			}
		}
		return a[low1] < b[low2] ? a[low1] : b[low2];
	}

	/**
	 * 获取前半段的起始下标
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static int getPrePart(int s, int t) {
		return (s + t) / 2;
	}

	/**
	 * 获取后半段的起始下标
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static int getPostPart(int s, int t) {
		int m = (s + t) / 2;
		if ((s + t) % 2 == 0) {
			return m;
		} else {
			return m + 1;
		}
	}

	public static void main(String[] args) {
		int[] a = { 11, 13, 15, 16, 19 };
		int[] b = { 2, 4, 6, 8, 20 };
		System.out.println("方法一通过递归分治的方法求a和b序列的中位数，该中位数为：");
		System.out
				.println(findMidNumber(a, b, 0, 0, a.length - 1, b.length - 1));
		System.out.println("方法二通过循环的方法求a和b序列的中位数，该中位数为：");
		System.out
				.println(findMidNumber(a, 0, a.length - 1, b, 0, b.length - 1));
	}

}
