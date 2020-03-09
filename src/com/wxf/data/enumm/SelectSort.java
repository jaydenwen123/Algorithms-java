package com.wxf.data.enumm;

import java.beans.IntrospectionException;
import java.util.Arrays;

public class SelectSort {

	public SelectSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ѡ������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] selectSort(int[] data) {

		// �������˼·����:
		// 1.���ȼٶ���������С��,Ȼ�����ν��бȽ�
		// 2.���ĳλ������С�ڸ���,����н���
		// 3.����һ�˱ȽϺ�,data[0]λ��С����
		// 4.n�����ܹ���Ҫ����n-1��
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (data[min] > data[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = data[min];
				data[min] = data[i];
				data[i] = temp;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int data[] = { 2, 4, 7, 1, 3, 5, 67, 32, 21 };
		System.out.println("����ǰ��");
		System.out.println(Arrays.toString(data));
		System.out.println("�����");
		System.out.println(Arrays.toString(selectSort(data)));

	}
}
