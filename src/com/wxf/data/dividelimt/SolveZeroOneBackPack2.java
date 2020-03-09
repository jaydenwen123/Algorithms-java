package com.wxf.data.dividelimt;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.wxf.data.dividelimt.SolveZeroOneBackPack1.BackPackNode;

public class SolveZeroOneBackPack2 {

	public SolveZeroOneBackPack2() {
		// TODO Auto-generated constructor stub
	}

	public static final int MAXN = 20;

	/**
	 * 定义该问题的节点类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BackPackNode implements Comparable<BackPackNode> {
		// 该节点的编号
		int no;
		// 该节点的层数
		int i;
		// 装入背包的重量
		int w;
		// 装入背包的价值
		double v;
		// 保存解向量的数组
		int x[] = new int[MAXN];
		// 该节点的上界
		double ub;

		public BackPackNode() {
			// TODO Auto-generated constructor stub
		}

		public BackPackNode(int no, int i, int w, double v, int[] x, double ub) {
			super();
			this.no = no;
			this.i = i;
			this.w = w;
			this.v = v;
			this.x = x;
			this.ub = ub;
		}

		public BackPackNode(int no, int i, int w, double v, double ub) {
			super();
			this.no = no;
			this.i = i;
			this.w = w;
			this.v = v;
			this.ub = ub;
		}

		public BackPackNode(int no, int i, int w, double v) {
			super();
			this.no = no;
			this.i = i;
			this.w = w;
			this.v = v;
		}

		@Override
		public int compareTo(BackPackNode o) {
			// TODO Auto-generated method stub
			return (int) (this.v - o.v);
		}

	}

	// 创建一个队列，用来组织活节点
	public static PriorityQueue<BackPackNode> queue = new PriorityQueue<>();
	private static int[] result;
	private static double maxv;

	/**
	 * 根据该题意，自定义的入队的操作
	 * 
	 * @param no
	 * @param i
	 * @param w
	 * @param v
	 * @return
	 */
	public static void enQueue(int n, BackPackNode node) {
		// 创建要加入队列的对象
		if (node.i == n) {
			if (node.v > maxv) {
				maxv = node.v;
				System.arraycopy(node.x, 0, result, 0, node.i);
			}
		} else {
			queue.offer(node);
		}
	}

	/**
	 * 通过队列式分支限界法求解0/1背包问题
	 * 
	 * @param n
	 * @param w
	 * @param v
	 */
	public static void solveZeroOneBackPack(int n, int W, int[] w, int[] v) {

		// 通过队列式分支限界法求解0/1背包问题的思路如下：
		// 1.首先初始化一个队列，然后将根节点入队
		// 2.当队列不为空时，一直执行出队的操作
		// 3.构建其左孩子节点，然后判断其是否符合约定条件，符合的条件下，将其入队
		// 4.构建右孩子，并且也检查其是否符合约定条件，并将其入队
		BackPackNode e, e1, e2;
		// 记录入队节点的个数
		int count = 1;
		// 构建根节点,然后对其属性进行赋值
		e = new BackPackNode(count++, 0, 0, 0);
		for (int i = 0; i < MAXN; i++)
			e.x[i] = 0;
		// 计算该根节点的上界
		findBound(n, W, w, v, e);
		// 将根节点入队
		queue.offer(e);
		while (!queue.isEmpty()) {
			// 构建其左孩子节点，然后判断其是否符合约定条件，符合的条件下，将其入队
			BackPackNode temp = queue.poll();
			int no;
			if ((temp.w + w[temp.i + 1]) <= W) {
				no = count++;
				e1 = new BackPackNode(no, temp.i + 1, temp.w + w[temp.i + 1],
						temp.v + v[temp.i + 1]);
				System.arraycopy(temp.x, 0, e1.x, 0, temp.i);
				e1.x[e1.i] = 1;
				findBound(n, W, w, v, e1);
				enQueue(n, e1);
			}
			// 构建右孩子，并且也检查其是否符合约定条件，并将其入队
			no = count++;
			e2 = new BackPackNode(no, temp.i + 1, temp.w, temp.v);
			System.arraycopy(temp.x, 0, e2.x, 0, temp.i);
			e2.x[e2.i] = 0;
			findBound(n, W, w, v, e2);
			if (e2.ub > maxv) {
				enQueue(n, e2);
			}
		}

	}

	/**
	 * 计算分支节点e的上界
	 * 
	 * @param n
	 * @param w
	 * @param v
	 * @param e
	 */
	public static void findBound(int n, int W, int[] w, int v[], BackPackNode e) {
		int i = e.i + 1;
		int sumw = e.w;
		double sumv = e.v;
		while (i <= n && (sumw + w[i]) <= W) {
			sumw += w[i];
			sumv += v[i];
			i++;
		}
		// 表示的是该节点的下一个节点全部装入不了背包，只能装少数
		if (i <= n) {
			e.ub = sumv + (W - w[i]) * v[i] / w[i];
		} else {
			e.ub = sumv;
		}
	}

	public static void main(String[] args) {
		int w[] = { 0, 16, 15, 15 };
		int v[] = { 0, 45, 25, 25 };
		result = new int[MAXN];
		solveZeroOneBackPack(3, 30, w, v);
		for (int i = 1; i <= 3; i++) {
			System.out.print(result[i] + "\t");
		}
		System.out.println("\n总价值：" + maxv);

	}

}
