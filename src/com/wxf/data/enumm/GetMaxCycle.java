package com.wxf.data.enumm;

import com.wxf.data.recursion.Sort;

public class GetMaxCycle {

	/**
	 * 从一个数组中，选出三个数，使其构成的三角形的周长最大，并返回，如果 不能构成三角形，则返回0
	 * 
	 * @param data
	 * @return
	 */
	public static int getMaxcicyle(int data[]) {
		int max = 0;
		// 思路如下：
		// 首选进行对数组中的元素进行判断，元素个数是否大于3，如果小于3返回-1
		// 其次找出数组中的前三个大数，然后判断该三个数是否可以构成
		// 三角形，如果可以则返回周长，如果不能，则返回0
		Sort.bubbleSort1(data);
		int n = data.length;
		int max1 = data[n - 1];
		int max2 = data[n - 2];
		int max3 = data[n - 3];
		if (max3 + max2 > max1 && max1 - max2 < max3 && max1 - max3 < max2)
			return max1 + max2 + max3;
		else {
			return 0;
		}

	}

	public static void main(String[] args) {

		int[] data = { 4, 5, 8, 20 };
		System.out.println(getMaxcicyle(data));
	}
}
