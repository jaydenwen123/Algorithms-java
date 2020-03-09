package com.wxf.data.divideConquer;

import java.util.Arrays;

public class FindKElement {

	public FindKElement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Ѱ��һ�������е�kСԪ��
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @param k
	 * @return
	 */
	public static int findKPositionElement(int[] data, int low, int high, int k) {
		if (k < low && k > high)
			return -1;
		int i = FindInitElementPosition(data, low, high);
		// ���½��еݹ�
		if (i == k - 1) {
			return data[i];
		} else if (i < k - 1) {
			return findKPositionElement(data, i + 1, high, k);
		} else {
			return findKPositionElement(data, low, i - 1, k);
		}
	}

	/**
	 * ��������
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] QuickSort(int[] data, int low, int high) {
		int i = 0;
		if (low < high) {
			i = FindInitElementPosition(data, low, high);
			QuickSort(data, low, i - 1);
			QuickSort(data, i + 1, high);
		}
		return data;
	}

	/**
	 * ֪����ʼԪ�ص�λ��
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	private static int FindInitElementPosition(int[] data, int low, int high) {
		// Ѱ��һ������data�е�kСԪ��
		// 1.���ȶ�һ����ʼֵ���������еĵ�һ��Ԫ��
		// 2.ͨ�����������˼�룬�ҵ���Ԫ�غ��ʵ�λ��i
		// 3.Ȼ���ж�k��i��λ�ô�С
		// 4.Ȼ���پ�������ͬ��������еݹ�Ѱ��
		int i = low, j = high;
		int temp = data[low];
		while (i != j) {
			while (i < j && data[j] >= temp)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] <= temp)
				i++;
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// ����Ԫ�ز嵽���ʵ�λ��
		data[i] = temp;
		return i;
	}

	public static void main(String[] args) {
		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("���ҵ�3СԪ�أ�");
		System.out.println(findKPositionElement(data, 0, data.length - 1,
				4));
		for(int i=1;i<=10;i++){
			System.out.print("���ҵ�"+i+"СԪ�أ�");
			System.out.println(findKPositionElement(data, 0, data.length - 1,
					i));
		}
		System.out.println("���������Ľ����");
		System.out.println(Arrays.toString(QuickSort(data, 0, data.length-1)));
	}
}
