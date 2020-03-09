package com.wxf.data.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Administrator 冒泡排序算法
 * 
 */
public class DubbleSort {

	// 要进行排序的数组
	private int[] data;
	// 该数组的元素的个数
	private int length;
	// 中间标量，用来提高程序的效率
	private boolean flag = false;

	public DubbleSort(int[] data) {
		super();
		this.data = data;
		length = data.length;
	}

	/**
	 * @return 冒泡算法。 这次设置了一个中间变量。用来判断是否在每趟的比较过程中，都进行了交换。 该变量的写法有一下两种方式，分为如下
	 */
	public int[] dubbleSort1() {
		for (int i = 1; i < length && flag == false; i++) {
			flag = true;
			for (int j = 0; j < length - i; j++) {
				if (data[j] > data[j + 1]) {
					// exchangeTwoNumber(data[j], data[j + 1]);
					flag = false;
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * @return 该方法返回的是排序以后的数组
	 */
	public int[] dubbleSort2() {
		for (int i = 1; i < length; i++) {
			flag = true;
			for (int j = 0; j < length - i; j++) {
				if (data[j] > data[j + 1]) {
					// exchangeTwoNumber(data[j], data[j + 1]);
					flag = false;
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
			// 在这儿判断上面的该趟有没有进行交换数字，如果该趟没有进行交换，这说明该数组已经有序，执行退出趟的循环，
			if (flag == true) {
				break;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 38, 5, 19, 26, 49, 97, 1, 66, 345 };
		Date date1 = new Date(System.nanoTime());
		DubbleSort dubbleSort = new DubbleSort(data);
		// 排序前的数组
		System.out.println("排序前的数组：");
		System.out.println(Arrays.toString(data));
		data = dubbleSort.dubbleSort2();
		Date date2 = new Date(System.nanoTime());
		// 程序执行的时间
		System.out.println("排序执行得时间：");
		System.out
				.println(((float) (date2.getTime() - date1.getTime()) / 1000000));
		// 排序后的数组
		System.out.println("排序后的数组：");
		System.out.println(Arrays.toString(data));
	}
}
