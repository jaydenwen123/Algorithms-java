package com.wxf.data.sort;

import java.util.Arrays;

public class RadixSort {

	public static int[] radixSort(int[] data, int d) {
		// n����λ����Ӧ������1,10,100
		int n = 1;
		int k = 0;
		int length = data.length;
		int[][] bucket = new int[10][length];
		int[] order = new int[length];
		while (n < d) {
			for (int num : data) {
				int digit = (num / n) % 10;
				bucket[digit][order[digit]] = num;
				order[digit]++;

			}
			for (int i = 0; i < length; i++) {
				if (order[i] != 0) {
					for (int j = 0; j < order[i]; j++) {
						data[k] = bucket[i][j];
						k++;
					}
				}
				order[i] = 0;
			}
			n *= 10;
			k = 0;
		}
		return data;
	}

	private static void radixSort2(int[] array, int d) {
		int n = 1;// ����λ����Ӧ������1,10,100...
		int k = 0;// ����ÿһλ�����Ľ��������һλ����������
		int length = array.length;
		int[][] bucket = new int[10][length];// ����Ͱ���ڱ���ÿ�������Ľ������һλ����������ͬ�����ַ���ͬһ��Ͱ��
		int[] order = new int[length];// ���ڱ���ÿ��Ͱ���ж��ٸ�����
		while (n < d) {
			for (int num : array) // ������array���ÿ�����ַ�����Ӧ��Ͱ��
			{
				int digit = (num / n) % 10;
				bucket[digit][order[digit]] = num;
				order[digit]++;
			}
			for (int i = 0; i < length; i++)// ��ǰһ��ѭ�����ɵ�Ͱ������ݸ��ǵ�ԭ���������ڱ�����һλ��������
			{
				if (order[i] != 0)// ���Ͱ�������ݣ����ϵ��±������Ͱ�������ݱ��浽ԭ������
				{
					for (int j = 0; j < order[i]; j++) {
						array[k] = bucket[i][j];
						k++;
					}
				}
				order[i] = 0;// ��Ͱ���������0��������һ��λ����
			}
			n *= 10;
			k = 0;// ��k��0��������һ�ֱ���λ������
		}

	}

	public static void main(String[] args) {
		int[] data = new int[] { 73, 22, 54, 51, 87, 35, 43, 56, 72 };
		System.out.println("����ǰ�����飺");
		System.out.println(Arrays.toString(data));
		System.out.println("��ʼ����");
		radixSort2(data, 100);
		System.out.println("����������Ԫ�أ�");
		System.out.println(Arrays.toString(data));

	}

}
