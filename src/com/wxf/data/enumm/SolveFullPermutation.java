package com.wxf.data.enumm;

import java.util.Stack;

/**
 * ͨ��������ٷ����ȫ��������
 * 
 * @author Administrator
 * 
 */
public class SolveFullPermutation {

	/**
	 * �������ʵ���࣬��ʾһ�����еĶ���
	 * 
	 * @author Administrator
	 * 
	 */
	public static final int MAXN = 10;

	public static class Unit {
		// ������ʾ�������е�����Ԫ��
		public int[] data;
		// Ԫ�صĸ���
		public int n;

		public Unit() {
			// TODO Auto-generated constructor stub
		}

		public Unit(int[] data, int n) {
			super();
			this.data = data;
			this.n = n;
		}

	}

	/**
	 * ͨ���ݹ鷽ʽ���ȫ��������
	 * 
	 * @param data
	 * @param n
	 * @param k
	 */
	public static void solveFullPermutation(int data[], int n, int k) {
		if (k == n) {
			display(data);
		} else {
			for (int i = 1; i <= n; i++) {
				boolean flag = false;
				for (int j = 0; j < k; j++) {
					if (data[j] == i) {
						flag = true;
					}
				}
				if (!flag) {
					data[k] = i;
					solveFullPermutation(data, n, k + 1);
				}
			}
		}
	}

	/**
	 * ͨ��������ٷ����
	 * 
	 * @param n
	 */
	public static void solveFullPermutation(int n) {

		// ͨ��������ٷ����ȫ���������˼·���£�
		// 1.���ȳ�ʼ��һ��ջ
		// 2.Ȼ���ʼ��һ��Ԫ�ؽ�����ջ
		// 3.��ջ��Ϊ��ʱ��һֱִ��
		// 4.����ջ��Ԫ�أ�Ȼ�󲻶ϵĲ����µ�Ԫ��
		// 5.�����µ�Ԫ�ؽ�����ջ
		Stack<Unit> stack = new Stack<>();
		Unit unit = new Unit(new int[] { 1 }, 1);
		Unit temp = null;
		Unit pre = null;
		stack.push(unit);
		int count = 0;
		while (!stack.isEmpty()) {
			// ����ջ��Ԫ��
			temp = stack.pop();
			int[] arr = new int[temp.n];
			copyArray(temp.data, arr, temp.n);
			// �����ϴε����е�˳��
			if (temp.n == n) {
				// �����е�Ԫ�صĸ���Ϊnʱ��˵���Ѿ��ź���ֱ���������
				count++;
				display(temp.data);
			} else if (temp.n < n) {
				// ִ�в��룬������ջ��2��ʼ��n,����i��ʾ����Ҫ�����λ�ã�n��Ԫ����n+1���յ�
				for (int i = 0; i < temp.n + 1; i++) {
					int[] dest = new int[temp.n + 1];
					insertToArray(arr, temp.n + 1, i, temp.n + 1, dest);
					pre = new Unit(dest, dest.length);
					stack.push(pre);
				}
			}
		}
		System.out.println();
		System.out.println("���еĸ���Ϊ��" + count);
	}

	/**
	 * ������array�е�indexλ�ò���Ԫ��el
	 * 
	 * @param array
	 * @param index
	 * @param el
	 */
	public static void insertToArray(int[] srarr, int m, int index, int el,
			int[] dest) {

		if (index < 0 || index > m)
			return;
		System.arraycopy(srarr, 0, dest, 0, srarr.length);
		for (int i = dest.length - 1; i > index; i--) {
			dest[i] = dest[i - 1];

		}
		dest[index] = el;
	}

	/**
	 * ��a�����е�m������Ԫ�ظ��Ƶ�b������
	 * 
	 * @param a
	 * @param b
	 * @param m
	 */
	public static void copyArray(int[] a, int b[], int m) {
		for (int i = 0; i < m; i++) {
			b[i] = a[i];
		}
	}

	public static void display(int[] data) {

		for (int i = 0; i < data.length; i++)
			System.out.print(data[i]);
		System.out.print("\t");
	}

	public static void main(String[] args) {
		// System.out.println("ͨ��������ٷ����ȫ�������⣺");
		// System.out.println("3��ȫ�������£�");
		// solveFullPermutation(3);
		// System.out.println("4��ȫ�������£�");
		// solveFullPermutation(4);
		// System.out.println("5��ȫ�������£�");
		// solveFullPermutation(5);
		System.out.println("�ݹ鷽ʽ�����ٷ����ȫ�������⣺");
		int n = 3;
		int a[] = new int[n];
		solveFullPermutation(a, n, 0);
	}

}
