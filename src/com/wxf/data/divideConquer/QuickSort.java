package com.wxf.data.divideConquer;

import java.util.Arrays;

public class QuickSort {

	public QuickSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 快速排序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] quickSort(int[] data, int low, int high) {
		if (low < high) {
			int i = dividePosition(data, low, high);
			quickSort(data, i + 1, high);
			quickSort(data, low, i - 1);
		}
		return data;
	}

	/**
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	private static int dividePosition(int[] data, int low, int high) {

		// 快速排序的算法思路如下：
		// 1.首先将第一个元素作为基准元素
		// 2。然后从该数组的最后一个元素依次往前找
		// 3.如果数比该基准元素大，则一直循环，否则将两个元素进行交换位置
		// 4.交换位置后然后从前往后找，如果遇到的数小于该基准元素，则一直循环么
		// 5.如果遇到的数大于该基准数，进行进行交换将树的位置
		// 6.最后知道从前往后和从后往前找相遇后，退出
		int i = low, j = high;
		int temp = data[low];
		while (i != j) {
			while (i < j && data[j] > temp)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] < temp) {
				i++;
			}
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// 循环退出时，i==j;
		data[i] = temp;
		return i;
	}

	public static void main(String[] args) {

		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("排序前：");
		System.out.println(Arrays.toString(data));
		System.out.println("排序后：");
		System.out
				.println(Arrays.toString(quickSort(data, 0, data.length - 1)));
	}
}
