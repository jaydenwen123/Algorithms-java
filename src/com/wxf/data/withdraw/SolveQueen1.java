package com.wxf.data.withdraw;

public class SolveQueen1 {

	public static int MAX = 20;
	public static int[] q = new int[MAX];

	/**
	 * 第一种方法
	 * 
	 * 不采用栈求解n皇后问题
	 * 
	 * @param n
	 */
	public static int solveQueen(int n) {

		// 不采用栈求解n皇后问题的思路如下：
		// 1.首先初始化，放置第一个元素为第一行第0列
		// 2.然后开始外层循环控制找出所有的解
		// 3.找所有解时，意味着需要从初始元素的第一列依次往右试探，知道最后到达第n列
		// 4.在所有的列中，该元素可以放置的条件是首先它必须在棋盘内，其次是可以防止的下，即place函数返回0
		// 5.当内层循环退出时，有两种情况，一种是该元素放不下，此时需要回溯，即k--。第二种情况是已经尝试完所有的列。
		// 此时需要判断，如果k=n则表示放置完所有的元素，即输出一组解，否则表示还有元素没放完，放置下一个元素

		// 第一步
		int i = 1;
		int count = 0;
		q[i] = 0;
		// 第二步
		while (i <= n && i > 0) {
			q[i] = q[i] + 1;
			// 第三部
			while (q[i] <= n && place(i) == 0) {
				q[i]++;
			}
			if (q[i] <= n) {
				// 第四步
				if (i == n) {
					dispasolutons(n);
					count++;
				} else {
					i++;
					q[i] = 0;
				}
				// 第五步
			} else {
				i--;
			}

		}
		return count;
	}

	/**
	 * 参考该代码
	 * 
	 * @param n
	 * @return
	 */
	public static int solveQueen1(int n) {
		int k = 1;
		int count = 0;
		q[k] = 0;
		while (k <= n && k > 0) {
			q[k] = q[k] + 1;
			while (q[k] <= n && place(k) == 0) {
				q[k]++;
			}
			if (q[k] <= n) {
				if (n == k) {
					dispasolutons(n);
					count++;
				} else {
					k++;
					q[k] = 0;
				}
			} else {
				k--;
			}
		}
		return count;
	}

	/**
	 * 展现结果
	 * 
	 * @param n
	 */
	private static void dispasolutons(int n) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= n; i++) {
			System.out.print("(" + i + "," + q[i] + ")\t");
		}
		System.out.println();
	}

	/**
	 * 放置第k个元素
	 * 
	 * @param k
	 * @return
	 */
	public static int place(int k) {
		int i = 1;
		while (i < k) {
			// 能放置该元素的条件是该元素
			if ((q[i] == q[k]) || (Math.abs(q[i] - q[k]) == Math.abs(i - k))) {
				return 0;
			}
			i++;
		}

		return 1;
	}


	public static void main(String[] args) {
		System.out.println("4皇后所有可能的情况如下：");
		int count = solveQueen(4);
		System.out.println("4皇后总共有" + count + "种方案");
		System.out.println("5皇后所有可能的情况如下：");
		count = solveQueen(5);
		System.out.println("5皇后总共有" + count + "种方案");
	}
}
