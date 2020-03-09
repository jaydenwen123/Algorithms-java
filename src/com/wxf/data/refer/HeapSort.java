package com.wxf.data.refer;

import java.util.Arrays;

public class HeapSort {

	public static void heapSort(int[] data) {
		System.out.println("��ʼ����");
		int arrayLength = data.length;
		// ѭ������
		for (int i = 0; i < arrayLength - 1; i++) {
			// ����
			buildMaxHeap(data, arrayLength - 1 - i);
			// �����Ѷ������һ��Ԫ��
			swap(data, 0, arrayLength - 1 - i);
			System.out.println(Arrays.toString(data));
		}
	}

	private static void swap(int[] data, int i, int j) {
		// TODO Auto-generated method stub
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	// ��data�����0��lastIndex���󶥶�
	private static void buildMaxHeap(int[] data, int lastIndex) {
		// TODO Auto-generated method stub
		// ��lastIndex���ڵ㣨���һ���ڵ㣩�ĸ��ڵ㿪ʼ
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// k���������жϵĽڵ�
			int k = i;
			// �����ǰk�ڵ���ӽڵ����
			while (k * 2 + 1 <= lastIndex) {
				// k�ڵ�����ӽڵ������
				int biggerIndex = 2 * k + 1;
				// ���biggerIndexС��lastIndex����biggerIndex+1�����k�ڵ�����ӽڵ����
				if (biggerIndex < lastIndex) {
					// �������ӽڵ��ֵ�ϴ�
					if (data[biggerIndex] < (data[biggerIndex + 1])) {
						// biggerIndex���Ǽ�¼�ϴ��ӽڵ������
						biggerIndex++;
					}
				}
				// ���k�ڵ��ֵС����ϴ���ӽڵ��ֵ
				if (data[k] < (data[biggerIndex])) {
					// ��������
					swap(data, k, biggerIndex);
					// ��biggerIndex����k����ʼwhileѭ������һ��ѭ�������±�֤k�ڵ��ֵ�����������ӽڵ��ֵ
					k = biggerIndex;
				} else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = { 21, 30, 45, 56, 87, 4, 32

		};
		System.out.println("����֮ǰ��\n" + Arrays.toString(data));
		heapSort(data);
		System.out.println("����֮��\n" + Arrays.toString(data));
	}

}
