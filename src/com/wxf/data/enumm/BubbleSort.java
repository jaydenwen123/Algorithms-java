package com.wxf.data.enumm;

import java.util.Arrays;

public class BubbleSort {

	public BubbleSort() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ð������
	 * 
	 * @param data
	 * @return
	 */
	public static int[] bubbleSort(int[] data) {
		// ð�������˼·���£�
		// 1.���ȼٶ�ÿһ�εĵ�һ��Ԫ�������ֵ��Ȼ������ѭ��
		// 2.��������֮��Ƚ�
		// 3.�����������������С������н���
		// 4.���ȷ�����ѭ��Ϊn-1��
		// 5.�ڲ�ѭ��Ϊn-i��
		int n = data.length;
		// ���ѭ��Ϊn-1��
		for (int i = 0; i < n - 1; i++) {
			// ���ñ�־λ�������ж��Ƿ�ð�������У�ĳ���Ƿ������Ѿ�����
			boolean flag = true;
			for (int j = 1; j < n - i; j++) {
				if (data[j-1] > data[j]) {
					int temp = data[j];
					data[j] = data[j-1];
					data[j - 1] = temp;
					flag = false;
				}
			}
			if (flag) {
				break;
			}

		}
		return data;
	}
	
	public static void main(String[] args) {
		int data[]={2,4,7,1,3,5,67,32,21};
		System.out.println("����ǰ��");
		System.out.println(Arrays.toString(data));
		System.out.println("�����");
		System.out.println(Arrays.toString(bubbleSort(data)));
		
	}

}
