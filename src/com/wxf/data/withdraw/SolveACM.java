package com.wxf.data.withdraw;

import java.util.Arrays;
import java.util.Scanner;

import org.omg.CORBA.TCKind;

public class SolveACM {

	public SolveACM() {
		// TODO Auto-generated constructor stub
	}

	public static boolean hasResult = false;
	public static int n;
	public static int m = 0;
	public static int[] plan;
	public static int minC = 32767;

	public static void solveACM(int n, int m, int[] op, int i, int[] w, int tw) {
		int result = -1;
		// 通过回溯法来求解ACM训练题的思路如下：
		// 1.首先得到砝码的个数n，砝码的质量w[]，以及对应的袋子的重量m，以上的数据全部由控制台输入
		// 2.根据回溯法，从第一个砝码开始比较，最优解满足的条件是，砝码的和等于袋子的重量，同时选取的砝码的个数最少
		if (i == n) {
			if (tw == m) {
				// 找最优解
				result = getCount(op, n);
				if (minC > result) {
					minC = result;
					System.arraycopy(op, 0, plan, 0, op.length);
					// 表示有解
					hasResult = true;
				}
			}
		} else {
			// 第i个砝码选取
			if (tw + w[i] <= m) {
				op[i] = 1;
				solveACM(n, m, op, i + 1, w, tw + w[i]);
			}
			// 第i个砝码不选取
			op[i] = 0;
			solveACM(n, m, op, i + 1, w, tw);
		}
	}

	/**
	 * 展现结果
	 * 
	 * @param w
	 * @param n
	 */
	public static void display(int[] w, int n, int[] plan) {
		System.out.println(Arrays.toString(plan));
		for (int i = 0; i < n; i++) {
			if (plan[i] == 1) {
				System.out.print(w[i] + "\t");
			}
		}
		System.out.println();
	}

	/**
	 * 获取元素的个数
	 * 
	 * @param op
	 * @param n
	 * @return
	 */
	public static int getCount(int[] op, int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (op[i] == 1)
				count++;
		}
		return count;
	}

	public static void main(String[] args) {
		// n = 6;
		// n = 3;
		// int op[] = new int[6];

		// int[] w = { 1, 2, 3, 2, 3, 5 };
		System.out.println("从键盘输入砝码的个数n,袋子的重量m:");
		System.out.println("第二行输入砝码的重量");
		Scanner scanner = new Scanner(System.in);
		System.out.println("输入样本：");
		// 输入的第一个数为砝码的个数
		n = scanner.nextInt();
		// 输入的第二个数为袋子的重量
		m = scanner.nextInt();
		int op[] = new int[n];
		int[] w = new int[n];
		int i = 0;
		while (i < n) {
			w[i] = scanner.nextInt();
			i++;
		}
		// int[] w = { 5, 9, 1 };
		plan = new int[n];
		solveACM(n, m, op, 0, w, 0);
		if (hasResult) {
			System.out.println("有解：选取的最小的砝码的个数为：" + minC);
			System.out.println("解如下：");
			System.out.println("输出结果：");
			display(w, n, plan);
		} else {
			System.out.println("没有找到合适的解：");
		}
	}
}
