package com.wxf.data.recursion;

import java.util.Arrays;

public class Sort {

	public Sort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * ��ѡ������ �ݹ� ��data �����е�n-i��Ԫ�ؽ������� ����selectSort2(int[] data,int n,int
	 * i)�Ǵ�����selectSort2(int[] data,int n,int i-1)��С���⣬��i=n-1ʱ������
	 * 
	 * @param data
	 * @param n
	 * @param i
	 * @return
	 * 
	 */
	public static void selectSort2(int[] data, int n, int i) {

		if (i == n - 1)
			return;
		else {
			int index = i;
			for (int j = i + 1; j < n; j++) {
				if (data[j] < data[index]) {
					index = j;
				}
			}
			if (index != i) {
				int temp = data[index];
				data[index] = data[i];
				data[i] = temp;
			}
			// ����ݹ�
			selectSort2(data, n, i + 1);
		}
	}

	/**
	 * ��ѡ������ �ǵݹ�
	 * 
	 * @param data
	 * @return
	 */
	public static int[] selectSort1(int[] data) {
		// ��ѡ�������˼·��
		// 1.����n�������бȽ�n-1�αȽϣ�ÿ���ҳ���С������Ȼ����͵����Ԫ�ؽ��н���
		// 2.���ѭ����n-1��
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			// ÿ�μٶ���i����Ϊ��С����
			int min = data[i];
			int index = 0;
			for (int j = i + 1; j < n; j++) {
				if (data[j] < min) {
					min = data[j];
					index = j;
				}
			}
			if (min != data[i]) {
				int temp = data[i];
				data[i] = min;
				data[index] = temp;
			}
		}
		return data;
	}

	// public static int[] bubbleSort2(int data,)

	public static int[] quickSort(int[] data, int low, int high) {
		// ���������˼·���£�
		// 1�����ȸ��㷨��һ���ݹ���㷨
		// 2���ٶ�������ĵ�һ��Ԫ��Ϊ��ʼֵ��Ȼ�󲻶ϵ�������������һ��Ԫ����ǰ���ң��ҵ�����С������н���λ��
		// 3.Ȼ���ִ�ǰ�����ң��ҵ������ģ��ڽ��н���λ��
		// 4.ֱ����ǰ����ʹӺ���ǰ������һ��
		// 5.���ĵ���Ч���Ǹ���ǰ���Ԫ�ض�����С�����������Ԫ�ض������
		// 6.Ȼ��ʼ�����������������н��еݹ�

		int temp = data[low];
		int i = low, j = high;
		while (i < j) {
			while (i < j && temp < data[j])
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && temp > data[i]) {
				i++;
			}
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		data[i] = temp;
		// ������еݹ�
		if (low < i)
			quickSort(data, low, i - 1);
		if (i < high)
			quickSort(data, i + 1, high);
		return data;
	}

	/**
	 * 
	 * ð������ �ǵݹ�
	 * 
	 * @param data
	 * @return
	 */
	public static int[] bubbleSort1(int[] data) {

		// ð�������˼·���£�
		// 1.����ð�������ǽ�������֮�������ÿ���ҵ�����ֵȻ��ŵ���������һ��λ����ȥ
		// 2.���ѭ����Ҫ����n-1��
		// 3.�ڲ�ѭ����Ҫ����n-i��
		// 4.Ϊ����������Ч���趨��һ����־λ
		int n = data.length;
		boolean flag = false;
		for (int i = 1; i < n; i++) {
			flag = false;
			for (int j = 0; j < n - i; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = true;
				}
			}
			if (!flag) {
				break;
			}
		}
		return data;
	}

	/**
	 * 
	 * ��ѡ������ �ݹ� ��data �����е�n-i��Ԫ�ؽ������� ����selectSort2(int[] data,int n,int
	 * i)�Ǵ�����selectSort2(int[] data,int n,int i-1)��С���⣬��i=n-1ʱ������
	 * 
	 * @param data
	 * @param n
	 * @param i
	 * @return
	 * 
	 */
	public static void bubbleSort2(int[] data, int n, int i) {

		if (i != n - 1) {
			// ð������ݹ��㷨˼·���£�
			// 1.������Ҫ�����data��n-i��Ԫ�ص�����
			// 2.Ȼ���ٽ�����n-i+1��Ԫ���еݹ�ִ��
			boolean flag = false;
			for (int j = 0; j < n - i - 1; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = true;
				}
			}
			if (!flag)
				return;
			else {
				bubbleSort2(data, n, i + 1);
			}
		}
	}

	/**
	 * �������� �ǵݹ�
	 * 
	 * @param data
	 * @return
	 */
	public static int[] insertSort(int[] data) {
		// ���������˼·���£�
		// 1.����ÿ�δ��ҵ�Ҫ�����Ԫ��
		// 2.Ȼ�����ǰ��������Ԫ�����ν��бȽ�
		// 3.�ҵ����ʵ�λ�ã����в���
		int n = data.length;
		for (int i = 1; i < n; i++) {
			// �������Ԫ��
			int el = data[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (data[j] < el) {
					break;
				} else {
					data[j + 1] = data[j];
				}
			}
			data[j + 1] = el;
		}
		return data;
	}

	/**
	 * ��������ݹ鷨
	 * 
	 * @param data
	 * @param n
	 * @param i
	 */
	public static void insertSort2(int[] data, int n, int i) {
		if (i == n - 1)
			return;
		else {
			int el = data[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (data[j] < el) {
					break;
				} else {
					data[j + 1] = data[j];
				}
			}
			data[j + 1] = el;
			insertSort2(data, n, i + 1);
		}
	}
	

	public static void main(String[] args) {
		int[] data = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data1 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data2 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data3 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data4 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		int[] data5 = new int[] { 1, 43, 6, 7, 4, 23, 7, 87, 34, 44, 5 };
		System.out.println("ѡ������");
		System.out.println(Arrays.toString(selectSort1(data)));
		System.out.println("��������");
		System.out.println(Arrays
				.toString(quickSort(data1, 0, data.length - 1)));
		System.out.println("ð������");
		System.out.println(Arrays.toString(bubbleSort1(data2)));

		System.out.println("��ѡ�����򣺵ݹ�ⷨ");
		selectSort2(data3, data3.length, 0);
		System.out.println(Arrays.toString(data3));
		System.out.println("ð�����򣺵ݹ��㷨");
		System.out.println(Arrays.toString(data4));
		bubbleSort2(data4, data4.length, 0);
		System.out.println(Arrays.toString(data4));
		System.out.println("��������");
		System.out.println(Arrays.toString(data5));
		// System.out.println(Arrays.toString(insertSort(data5)));
		insertSort2(data5, data5.length + 1, 0);
		System.out.println(Arrays.toString(data5));
	}

}
