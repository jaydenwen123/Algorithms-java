package com.wxf.data.enumm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SolveSmithNum {

	public SolveSmithNum() {
		// TODO Auto-generated constructor stub
	}

	public static final int MAXN = 20;

	/**
	 * 解决给定一个正整数N，然后求大于N的最小的smith树
	 * 
	 * smith数的条件是： 该数是一个合数，同时它的质因数诸位相加之和等于其本身诸位相加之和
	 * 
	 */
	public static int solveSmithNum(int n) {
		boolean flag = false;
		while (!flag) {
			if (isZhi(n)) {
				n = n + 1;
			}
			flag = isSmithNum(n);
			n++;
		}
		List<Integer> list = getSingleFactors(n - 1);
		System.out.print("该数的质因数为：");
		System.out.println(Arrays.asList(list));
		System.out.println("质因数分解式逐位相加之和为：" + getFactorsSum(list));
		list = getEachNumber(n - 1, new ArrayList<Integer>());
		System.out.print("该数的逐位数字为：");
		System.out.println(Arrays.asList(list));
		System.out.println("该数逐位之和为：" + getEachSum(list));
		return n - 1;
	}

	/**
	 * 判断一个数是否是smith数
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isSmithNum(int n) {
		boolean result = false;
		// 判断一个数是否为smith数的思路如下：
		// 1.首先获取该数的所有因数，将其用数组保存
		List<Integer> factors = getSingleFactors(n);
		// 2.然后再获取该数的逐位数字，同时也用数组保存
		List<Integer> each = new ArrayList<Integer>();
		each = getEachNumber(n, each);
		// 3.将该数的逐为数字获取到，相加
		int sum1 = getEachSum(each);
		int sum2 = getFactorsSum(factors);
		// System.out.println(sum1+","+sum2);
		if (sum1 == sum2)
			result = true;
		// 4.如果相等，则返回true，否则，返回false
		return result;
	}

	/**
	 * 获取一个整数的逐位的数字
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> getEachNumber(int n, List<Integer> each) {
		if (n != 0) {
			getEachNumber(n / 10, each);
			each.add(n % 10);
			// System.out.println(n % 10);
		}
		return each;
	}

	/**
	 * 获取该数的所有因数，并且保存到list集合中返回
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> getFactors(int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0) {
				list.add(i);
			}
		}
		return list;
	}

	public static int getFactorsSum(List<Integer> list) {
		int sum = 0;
		// 遍历所有的因数
		for (Integer i : list) {
			List<Integer> each = new ArrayList<>();
			// 获取不同的因数的逐位的数字
			each = getEachNumber(i, each);
			// 对其相加求和
			for (Integer j : each) {
				sum += j;
			}
		}
		return sum;
	}

	/**
	 * 逐位相加求和并返回
	 * 
	 * @param each
	 * @return
	 */
	public static int getEachSum(List<Integer> each) {
		int sum = 0;
		for (Integer i : each) {
			sum += i;
		}
		return sum;
	}

	/**
	 * 获取某数的质因数分解式的各项，保存到list集合中返回
	 * 
	 * @param n
	 * @return
	 */
	public static List<Integer> getSingleFactors(int n) {
		List<Integer> list = new ArrayList<>();
		while (!isZhi(n)) {
			int first = getFirstFactor(n);
			list.add(first);
			n /= first;
		}
		list.add(n);
		return list;
	}

	/**
	 * 获取合数的第一个质因数
	 * 
	 * @param n
	 * @return
	 */
	public static int getFirstFactor(int n) {
		int i = 2;
		for (; i < n / 2; i++) {
			if (n % i == 0) {
				break;
			}
		}
		return i;
	}

	/**
	 * 判断一个数是否为质数
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isZhi(int n) {
		boolean result = true;
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0) {
				result = false;
			}
		}
		return result;

	}

	public static void main(String[] args) {
		// List<Integer> each = new ArrayList<>();
		// getEachNumber(4937775, each);
		// for (Integer i : each) {
		// System.out.println(i);
		// }
		// System.out.println(getEachSum(each));
		// List<Integer> list = getSingleFactors(4937775);
		// for (Integer i : list) {
		// System.out.println(i);
		// }
		System.out.println("输入样本：");
		Scanner scanner = new Scanner(System.in);
		List<Integer> cases = new ArrayList<>();
		int ca = scanner.nextInt();
		while (ca != 0) {
			cases.add(ca);
			ca = scanner.nextInt();
		}

		System.out.println("输出结果：");
		for (Integer e : cases) {
			System.out.println(solveSmithNum(e));
		}

		// System.out.println(isSmithNum(4937775));

	}
}
