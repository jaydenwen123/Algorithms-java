package com.wxf.data.recursion;

public class BinSearch {

	public BinSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �ݹ���ƶ��ֲ���
	 * 
	 * @param data
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binSearch(int data, int[] arr, int low, int high) {
		int i = 0;
		int mid = (low + high) / 2;
		// �ݹ����ĳ���
		if (low <= high) {
			if (data == arr[mid])
				return mid;
			else if (data < arr[mid])
				return binSearch(data, arr, low, mid - 1);
			else {
				return binSearch(data, arr, mid + 1, high);
			}
		}
		return -1;

	}

	/**
	 * ���ֲ��ҵķǵݹ���Ƶ��㷨
	 * 
	 * @param data
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binSearch2(int data, int[] arr, int low, int high) {
		int i = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == data)
				return mid;
			else if (arr[mid] < data)
				low = mid + 1;
			else {
				high = mid - 1;
			}
		}
		return i;
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 56, 65, 233 };
		int i = binSearch2(56, arr, 0, arr.length - 1);
		System.out.println(i);
	}
}
