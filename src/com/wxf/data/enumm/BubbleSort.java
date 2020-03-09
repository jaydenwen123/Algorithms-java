package com.wxf.data.enumm;

import java.util.Arrays;

public class BubbleSort {

	public BubbleSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 冒泡排序
	 * 
	 * @param data
	 * @return
	 */
	public static int[] bubbleSort(int[] data) {
		// 冒泡排序的思路如下：
		// 1.首先假定每一次的第一个元素是最大值，然后依次循环
		// 2.进行两辆之间比较
		// 3.如果该数比其后面的数小，则进行交换
		// 4.最后确定外层循环为n-1次
		// 5.内层循环为n-i次
		int n = data.length;
		// 外层循环为n-1次
		for (int i = 0; i < n - 1; i++) {
			// 设置标志位，用来判断是否冒泡排序中，某趟是否数据已经有序
			boolean flag = true;
			for (int j = 1; j < n - i; j++) {
				if (data[j-1] > data[j]) {
					int temp = data[j];
					data[j] = data[j-1];
					data[j - 1] = temp;
					flag = false;
				}
			}
			if (flag) {
				break;
			}

		}
		return data;
	}
	
	public static void main(String[] args) {
		int data[]={2,4,7,1,3,5,67,32,21};
		System.out.println("排序前：");
		System.out.println(Arrays.toString(data));
		System.out.println("排序后：");
		System.out.println(Arrays.toString(bubbleSort(data)));
		
	}

}
