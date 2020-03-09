package com.wxf.data.withdraw;

import java.util.Arrays;

public class SolveFullPerm {

	public SolveFullPerm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���ȫ��������
	 * 
	 * @param data
	 */
	public static void solveFullPerm(Object[] data, int k, int n) {

		// ���ݻ��ݷ����ȫ���������˼·���£�
		// ���õݹ��˼�����������f(s,k,n)��ʾ�������s[k...n-1]�����е�ȫ������
		// 1.�ݹ���Ϊ����k<n-1ʱ��f(s,k+1,n)��С���⣬ǰ���Ǵ����⣬���ڸ�������н�
		// s[k]��s[k...n-1]�е�Ԫ�ؽ����İ취
		// 2.�ݹ����Ϊ����k=n-1ʱ��������е�Ԫ�ز�����һ��ȫ����
		Object temp;
		if (k == n - 1) {
			System.out.println(Arrays.toString(data));
		} else {
			for (int i = k; i < n; i++) {
				temp = data[i];
				data[i] = data[k];
				data[k] = temp;
				solveFullPerm(data, k + 1, n);
				temp = data[i];
				data[i] = data[k];
				data[k] = temp;
			}

		}
	}

	public static void main(String[] args) {
		Integer[] data = { 1, 2, 3, 5 };
		System.out.println("ͨ�����ݷ����ȫ���е����⣺");
		solveFullPerm(data, 0, data.length);
	}
}
