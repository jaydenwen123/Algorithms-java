package com.wxf.data.divideConquer;

import java.awt.SecondaryLoop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FindMaxAndSubMaxElement {

	public FindMaxAndSubMaxElement() {
		// TODO Auto-generated constructor stub
	}

	public static final int[] s1 = new int[2];
	public static final int[] s2 = new int[2];

	/**
	 * �����е����Ԫ�غʹδ�Ԫ��
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void findMaxAndSubMaxElement(int[] data, int low, int high,
			int max1, int max2) {
		// ���������е����Ԫ�غʹ����Ԫ�ص�˼·���£�
		// 1.�����ҵݹ���͵ݹ����
		// 2.�ݹ����Ϊ����������ֻ������Ԫ��ʱ���Ƚ�����Ԫ�صõ����Ԫ�غʹ����Ԫ��
		// 3.�ݹ���Ϊ�����ȴӸ����е��м�λ�ÿ�ʼ�������зֳ����롣
		// 4.������������ҳ����ʹ����Ԫ�أ����Ұ�������Ҳ�ҳ����ʹ����Ԫ��
		// 5.Ȼ����бȽϣ������������е����Ԫ�غʹ����Ԫ��
		// �ݹ����
		int x1 = 0, x2 = 0, x3 = 0, x4 = 0;
		if (high - low < 1)
			return;
		if (high - low == 1) {
			if (data[low] < data[high]) {
				max1 = data[high];
				max2 = data[low];
			} else {
				max1 = data[low];
				max2 = data[high];
			}
			System.out.println(max1 + "," + max2);
		} else {
			int mid = (low + high) / 2;
			findMaxAndSubMaxElement(data, low, mid, x1, x2);
			findMaxAndSubMaxElement(data, mid + 1, high, x3, x4);
			if (x1 < x3) {
				max1 = x3;
				if (x1 > x4) {
					max2 = x1;
				} else {
					max2 = x4;
				}
			} else {
				max1 = x1;
				if (x2 > x3) {
					max2 = x2;
				} else {
					max2 = x3;
				}
			}

		}

	}

	public static void test(int a, int b) {
		a = 3;
		b = 5;
		System.out.println("hello");

	}

	public static void main(String[] args) {
		int[] save = new int[2];
		int[] data = { 2, 3, 45, 5, 7, 1, 4, 9, 6 };
		int max1 = 0, max2 = 0;
		findMaxAndSubMaxElement(data, 0, data.length - 1, max1, max2);
		System.out.println(save[0] + "," + save[1]);

	}
}
