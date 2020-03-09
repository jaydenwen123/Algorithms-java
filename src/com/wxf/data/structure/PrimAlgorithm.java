package com.wxf.data.structure;

import java.util.Arrays;

public class PrimAlgorithm {

	public PrimAlgorithm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 最小生成树的每个节点信息
	 * 
	 * @author Administrator
	 * 
	 */
	public static class MInTree {
		// 1.狐头点的信息
		public Object vertex;
		// 2.点的权值
		public int weigh;

		public MInTree() {
			// TODO Auto-generated constructor stub
		}

		public MInTree(Object vertex, int weigh) {
			super();
			this.vertex = vertex;
			this.weigh = weigh;
		}

	}

	/**
	 * 普利姆算法构造最小生成树
	 */
	public static void prim(Graphic g, MInTree[] treeInfo) {
		// 1。首先是找一个初值，然后从初值开始查找，
		int num = g.getVerNums();
		int j = 0;
		MInTree temp = null;
		int low[] = new int[num];
//		首先将low里面存放集合第零行的值
		for (int i = 1; i < num; i++)
			low[i] = g.getWeigh(0, i);
		MInTree first = new MInTree(g.getVertexs()[0], -1);
		treeInfo[0] = first;
		low[0] = -1;
		// 获取全部的边
		for (int m = 1; m < num; m++) {
			// 2.在初值节点中找到边权值最小的节点
			int min = 100000;
			for (int i = 1; i < num; i++) {
				if (low[i] > 0 && low[i] < min) {
					min = low[i];
					j = i;
				}
			}
			temp = new MInTree(g.getVertexs()[j], min);
			// 3.将其最小的节点放入到treeINfo中
			treeInfo[m] = temp;
			low[j] = -1;
			// 4.在依次的循环。直到最后treeInfo的数组大小个数等于g的节点的值即可。
			for (int k = 1; k < num; k++) {
				if (g.getWeigh(j, k) < low[k]) {
					low[k] = g.getWeigh(j, k);
					System.out.println(Arrays.toString(low));
				}
		}
		}
		
	}

	/**
	 * 带权值的无向图
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Graphic {
		// 图的属性
		// 1.点集合
		private Object[] vertexs;
		// 2.边信息的集合包括权值（0，表示节点之间没有边，否则该值表示的是权值）
		private int[][] edges;
		// 3.点数
		private int verNums = 0;

		private int size = 0;

		public Graphic(int size) {
			// TODO Auto-generated constructor stub

			init(size);
		}

		/**
		 * ' 返回点的位置
		 * 
		 * @param obj
		 * @return
		 */
		public int getIndex(Object obj) {
			int index = -1;
			for (int i = 0; i < verNums; i++) {
				if (obj.equals(vertexs[i]))
					index = i;
			}
			return index;

		}

		// 图的方法
		// 初始化图
		public void init(int size) {
			vertexs = new Object[size];
			edges = new int[size][size];
			verNums = size;
			for (int i = 0; i < verNums; i++)
				for (int j = 0; j < verNums; j++)
					edges[i][j] = edges[j][i] = 100000;

		}

		// 1.添加边从点a到点b
		public void addEdge(int v1, int v2, int weigh) {
			checkVertex(v1, v2);
			edges[v1][v2] = edges[v2][v1] = weigh;
		}

		private void checkVertex(int v1, int v2) {
			if (v1 < 0 || v1 >= verNums || v2 < 0 || v2 >= verNums || v1 == v2)
				throw new RuntimeException(
						" the v1 and v2 arguement is error!!!");
		}

		// 2.添加点
		public void addVertex(Object obj) {
			if (size == verNums)
				throw new RuntimeException(" the vertex is full");
			vertexs[size++] = obj;
		}

		// 3.删除从a到b的边
		public void deleteEdge(int v1, int v2) {
			checkVertex(v1, v2);
			edges[v1][v2] = 0;
		}

		// 4.删除节点
		public void deleteVertex(Object obj) {
			System.out.println("delete vertex!!!");
		}

		// 5.获取点a的第一个邻接节点下标
		public int getFirstAdj(int i) {
			int index = -1;
			for (int j = 0; j < verNums; j++) {
				if (edges[i][j] != 0)
					return edges[i][j];
			}
			return index;
		}

		// 6.获取点b的最后一个邻接节点下标
		public int getLastAdj(int i) {
			int index = -1;
			for (int j = verNums - 1; j >= 0; j--) {
				if (edges[i][j] != 0)
					return edges[i][j];
			}
			return index;
		}

		// 7.获取点a的邻接节点b的下一个邻接节点下标
		public int getPreAdj(int i, int j) {
			int index = -1;
			for (int k = j - 1; k >= 0; k--) {
				if (edges[i][k] != 0)
					return edges[i][k];
			}
			return index;
		}

		// 8.获取点a的邻接节点b的上一个邻接节点下标
		public int getNext(int i, int j) {
			int index = -1;
			for (int k = j + 1; k < verNums; k++) {
				if (edges[i][k] != 0)
					return edges[i][k];
			}
			return index;
		}

		// 获取权值
		public int getWeigh(int v1, int v2) {
			// checkVertex(v1, v2);
			return edges[v1][v2];
		}

		public Object[] getVertexs() {
			return vertexs;
		}

		public void setVertexs(Object[] vertexs) {
			this.vertexs = vertexs;
		}

		public int[][] getEdges() {
			return edges;
		}

		public void setEdges(int[][] edges) {
			this.edges = edges;
		}

		public int getVerNums() {
			return verNums;
		}

		public void setVerNums(int verNums) {
			this.verNums = verNums;
		}

		public Graphic(Object[] vertexs, int[][] edges, int verNums) {
			super();
			init(verNums);
			this.vertexs = vertexs;
			this.edges = edges;
			this.verNums = verNums;
		}

	}

	public static void main(String[] args) {
		Graphic g = new Graphic(7);
		g.addVertex("A");// 0
		g.addVertex("B");// 1
		g.addVertex("C");// 2
		g.addVertex("D");// 3
		g.addVertex("E");// 4
		g.addVertex("F");// 5
		g.addVertex("G");// 6
		// a-b a-c
		g.addEdge(0, 1, 50);
		g.addEdge(0, 2, 60);
		// b-d b-e
		g.addEdge(1, 3, 65);
		g.addEdge(1, 4, 40);
		// c-d c-g
		g.addEdge(2, 3, 52);
		g.addEdge(2, 6, 45);
		// d-f d-g
		g.addEdge(3, 4, 50);
		g.addEdge(3, 5, 30);
		g.addEdge(3, 6, 42);
		// e-f
		g.addEdge(4, 5, 70);

		for (int i = 0; i < g.getVerNums(); i++) {
			for (int j = 0; j < g.getVerNums(); j++) {
				System.out.print(g.getWeigh(i, j) + "\t");
			}
			System.out.println();
		}
		// System.out.println("测试其他的方法对不对");
		// System.out.println(g.getFirstAdj(3));
		// System.out.println(g.getLastAdj(3));
		// System.out.println("*****************");
		// System.out.println(g.getNext(3, 2));
		// System.out.println(g.getPreAdj(3, 2));
		MInTree[] treeInfo = new MInTree[7];
		prim(g, treeInfo);
		for (MInTree tree : treeInfo)
			System.out.println(tree.vertex + "," + tree.weigh);
	}
}
