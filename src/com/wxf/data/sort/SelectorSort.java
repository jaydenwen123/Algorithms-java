package com.wxf.data.sort;

import java.util.Arrays;

/**
 * @author Administrator 对一组数进行直接选择排序 一下提供两种方法， 方法一：是通过直接保存最小的数然后在每趟结束时，对其进行赋值
 *         方法二：是通过保存最小数的小标，然后再每趟结束时。通过下表来判断是否当前的数时最小数，如果不是。则进行交换
 * 
 */
public class SelectorSort {

	/**
	 * @param data
	 *            要排序的数组
	 * @return 返回的是已经排好序的数组
	 */
	public static int[] selectorSort1(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// 每次每次循环的当前数为最小。
			int min = data[i];
			// 然后依次和它后面的数据元素进行比较。
			for (int j = i + 1; j < data.length; j++) {
				// 如果后面的数据元素有比他小的。然后进行两数的交换。当前的数仍然为最小。
				if (data[j] < min) {
					int temp = min;
					min = data[j];
					data[j] = temp;
				}
			}
			// 每次比较完后，得到最小的数保存在变量min中。然后对数组中的i位置的元素进行赋值，让其为最小的数。
			data[i] = min;

		}
		return data;
	}

	/**
	 * @param data
	 *            要排序的数组
	 * @return返回的是已经排好序的数组
	 */
	public static int[] selectorSort2(int[] data) {
		for (int i = 1; i < data.length; i++) {
			// 保存当前的数组元素为最下肢，并保存其小标
			int minIndex = i - 1;
			for (int j = i; j < data.length; j++) {
				// 如果当前的数不是最小，则把该数的小标记性赋值给minIndex
				if (data[j] < data[minIndex])
					minIndex = j;
			}
			// 最后判断保存的小标有没有变化，如果没有任何变化，则不用进行处理。否则，交换数据。
			if (minIndex != i - 1) {
				int temp = data[minIndex];
				data[minIndex] = data[i - 1];
				data[i - 1] = temp;
			}
		}
		return data;
	}

	/**
	 * @param data
	 * @return 该算法主要是修改后的稳定算法
	 */
	public static int[] selectorSort3(int[] data) {

		for (int i = 0; i < data.length; i++) {
			int temp = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[temp]) {
					temp = j;
				}
			}
			// 这儿主要是进行下表的判断。如果二者不相等。则将temp前面的内容卓次后移，最后将该最小的数插入进去
			if (temp != i) {
				int min = data[temp];
				for (int k = temp; k > i; k--) {
					data[k] = data[k - 1];
				}
				data[i] = min;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 1, 54, 5, 87, 345 };
		System.out.println("排序前的数组：");
		System.out.println(Arrays.toString(data));
		data = selectorSort3(data);
		System.out.println("排序后的数组元素：");
		System.out.println(Arrays.toString(data));
	}

}
