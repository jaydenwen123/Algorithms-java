package com.wxf.data.sort;

import java.util.Arrays;

public class MergeSort {

	public static int[] mergeSort(int[] data) {
		sort(data, 0, data.length - 1);
		return data;
	}

	/**
	 * 归并排序的算法
	 * 
	 * @param data
	 *            待排序的数组
	 * @param left
	 *            数组的要排序的第一个元素的下标
	 * @param right
	 *            数组中要排序的最后一个元素的下标
	 */
	public static void sort(int[] data, int left, int right) {
		if (left >= right)
			return;
		// 计算得到归并排序的分割两个字数组的值
		int center = (left + right) / 2;
		// 对左边的数组进行递归排序
		sort(data, left, center);
		// 对右边的数组进行递归排序
		sort(data, center + 1, right);
		// 最后合并两个子数组
		merge(data, left, center, right);
		System.out.println("oo:" + Arrays.toString(data));
	}

	/**
	 * 合并两个已经有序的数组。合并后的的数组也有序
	 * 
	 * @param data
	 *            要合并的数组
	 * @param left
	 *            第一个子数组的下标
	 * @param center
	 *            第一子数组的最后一个元素的下标
	 * @param right
	 *            第二个数组的最后一个元素的小标，第二个数组的第一个元素下标为center+1
	 */
	public static void merge(int[] data, int left, int center, int right) {
		int[] newData = new int[right - left + 1];
		int mid = center + 1;
		int i = 0, j = 0;
		// 进行对两个子数组比较，每次比较将子数组中较小的元素存放到零时的数组newData中
		int temp = left;
		while (left <= center && mid <= right) {
			if (data[left] < data[mid]) {
				newData[i++] = data[left++];
			} else {
				newData[i++] = data[mid++];
			}
		}
		// 如果上述的循环结束，以为这比较结束了，因此下面的两个循环用来判断是哪个条件结了循环
		// 然后再把剩余的数全部追加到临时的数组中。
		while (mid <= right) {
			newData[i++] = data[mid++];
		}
		while (left <= center) {
			newData[i++] = data[left++];
		}
		/*
		 * while (j <= newData.length-1) { data[temp++] = newData[j++]; }
		 */
		System.arraycopy(newData, 0, data, temp, newData.length);
		// 最后再将临时的数组中的值拷贝到原数组中。
		// System.out.println(Arrays.toString(newData));
		/*
		 * 下面的方式也可以 for(int j=0;j<newData.length;j++){ data[left++]=newData[j];
		 * }
		 */
	}

	public static void main(String[] args) {
		// int[] data = new int[] { 72, 73, 71, 23, 94, 16, 5, 68, 64 };
		int[] data = new int[] { 1, 54, 5, 87, 345, 4, 56, 122 };
		System.out.println("排序前的数组：");
		System.out.println(Arrays.toString(data));
		System.out.println("开始排序：");
		data = mergeSort(data);
		System.out.println("排序后的数组元素：");
		System.out.println(Arrays.toString(data));

	}
}
