package com.wxf.data.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Administrator ð�������㷨
 * 
 */
public class DubbleSort {

	// Ҫ�������������
	private int[] data;
	// �������Ԫ�صĸ���
	private int length;
	// �м������������߳����Ч��
	private boolean flag = false;

	public DubbleSort(int[] data) {
		super();
		this.data = data;
		length = data.length;
	}

	/**
	 * @return ð���㷨�� ���������һ���м�����������ж��Ƿ���ÿ�˵ıȽϹ����У��������˽����� �ñ�����д����һ�����ַ�ʽ����Ϊ����
	 */
	public int[] dubbleSort1() {
		for (int i = 1; i < length && flag == false; i++) {
			flag = true;
			for (int j = 0; j < length - i; j++) {
				if (data[j] > data[j + 1]) {
					// exchangeTwoNumber(data[j], data[j + 1]);
					flag = false;
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * @return �÷������ص��������Ժ������
	 */
	public int[] dubbleSort2() {
		for (int i = 1; i < length; i++) {
			flag = true;
			for (int j = 0; j < length - i; j++) {
				if (data[j] > data[j + 1]) {
					// exchangeTwoNumber(data[j], data[j + 1]);
					flag = false;
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
			// ������ж�����ĸ�����û�н��н������֣��������û�н��н�������˵���������Ѿ�����ִ���˳��˵�ѭ����
			if (flag == true) {
				break;
			}
		}
		return data;
	}

	public static void main(String[] args) {
		int[] data = new int[] { 38, 5, 19, 26, 49, 97, 1, 66, 345 };
		Date date1 = new Date(System.nanoTime());
		DubbleSort dubbleSort = new DubbleSort(data);
		// ����ǰ������
		System.out.println("����ǰ�����飺");
		System.out.println(Arrays.toString(data));
		data = dubbleSort.dubbleSort2();
		Date date2 = new Date(System.nanoTime());
		// ����ִ�е�ʱ��
		System.out.println("����ִ�е�ʱ�䣺");
		System.out
				.println(((float) (date2.getTime() - date1.getTime()) / 1000000));
		// ����������
		System.out.println("���������飺");
		System.out.println(Arrays.toString(data));
	}
}
