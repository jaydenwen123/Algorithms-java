package com.wxf.data.sort;

import java.util.Arrays;

public class MergeSort {

	public static int[] mergeSort(int[] data) {
		sort(data, 0, data.length - 1);
		return data;
	}

	/**
	 * �鲢������㷨
	 * 
	 * @param data
	 *            �����������
	 * @param left
	 *            �����Ҫ����ĵ�һ��Ԫ�ص��±�
	 * @param right
	 *            ������Ҫ��������һ��Ԫ�ص��±�
	 */
	public static void sort(int[] data, int left, int right) {
		if (left >= right)
			return;
		// ����õ��鲢����ķָ������������ֵ
		int center = (left + right) / 2;
		// ����ߵ�������еݹ�����
		sort(data, left, center);
		// ���ұߵ�������еݹ�����
		sort(data, center + 1, right);
		// ���ϲ�����������
		merge(data, left, center, right);
		System.out.println("oo:" + Arrays.toString(data));
	}

	/**
	 * �ϲ������Ѿ���������顣�ϲ���ĵ�����Ҳ����
	 * 
	 * @param data
	 *            Ҫ�ϲ�������
	 * @param left
	 *            ��һ����������±�
	 * @param center
	 *            ��һ����������һ��Ԫ�ص��±�
	 * @param right
	 *            �ڶ�����������һ��Ԫ�ص�С�꣬�ڶ�������ĵ�һ��Ԫ���±�Ϊcenter+1
	 */
	public static void merge(int[] data, int left, int center, int right) {
		int[] newData = new int[right - left + 1];
		int mid = center + 1;
		int i = 0, j = 0;
		// ���ж�����������Ƚϣ�ÿ�αȽϽ��������н�С��Ԫ�ش�ŵ���ʱ������newData��
		int temp = left;
		while (left <= center && mid <= right) {
			if (data[left] < data[mid]) {
				newData[i++] = data[left++];
			} else {
				newData[i++] = data[mid++];
			}
		}
		// ���������ѭ����������Ϊ��ȽϽ����ˣ�������������ѭ�������ж����ĸ���������ѭ��
		// Ȼ���ٰ�ʣ�����ȫ��׷�ӵ���ʱ�������С�
		while (mid <= right) {
			newData[i++] = data[mid++];
		}
		while (left <= center) {
			newData[i++] = data[left++];
		}
		/*
		 * while (j <= newData.length-1) { data[temp++] = newData[j++]; }
		 */
		System.arraycopy(newData, 0, data, temp, newData.length);
		// ����ٽ���ʱ�������е�ֵ������ԭ�����С�
		// System.out.println(Arrays.toString(newData));
		/*
		 * ����ķ�ʽҲ���� for(int j=0;j<newData.length;j++){ data[left++]=newData[j];
		 * }
		 */
	}

	public static void main(String[] args) {
		// int[] data = new int[] { 72, 73, 71, 23, 94, 16, 5, 68, 64 };
		int[] data = new int[] { 1, 54, 5, 87, 345, 4, 56, 122 };
		System.out.println("����ǰ�����飺");
		System.out.println(Arrays.toString(data));
		System.out.println("��ʼ����");
		data = mergeSort(data);
		System.out.println("����������Ԫ�أ�");
		System.out.println(Arrays.toString(data));

	}
}
