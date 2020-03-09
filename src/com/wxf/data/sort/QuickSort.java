package com.wxf.data.sort;

import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	// Ҫ���������
	private int[] data;
	// �������Ԫ�صĸ���
	private int length;

	public QuickSort(int[] data) {
		super();
		this.data = data;
	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @return �����������Ҫ�㷨��
	 */
	public static int[] quickSort1(int[] data, int low, int high) {
		int i = low, j = high;
		int temp = data[low];
		while (i < j) {
			while (i < j && temp <= data[j])
				j--;
			// �����if�����Ҫ�����ж������while�˳�ѭ����������
			/**
			 * ��Ϊwhile�˳�ѭ�������������һ����i>=j һ����temp>data[j]
			 * ���������if�����Ҫ��������˳�ѭ���ĵڶ������
			 */
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && temp > data[i])
				i++;
			// ����Ĵ��뺬�������һ����
			if (i < j) {
				data[j] = data[i];
				j--;

			}

		}
		data[i] = temp;
		// �ݹ飬�������������������
		/**
		 * һ���Ƕ�������������еݹ顣
		 */
		if (low < i)
			quickSort1(data, low, i - 1);
		if (j < high)
			quickSort1(data, j + 1, high);
		return data;

	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @return ����ֵΪ���յĲ����׼Ԫ�ص�λ�� �����������Ҫ�㷨��
	 */
	public static int getCriteriaIndex(int[] data, int low, int high) {
		int i = low, j = high;
		int temp = data[low];
		while (i < j) {
			while (i < j && temp <= data[j])
				j--;
			// �����if�����Ҫ�����ж������while�˳�ѭ����������
			/**
			 * ��Ϊwhile�˳�ѭ�������������һ����i>=j һ����temp>data[j]
			 * ���������if�����Ҫ��������˳�ѭ���ĵڶ������
			 */
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && temp > data[i])
				i++;
			// ����Ĵ��뺬�������һ����
			if (i < j) {
				data[j] = data[i];
				j--;

			}

		}
		data[i] = temp;
		return i;

	}

	/**
	 * @param data
	 * @param low
	 * @param high
	 * @return ����ֵΪ����õ����� �ݹ���η��Ĵ��롣
	 */
	public static int[] quickSort2(int[] data, int low, int high) {
		if (low < high) {
			int i = getCriteriaIndex(data, low, high);
			quickSort2(data, low, i - 1);
			quickSort2(data, i + 1, high);
		}
		return data;
	}

	public static void main(String[] args) {

		int[] data = new int[] { 60, 55, 48, 37, 10, 90, 84, 36, 45, 76, 7 };
		int[] data1 = new int[] { 60, 55, 48, 37, 10, 90, 84, 36, 45, 76, 7 };
		System.out.println(data.length);
		Date date1 = new Date(System.nanoTime());
		System.out.println("δ��������飺");
		System.out.println(Arrays.toString(data));
		data = QuickSort.quickSort2(data, 0, data.length - 1);
		Date date2 = new Date(System.nanoTime());
		System.out.println("���������飺");
		System.out.println(Arrays.toString(data));
		System.out.println("������Ҫ��ʱ�䣺");
		System.out
				.println(((float) (date2.getTime() - date1.getTime()) / 1000000));

		/*
		 DubbleSort dubbleSort = new DubbleSort(data1); Date date3 = new
		 Date(System.nanoTime()); System.out.println("δ��������飺");
		 System.out.println(Arrays.toString(data1)); data1 =
		 dubbleSort.dubbleSort1(); Date date4 = new Date(System.nanoTime());
		 System.out.println("���������飺");
		 System.out.println(Arrays.toString(data1));
		 System.out.println("������Ҫ��ʱ�䣺"); System.out .println(((float)
		 (date4.getTime() - date3.getTime()) / 1000000));
		 */
	}
}
