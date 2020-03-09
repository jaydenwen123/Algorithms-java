package com.wxf.data.enumm;


/**
 * @author Administrator
 * 
 */
public class SolvePowSet2 {

	// 最大的自己元素的个数
	public static final int MAXSIZE = 1000;
	// 最大的n的个数
	public static final int MAXN = 10;
	// 存放不同的子集元素，其中，data[i][0]表示的是该子集元素的个数
	public int[][] data;
	// 子集的个数
	public int n;

	public SolvePowSet2(int[][] data, int n) {
		super();
		this.data = data;
		this.n = n;
	}

	public SolvePowSet2(int[][] data) {
		super();
		this.data = data;
	}

	public SolvePowSet2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过递归方法结合穷举法来解决幂集问题 该方法表示，将元素i插入到子集中
	 * 
	 * @param i
	 * @param n
	 * @return
	 */
	public static SolvePowSet2 solvePowSet(int i, int n,
			SolvePowSet2 solvePowSet2) {
		int temp[] = new int[MAXN];
		// 获取子集的个数
		int m = solvePowSet2.n;
		if (i <= n) {
			for (int j = 0; j < m; j++) {
				// 将元素插入到子集中
				// 1.首先
				copyArray(solvePowSet2.data[j], temp, solvePowSet2.data[j][0]);
				temp[0]++;
				temp[temp[0]] = i;
				copyArray(temp, solvePowSet2.data[solvePowSet2.n], temp[0]);
				solvePowSet2.n++;
			}

			solvePowSet(i + 1, n, solvePowSet2);
		}

		return solvePowSet2;
	}

	/**
	 * 通过常规方法解决幂集问题
	 * 
	 * @param n
	 * @return
	 */
	public static SolvePowSet2 solvePowSet(int n) {

		SolvePowSet2 solvePowSet2 = new SolvePowSet2(new int[MAXSIZE][MAXN]);
		// 采用增量穷举法的思路如下：
		// 1.首先初始化一个SolvePowSet2对象，该对象中的data存储不同子集的数据元素
		// 2.接着首先定义一个空集，将其先添加到上述对象中
		solvePowSet2.data[0][0] = 0;
		solvePowSet2.n = 1;
		int temp[] = new int[MAXN];
		// 3.从1到n进行一次循环，每次循环表示增加数字
		for (int i = 1; i <= n; i++) {
			// 4.增加数字的过程如下：
			// a.首先思想是找到目前有多少个子集,
			int m = solvePowSet2.n;
			for (int j = 0; j < m; j++) {
				copyArray(solvePowSet2.data[j], temp, solvePowSet2.data[j][0]);
				// b.然后再对每个子集添加该元素
				// 元素的个数加一
				temp[0]++;
				// 在将其该元素插入
				temp[temp[0]] = i;
				// c.添加完成后将其保存到上述对象中
				copyArray(temp, solvePowSet2.data[solvePowSet2.n], temp[0]);
				// d.子集的个数增加1
				solvePowSet2.n++;
			}
		}
		// for(int i=0;i<solvePowSet2.n;i++){
		// System.out.println(Arrays.toString(solvePowSet2.data[i]));
		// }
		return solvePowSet2;
	}

	/**
	 * 
	 * 输出全部的幂级
	 * 
	 * @param solvePowSet2
	 */
	public static void display(SolvePowSet2 solvePowSet2) {
		int[][] data = solvePowSet2.data;
		int n = solvePowSet2.n;
		for (int i = 0; i < n; i++) {
			System.out.print("{  ");
			for (int j = 1; j <= data[i][0]; j++) {
				System.out.print(data[i][j] + "  ");
			}
			System.out.print("}  ");

		}
	}

	/**
	 * 将a数组中的m个数据元素复制到b数组中
	 * 
	 * @param a
	 * @param b
	 * @param m
	 */
	public static void copyArray(int[] a, int b[], int m) {
		for (int i = 0; i <= m; i++) {
			b[i] = a[i];
		}
	}

	public static void main(String[] args) {
		SolvePowSet2 solvePowSet2 = new SolvePowSet2(new int[MAXSIZE][MAXN]);
		// 赋初值，将空集添加到该集合中
		solvePowSet2.data[0][0] = 0;
		solvePowSet2.n = 1;
		
		
		SolvePowSet2 set2 = solvePowSet(3);
		System.out.println("通过常规方法求解幂级数问题：");
		display(set2);
		System.out.println();
		System.out.println("通过递归方法求解幂集问题：");
		set2 = solvePowSet(1, 3,solvePowSet2);
		display(set2);

	}
}
