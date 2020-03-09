package com.wxf.data.sort;

import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	/**
	 * @param data
	 * @return �������java����ʵ�� �����ṩ���ַ�����ʵ�ֶ�����
	 */
	public static int[] heapSort1(int[] data) {

		// ѭ��������
		for (int i = data.length - 1; i > 0; i--) {
			// ������
			createHeap(data, i);
			// ���Ѷ������һ��Ԫ�ؽ��н���
			 System.out.println(Arrays.toString(data));
			exchangeElement(data, 0, i);
			// ���ÿ������������
		}
		return data;
	}

	/**
	 * @param data
	 * @return �ṩ�ڶ��ֶ�������㷨 �������㷨�Ĳ��裺 1.�����ѣ� 2.ѭ�������� 3.��������
	 */
	public static int[] heapSort2(int[] data) {
		// ��ʼ������
		initCreateHeap(data);
		System.out.println(Arrays.toString(data));
		for (int i = data.length - 1; i >= 0; i--) {
			exchangeElement(data, 0, i);
			createHeap(data, i, 0);
			System.out.println(Arrays.toString(data));
		}
		return data;
	}

	/**
	 * @param data
	 *            ѭ�����ѵĹ��̣�
	 *            ��Ϊ��ʼ���������ѵĹ��̣����Ǵӵ�һ����Ҷ���a[(n-1)/2]��ʼ�����ڵ�a[0]λ��ѭ������createHeap
	 *            ()�����Ĺ���
	 */
	private static void initCreateHeap(int[] data) {
		// TODO Auto-generated method stub
		for (int i = (data.length - 1) / 2; i >= 0; i--) {
			createHeap(data, data.length, i);
		}
	}

	/**
	 * @param data
	 *            ԭʼԪ�ص�����
	 * @param length
	 *            �������Ľڵ����
	 * @param h
	 *            Ҫ�����Ķ� �Ķ��������ڵ��±�
	 * 
	 */
	private static void createHeap(int[] data, int n, int h) {
		// TODO Auto-generated method stub
		int father = h;
		// ���ȶ��亢�ӵ��±�����ж��Ƿ�С��n
		int child = father * 2 + 1;
		while (child<n) {
			if (child < n-1) {
				// �����Һ��ӽڵ���бȽϴ�С
				if (data[child] < data[child + 1]) {
					child++;
				}
			}
			if (data[father] < data[child]) {
				exchangeElement(data, father, child);
			} else {
				break;
			}
		}
	}

	/**
	 * @param data
	 * @param i
	 * @param j
	 *            ���������е�����ֵ
	 */
	public static void exchangeElement(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	/**
	 * @param data
	 *            ԭʼ������
	 * @param lastIndex
	 *            �Ӹ�Ԫ���𣬿�ʼ������ ������
	 */
	public static void createHeap(int[] data, int lastIndex) {
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// ��ֵΪ��lastIndex�ĸ��׽ڵ��ֵ
			int father = i;
			//
			while (father * 2 + 1 <= lastIndex) {
				// ���Ϊ�丸�׽ڵ�����ӡ�
				int child = father * 2 + 1;
				if (child < lastIndex) {
					// �������Ӻ��Һ��ӽ��бȽϣ�ȡ�ýϴ���Ǹ���
					if (data[child] < data[child + 1]) {
						child++;
					}
				}
				// �Ը��׺����Һ����нϴ�������бȽϣ�������׽ڵ��ֵС�ں��ӽڵ��ֵ������н���
				if (data[father] < data[child]) {
					exchangeElement(data, father, child);
				} else {
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] data = new int[] { 1, 54, 5, 87, 34, 3, 32, 12 };
		int[] data2 = new int[] { 1, 54, 5, 87, 34, 3, 32, 12 };
		System.out.println("����ǰ�����飺");
//		System.out.println(Arrays.toString(data));
		System.out.println("��ʼ������ʹ�÷���һ��");
		data = heapSort1(data);
		System.out.println("����������Ԫ�أ�");
		System.out.println(Arrays.toString(data));

		System.out.println("����ǰ�����飺");
//		System.out.println(Arrays.toString(data2));
		System.out.println("��ʼ������ʹ�÷�������");
		data = heapSort2(data2);
		System.out.println("����������Ԫ�أ�");
		System.out.println(Arrays.toString(data2));
	}

//	public static void main(String[] args) {
//		int[] array = new int[] { 56,
//
//		57, 43, 2, 4, 12, 567, 87, 9, 98, 5, 43, 423, 123, 12, 312, 3, 2, 5,
//				23, 34, 546, 7, 899, 0, 64, 23, 45, 67, 8, 9, 4, 6, 67, 8, 23,
//				97, 11, 234, 90, 234, 2, 345, 46, 567, 57, 21, 343, 546, 63, 2,
//				1, 454, 2, 1, 1, 56, 87, 3, 2343, 546, 567, 687, 34, 54, 534,
//				45, 6, 57, 657, 6, 44, 234, 454, 667, 658, 7698, 9, 234, 242,
//				24, 2354, 365, 5467, 234, 56, 5, 767, 8789, 789, 90, 9, 898,
//				234, 35, 436, 54, 7, 234, 54, 4, 65, 76, 687, 8, 79, 546, 4,
//				53, 3, 53, 34, 2, 42, 42, 42, 23, 4, 234, 3, 6, 5, 67, 1, 24,
//				234, 6, 567, 46, 5, 68, 65, 7, 67 };
//
//		Date time1 = new Date(System.nanoTime());
//		System.out.println("��ʼ������ʹ�÷���һ��");
//		array = heapSort1(array);
//		Date time2 = new Date(System.nanoTime());
//		System.out.println("sort data:" + Arrays.toString(array));
//		System.out.println("sssws:"
//				+ (float) (time2.getTime() - time1.getTime()) / 100000);
//
//		System.out.println("��ʼ������ʹ�÷�������");
//		time1 = new Date(System.nanoTime());
////		array = heapSort2(array);
//		System.out.println(Arrays.toString(Test.heapSort(array)));
//		time2 = new Date(System.nanoTime());
////		System.out.println("sort data:" + Arrays.toString(array));
//		System.out.println("sssws:"
//				+ (float) (time2.getTime() - time1.getTime()) / 100000);
//	}

}
