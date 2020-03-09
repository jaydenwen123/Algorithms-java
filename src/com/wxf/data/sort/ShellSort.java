package com.wxf.data.sort;

import java.util.Arrays;

public class ShellSort {

	public ShellSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 * @return
	 * 希尔排序
	 */
	public static int[] shellSort(int[] data) {

		for (int d = 5; d > 0; d = d - 2) {
			for (int c = 0; c < data.length - d; c++) {
				for (int i = c; i < data.length; i = i + d) {
					for (int j = i; j > 0; j = j - d) {
						if (j < d) {
							break;
						}
						if (data[j] < data[j - d]) {
							int temp = data[j];
							data[j] = data[j - d];
							data[j - d] = temp;
						}
					}
				}
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		System.out.println("使用希尔排序：");
		System.out.println("排序前的数组：\n" + Arrays.toString(data));
		data = shellSort(data);
		System.out.println("排序后的数组：\n" + Arrays.toString(data));
	}
}
