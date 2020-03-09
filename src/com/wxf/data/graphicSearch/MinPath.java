package com.wxf.data.graphicSearch;

import java.util.Arrays;

public class MinPath {

	public static final int MAX = 100000;

	/**
	 * 通过狄克斯特拉算法求最短路径
	 * 
	 * @param graphic
	 * @param v
	 * @param dist
	 * @param path
	 */
	public static void dijstra(MatrixGraphic graphic, Object v, int[] dist,
			int path[]) {

		// 通过狄克斯特拉算法求解最短路径问题的思路如下：
		// 1.首先通过一个数组保存已经找过的最短路径，一开始只有初始的节点
		// 2.然后每次给该数组中添加一个以上一次添加的节点为原点的权值最小的节点
		// 3.如果Wuj>Wuv+Wvj，则进行重新给定j节点的最小路径
		// 4.直到当该数组中的元素个数为节点的个数时，算法结束
		int n = graphic.getN();
		int[] s = new int[n];
		int k = 0, index = 0, weight, i;
		index = graphic.getPosition(v);
		for (int m = 0; m < n; m++) {
			dist[m] = graphic.getEdge()[index][m];
			s[m] = 0;
			if (dist[m] < MAX)
				path[m] = index;
			else {
				path[m] = -1;
			}
		}
		// 保存最短路径（图中的节点的下标）
		path[index] = 0;
		// 该变量用来保存起始节点的下标
		i = index;
		s[i] = 1;
		for (int j = 0; j < n; j++) {
			// 找到从初始节点出发的权值最小的节点
			int min = MAX;
			for (k = 0; k < n; k++) {
				// 保存最短路径的距离
				if (path[k] == 0 && dist[k] < min) {
					min = dist[k];
					index = k;
				}
			}
			s[index] = 1;
			for (k = 0; k < n; k++) {
				if (s[k] == 0) {
					if (graphic.getEdge()[index][k] < MAX
							&& graphic.getEdge()[index][k] + dist[index] < dist[k]) {
						dist[k] = graphic.getEdge()[index][k] + dist[index];
						path[k] = index;
					}
				}
			}
			System.out.println(Arrays.toString(dist));
			System.out.println(Arrays.toString(path));
		}
	}

	public static void main(String[] args) {
		MatrixGraphic graphic = new MatrixGraphic(7, MAX);
		graphic.addVertex(new Object[] { 0, 1, 2, 3, 4, 5, 6 });
		int weight[][] = new int[][] { { 0, 4, 6, 6, MAX, MAX, MAX },
				{ MAX, 0, 1, MAX, 7, MAX, MAX },
				{ MAX, MAX, 0, MAX, 6, 4, MAX },
				{ MAX, MAX, 2, 0, MAX, 5, MAX },
				{ MAX, MAX, MAX, MAX, 0, MAX, 6 },
				{ MAX, MAX, MAX, MAX, 1, 0, 8 },
				{ MAX, MAX, MAX, MAX, MAX, MAX, 0 }, };
		graphic.addEdge(weight);
		System.out.println("图的邻接矩阵：");
		graphic.printGraphic();
		int count = graphic.getEdgeCount(true);
		System.out.println("图的边的个数为：" + count);
		int[] dist = new int[7];
		int[] path = new int[7];
		dijstra(graphic, 0, dist, path);

	}
}
