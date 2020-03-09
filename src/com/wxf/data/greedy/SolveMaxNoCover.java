package com.wxf.data.greedy;

public class SolveMaxNoCover {

	public SolveMaxNoCover() {
		// TODO Auto-generated constructor stub
	}

	public static class Interval {
		public int left;
		public int right;

		public Interval() {
			// TODO Auto-generated constructor stub
		}

		public Interval(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return "[" + left + "," + right + ")";
		}

	}

	/**
	 * 
	 * �������ཻ��������
	 * 
	 * @return
	 */
	public static int solveMaxNoCover(Interval[] data, Interval[] result) {
		int m = 0;
		// ��������ཻ���������˼·���£�
		// 1.���ȶ�n������������Ҷ˵���е�����������������õ��ǿ�������
		// 2��Ȼ����в��ң������һ���������˵���ڸ�������Ҷ˵㣬��˵������
		// ���������һ�������У�������������һ����ֱ���ҵ����ཻ������ʱ������
		// ���浽result�У�ͬʱm��1��ֱ��data�е��������ݱ�����Ϊֹ

		// ������,���ÿ�������
		QuickSort(data, 0, data.length - 1);
		// ��ʼ��
		Interval temp = data[0];
		result[0] = temp;
		// �ڽ��в���
		for (int i = 1; i < data.length; i++) {
			if (data[i].left > temp.right) {
				m++;
				result[m] = data[i];
				temp = data[i];

			} else {
				// ���±�ʾ���ж�ǰ�����������Ƿ�����Ĺ�ϵ
				if (temp.left < data[i].left && temp.right <= data[i].right) {
					temp = data[i];
					result[m] = temp;

				}
			}
		}
		return m + 1;

	}

	/**
	 * ��������,����������Ҷ˵��������
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void QuickSort(Interval[] data, int low, int high) {

		int i = low, j = high;
		// ���������˼·���£�
		// 1.���ȼ����һ��Ԫ��Ϊ��ʼԪ�ء�
		Interval temp = data[low];
		// 2.Ȼ���ҵ���Ԫ�ص�λ��
		while (i != j) {
			while (i < j && data[j].right >= temp.right)
				j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i].right < temp.right)
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

	/**
	 * ��������е�ֵ
	 * 
	 * @param data
	 * @param n
	 */
	public static void display(Interval[] data, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Interval[] data = { new Interval(2, 6), new Interval(1, 4),
				new Interval(3, 6), new Interval(3, 4), new Interval(3, 7),
				new Interval(6, 8), new Interval(2, 4), new Interval(0, 4),
				new Interval(3, 5), };
		// System.out.println("����ǰ��");
		// display(data, data.length);
		// QuickSort(data, 0, data.length - 1);
		// System.out.println("�����");
		// display(data, data.length);
		 
		Interval[] result = new Interval[data.length];
		int m = solveMaxNoCover(data, result);
		System.out.println("ѡȡ������Ϊ��"+m);
		System.out.println("ѡȡ������Ϊ��");
		display(result, m);

	}
}
