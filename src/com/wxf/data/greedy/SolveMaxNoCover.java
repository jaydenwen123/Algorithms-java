package com.wxf.data.greedy;

public class SolveMaxNoCover {

	public SolveMaxNoCover() {
		// TODO Auto-generated constructor stub
	}

	public static class Interval {
		public int left;
		public int right;

		public Interval() {
			// TODO Auto-generated constructor stub
		}

		public Interval(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "[" + left + "," + right + ")";
		}

	}

	/**
	 * 
	 * 求解最大不相交区间问题
	 * 
	 * @return
	 */
	public static int solveMaxNoCover(Interval[] data, Interval[] result) {
		int m = 0;
		// 关于最大不相交区间问题的思路如下：
		// 1.首先对n个区间根据其右端点进行递增排序，这里排序采用的是快速排序
		// 2、然后进行查找，如果下一个区间的左端点大于该区间的右端点，则说明该区
		// 间包含在下一个区间中，因此舍掉再找下一个，直到找到不相交的区间时，将其
		// 保存到result中，同时m加1。直到data中的所有数据遍历完为止

		// 先排序,采用快速排序
		QuickSort(data, 0, data.length - 1);
		// 初始化
		Interval temp = data[0];
		result[0] = temp;
		// 在进行查找
		for (int i = 1; i < data.length; i++) {
			if (data[i].left > temp.right) {
				m++;
				result[m] = data[i];
				temp = data[i];

			} else {
				// 以下表示的判断前后两个区间是否包含的关系
				if (temp.left < data[i].left && temp.right <= data[i].right) {
					temp = data[i];
					result[m] = temp;

				}
			}
		}
		return m + 1;

	}

	/**
	 * 快速排序,根据区间的右端点进行排序
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void QuickSort(Interval[] data, int low, int high) {

		int i = low, j = high;
		// 快速排序的思路如下：
		// 1.首先假设第一个元素为初始元素。
		Interval temp = data[low];
		// 2.然后找到该元素的位置
		while (i != j) {
			while (i < j && data[j].right >= temp.right)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i].right < temp.right)
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

	/**
	 * 输出数组中的值
	 * 
	 * @param data
	 * @param n
	 */
	public static void display(Interval[] data, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Interval[] data = { new Interval(2, 6), new Interval(1, 4),
				new Interval(3, 6), new Interval(3, 4), new Interval(3, 7),
				new Interval(6, 8), new Interval(2, 4), new Interval(0, 4),
				new Interval(3, 5), };
		// System.out.println("排序前：");
		// display(data, data.length);
		// QuickSort(data, 0, data.length - 1);
		// System.out.println("排序后：");
		// display(data, data.length);
		 
		Interval[] result = new Interval[data.length];
		int m = solveMaxNoCover(data, result);
		System.out.println("选取的区间为："+m);
		System.out.println("选取的区间为：");
		display(result, m);

	}
}
