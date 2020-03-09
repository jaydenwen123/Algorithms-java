package com.wxf.data.sort;

import java.util.Arrays;

public class InsertSort {

	public InsertSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 *            �����������
	 * @return ��������� ֱ�Ӳ��������㷨�� �÷���ͨ������ѭ����ʵ�֡� ���ѭ��ʹ��forѭ�����ڲ�Ƕ��һ��whileѭ��
	 */
	public static int[] insertSort1(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// ����Ҫ�����Ԫ��ֵ����Ϊ�����п���Ҫ���к��ƵĲ�������������棬������ֵ���ǵ����⡣
			int temp = data[i + 1];
			int j = i;
			// ͨ��whileѭ�����ж����a[j+1]λ�õ�Ԫ��С����ǰ���Ԫ�أ�����ǰ���Ԫ�ؽ��к��ƣ��ճ�һ��λ�á�
			while (j > -1 && temp <= data[j]) {
				data[j + 1] = data[j];
				j--;
			}
			// ����������whileѭ����֤��������ʱ���ơ���񡣶���ִ�������Ĳ���
			// ��Ϊ:
			// 1.��������������ĺ��Ʋ�������j+1��ֵӦ��ΪҪ����ĸ�Ԫ�ص�λ�á�
			// 2.���������������ѭ�����������Ԫ�ر�ǰ����������������λ�þ�Ӧ��Ϊj+1
			data[j + 1] = temp;
		}
		return data;
	}

	/**
	 * @param data
	 *            �����������
	 * @return �������������� ֱ�Ӳ������򷽷�2 ������������Ҳ�ǲ�������ѭ���� ������forѭ�� �ڲ�Ƕ�׵���ȻΪforѭ��
	 */
	public static int[] insertSort2(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// ���浱ǰҪ�����ֵ
			int temp = data[i + 1];
			int j = i;
			// ���ڲ�ѭ����Ҫ�������и�Ԫ�غ���ǰ���Ѿ������������бȽ�
			for (; j >= 0; j++) {
				if (data[j] >= temp) {
					data[j + 1] = data[j];
					j--;
				} else {
					break;
				}
			}
			// ����ڸ�λ�ò���Ԫ��
			data[j + 1] = temp;
		}
		return data;
	}

	/**
	 * @param array
	 *            ����ǰ������
	 * @return ����ֵΪ���������� �÷������ͨ��forѭ����ִ�� ��ѭ��ͨ��whileѭ�������жϺͱȽ�
	 */
	private static int[] InsertSort3(int[] array) {
		for (int i = 1; i < array.length; i++) {

			int key = array[i];
			int index = i;
			// ��ǰ��������б�����ֻ��Ҫ�ҳ��ȵ�ǰ����С�����������˳�ѭ��
			// һ�����������㣬���˳�ѭ��
			while (index > 0 && array[index - 1] > key) {
				// ��������Ӧ���ǣ�����ǰ������Ҫ���������ʱ������������ƶ�
				array[index] = array[index - 1];
				index--;
			}
			// ��ѭ������ʱ����ζ���Ѿ��ҵ��ͺ��ʵ�λ�ã�Ȼ���ٽ������
			array[index] = key;
		}
		return array;
	}

	/**
	 * Ŀǰֻ�����ε������������
	 * 
	 * @param array����ΪҪ���������
	 * @return ����ֵΪ���������� ͨ������forѭ����ʵ��
	 */
	private static int[] InsertSort4(int[] array) {
		int key = 0;
		for (int i = 1; i < array.length; i++) {
			key = array[i];
			int j = i;
			for (j = i; j > 0; j--) {
				// ����Ѿ��ź���������е�Ԫ�ش���Ҫ�����Ԫ�أ�����н�����
				if (array[j - 1] > key) {
					// ����ǰλ�õ�Ԫ�������� array[j+1]=array[j];
					array[j] = array[j - 1];
				} else {
					// ���������continue��ԭ����ǰ��������Ѿ����ź����ֻ��Ҫ��ǰ�����ң��ҵ������ʵ�λ��
					// ������Ҫ���������
					break;
				}
			}
			array[j] = key;
		}
		return array;
	}

	public static void main(String[] args) {

		int[] data = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		int[] data2 = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		int[] data3 = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		int[] data4 = new int[] { 2, 45, 4, 657, 423, 4, 123, 34 };
		System.out.println("ʹ�÷���һ��������");
		System.out.println("����ǰ�����飺\n" + Arrays.toString(data));
		data = insertSort1(data);
		System.out.println("���������飺\n" + Arrays.toString(data));
		System.out.println("ʹ�÷�������������");
		System.out.println("����ǰ�����飺\n" + Arrays.toString(data2));
		data2 = insertSort1(data2);
		System.out.println("���������飺\n" + Arrays.toString(data2));
		System.out.println("ʹ�÷���������");
		System.out.println("����ǰ�����飺\n" + Arrays.toString(data3));
		data3 = insertSort1(data3);
		System.out.println("���������飺\n" + Arrays.toString(data3));
		System.out.println("ʹ�÷����Ľ�������");
		System.out.println("����ǰ�����飺\n" + Arrays.toString(data4));
		data4 = insertSort1(data4);
		System.out.println("���������飺\n" + Arrays.toString(data4));
	}

	/*
	 * public static void main(String[] args) { // int[] array = new int[] { 56,
	 * 57, 43, 2, 4, 12, 567, 87, 9, 98, 5, 43, 423, 123, 12, 312, 3, 2, 5, 23,
	 * 34, 546, 7, 899, 0, 64, 23, 45, 67, 8, 9, 4, 6, 67, 8, 23, 97, 11, 234,
	 * 90, 234, 2, 345, 46, 567, 57, 21, 343, 546, 63, 2, 1, 454, 2, 1, 1, 56,
	 * 87, 3, 2343, 546, 567, 687, 34, 54, 534, 45, 6, 57, 657, 6, 44, 234, 454,
	 * 667, 658, 7698, 9, 234, 242, 24, 2354, 365, 5467, 234, 56, 5, 767, 8789,
	 * 789, 90, 9, 898, 234, 35, 436, 54, 7, 234, 54, 4, 65, 76, 687, 8, 79,
	 * 546, 4, 53, 3, 53, 34, 2, 42, 42, 42, 23, 4, 234, 3, 6, 5, 67, 1, 24,
	 * 234, 6, 567, 46, 5, 68, 65, 7, 67 }; System.out.println("primitive data:"
	 * + Arrays.toString(array)); Date time1 = new Date(System.nanoTime()); //
	 * nanoTime() // array = InsertSort3(array); // array = InsertSort4(array);
	 * Date time2 = new Date(System.nanoTime()); System.out.println("sort data:"
	 * + Arrays.toString(array)); SimpleDateFormat format = new
	 * SimpleDateFormat("hh:mm:ss"); System.out.println("length:" +
	 * array.length); // System.out.println("�����ʱ�䣺" + format.format(time1)); //
	 * System.out.println("�����ʱ�䣺" + format.format(time2));
	 * System.out.println("sssws:" + (float) (time2.getTime() - time1.getTime())
	 * / 1000000);
	 * 
	 * }
	 */

}
