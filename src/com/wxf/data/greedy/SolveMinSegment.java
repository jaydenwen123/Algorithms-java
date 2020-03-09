package com.wxf.data.greedy;


public class SolveMinSegment {

	public SolveMinSegment() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 求解区间覆盖问题
	 * 
	 * @param data
	 *            数组中存储的是直线上的n个点。即x序列
	 * @param k
	 *            表示的是固定的区间长度
	 * @return 返回的是最少的固定长度的区间个数
	 */
	public static int solveMinSegment(int[] data, int k, int[] start) {
		// 关于求解区间覆盖问题的思路如下：
		// 1.首先对序列data进行递增排序
		QuickSort(data, 0, data.length - 1);
		// 2.遍历整个序列。求其解
		// 该变量用来记录区间的个数
		int m = 0;
		start[m] = data[0];
		// 假设第一个元素为第一个闭区间的起点
		int temp = data[0];
		for (int i = 1; i < data.length; i++) {
			if (data[i] - temp > k) {
				temp = data[i];
				m++;
				start[m] = temp;
			}
		}
		return m + 1;
	}

	/**
	 * 快速排序
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void QuickSort(int[] data, int low, int high) {

		int i = low, j = high;
		// 快速排序的思路如下：
		// 1.首先假设第一个元素为初始元素。
		int temp = data[low];
		// 2.然后找到该元素的位置
		while (i != j) {
			while (i < j && data[j] >= temp)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] < temp)
				i++;
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// 此时找到初始元的 位置。然后将其放入
		data[i] = temp;
		// 3.然后开始在两个不同的子区间进行递归
		if (low < j) {
			QuickSort(data, low, j - 1);
		}
		if (j < high) {
			QuickSort(data, j + 1, high);
		}
	}

	public static void display(int[] data, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] data = { 2, 4, 1, 6, -2, 5, 3 };
		int[] start = new int[data.length];
		// System.out.println("排序前：");
		// System.out.println(Arrays.toString(data));
		// QuickSort(data, 0, data.length - 1);
		// System.out.println("排序后：");
		// System.out.println(Arrays.toString(data));

		System.out.println("求解结果：");
		int m = solveMinSegment(data, 3, start);
		System.out.println("选取的区间个数为：" + m);
		System.out.println("选取的区间：");
		display(start, m);
	}

}
