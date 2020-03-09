package com.wxf.data.divideConquer;


public class SolveMaxSubSum {

	public SolveMaxSubSum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ͨ���ݹ�ķ�ʽ�����������������к�����
	 * 
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	public static int solveMaxSubSum(int[] data, int low, int high) {
		// ͨ�����η����������������к�����˼·���£�
		// 1.���ȴӸ����е��м��ĳ��������
		// 2.��Ϊ���������������һ������������к�λ�ڸ������
		// �ڸ�������£�ͨ���ݹ���ͣ��ݹ�����ǵ�����Ϊ������ʱ����������������򷵻ظ��������򷵻�0
		// 3.�ڶ��������λ�ڸ������Ҷ�
		// Ҳͨ���ݹ���ͣ��ݹ���ں�1���һ��
		// 4.���������������������а�������
		// ���������,��Ҫ�������´������ȴ���벿��������ͣ�Ȼ�����Ұ벿��������ͣ������Ӻ�1,2����������ֵ
		int leftMaxSum = 0, rightMaxSum = 0;
		int leftBorderMaxSum = 0, rightBorderMaxSum = 0;
		int leftSum = 0, rightSum = 0;
		if (low == high) {
			if (data[low] > 0) {
				return data[low];
			} else {
				return 0;
			}

		}
		int mid = (low + high) / 2;
		leftMaxSum = solveMaxSubSum(data, low, mid);
		rightMaxSum = solveMaxSubSum(data, mid + 1, high);
		// ���������������,��������£�������м�������߿�ʼ������ͣ������ܴ��������м����
		for (int i = mid; i >= low; i--) {
			leftSum += data[i];
			if (leftSum > leftBorderMaxSum)
				leftBorderMaxSum = leftSum;

		}
		for (int j = mid + 1; j <= high; j++) {
			rightSum += data[j];
			if (rightSum > rightBorderMaxSum)
				rightBorderMaxSum = rightSum;
		}
		return max(leftMaxSum, rightMaxSum, leftBorderMaxSum
				+ rightBorderMaxSum);
	}

	/**
	 * �ҳ�abc�������е����ֵ
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static int max(int a, int b, int c) {
		if (a < b)
			a = b;
		return a > c ? a : c;
	}

	public static void main(String[] args) {
		int[] a = { -2, 11, -4, 13, -5, -2 };
		int[] b = { -6, 2, 4, -7, 5, 3, 2, -1, 6, -9, 10, -2 };
		System.out.println(solveMaxSubSum(a, 0, a.length - 1));
		System.out.println(solveMaxSubSum(b, 0, b.length - 1));
	}
}
