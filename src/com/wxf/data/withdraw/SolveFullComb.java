package com.wxf.data.withdraw;

public class SolveFullComb {

	public SolveFullComb() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 采用回溯法求解全排列问题,非递归算法设计
	 * 
	 * @param a
	 *            存放每次排列的结果的数组
	 * @param n
	 *            对n个数
	 * @param k
	 *            求k个数的全排列
	 */
	public static int solveFullComb1(int a[], int n, int k, int count) {
		// 采用非递归的思想通过回溯法求解全排列问题的思路如下:
		// 首先找到的组合序列满足以下条件即其中i为数组的下标从0开始小于k
		// 1)a[i+1]>a[i]
		// 2)a[i]-i<=n-k+1
		// 1.首先初始化，置a[0]位最小值1,在满足条件2的情况下有一下2,3
		// 2.判断如果i=k-1时，则说明已经找到一组排列，将其输出然后让a[k-1]的元素加1
		// 3.如果i<k-1时，让i++,寻找下一个元素,a[i]=a[i-1]+1;
		// 4.如果条件2不成立，表示的是a[i]的候选元素已经试探完毕，如果此时i的值为0，表示
		// 所有位置都已经试探完毕，算法结束。否则，i>0，时，让其回溯即i-1,回溯到前一个位置，使其a[i]+1;
		int i = 0;
		a[0] = 1;
		while (true) {
			if (a[i] - i <= n - k + 1) {
				if (i == k - 1) {
					displayResult(a, k);
					a[k - 1] = a[k - 1] + 1;
					count++;
				} else {
					i++;
					a[i] = a[i - 1] + 1;
				}
			} else {
				if (i == 0)
					break;
				a[--i]++;
			}
		}
		return count;
	}

	/**
	 * 显示结果
	 * 
	 * @param a
	 * @param k
	 */
	public static void displayResult(int[] a, int k) {
		for (int i = 0; i < k; i++) {
			System.out.print(a[i] + "\t");
		}
		System.out.println();
	}

	/**
	 * 采用回溯法递归的思想解决全排列问题
	 * 
	 * @param a
	 * @param n
	 * @param k
	 * @param i
	 */
	public static void solveFullComb2(int[] a, int n, int k, int i) {

		// 方法二采用回溯法递归的思路求解
		if (i < 0)
			return;
		while (a[i] - i <= n - k + 1) {
			if (i == k - 1) {
				displayResult(a, k);
				a[i]++;
			} else {
				i++;
				a[i] = a[i - 1] + 1;
				solveFullComb2(a, n, k, i);
			}
		}
		if (i > 0) {
			a[--i]++;
			solveFullComb2(a, n, k, i);
		}
	}

	public static void main(String[] args) {
		int[] a = new int[10];
		int n = 5, k = 3;
		int count = 0;
		System.out.println("n=5，k=3的全排列如下：");
		count = solveFullComb1(a, n, k, count);
		System.out.println("总共有" + count + "种结果");
		System.out.println("第二种方法：");
		a[0] = 1;
		solveFullComb2(a, n, k, 0);
	}
}
