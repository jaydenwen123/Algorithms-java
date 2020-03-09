package com.wxf.data.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 求解删数问题
 * 
 * @author Administrator
 * 
 */
public class SolveDeleteNumber {

	// 关于删数问题思路如下：
	// 1.首先定义一个类，该类中含有数字以及该数字处于哪一位（个位，十位，百位。。。）
	// 2.以一个5位数，删除的数字个数为3为例，首先对位数大于2的数字进行从高到低的排序，找到最小的数
	// 首先从对该数字的第百位往前，找最小的数字
	// 然后再从找到的最下的数字，中找最小的数字即可，直到获得的数字个数为5-3=2个
	public SolveDeleteNumber() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 求解删数问题
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static int solveDeleteNumber(int d, int k) {
		int n = getNumberLength(d);
		if (k > n)
			return -1;
		// 该集合用来存储找到的符合题意的最优解
		List<Integer> list = new ArrayList<>();
		int min = getNumber(d, n);
		// i用来标记从该位置起，然后从后再找符合题意的数字
		int i = n - 1;
		// 该参数表示将要删除的个数
		int count = 0;
		for (; i >= k; i--) {
			if (min > getNumber(d, i)) {
				// 找到了最高位，最小数字
				min = getNumber(d, i);
				count++;
			}
		}
		list.add(min);
		System.out.println(count);
		System.out.println(min);
		System.out.println(i);
		if(k-count-1==i){
			
		}
		return n;
	}

	/**
	 * 获取整数d的位数
	 * 
	 * 最笨的办法
	 * 
	 * @param d
	 * @return
	 */
	private static int getNumberLength(int d) {
		// TODO Auto-generated method stub
		String s = d + "";
		return s.length();
	}

	/**
	 * @return
	 */
	public static int getNumber(int d, int n) {
		String s = d + "";
		int length = s.length() + 1;
		return Integer.parseInt(s.charAt(length - n - 1) + "");
	}

	public static void main(String[] args) {
		int n = 54178;
		// System.out.println(getNumberLength(n));
		// System.out.println(getNumber(n, 5));
		solveDeleteNumber(n, 3);
	}
}
