package com.wxf.data.divideConquer;

public class BinaryFind {

	public BinaryFind() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过分治法来解决data[i]=i问题
	 * 
	 * 该问题类似于二分查找的思路
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binaryFind(int[] data, int low, int high) {
		int index = -1;
		if (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == mid)
				return mid;
			else if (data[mid] < mid) {
				return binaryFind(data, mid + 1, high);
			} else {
				return binaryFind(data, low, mid - 1);
			}
		}
		return index;
	}

	public static void main(String[] args) {
		int[] data = { -1, 0, 1, 2, 4, 7, 9, 11, 15, 34 };
		System.out.println(binaryFind(data, 0, data.length - 1));
	}
}
