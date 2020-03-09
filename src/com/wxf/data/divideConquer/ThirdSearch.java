package com.wxf.data.divideConquer;


public class ThirdSearch {

	public ThirdSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 三分查找方法，通过分治法来实现
	 * 
	 * 如果返回-1表示的是未找到
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @param element
	 * @return
	 */
	public static int thirdSearch(int[] data, int low, int high, int element) {
		int index = -1;
		if (low <= high) {
			int mid1 = (high - low) / 3 + low;
			int mid2 = high - (high - low) / 3;
			if (data[mid1] == element)
				return mid1;
			if (element == data[mid2])
				return mid2;
			if (element < data[mid1])
				return thirdSearch(data, low, mid1 - 1, element);
			else {

				if (element < data[mid2]) {
					return thirdSearch(data, mid1 + 1, mid2 - 1, element);
				} else {
					return thirdSearch(data, mid2 + 1, high, element);
				}
			}
		}
		return index;
	}

	/**
	 * 二分查找，分治算法
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @param element
	 * @return
	 */
	public static int binearySearch(int[] data, int low, int high, int element) {
		int index = -1;
		if (low <= high) {
			int mid = (high - low) / 2 + low;
			if (data[mid] == element)
				return mid;
			else if (data[mid] < element) {
				return binearySearch(data, mid + 1, high, element);
			} else {
				return binearySearch(data, low, mid - 1, element);
			}
		}
		return index;
	}

	public static void main(String[] args) {
		int[] data = { 1, 3, 5, 7, 9, 12, 14, 17, 34, 42, 56, 67, 78, 89 };
		System.out.println("三分查找：");
		System.out.println(thirdSearch(data, 0, data.length - 1, 12));
		System.out.println("二分查找：");
		System.out.println(binearySearch(data, 0, data.length - 1, 12));
	}
}
