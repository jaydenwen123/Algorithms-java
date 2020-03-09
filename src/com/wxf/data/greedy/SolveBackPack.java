package com.wxf.data.greedy;

import java.util.Arrays;

public class SolveBackPack {

	public SolveBackPack() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �����������
	 * 
	 * @param w
	 *            ��ͬ��Ʒ������
	 * @param v
	 *            ��ͬ��Ʒ�ļ۸�
	 * @param W
	 *            �������
	 * @return ���ص����Ž�
	 */
	public static int solveBackPack(int[] w, int[] v, int n, int W,
			double[] result) {
		// ͳ�����Ž������Ԫ��
		int count = 0;
		// ��ⱳ�������˼·���£�
		// 1������ͨ��w��v���㵥λ������ֵ
		// 2��Ȼ��Ե�λ������ֵ���еݼ�����
		// 3����ʼ���ɨ�������Ž�
		double data[] = new double[n];
		for (int i = 0; i < n; i++) {
			// ȡС�������λ
			data[i] = v[i] * 100 / (w[i] * 100.0);
		}
		// �ݼ�����
		QuickSort(data, 0, data.length - 1, w, v);
		// ��ʼ���ɨ��
		int weight = W;
		// �ý���ĵ�һλ����������ܵļ�ֵ
		result[0] = 0;
		for (int i = 0; i < n; i++) {
			// �����ǰ��Ʒ������С��Weight���������
			if (w[i] <= weight) {
				weight = weight - w[i];
				count++;
				result[0] += v[i];
				result[count] = 1;
			} else {
				// ���������ʾ���ǣ���ǰ��ʣ����������Ҫ�������Ʒ��������ֻ�ܽ���һ���֣����뵽������
				if (weight > 0) {
					// ��ʾʣ�������ռ����Ʒ�����ı���
					double x = weight * 100 / (w[i] * 100.0);
					result[0] += v[i] * x;
					count++;
					result[count] = x;
					break;
				}

			}
		}
		return count;
	}

	/**
	 * 
	 * �������򣬵ݼ�����
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void QuickSort(double[] data, int low, int high, int[] w,
			int[] v) {

		// �������򡣶�double���͵����ݽ��еݼ�����
		int i = low, j = high;
		double temp = data[low];
		int wei = w[low];
		int val = v[low];
		while (i != j) {
			while (i < j && data[j] < temp) {
				j--;
			}
			if (i < j) {
				data[i] = data[j];
				w[i] = w[j];
				v[i] = v[j];
				i++;
			}
			while (i < j && data[i] >= temp) {
				i++;
			}
			if (i < j) {
				data[j] = data[i];
				w[j] = w[i];
				v[j] = v[i];
				j--;
			}

		}
		data[i] = temp;
		w[i] = wei;
		v[i] = val;
		if (low < i)
			QuickSort(data, low, i - 1, w, v);
		if (i < high)
			QuickSort(data, i + 1, high, w, v);
	}

	/**
	 * ��ʾ���
	 * 
	 * @param result
	 * @param n
	 */
	public static void display(double[] result, int n) {

		for (int i = 1; i <= n; i++) {
			System.out.print(result[i] + "\t");
		}
		System.out.println();

	}

	public static void display(int[] result, int n) {

		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + "\t");
		}
		System.out.println();

	}

	public static void main(String[] args) {

		// ��Ӧ�Ĳ�ͬ��Ʒ ������
		int w[] = { 10, 20, 30, 40, 50 };
		// ��Ӧ��ͬ��Ʒ�ļ�ֵ
		int v[] = { 20, 30, 66, 40, 60 };
		// ��Ӧ�ı���������������
		int W = 100;
		// ��Ʒ����
		int n = v.length;
		// �����������洢���Ž������ֵ�����е�0��Ԫ�ش�ŵ������ļ�ֵ���������Ԫ�ش�����Ž�
		double result[] = new double[n + 1];
		// ����������⣬�������Ž������Ԫ�صĸ���
		int count = solveBackPack(w, v, n, W, result);
		System.out.println("��õ����Ž����£�");
		System.out.println("�Ե�λ������ֵv/w�ݼ��������");
		// ��ʾ���
		System.out.print("i:\t");
		for (int i = 0; i < n; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.print("v:\t");
		display(v, n);
		System.out.print("w:\t");
		display(w, n);
		System.out.print("x:\t");
		display(result, count);
		System.out.println("��õ�����ֵΪ��" + result[0]);
	}
}
