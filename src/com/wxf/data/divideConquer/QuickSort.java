package com.wxf.data.divideConquer;

import java.util.Arrays;

public class QuickSort {

	public QuickSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] quickSort(int[] data, int low, int high) {
		if (low < high) {
			int i = dividePosition(data, low, high);
			quickSort(data, i + 1, high);
			quickSort(data, low, i - 1);
		}
		return data;
	}

	/**
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	private static int dividePosition(int[] data, int low, int high) {

		// ����������㷨˼·���£�
		// 1.���Ƚ���һ��Ԫ����Ϊ��׼Ԫ��
		// 2��Ȼ��Ӹ���������һ��Ԫ��������ǰ��
		// 3.������ȸû�׼Ԫ�ش���һֱѭ������������Ԫ�ؽ��н���λ��
		// 4.����λ�ú�Ȼ���ǰ�����ң������������С�ڸû�׼Ԫ�أ���һֱѭ��ô
		// 5.��������������ڸû�׼�������н��н���������λ��
		// 6.���֪����ǰ����ʹӺ���ǰ���������˳�
		int i = low, j = high;
		int temp = data[low];
		while (i != j) {
			while (i < j && data[j] > temp)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] < temp) {
				i++;
			}
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// ѭ���˳�ʱ��i==j;
		data[i] = temp;
		return i;
	}

	public static void main(String[] args) {

		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("����ǰ��");
		System.out.println(Arrays.toString(data));
		System.out.println("�����");
		System.out
				.println(Arrays.toString(quickSort(data, 0, data.length - 1)));
	}
}
