package com.wxf.data.sort;

import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	// 要排序的数组
	private int[] data;
	// 该数组的元素的个数
	private int length;

	public QuickSort(int[] data) {
		super();
		this.data = data;
	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @return 快速排序的主要算法。
	 */
	public static int[] quickSort1(int[] data, int low, int high) {
		int i = low, j = high;
		int temp = data[low];
		while (i < j) {
			while (i < j && temp <= data[j])
				j--;
			// 下面的if语句主要是来判断上面的while退出循环的条件。
			/**
			 * 因为while退出循环有两种情况，一种是i>=j 一种是temp>data[j]
			 * 所以下面的if语句主要用来表达退出循环的第二中情况
			 */
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && temp > data[i])
				i++;
			// 下面的代码含义和上面一样。
			if (i < j) {
				data[j] = data[i];
				j--;

			}

		}
		data[i] = temp;
		// 递归，对两个子数组进行排序
		/**
		 * 一下是对两个子数组进行递归。
		 */
		if (low < i)
			quickSort1(data, low, i - 1);
		if (j < high)
			quickSort1(data, j + 1, high);
		return data;

	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @return 返回值为最终的插入标准元素的位置 快速排序的主要算法。
	 */
	public static int getCriteriaIndex(int[] data, int low, int high) {
		int i = low, j = high;
		int temp = data[low];
		while (i < j) {
			while (i < j && temp <= data[j])
				j--;
			// 下面的if语句主要是来判断上面的while退出循环的条件。
			/**
			 * 因为while退出循环有两种情况，一种是i>=j 一种是temp>data[j]
			 * 所以下面的if语句主要用来表达退出循环的第二中情况
			 */
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && temp > data[i])
				i++;
			// 下面的代码含义和上面一样。
			if (i < j) {
				data[j] = data[i];
				j--;

			}

		}
		data[i] = temp;
		return i;

	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @return 返回值为排序好的数组 递归分治法的代码。
	 */
	public static int[] quickSort2(int[] data, int low, int high) {
		if (low < high) {
			int i = getCriteriaIndex(data, low, high);
			quickSort2(data, low, i - 1);
			quickSort2(data, i + 1, high);
		}
		return data;
	}

	public static void main(String[] args) {

		int[] data = new int[] { 60, 55, 48, 37, 10, 90, 84, 36, 45, 76, 7 };
		int[] data1 = new int[] { 60, 55, 48, 37, 10, 90, 84, 36, 45, 76, 7 };
		System.out.println(data.length);
		Date date1 = new Date(System.nanoTime());
		System.out.println("未排序的数组：");
		System.out.println(Arrays.toString(data));
		data = QuickSort.quickSort2(data, 0, data.length - 1);
		Date date2 = new Date(System.nanoTime());
		System.out.println("排序后的数组：");
		System.out.println(Arrays.toString(data));
		System.out.println("排序需要的时间：");
		System.out
				.println(((float) (date2.getTime() - date1.getTime()) / 1000000));

		/*
		 DubbleSort dubbleSort = new DubbleSort(data1); Date date3 = new
		 Date(System.nanoTime()); System.out.println("未排序的数组：");
		 System.out.println(Arrays.toString(data1)); data1 =
		 dubbleSort.dubbleSort1(); Date date4 = new Date(System.nanoTime());
		 System.out.println("排序后的数组：");
		 System.out.println(Arrays.toString(data1));
		 System.out.println("排序需要的时间："); System.out .println(((float)
		 (date4.getTime() - date3.getTime()) / 1000000));
		 */
	}
}
