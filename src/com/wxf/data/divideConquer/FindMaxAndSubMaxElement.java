package com.wxf.data.divideConquer;

import java.awt.SecondaryLoop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FindMaxAndSubMaxElement {

	public FindMaxAndSubMaxElement() {
		// TODO Auto-generated constructor stub
	}

	public static final int[] s1 = new int[2];
	public static final int[] s2 = new int[2];

	/**
	 * 求序列的最大元素和次大元素
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void findMaxAndSubMaxElement(int[] data, int low, int high,
			int max1, int max2) {
		// 关于求序列的最大元素和次最大元素的思路如下：
		// 1.首先找递归体和递归出口
		// 2.递归出口为：当序列中只有两个元素时，比较两个元素得到最大元素和次最大元素
		// 3.递归体为：首先从该序列的中间位置开始，将序列分成两半。
		// 4.在左半序列中找出最大和次最大元素，在右半序列中也找出最大和次最大元素
		// 5.然后进行比较，最后打出真个序列的最大元素和次最大元素
		// 递归出口
		int x1 = 0, x2 = 0, x3 = 0, x4 = 0;
		if (high - low < 1)
			return;
		if (high - low == 1) {
			if (data[low] < data[high]) {
				max1 = data[high];
				max2 = data[low];
			} else {
				max1 = data[low];
				max2 = data[high];
			}
			System.out.println(max1 + "," + max2);
		} else {
			int mid = (low + high) / 2;
			findMaxAndSubMaxElement(data, low, mid, x1, x2);
			findMaxAndSubMaxElement(data, mid + 1, high, x3, x4);
			if (x1 < x3) {
				max1 = x3;
				if (x1 > x4) {
					max2 = x1;
				} else {
					max2 = x4;
				}
			} else {
				max1 = x1;
				if (x2 > x3) {
					max2 = x2;
				} else {
					max2 = x3;
				}
			}

		}

	}

	public static void test(int a, int b) {
		a = 3;
		b = 5;
		System.out.println("hello");

	}

	public static void main(String[] args) {
		int[] save = new int[2];
		int[] data = { 2, 3, 45, 5, 7, 1, 4, 9, 6 };
		int max1 = 0, max2 = 0;
		findMaxAndSubMaxElement(data, 0, data.length - 1, max1, max2);
		System.out.println(save[0] + "," + save[1]);

	}
}
