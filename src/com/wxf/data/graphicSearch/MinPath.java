package com.wxf.data.graphicSearch;

import java.util.Arrays;

public class MinPath {

	public static final int MAX = 100000;

	/**
	 * ͨ���ҿ�˹�����㷨�����·��
	 * 
	 * @param graphic
	 * @param v
	 * @param dist
	 * @param path
	 */
	public static void dijstra(MatrixGraphic graphic, Object v, int[] dist,
			int path[]) {

		// ͨ���ҿ�˹�����㷨������·�������˼·���£�
		// 1.����ͨ��һ�����鱣���Ѿ��ҹ������·����һ��ʼֻ�г�ʼ�Ľڵ�
		// 2.Ȼ��ÿ�θ������������һ������һ����ӵĽڵ�Ϊԭ���Ȩֵ��С�Ľڵ�
		// 3.���Wuj>Wuv+Wvj����������¸���j�ڵ����С·��
		// 4.ֱ�����������е�Ԫ�ظ���Ϊ�ڵ�ĸ���ʱ���㷨����
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
		// �������·����ͼ�еĽڵ���±꣩
		path[index] = 0;
		// �ñ�������������ʼ�ڵ���±�
		i = index;
		s[i] = 1;
		for (int j = 0; j < n; j++) {
			// �ҵ��ӳ�ʼ�ڵ������Ȩֵ��С�Ľڵ�
			int min = MAX;
			for (k = 0; k < n; k++) {
				// �������·���ľ���
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
		System.out.println("ͼ���ڽӾ���");
		graphic.printGraphic();
		int count = graphic.getEdgeCount(true);
		System.out.println("ͼ�ıߵĸ���Ϊ��" + count);
		int[] dist = new int[7];
		int[] path = new int[7];
		dijstra(graphic, 0, dist, path);

	}
}
