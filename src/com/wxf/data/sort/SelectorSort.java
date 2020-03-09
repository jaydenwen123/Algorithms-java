package com.wxf.data.sort;

import java.util.Arrays;

/**
 * @author Administrator ��һ��������ֱ��ѡ������ һ���ṩ���ַ����� ����һ����ͨ��ֱ�ӱ�����С����Ȼ����ÿ�˽���ʱ��������и�ֵ
 *         ����������ͨ��������С����С�꣬Ȼ����ÿ�˽���ʱ��ͨ���±����ж��Ƿ�ǰ����ʱ��С����������ǡ�����н���
 * 
 */
public class SelectorSort {

	/**
	 * @param data
	 *            Ҫ���������
	 * @return ���ص����Ѿ��ź��������
	 */
	public static int[] selectorSort1(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			// ÿ��ÿ��ѭ���ĵ�ǰ��Ϊ��С��
			int min = data[i];
			// Ȼ�����κ������������Ԫ�ؽ��бȽϡ�
			for (int j = i + 1; j < data.length; j++) {
				// ������������Ԫ���б���С�ġ�Ȼ����������Ľ�������ǰ������ȻΪ��С��
				if (data[j] < min) {
					int temp = min;
					min = data[j];
					data[j] = temp;
				}
			}
			// ÿ�αȽ���󣬵õ���С���������ڱ���min�С�Ȼ��������е�iλ�õ�Ԫ�ؽ��и�ֵ������Ϊ��С������
			data[i] = min;

		}
		return data;
	}

	/**
	 * @param data
	 *            Ҫ���������
	 * @return���ص����Ѿ��ź��������
	 */
	public static int[] selectorSort2(int[] data) {
		for (int i = 1; i < data.length; i++) {
			// ���浱ǰ������Ԫ��Ϊ����֫����������С��
			int minIndex = i - 1;
			for (int j = i; j < data.length; j++) {
				// �����ǰ����������С����Ѹ�����С����Ը�ֵ��minIndex
				if (data[j] < data[minIndex])
					minIndex = j;
			}
			// ����жϱ����С����û�б仯�����û���κα仯�����ý��д������򣬽������ݡ�
			if (minIndex != i - 1) {
				int temp = data[minIndex];
				data[minIndex] = data[i - 1];
				data[i - 1] = temp;
			}
		}
		return data;
	}

	/**
	 * @param data
	 * @return ���㷨��Ҫ���޸ĺ���ȶ��㷨
	 */
	public static int[] selectorSort3(int[] data) {

		for (int i = 0; i < data.length; i++) {
			int temp = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[temp]) {
					temp = j;
				}
			}
			// �����Ҫ�ǽ����±���жϡ�������߲���ȡ���tempǰ�������׿�κ��ƣ���󽫸���С���������ȥ
			if (temp != i) {
				int min = data[temp];
				for (int k = temp; k > i; k--) {
					data[k] = data[k - 1];
				}
				data[i] = min;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 1, 54, 5, 87, 345 };
		System.out.println("����ǰ�����飺");
		System.out.println(Arrays.toString(data));
		data = selectorSort3(data);
		System.out.println("����������Ԫ�أ�");
		System.out.println(Arrays.toString(data));
	}

}
