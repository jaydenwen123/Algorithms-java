package com.wxf.data.divideConquer;

import java.util.Arrays;

public class MergeSort {

	public MergeSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �鲢����
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] mergeSort(int[] data, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(data, mid + 1, high);
			mergeSort(data, low, mid);
			merge(data, low, mid, high);
		}
		return data;
	}

	/**
	 * �ϲ��������������Ϊһ�����������
	 * 
	 * @param data
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(int[] data, int low, int mid, int high) {
		// �鲢�����˼·���£�
		// 1.��ѡ�ǵݹ����������飬ͨ��һ���м������ָ���������
		// 2.�ϲ��������������Ϊһ����������飬�ϲ���˼·���£�
		// a���������������������ͨ��һ���м���mid�����ݡ�
		// b��ͨ�����ϵĶ������������е�ÿ�������бȽϣ�ÿ��ѡ����С����
		// c.Ȼ��֪������ĳ�������е�����ȫ���Ƚ���
		// d.�ڶ������������Ƿ�Ϊ���жϣ������Ϊ�գ���ֱ�ӽ��������е����ݸ��Ƶ���������
		// e.����㷨����

		// �����Ѹ�������������������������Ԫ��
		int[] newArr = new int[high - low + 1];
		int i = low, j = mid + 1;
		int k = 0;
		while (i <= mid && j <= high) {
			if (data[i] < data[j]) {
				newArr[k++] = data[i++];
			} else {
				newArr[k++] = data[j++];
			}
		}
		// һ������ѭ��ֻ��ִ��һ�Σ������ж��Ƿ��������������Ƿ��п�������ݣ�
		// �����Ȼ����ȫ�����Ƶ���������
		while (i <= mid) {
			newArr[k++] = data[i++];
		}
		while (j <= high) {
			newArr[k++] = data[j++];
		}
		// ��������Ŀ�������
		System.arraycopy(newArr, 0, data, low, newArr.length);
	}

	public static void main(String[] args) {

		int[] data = { 2, 5, 1, 7, 10, 6, 9, 4, 3, 8 };
		System.out.println("����ǰ��");
		System.out.println(Arrays.toString(data));
		System.out.println("�����");
		System.out
				.println(Arrays.toString(mergeSort(data, 0, data.length - 1)));
	}

}
