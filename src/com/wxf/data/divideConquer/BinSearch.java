package com.wxf.data.divideConquer;

import java.util.Arrays;

public class BinSearch {

	public BinSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 二分查找 递归实现
	 * 
	 * @param data
	 * @param el
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binSearch(int[] data, int el, int low, int high) {
		int index = -1;
		if (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == el)
				return mid;
			else if (data[mid] < el) {
				return binSearch(data, el, mid + 1, high);
			} else {
				return binSearch(data, el, low, mid - 1);
			}

		}
		return index;
	}

	/**
	 * 二分查找 非递归实现
	 * 
	 * @param data
	 * @param el
	 * @return
	 */
	public static int binSearch(int[] data, int el) {
		int index = -1;
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == el)
				return mid;
			else if (data[mid] < el) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return index;
	}

	public static void main(String[] args) {

		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("要查找的数组为：");
		System.out.println(Arrays.toString(data));
		System.out.println("二分查找递归方法实现：");
		System.out.println("元素7在数组中的位置为："+binSearch(data, 7, 0, data.length - 1));
		System.out.println("元素19在数组中的位置为："+binSearch(data, 19, 0, data.length - 1));
		System.out.println("二分查找非递归循环结构实现：");
		System.out.println("元素7在数组中的下标为："+binSearch(data, 7));
		System.out.println("元素19在数组中的下标为："+binSearch(data, 19));
	}
}
