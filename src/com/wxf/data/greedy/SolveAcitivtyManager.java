package com.wxf.data.greedy;

public class SolveAcitivtyManager {

	public SolveAcitivtyManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 求解活动安排问题，该问题可以采用面向对象的思想，也可以不采用 ，如果面向对象的思想的话，参考七届最大不相交区间问题求解
	 * 
	 * 该问题类似于求解最大不相交区间问题，
	 * 
	 * @param b
	 *            用来存储的是n个活动的开始时间
	 * @param e
	 *            用来存储的是n个活动的结束时间
	 * @param n
	 *            n表示的是n个活动
	 * @param B
	 *            用来存储的是最优解的开始时间
	 * @param E
	 *            用来存储的是最优解的结束时间
	 * @return 返回的是最优解的个数
	 */
	public static int solveActivityManager(int[] b, int[] e, int n, int[] B,
			int[] E) {
		int m = 0;
		// 解决该问题的思路如下：
		// 1.还是根据结束时间对n个活动进行递增排序
		// 2.然后找出最优解
		// 采用快速排序进行排序
		QuickSort(b, e, 0, b.length - 1);
		// 设置初始元素
		B[0] = b[0];
		E[0] = e[0];
		int j = 0;

		for (int i = 1; i < n; i++) {
			// 即该活动的开始时间大于前一个活动的结束时间
			if (b[i] > e[j]) {
				j = i;
				m++;
				B[m] = b[i];
				E[m] = e[i];
			} else {
				// 以下用来判断两个区间是否属于包含的关系，如果是则将当前的区间换成该区间包含在内的那个子区间
				if (b[j] < b[i] && e[j] >= e[i]) {
					B[m] = b[i];
					E[m] = e[i];
				}
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
	public static void QuickSort(int[] b, int[] e, int low, int high) {

		int i = low, j = high;
		// 快速排序的思路如下：
		// 1.首先假设第一个元素为初始元素。
		int temp = e[low];
		int end = b[low];
		// 2.然后找到该元素的位置
		while (i != j) {
			while (i < j && e[j] >= temp)
				j--;
			if (i < j) {
				e[i] = e[j];
				b[i] = b[j];
				i++;
			}
			while (i < j && e[i] < temp)
				i++;
			if (i < j) {
				e[j] = e[i];
				b[j] = b[i];
				j--;
			}
		}
		// 此时找到初始元的 位置。然后将其放入
		e[i] = temp;
		b[i] = end;
		// 3.然后开始在两个不同的子区间进行递归
		if (low < j) {
			QuickSort(b, e, low, j - 1);
		}
		if (j < high) {
			QuickSort(b, e, j + 1, high);
		}
	}

	/**
	 * 打印数组data中的n个元素
	 * 
	 * @param data
	 * @param n
	 */
	public static void display(int[] b, int[] e, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("[" + b[i] + "," + e[i] + ")\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// n个活动的开始时间
		int[] b = { 1, 3, 2, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
		// n个活动的结束时间
		int[] e = { 4, 5, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		System.out.println("排序前：");
		display(b, e, b.length);
		QuickSort(b, e, 0, b.length - 1);
		System.out.println("排序后：");
		display(b, e, b.length);
		int n = b.length;
		int[] B = new int[n];
		int[] E = new int[n];
		int m = solveActivityManager(b, e, n, B, E);
		System.out.println("求得的解总共有："+m+"个");
		System.out.println("解如下所示：");
		display(B, E, m);
	}
}
