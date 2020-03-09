package com.wxf.data.greedy;

public class SolveAcitivtyManager {

	public SolveAcitivtyManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����������⣬��������Բ�����������˼�룬Ҳ���Բ����� �������������˼��Ļ����ο��߽�����ཻ�����������
	 * 
	 * �������������������ཻ�������⣬
	 * 
	 * @param b
	 *            �����洢����n����Ŀ�ʼʱ��
	 * @param e
	 *            �����洢����n����Ľ���ʱ��
	 * @param n
	 *            n��ʾ����n���
	 * @param B
	 *            �����洢�������Ž�Ŀ�ʼʱ��
	 * @param E
	 *            �����洢�������Ž�Ľ���ʱ��
	 * @return ���ص������Ž�ĸ���
	 */
	public static int solveActivityManager(int[] b, int[] e, int n, int[] B,
			int[] E) {
		int m = 0;
		// ����������˼·���£�
		// 1.���Ǹ��ݽ���ʱ���n������е�������
		// 2.Ȼ���ҳ����Ž�
		// ���ÿ��������������
		QuickSort(b, e, 0, b.length - 1);
		// ���ó�ʼԪ��
		B[0] = b[0];
		E[0] = e[0];
		int j = 0;

		for (int i = 1; i < n; i++) {
			// ���û�Ŀ�ʼʱ�����ǰһ����Ľ���ʱ��
			if (b[i] > e[j]) {
				j = i;
				m++;
				B[m] = b[i];
				E[m] = e[i];
			} else {
				// ���������ж����������Ƿ����ڰ����Ĺ�ϵ��������򽫵�ǰ�����任�ɸ�����������ڵ��Ǹ�������
				if (b[j] < b[i] && e[j] >= e[i]) {
					B[m] = b[i];
					E[m] = e[i];
				}
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
	public static void QuickSort(int[] b, int[] e, int low, int high) {

		int i = low, j = high;
		// ���������˼·���£�
		// 1.���ȼ����һ��Ԫ��Ϊ��ʼԪ�ء�
		int temp = e[low];
		int end = b[low];
		// 2.Ȼ���ҵ���Ԫ�ص�λ��
		while (i != j) {
			while (i < j && e[j] >= temp)
				j--;
			if (i < j) {
				e[i] = e[j];
				b[i] = b[j];
				i++;
			}
			while (i < j && e[i] < temp)
				i++;
			if (i < j) {
				e[j] = e[i];
				b[j] = b[i];
				j--;
			}
		}
		// ��ʱ�ҵ���ʼԪ�� λ�á�Ȼ�������
		e[i] = temp;
		b[i] = end;
		// 3.Ȼ��ʼ��������ͬ����������еݹ�
		if (low < j) {
			QuickSort(b, e, low, j - 1);
		}
		if (j < high) {
			QuickSort(b, e, j + 1, high);
		}
	}

	/**
	 * ��ӡ����data�е�n��Ԫ��
	 * 
	 * @param data
	 * @param n
	 */
	public static void display(int[] b, int[] e, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("[" + b[i] + "," + e[i] + ")\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// n����Ŀ�ʼʱ��
		int[] b = { 1, 3, 2, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
		// n����Ľ���ʱ��
		int[] e = { 4, 5, 4, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		System.out.println("����ǰ��");
		display(b, e, b.length);
		QuickSort(b, e, 0, b.length - 1);
		System.out.println("�����");
		display(b, e, b.length);
		int n = b.length;
		int[] B = new int[n];
		int[] E = new int[n];
		int m = solveActivityManager(b, e, n, B, E);
		System.out.println("��õĽ��ܹ��У�"+m+"��");
		System.out.println("��������ʾ��");
		display(B, E, m);
	}
}
