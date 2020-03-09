package com.wxf.data.enumm;

import java.util.Stack;

/**
 * 通过增量穷举法求解全排列问题
 * 
 * @author Administrator
 * 
 */
public class SolveFullPermutation {

	/**
	 * 抽象出的实体类，表示一次排列的对象
	 * 
	 * @author Administrator
	 * 
	 */
	public static final int MAXN = 10;

	public static class Unit {
		// 用来表示依次排列的数组元素
		public int[] data;
		// 元素的个数
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
	 * 通过递归方式解决全排列问题
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
	 * 通过增量穷举法解决
	 * 
	 * @param n
	 */
	public static void solveFullPermutation(int n) {

		// 通过增量穷举法解决全排列问题的思路如下：
		// 1.首先初始化一个栈
		// 2.然后初始化一个元素进行入栈
		// 3.当栈不为空时，一直执行
		// 4.弹出栈顶元素，然后不断的插入新的元素
		// 5.并将新的元素进行入栈
		Stack<Unit> stack = new Stack<>();
		Unit unit = new Unit(new int[] { 1 }, 1);
		Unit temp = null;
		Unit pre = null;
		stack.push(unit);
		int count = 0;
		while (!stack.isEmpty()) {
			// 弹出栈顶元素
			temp = stack.pop();
			int[] arr = new int[temp.n];
			copyArray(temp.data, arr, temp.n);
			// 保存上次的排列的顺序
			if (temp.n == n) {
				// 当排列的元素的个数为n时，说明已经排好序直接输出即可
				count++;
				display(temp.data);
			} else if (temp.n < n) {
				// 执行插入，并且入栈从2开始到n,其中i表示的是要插入的位置，n个元素由n+1个空挡
				for (int i = 0; i < temp.n + 1; i++) {
					int[] dest = new int[temp.n + 1];
					insertToArray(arr, temp.n + 1, i, temp.n + 1, dest);
					pre = new Unit(dest, dest.length);
					stack.push(pre);
				}
			}
		}
		System.out.println();
		System.out.println("排列的个数为：" + count);
	}

	/**
	 * 给数组array中的index位置插入元素el
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
	 * 将a数组中的m个数据元素复制到b数组中
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
		// System.out.println("通过增量穷举法解决全排列问题：");
		// System.out.println("3的全排列如下：");
		// solveFullPermutation(3);
		// System.out.println("4的全排列如下：");
		// solveFullPermutation(4);
		// System.out.println("5的全排列如下：");
		// solveFullPermutation(5);
		System.out.println("递归方式结合穷举法解决全排列问题：");
		int n = 3;
		int a[] = new int[n];
		solveFullPermutation(a, n, 0);
	}

}
