package com.wxf.data.recursion;

import java.util.Arrays;

public class Sort {

	public Sort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * 简单选择排序 递归 对data 数组中的n-i个元素进行排序 其中selectSort2(int[] data,int n,int
	 * i)是大问题selectSort2(int[] data,int n,int i-1)是小问题，当i=n-1时，结束
	 * 
	 * @param data
	 * @param n
	 * @param i
	 * @return
	 * 
	 */
	public static void selectSort2(int[] data, int n, int i) {

		if (i == n - 1)
			return;
		else {
			int index = i;
			for (int j = i + 1; j < n; j++) {
				if (data[j] < data[index]) {
					index = j;
				}
			}
			if (index != i) {
				int temp = data[index];
				data[index] = data[i];
				data[i] = temp;
			}
			// 对其递归
			selectSort2(data, n, i + 1);
		}
	}

	/**
	 * 简单选择排序 非递归
	 * 
	 * @param data
	 * @return
	 */
	public static int[] selectSort1(int[] data) {
		// 简单选择排序的思路：
		// 1.对于n个数进行比较n-1次比较，每次找出最小的数，然后将其和第零个元素进行交换
		// 2.外层循环是n-1次
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			// 每次假定第i个数为最小的数
			int min = data[i];
			int index = 0;
			for (int j = i + 1; j < n; j++) {
				if (data[j] < min) {
					min = data[j];
					index = j;
				}
			}
			if (min != data[i]) {
				int temp = data[i];
				data[i] = min;
				data[index] = temp;
			}
		}
		return data;
	}

	// public static int[] bubbleSort2(int data,)

	public static int[] quickSort(int[] data, int low, int high) {
		// 快速排序的思路如下：
		// 1、首先该算法是一个递归的算法
		// 2、假定该数组的第一个元素为初始值，然后不断的在其数组的最后一个元素向前查找，找到比其小的则进行交换位置
		// 3.然后又从前往后找，找到比其大的，在进行交换位置
		// 4.直到从前往后和从后往前找遇到一起
		// 5.最后的到的效果是该数前面的元素都比其小，该数后面的元素都比其大
		// 6.然后开始在其其后的两个数组中进行递归

		int temp = data[low];
		int i = low, j = high;
		while (i < j) {
			while (i < j && temp < data[j])
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && temp > data[i]) {
				i++;
			}
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		data[i] = temp;
		// 下面进行递归
		if (low < i)
			quickSort(data, low, i - 1);
		if (i < high)
			quickSort(data, i + 1, high);
		return data;
	}

	/**
	 * 
	 * 冒泡排序 非递归
	 * 
	 * @param data
	 * @return
	 */
	public static int[] bubbleSort1(int[] data) {

		// 冒泡排序的思路如下：
		// 1.首先冒泡排序是进行两辆之间的排序，每次找到最大的值然后放到数组的最后一个位置上去
		// 2.外层循环需要进行n-1趟
		// 3.内层循环需要进行n-i趟
		// 4.为了提高排序的效率需定义一个标志位
		int n = data.length;
		boolean flag = false;
		for (int i = 1; i < n; i++) {
			flag = false;
			for (int j = 0; j < n - i; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = true;
				}
			}
			if (!flag) {
				break;
			}
		}
		return data;
	}

	/**
	 * 
	 * 简单选择排序 递归 对data 数组中的n-i个元素进行排序 其中selectSort2(int[] data,int n,int
	 * i)是大问题selectSort2(int[] data,int n,int i-1)是小问题，当i=n-1时，结束
	 * 
	 * @param data
	 * @param n
	 * @param i
	 * @return
	 * 
	 */
	public static void bubbleSort2(int[] data, int n, int i) {

		if (i != n - 1) {
			// 冒泡排序递归算法思路如下：
			// 1.首先需要解决对data中n-i个元素的排序
			// 2.然后再进行再n-i+1个元素中递归执行
			boolean flag = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = true;
				}
			}
			if (!flag)
				return;
			else {
				bubbleSort2(data, n, i + 1);
			}
		}
	}

	/**
	 * 插入排序 非递归
	 * 
	 * @param data
	 * @return
	 */
	public static int[] insertSort(int[] data) {
		// 插入排序的思路如下：
		// 1.首先每次从找到要插入的元素
		// 2.然后和其前面的有序的元素依次进行比较
		// 3.找到合适的位置，进行插入
		int n = data.length;
		for (int i = 1; i < n; i++) {
			// 带插入的元素
			int el = data[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (data[j] < el) {
					break;
				} else {
					data[j + 1] = data[j];
				}
			}
			data[j + 1] = el;
		}
		return data;
	}

	/**
	 * 插入排序递归法
	 * 
	 * @param data
	 * @param n
	 * @param i
	 */
	public static void insertSort2(int[] data, int n, int i) {
		if (i == n - 1)
			return;
		else {
			int el = data[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (data[j] < el) {
					break;
				} else {
					data[j + 1] = data[j];
				}
			}
			data[j + 1] = el;
			insertSort2(data, n, i + 1);
		}
	}
	

	public static void main(String[] args) {
		int[] data = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data1 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data2 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data3 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data4 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data5 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		System.out.println("选择排序：");
		System.out.println(Arrays.toString(selectSort1(data)));
		System.out.println("快速排序：");
		System.out.println(Arrays
				.toString(quickSort(data1, 0, data.length - 1)));
		System.out.println("冒泡排序；");
		System.out.println(Arrays.toString(bubbleSort1(data2)));

		System.out.println("简单选择排序：递归解法");
		selectSort2(data3, data3.length, 0);
		System.out.println(Arrays.toString(data3));
		System.out.println("冒泡排序：递归算法");
		System.out.println(Arrays.toString(data4));
		bubbleSort2(data4, data4.length, 0);
		System.out.println(Arrays.toString(data4));
		System.out.println("插入排序：");
		System.out.println(Arrays.toString(data5));
		// System.out.println(Arrays.toString(insertSort(data5)));
		insertSort2(data5, data5.length + 1, 0);
		System.out.println(Arrays.toString(data5));
	}

}
