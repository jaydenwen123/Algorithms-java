package com.wxf.data.greedy;

import java.util.Arrays;

public class SolveBackPack {

	public SolveBackPack() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 解决背包问题
	 * 
	 * @param w
	 *            不同物品的重量
	 * @param v
	 *            不同物品的价格
	 * @param W
	 *            最大重量
	 * @return 返回的最优解
	 */
	public static int solveBackPack(int[] w, int[] v, int n, int W,
			double[] result) {
		// 统计最优解包含的元素
		int count = 0;
		// 求解背包问题的思路如下：
		// 1、首先通过w和v计算单位重量价值
		// 2、然后对单位重量价值进行递减排序
		// 3、开始逐个扫描找最优解
		double data[] = new double[n];
		for (int i = 0; i < n; i++) {
			// 取小数点后两位
			data[i] = v[i] * 100 / (w[i] * 100.0);
		}
		// 递减排序
		QuickSort(data, 0, data.length - 1, w, v);
		// 开始逐个扫描
		int weight = W;
		// 该结果的第一位数用来存放总的价值
		result[0] = 0;
		for (int i = 0; i < n; i++) {
			// 如果当前物品的重量小于Weight，则将其加入
			if (w[i] <= weight) {
				weight = weight - w[i];
				count++;
				result[0] += v[i];
				result[count] = 1;
			} else {
				// 此种情况表示的是，当前的剩余重量不足要放入的物品的重量，只能将其一部分，放入到背包中
				if (weight > 0) {
					// 表示剩余的重量占该物品重量的比例
					double x = weight * 100 / (w[i] * 100.0);
					result[0] += v[i] * x;
					count++;
					result[count] = x;
					break;
				}

			}
		}
		return count;
	}

	/**
	 * 
	 * 快速排序，递减排序
	 * 
	 * @param data
	 * @param low
	 * @param high
	 */
	public static void QuickSort(double[] data, int low, int high, int[] w,
			int[] v) {

		// 快速排序。对double类型的数据进行递减排序
		int i = low, j = high;
		double temp = data[low];
		int wei = w[low];
		int val = v[low];
		while (i != j) {
			while (i < j && data[j] < temp) {
				j--;
			}
			if (i < j) {
				data[i] = data[j];
				w[i] = w[j];
				v[i] = v[j];
				i++;
			}
			while (i < j && data[i] >= temp) {
				i++;
			}
			if (i < j) {
				data[j] = data[i];
				w[j] = w[i];
				v[j] = v[i];
				j--;
			}

		}
		data[i] = temp;
		w[i] = wei;
		v[i] = val;
		if (low < i)
			QuickSort(data, low, i - 1, w, v);
		if (i < high)
			QuickSort(data, i + 1, high, w, v);
	}

	/**
	 * 显示结果
	 * 
	 * @param result
	 * @param n
	 */
	public static void display(double[] result, int n) {

		for (int i = 1; i <= n; i++) {
			System.out.print(result[i] + "\t");
		}
		System.out.println();

	}

	public static void display(int[] result, int n) {

		for (int i = 0; i < n; i++) {
			System.out.print(result[i] + "\t");
		}
		System.out.println();

	}

	public static void main(String[] args) {

		// 对应的不同物品 的重量
		int w[] = { 10, 20, 30, 40, 50 };
		// 对应不同物品的价值
		int v[] = { 20, 30, 66, 40, 60 };
		// 对应的背包的最大承受重量
		int W = 100;
		// 物品个数
		int n = v.length;
		// 该数组用来存储最优解和最大价值，其中第0个元素存放的是最大的价值，其后其他元素存放最优解
		double result[] = new double[n + 1];
		// 解决背包问题，返回最优解包含的元素的个数
		int count = solveBackPack(w, v, n, W, result);
		System.out.println("获得的最优解如下：");
		System.out.println("以单位重量价值v/w递减排序并求解");
		// 显示结果
		System.out.print("i:\t");
		for (int i = 0; i < n; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.print("v:\t");
		display(v, n);
		System.out.print("w:\t");
		display(w, n);
		System.out.print("x:\t");
		display(result, count);
		System.out.println("获得的最大价值为：" + result[0]);
	}
}
