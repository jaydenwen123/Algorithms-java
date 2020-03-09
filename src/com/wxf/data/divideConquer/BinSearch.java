package com.wxf.data.divideConquer;

import java.util.Arrays;

public class BinSearch {

	public BinSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ֲ��� �ݹ�ʵ��
	 * 
	 * @param data
	 * @param el
	 * @param low
	 * @param high
	 * @return
	 */
	public static int binSearch(int[] data, int el, int low, int high) {
		int index = -1;
		if (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == el)
				return mid;
			else if (data[mid] < el) {
				return binSearch(data, el, mid + 1, high);
			} else {
				return binSearch(data, el, low, mid - 1);
			}

		}
		return index;
	}

	/**
	 * ���ֲ��� �ǵݹ�ʵ��
	 * 
	 * @param data
	 * @param el
	 * @return
	 */
	public static int binSearch(int[] data, int el) {
		int index = -1;
		int low = 0;
		int high = data.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (data[mid] == el)
				return mid;
			else if (data[mid] < el) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return index;
	}

	public static void main(String[] args) {

		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("Ҫ���ҵ�����Ϊ��");
		System.out.println(Arrays.toString(data));
		System.out.println("���ֲ��ҵݹ鷽��ʵ�֣�");
		System.out.println("Ԫ��7�������е�λ��Ϊ��"+binSearch(data, 7, 0, data.length - 1));
		System.out.println("Ԫ��19�������е�λ��Ϊ��"+binSearch(data, 19, 0, data.length - 1));
		System.out.println("���ֲ��ҷǵݹ�ѭ���ṹʵ�֣�");
		System.out.println("Ԫ��7�������е��±�Ϊ��"+binSearch(data, 7));
		System.out.println("Ԫ��19�������е��±�Ϊ��"+binSearch(data, 19));
	}
}
