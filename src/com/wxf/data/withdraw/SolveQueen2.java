package com.wxf.data.withdraw;

public class SolveQueen2 {

	public SolveQueen2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构建栈
	 * 
	 * @author Administrator
	 * 
	 */
	public static class QueenStack {
		public int[] q = new int[20];
		public int top;

		public QueenStack() {
			// TODO Auto-generated constructor stub
		}

	}

	/**
	 * 采用栈求解n皇后问题
	 * 
	 * @param n
	 */
	public static int solveQueenWithStack(int n) {
		// 采用栈来求解n皇后问题的思路如下：
		// 1.初始化第1个元素然后将其入栈
		// 2.当栈不为空时，一直循环，取栈顶元素的皇后序号k
		// 3.从第k个皇后的下一列到第n列中找一个合适的位置，如果存在这样的位置（k，j），将栈顶皇后的序号修改为j。
		// 如果k==n表示所有皇后放置完毕，输出占中元素构成的一个解；如果k<n表示皇后没有放完，将第k+1个皇后进栈
		// ，新近的皇后的序号均为0开始试探，如果不存在这样的位置（k,j），表示第k个皇后放不下，则回溯到第k-1个皇后，也就是退栈一次
		// 让第k-1个皇后变为栈顶皇后，然后做同样的处理，整个过程循环到栈空为止
		QueenStack stack = new QueenStack();
		int k = 1,count=0;
		stack.top = 0;
		stack.top++;
		stack.q[stack.top] = 0;
		while (stack.top > 0) {
			k = stack.top;
			int find = 0;
			for (int j = stack.q[k]+1; j <= n; j++) {
				// 找一个合适的位置
				if (place(stack, k, j) != 0) {
					stack.q[stack.top] = j;
					find = 1;
					break;
				}
			}
			if (find == 1) {
				if (k == n) {
					display(stack);
					count++;
				} else {
					// 将第k+1个元素入栈
					stack.top++;
					stack.q[stack.top] = 0;
				}
			} else {
				stack.top--;
			}
		}

		return count;
	}

	/**
	 * 检测是否可以放得下第k个皇后
	 * 
	 * @param stack
	 * @param k
	 * @param j
	 * @return
	 */
	public static int place(QueenStack stack, int k, int j) {
		int i = 1;
		if (k == 1)
			return 1;
		while (i < k) {
			if (stack.q[i] == j
					|| (Math.abs(j - stack.q[i]) == Math.abs(i - k)))
				return 0;
			i++;
		}
		return 1;
	}

	public static void display(QueenStack stack) {
		for (int i = 1; i <= stack.top; i++) {
			System.out.print("(" + i + "," + stack.q[i] + ")\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("4皇后所有可能的情况如下：");
		int count = solveQueenWithStack(4);
		System.out.println("4皇后总共有" + count + "种方案");
		System.out.println("5皇后所有可能的情况如下：");
		count = solveQueenWithStack(5);
		System.out.println("5皇后总共有" + count + "种方案");
	}

}
