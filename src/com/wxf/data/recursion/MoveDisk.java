package com.wxf.data.recursion;

public class MoveDisk {

	/**
	 * 解决汉诺塔问题 递归解法
	 * 
	 * 该方法的功能是把n个盘子借助于aut柱子，从from移动到to柱子上
	 * 
	 * 问题描述：设有3个标号为A，B，C的柱子，在A柱子上面放着n个盘子，每一个都比下面的略小一点，
	 * 要求吧A柱子上面的盘子全部移动到C柱子上面，移动规则为：
	 * 
	 * 1.一次只能移动一个盘子，2移动过程中大盘子不能放在小盘子上面，3在移动过程总盘子可以 放在A,B,C的任意一个柱子上
	 * 
	 * // 1.递归出口 当n=1时，可以直接求解 // 2.递归体 首先把n-1个盘子从from移动到aut借助于to //
	 * 然后再吧把n-1个盘子从aut移动到to，借助于from
	 * 
	 * @param n
	 * @param from
	 * @param to
	 * @param aut
	 */
	public static void tower(int n, String from, String to, String aut) {

		// 1.递归出口 当n=1时，可以直接求解
		// 2.递归体 首先把n-1个盘子从from移动到aut借助于to
		// 然后再吧把n-1个盘子从aut移动到to，借助于from
		if (n == 1) {
			System.out.println("把盘子1从" + from + "借助于" + aut + "移动到" + to);
			return;
		}
		tower(n - 1, from, aut, to);
		System.out.println("把盘子" + n + "从" + from + "借助于" + aut + "移动到" + to);
		tower(n - 1, aut, to, from);
	}

	public MoveDisk() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		tower(4, "A", "C", "B");
	}

}
