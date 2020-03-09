package com.wxf.data.sort;

import java.util.Arrays;

public class InsertSort {

	public InsertSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 *            待排序的数组
	 * @return 排序后数组 直接插入排序算法。 该方法通过两层循环来实现。 完成循环使用for循环，内层嵌套一个while循环
	 */
	public static int[] insertSort1(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// 保存要插入的元素值。因为下面有可能要进行后移的操作，如果不保存，则会存在值覆盖的问题。
			int temp = data[i + 1];
			int j = i;
			// 通过while循环来判断如果a[j+1]位置的元素小于它前面的元素，则将其前面的元素进行后移，空出一个位置。
			while (j > -1 && temp <= data[j]) {
				data[j + 1] = data[j];
				j--;
			}
			// 不过上述的while循环保证满足条件时后移。与否。都是执行下属的操作
			// 因为:
			// 1.如果进行了上述的后移操作。则j+1的值应该为要插入的该元素的位置。
			// 2.如果不进行上述的循环，则表述该元素比前面的数都大。因此它的位置就应该为j+1
			data[j + 1] = temp;
		}
		return data;
	}

	/**
	 * @param data
	 *            待排序的数组
	 * @return 返回排序后的数组 直接插入排序方法2 方法二的排序也是采用两层循环， 外层采用for循环 内层嵌套的仍然为for循环
	 */
	public static int[] insertSort2(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// 保存当前要插入的值
			int temp = data[i + 1];
			int j = i;
			// 该内层循环主要用来进行该元素和其前面已经有序的数组进行比较
			for (; j >= 0; j++) {
				if (data[j] >= temp) {
					data[j + 1] = data[j];
					j--;
				} else {
					break;
				}
			}
			// 最后在该位置插入元素
			data[j + 1] = temp;
		}
		return data;
	}

	/**
	 * @param array
	 *            排序前的数组
	 * @return 返回值为排序后的数组 该方法外层通过for循环来执行 内循环通过while循环进行判断和比较
	 */
	private static int[] InsertSort3(int[] array) {
		for (int i = 1; i < array.length; i++) {

			int key = array[i];
			int index = i;
			// 对前面的数进行遍历。只需要找出比当前的数小的数，即可退出循环
			// 一旦条件不满足，则退出循环
			while (index > 0 && array[index - 1] > key) {
				// 该行语句对应的是，当当前的数比要插入的数大时，将该数向后移动
				array[index] = array[index - 1];
				index--;
			}
			// 当循环结束时。意味着已经找到和合适的位置，然后再将其插入
			array[index] = key;
		}
		return array;
	}

	/**
	 * 目前只对整形的数组进行排序
	 * 
	 * @param array参数为要排序的数组
	 * @return 返回值为排序后的数组 通过两层for循环来实现
	 */
	private static int[] InsertSort4(int[] array) {
		int key = 0;
		for (int i = 1; i < array.length; i++) {
			key = array[i];
			int j = i;
			for (j = i; j > 0; j--) {
				// 如果已经排好序的数组中的元素大于要插入的元素，则进行交换。
				if (array[j - 1] > key) {
					// 将当前位置的元素往后移 array[j+1]=array[j];
					array[j] = array[j - 1];
				} else {
					// 这儿不能用continue；原因是前面的数组已经是排好序的只需要从前往后找，找到它合适的位置
					// 而不需要整体遍历。
					break;
				}
			}
			array[j] = key;
		}
		return array;
	}

	public static void main(String[] args) {

		int[] data = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		int[] data2 = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		int[] data3 = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		int[] data4 = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		System.out.println("使用方法一进行排序：");
		System.out.println("排序前的数组：\n" + Arrays.toString(data));
		data = insertSort1(data);
		System.out.println("排序后的数组：\n" + Arrays.toString(data));
		System.out.println("使用方法二进行排序：");
		System.out.println("排序前的数组：\n" + Arrays.toString(data2));
		data2 = insertSort1(data2);
		System.out.println("排序后的数组：\n" + Arrays.toString(data2));
		System.out.println("使用方法三排序：");
		System.out.println("排序前的数组：\n" + Arrays.toString(data3));
		data3 = insertSort1(data3);
		System.out.println("排序后的数组：\n" + Arrays.toString(data3));
		System.out.println("使用方法四进行排序：");
		System.out.println("排序前的数组：\n" + Arrays.toString(data4));
		data4 = insertSort1(data4);
		System.out.println("排序后的数组：\n" + Arrays.toString(data4));
	}

	/*
	 * public static void main(String[] args) { // int[] array = new int[] { 56,
	 * 57, 43, 2, 4, 12, 567, 87, 9, 98, 5, 43, 423, 123, 12, 312, 3, 2, 5, 23,
	 * 34, 546, 7, 899, 0, 64, 23, 45, 67, 8, 9, 4, 6, 67, 8, 23, 97, 11, 234,
	 * 90, 234, 2, 345, 46, 567, 57, 21, 343, 546, 63, 2, 1, 454, 2, 1, 1, 56,
	 * 87, 3, 2343, 546, 567, 687, 34, 54, 534, 45, 6, 57, 657, 6, 44, 234, 454,
	 * 667, 658, 7698, 9, 234, 242, 24, 2354, 365, 5467, 234, 56, 5, 767, 8789,
	 * 789, 90, 9, 898, 234, 35, 436, 54, 7, 234, 54, 4, 65, 76, 687, 8, 79,
	 * 546, 4, 53, 3, 53, 34, 2, 42, 42, 42, 23, 4, 234, 3, 6, 5, 67, 1, 24,
	 * 234, 6, 567, 46, 5, 68, 65, 7, 67 }; System.out.println("primitive data:"
	 * + Arrays.toString(array)); Date time1 = new Date(System.nanoTime()); //
	 * nanoTime() // array = InsertSort3(array); // array = InsertSort4(array);
	 * Date time2 = new Date(System.nanoTime()); System.out.println("sort data:"
	 * + Arrays.toString(array)); SimpleDateFormat format = new
	 * SimpleDateFormat("hh:mm:ss"); System.out.println("length:" +
	 * array.length); // System.out.println("运算的时间：" + format.format(time1)); //
	 * System.out.println("运算的时间：" + format.format(time2));
	 * System.out.println("sssws:" + (float) (time2.getTime() - time1.getTime())
	 * / 1000000);
	 * 
	 * }
	 */

}
