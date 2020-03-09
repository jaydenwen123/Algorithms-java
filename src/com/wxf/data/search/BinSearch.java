package com.wxf.data.search;


public class BinSearch {

	public BinSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过循环的方式来设计
	 * 
	 * @param data
	 *            要查找的数组
	 * @param element
	 *            要进行查找的元素
	 * @return 返回该元素在数组中的下表，若果返回为-1则表示没有找到，查找失败
	 */
	public static int binSearch1(int[] data, int element) {
		int index = -1;
		int n = data.length;
		// 将最小值data[0]付给low
		int low = 0;
		// 将最大值data[n-1]赋值给high变量
		int high = n - 1;
		while (low <= high) {
			// 求得二者的中间值
			int mid = (low + high) / 2;
			if (data[mid] < element)
				low = mid + 1;
			else if (data[mid] > element)
				high = mid - 1;
			else {
				return mid;
			}
		}
		return index;
	}

	/**
	 * 通过递归的思想来设计二分查找算法
	 * 
	 * @param data
	 *            要查找的数组
	 * @param element
	 *            查找的元素
	 * @param low
	 *            初始时的最小值
	 * @param high
	 *            初始时的最大值
	 * @return 返回查找到的元素的下标，返回-1表示没有找到该元素
	 */
	public static int binSearch2(int[] data, int element, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high) / 2;
		// 如果该元素正好等于对应的中间元素的值，则直接记性返回
		if (data[mid] == element)
			return mid;
		// 如果该元素大于中间元素的值，则在上半区进行查找，否在在下半区进行查找
		if (element > data[mid]) {
			return binSearch2(data, element, mid + 1, high);
		} else {
			return binSearch2(data, element, low, mid - 1);
		}
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4, 6, 43, 56 };
		System.out
				.println(parseResult(binSearch2(data, 6, 0, data.length - 1)));
	}

	/**
	 * 通过返回的查找的下标，来进行分析结果
	 * 
	 * @param i
	 * @return
	 */
	private static String parseResult(int i) {
		String result = i == -1 ? "查找失败，没有找到该元素" : "查找成功，该元素对应的数组中的下表为：" + i;
		return result;
	}

}
