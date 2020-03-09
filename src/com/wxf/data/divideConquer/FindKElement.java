package com.wxf.data.divideConquer;

import java.util.Arrays;

public class FindKElement {

	public FindKElement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 寻找一个序列中第k小元素
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @param k
	 * @return
	 */
	public static int findKPositionElement(int[] data, int low, int high, int k) {
		if (k < low && k > high)
			return -1;
		int i = FindInitElementPosition(data, low, high);
		// 以下进行递归
		if (i == k - 1) {
			return data[i];
		} else if (i < k - 1) {
			return findKPositionElement(data, i + 1, high, k);
		} else {
			return findKPositionElement(data, low, i - 1, k);
		}
	}

	/**
	 * 快速排序
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] QuickSort(int[] data, int low, int high) {
		int i = 0;
		if (low < high) {
			i = FindInitElementPosition(data, low, high);
			QuickSort(data, low, i - 1);
			QuickSort(data, i + 1, high);
		}
		return data;
	}

	/**
	 * 知道初始元素的位置
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	private static int FindInitElementPosition(int[] data, int low, int high) {
		// 寻找一个序列data中第k小元素
		// 1.首先定一个初始值，即该序列的第一个元素
		// 2.通过快速排序的思想，找到该元素合适的位置i
		// 3.然后判断k和i的位置大小
		// 4.然后再决定到不同的区间进行递归寻找
		int i = low, j = high;
		int temp = data[low];
		while (i != j) {
			while (i < j && data[j] >= temp)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] <= temp)
				i++;
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// 将该元素插到合适的位置
		data[i] = temp;
		return i;
	}

	public static void main(String[] args) {
		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("查找第3小元素：");
		System.out.println(findKPositionElement(data, 0, data.length - 1,
				4));
		for(int i=1;i<=10;i++){
			System.out.print("查找第"+i+"小元素：");
			System.out.println(findKPositionElement(data, 0, data.length - 1,
					i));
		}
		System.out.println("数组排序后的结果：");
		System.out.println(Arrays.toString(QuickSort(data, 0, data.length-1)));
	}
}
