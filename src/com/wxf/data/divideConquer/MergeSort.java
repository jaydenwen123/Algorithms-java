package com.wxf.data.divideConquer;

import java.util.Arrays;

public class MergeSort {

	public MergeSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 归并排序
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] mergeSort(int[] data, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(data, mid + 1, high);
			mergeSort(data, low, mid);
			merge(data, low, mid, high);
		}
		return data;
	}

	/**
	 * 合并两个有序的数组为一个有序的数组
	 * 
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] data, int low, int mid, int high) {
		// 归并排序的思路如下：
		// 1.首选是递归两个子数组，通过一个中间数来分割两个数组
		// 2.合并两个有序的数组为一个有序的数组，合并的思路如下：
		// a、首先两个有序的子数组通过一个中间数mid来区份。
		// b、通过不断的对两个子数组中的每个数进行比较，每次选出最小的数
		// c.然后知道其中某个数组中的数字全部比较完
		// d.在对两个子数组是否为空判断，如果不为空，则直接将子数组中的数据复制到新数组中
		// e.最后算法结束

		// 定义已给新数组用俩存放有序的排序后的元素
		int[] newArr = new int[high - low + 1];
		int i = low, j = mid + 1;
		int k = 0;
		while (i <= mid && j <= high) {
			if (data[i] < data[j]) {
				newArr[k++] = data[i++];
			} else {
				newArr[k++] = data[j++];
			}
		}
		// 一下两个循环只会执行一次，用来判断是否俩个子数组中是否有空余的数据，
		// 如果有然后将其全部复制到新数组中
		while (i <= mid) {
			newArr[k++] = data[i++];
		}
		while (j <= high) {
			newArr[k++] = data[j++];
		}
		// 进行数组的拷贝操作
		System.arraycopy(newArr, 0, data, low, newArr.length);
	}

	public static void main(String[] args) {

		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("排序前：");
		System.out.println(Arrays.toString(data));
		System.out.println("排序后：");
		System.out
				.println(Arrays.toString(mergeSort(data, 0, data.length - 1)));
	}

}
