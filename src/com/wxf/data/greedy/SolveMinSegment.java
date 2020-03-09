package com.wxf.data.greedy;


public class SolveMinSegment {

	public SolveMinSegment() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ������串������
	 * 
	 * @param data
	 *            �����д洢����ֱ���ϵ�n���㡣��x����
	 * @param k
	 *            ��ʾ���ǹ̶������䳤��
	 * @return ���ص������ٵĹ̶����ȵ��������
	 */
	public static int solveMinSegment(int[] data, int k, int[] start) {
		// ����������串�������˼·���£�
		// 1.���ȶ�����data���е�������
		QuickSort(data, 0, data.length - 1);
		// 2.�����������С������
		// �ñ���������¼����ĸ���
		int m = 0;
		start[m] = data[0];
		// �����һ��Ԫ��Ϊ��һ������������
		int temp = data[0];
		for (int i = 1; i < data.length; i++) {
			if (data[i] - temp > k) {
				temp = data[i];
				m++;
				start[m] = temp;
			}
		}
		return m + 1;
	}

	/**
	 * ��������
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void QuickSort(int[] data, int low, int high) {

		int i = low, j = high;
		// ���������˼·���£�
		// 1.���ȼ����һ��Ԫ��Ϊ��ʼԪ�ء�
		int temp = data[low];
		// 2.Ȼ���ҵ���Ԫ�ص�λ��
		while (i != j) {
			while (i < j && data[j] >= temp)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] < temp)
				i++;
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		// ��ʱ�ҵ���ʼԪ�� λ�á�Ȼ�������
		data[i] = temp;
		// 3.Ȼ��ʼ��������ͬ����������еݹ�
		if (low < j) {
			QuickSort(data, low, j - 1);
		}
		if (j < high) {
			QuickSort(data, j + 1, high);
		}
	}

	public static void display(int[] data, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] data = { 2, 4, 1, 6, -2, 5, 3 };
		int[] start = new int[data.length];
		// System.out.println("����ǰ��");
		// System.out.println(Arrays.toString(data));
		// QuickSort(data, 0, data.length - 1);
		// System.out.println("�����");
		// System.out.println(Arrays.toString(data));

		System.out.println("�������");
		int m = solveMinSegment(data, 3, start);
		System.out.println("ѡȡ���������Ϊ��" + m);
		System.out.println("ѡȡ�����䣺");
		display(start, m);
	}

}
