package com.wxf.data.enumm;

import com.wxf.data.recursion.Sort;

public class GetMaxCycle {

	/**
	 * ��һ�������У�ѡ����������ʹ�乹�ɵ������ε��ܳ���󣬲����أ���� ���ܹ��������Σ��򷵻�0
	 * 
	 * @param data
	 * @return
	 */
	public static int getMaxcicyle(int data[]) {
		int max = 0;
		// ˼·���£�
		// ��ѡ���ж������е�Ԫ�ؽ����жϣ�Ԫ�ظ����Ƿ����3�����С��3����-1
		// ����ҳ������е�ǰ����������Ȼ���жϸ��������Ƿ���Թ���
		// �����Σ���������򷵻��ܳ���������ܣ��򷵻�0
		Sort.bubbleSort1(data);
		int n = data.length;
		int max1 = data[n - 1];
		int max2 = data[n - 2];
		int max3 = data[n - 3];
		if (max3 + max2 > max1 && max1 - max2 < max3 && max1 - max3 < max2)
			return max1 + max2 + max3;
		else {
			return 0;
		}

	}

	public static void main(String[] args) {

		int[] data = { 4, 5, 8, 20 };
		System.out.println(getMaxcicyle(data));
	}
}
