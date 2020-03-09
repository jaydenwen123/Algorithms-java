package com.wxf.data.withdraw;

public class SolveZeroOneBackPack {

	public SolveZeroOneBackPack() {
		// TODO Auto-generated constructor stub
	}

	public static int MAX = 20;
	// 用来记录最优解的重量
	public static int maxWeigh = 0;
	// 用来记录最优解的价值
	public static int maxValue = 0;

	public static int[] result = new int[MAX];

	/**
	 * 解决0/1背包问题，通过回溯法
	 * 
	 * 回溯法：简而言之就是通过构建解空间树，然后进行深度优先遍历，最后得出最优解
	 * 
	 * @param n
	 *            n个物品
	 * @param i
	 *            i 表示第i个物品
	 * @param W
	 *            最大重量
	 * @param w
	 *            物品的重量
	 * @param v
	 *            物品的价值
	 * @param tw
	 *            搜索完该分支后的装入背包的总重量
	 * @param tv
	 *            搜索完该分支后的装入背包的总价值
	 * @param op
	 */
	public static void solveZeroOneBackPack1(int n, int i, int W, int[] w,
			int[] v, int tw, int tv, int[] op) {

		// 通过回溯法求解背包问题的思路如下：
		// 通过递归的方法求解，需要找到两个要素
		// 1.递归出口
		// 该问题的递归出口为：当i>n时，表示所有的物品都已经搜索完成，故算法结束
		if (i > n) {
			if (tw <= W && tv > maxValue) {
				maxValue = tv;
				maxWeigh = tw;
				for (int j = 1; j <= n; j++) {
					result[j] = op[j];
				}
			}
			return;
		}
		// 2、递归体
		// 当i<=n时，对于物品i有两种可能的结果，即选取或者不选取
		// 然后分别对选取和不选取的情况进行递归即可
		op[i] = 1;
		solveZeroOneBackPack1(n, i + 1, W, w, v, tw + w[i], tv + v[i], op);
		op[i] = 0;
		solveZeroOneBackPack1(n, i + 1, W, w, v, tw, tv, op);
	}

	/**
	 * 解决0/1背包问题，通过回溯法
	 * 
	 * 回溯法：简而言之就是通过构建解空间树，然后进行深度优先遍历，最后得出最优解
	 * 
	 * 该方法是对第一种方法的改进，通过增加一个限界条件来进行剪枝，防止有些分之节点的总重量已经超过了W,任然在扩展其孩子节点
	 * 
	 * @param n
	 *            n个物品
	 * @param i
	 *            i 表示第i个物品
	 * @param W
	 *            最大重量
	 * @param w
	 *            物品的重量
	 * @param v
	 *            物品的价值
	 * @param tw
	 *            搜索完该分支后的装入背包的总重量
	 * @param tv
	 *            搜索完该分支后的装入背包的总价值
	 * @param op
	 */
	public static void solveZeroOneBackPack2(int n, int i, int W, int[] w,
			int[] v, int tw, int tv, int[] op) {

		// 通过回溯法求解背包问题的思路如下：
		// 通过递归的方法求解，需要找到两个要素
		// 1.递归出口
		// 该问题的递归出口为：当i>n时，表示所有的物品都已经搜索完成，故算法结束
		if (i > n) {
			if (tw <= W && tv > maxValue) {
				maxValue = tv;
				maxWeigh = tw;
				for (int j = 1; j <= n; j++) {
					result[j] = op[j];
				}
			}
			return;
		}
		// 2、递归体
		// 当i<=n时，对于物品i有两种可能的结果，即选取或者不选取
		// 然后分别对选取和不选取的情况进行递归即可
		if (tw + w[i] < W) {
			op[i] = 1;
			solveZeroOneBackPack2(n, i + 1, W, w, v, tw + w[i], tv + v[i], op);
		}

		op[i] = 0;
		solveZeroOneBackPack2(n, i + 1, W, w, v, tw, tv, op);
	}

	/**
	 * 解决0/1背包问题，通过回溯法
	 * 
	 * 回溯法：简而言之就是通过构建解空间树，然后进行深度优先遍历，最后得出最优解
	 * 
	 * 该方法是对第二种方法的改进，通过增加一个限界条件来进行剪枝，防止有些分之节点的总重量已经超过了W,任然在扩展其孩子节点
	 * 
	 * 同时，又对右孩子节点进行了限制，实际上对右子树的限制很难，假设该问题的最优解至少取其中的3个物品（即不选的物品数小于等于1，这个条件不一定合理）
	 * ，从而产生进一步键之后的解空间树
	 * 
	 * @param n
	 *            n个物品
	 * @param i
	 *            i 表示第i个物品
	 * @param W
	 *            最大重量
	 * @param w
	 *            物品的重量
	 * @param v
	 *            物品的价值
	 * @param tw
	 *            搜索完该分支后的装入背包的总重量
	 * @param tv
	 *            搜索完该分支后的装入背包的总价值
	 * @param op
	 */
	public static void solveZeroOneBackPack3(int n, int i, int W, int[] w,
			int[] v, int tw, int tv, int[] op) {

		// 通过回溯法求解背包问题的思路如下：
		// 通过递归的方法求解，需要找到两个要素
		// 1.递归出口
		// 该问题的递归出口为：当i>n时，表示所有的物品都已经搜索完成，故算法结束
		if (i > n) {
			if (tw <= W && tv > maxValue) {
				maxValue = tv;
				maxWeigh = tw;
				for (int j = 1; j <= n; j++) {
					result[j] = op[j];
				}
			}
			return;
		}
		// 2、递归体
		// 当i<=n时，对于物品i有两种可能的结果，即选取或者不选取
		// 然后分别对选取和不选取的情况进行递归即可
		if (tw + w[i] < W) {
			op[i] = 1;
			solveZeroOneBackPack3(n, i + 1, W, w, v, tw + w[i], tv + v[i], op);
		}

		op[i] = 0;
		int m = 0;
		for (int j = 0; j < i; j++) {
			if (op[i] == 0)
				m++;
		}
		if (m <= 1) {
			solveZeroOneBackPack3(n, i + 1, W, w, v, tw, tv, op);
		}
	}

	/**
	 * 打印数组data中的前n个数
	 * 
	 * @param data
	 * @param n
	 */
	public static void display(int[] data, int n) {
		for (int i = 1; i <= n; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 4;
		int W = 7;
		int[] w = { 0, 5, 3, 2, 1 };
		int[] v = { 0, 4, 4, 3, 1 };
		int[] op = new int[MAX];
		solveZeroOneBackPack3(n, 1, W, w, v, 0, 0, op);
		System.out.println("选取的物品数为：");
		display(result, n);
		System.out.println("最大价值为："+maxValue + ",最大重量为：" + maxWeigh);
	}
}
