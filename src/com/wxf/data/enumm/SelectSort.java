package com.wxf.data.enumm;

import java.beans.IntrospectionException;
import java.util.Arrays;

public class SelectSort {

	public SelectSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 简单选择排序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] selectSort(int[] data) {

		// 简单排序的思路如下:
		// 1.首先假定该数是最小的,然后依次进行比较
		// 2.如果某位的数字小于该数,则进行交换
		// 3.经过一趟比较后,data[0]位最小的数
		// 4.n个数总共需要进行n-1趟
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (data[min] > data[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = data[min];
				data[min] = data[i];
				data[i] = temp;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int data[] = { 2, 4, 7, 1, 3, 5, 67, 32, 21 };
		System.out.println("排序前：");
		System.out.println(Arrays.toString(data));
		System.out.println("排序后：");
		System.out.println(Arrays.toString(selectSort(data)));

	}
}
