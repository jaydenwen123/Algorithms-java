package com.wxf.data.sort;

import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	/**
	 * @param data
	 * @return 堆排序的java代码实现 以下提供两种方法来实现堆排序
	 */
	public static int[] heapSort1(int[] data) {

		// 循环创建堆
		for (int i = data.length - 1; i > 0; i--) {
			// 创建堆
			createHeap(data, i);
			// 将堆顶和最后一个元素进行交换
			 System.out.println(Arrays.toString(data));
			exchangeElement(data, 0, i);
			// 输出每次排序后的数组
		}
		return data;
	}

	/**
	 * @param data
	 * @return 提供第二种堆排序的算法 堆排序算法的步骤： 1.创建堆， 2.循环创建堆 3.进行排序
	 */
	public static int[] heapSort2(int[] data) {
		// 初始化建堆
		initCreateHeap(data);
		System.out.println(Arrays.toString(data));
		for (int i = data.length - 1; i >= 0; i--) {
			exchangeElement(data, 0, i);
			createHeap(data, i, 0);
			System.out.println(Arrays.toString(data));
		}
		return data;
	}

	/**
	 * @param data
	 *            循环建堆的过程：
	 *            因为初始化创建最大堆的过程，就是从第一个非叶结点a[(n-1)/2]开始到根节点a[0]位置循环调用createHeap
	 *            ()函数的过程
	 */
	private static void initCreateHeap(int[] data) {
		// TODO Auto-generated method stub
		for (int i = (data.length - 1) / 2; i >= 0; i--) {
			createHeap(data, data.length, i);
		}
	}

	/**
	 * @param data
	 *            原始元素的数组
	 * @param length
	 *            二叉树的节点个数
	 * @param h
	 *            要创建的堆 的二叉树根节点下标
	 * 
	 */
	private static void createHeap(int[] data, int n, int h) {
		// TODO Auto-generated method stub
		int father = h;
		// 首先对其孩子的下表进行判断是否小于n
		int child = father * 2 + 1;
		while (child<n) {
			if (child < n-1) {
				// 对左右孩子节点进行比较大小
				if (data[child] < data[child + 1]) {
					child++;
				}
			}
			if (data[father] < data[child]) {
				exchangeElement(data, father, child);
			} else {
				break;
			}
		}
	}

	/**
	 * @param data
	 * @param i
	 * @param j
	 *            交换数组中的两个值
	 */
	public static void exchangeElement(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * @param data
	 *            原始的数组
	 * @param lastIndex
	 *            从该元素起，开始创建堆 创建堆
	 */
	public static void createHeap(int[] data, int lastIndex) {
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// 该值为其lastIndex的父亲节点的值
			int father = i;
			//
			while (father * 2 + 1 <= lastIndex) {
				// 这个为其父亲节点的左孩子。
				int child = father * 2 + 1;
				if (child < lastIndex) {
					// 对其左孩子和右孩子进行比较，取得较大的那个数
					if (data[child] < data[child + 1]) {
						child++;
					}
				}
				// 对父亲和左右孩子中较大的数进行比较，如果父亲节点的值小于孩子节点的值。则进行交换
				if (data[father] < data[child]) {
					exchangeElement(data, father, child);
				} else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] data = new int[] { 1, 54, 5, 87, 34, 3, 32, 12 };
		int[] data2 = new int[] { 1, 54, 5, 87, 34, 3, 32, 12 };
		System.out.println("排序前的数组：");
//		System.out.println(Arrays.toString(data));
		System.out.println("开始堆排序：使用方法一：");
		data = heapSort1(data);
		System.out.println("排序后的数组元素：");
		System.out.println(Arrays.toString(data));

		System.out.println("排序前的数组：");
//		System.out.println(Arrays.toString(data2));
		System.out.println("开始堆排序：使用方法二：");
		data = heapSort2(data2);
		System.out.println("排序后的数组元素：");
		System.out.println(Arrays.toString(data2));
	}

//	public static void main(String[] args) {
//		int[] array = new int[] { 56,
//
//		57, 43, 2, 4, 12, 567, 87, 9, 98, 5, 43, 423, 123, 12, 312, 3, 2, 5,
//				23, 34, 546, 7, 899, 0, 64, 23, 45, 67, 8, 9, 4, 6, 67, 8, 23,
//				97, 11, 234, 90, 234, 2, 345, 46, 567, 57, 21, 343, 546, 63, 2,
//				1, 454, 2, 1, 1, 56, 87, 3, 2343, 546, 567, 687, 34, 54, 534,
//				45, 6, 57, 657, 6, 44, 234, 454, 667, 658, 7698, 9, 234, 242,
//				24, 2354, 365, 5467, 234, 56, 5, 767, 8789, 789, 90, 9, 898,
//				234, 35, 436, 54, 7, 234, 54, 4, 65, 76, 687, 8, 79, 546, 4,
//				53, 3, 53, 34, 2, 42, 42, 42, 23, 4, 234, 3, 6, 5, 67, 1, 24,
//				234, 6, 567, 46, 5, 68, 65, 7, 67 };
//
//		Date time1 = new Date(System.nanoTime());
//		System.out.println("开始堆排序：使用方法一：");
//		array = heapSort1(array);
//		Date time2 = new Date(System.nanoTime());
//		System.out.println("sort data:" + Arrays.toString(array));
//		System.out.println("sssws:"
//				+ (float) (time2.getTime() - time1.getTime()) / 100000);
//
//		System.out.println("开始堆排序：使用方法二：");
//		time1 = new Date(System.nanoTime());
////		array = heapSort2(array);
//		System.out.println(Arrays.toString(Test.heapSort(array)));
//		time2 = new Date(System.nanoTime());
////		System.out.println("sort data:" + Arrays.toString(array));
//		System.out.println("sssws:"
//				+ (float) (time2.getTime() - time1.getTime()) / 100000);
//	}

}
