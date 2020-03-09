package com.wxf.data.graphicSearch;

import java.util.Arrays;

import com.wxf.data.enumm.findNumber;

/**
 * 根据克鲁斯卡尔算法来构建最小生成树
 * 
 * @author Administrator
 * 
 */
public class KruskalMinTree {

	// 该节点用来模拟表示无穷大
	public static final int MAX = 100000;
	public Object[] vertex;
	public int[] weight;

	public KruskalMinTree() {
		// TODO Auto-generated constructor stub
	}

	public KruskalMinTree(Object[] vertex, int[] weight) {
		super();
		this.vertex = vertex;
		this.weight = weight;
	}

	/**
	 * 根据克鲁斯卡尔算法来构建最小生成树
	 * 
	 * @param graphic
	 * @param trees
	 */
	public static void kruskalCreateMinTree(MatrixGraphic graphic,
			KruskalMinTree tree) {

		// 该算法构建最小生成树的思路如下：
		// 1.首先对图的所有边的权值进行递增排序（该处使用的是快速排序）
		// 2.然后依次找到从取值排序后的取值中，不断的取边，如果符合条件，则把其添加到数组中，集合
		// 3.最后当数组中的个数为n时算法结束
		Object[] ver = graphic.getVertex();
		// 获取所有变的信息
		int[][] edge = graphic.getEdge();
		// 获取顶点数
		int verCount = graphic.getN();
		// 获取边的个数
		int n = graphic.getEdgeCount();
		// 该数组主要保存
		Edge[] weight = new Edge[n];
		// 保存结果的数组
		Edge[] result = new Edge[n];
		// 保存边的终点
		int[] vends = new int[n];

		int k = 0;
		// 获取边的信息
		for (int i = 0; i < edge.length; i++) {
			for (int j = i + 1; j < edge[i].length; j++) {
				if (edge[i][j] > 0 && edge[i][j] < KruskalMinTree.MAX) {
					weight[k++] = new Edge(ver[i], ver[j], edge[i][j]);
				}
			}
		}
		// 对其递增排序
		quickSort(weight, 0, n - 1);
		System.out.println("排序后：");
		System.out.println(Arrays.toString(weight));
		// 接下来就是从weight中不断的取最小的权值，然后在构建节点并加入到数组中
		int index = 0;
		for (k = 0; k < n; k++) {
			int p1 = graphic.getPosition(weight[k].start);
			int p2 = graphic.getPosition(weight[k].end);
			int e1 = getEnd(vends, p1);
			int e2 = getEnd(vends, p2);
			if (e1 != e2) {
				vends[e1] = e2;
				result[index++] = weight[k];
			}
		}
		int length = 0;
		for (int i = 0; i < index; i++)
			length += result[i].weight;
		System.out.println(length);
		for (int i = 0; i < index; i++)
			System.out.print("("+result[i].start+","+result[i].end+")"+"\t");
		System.out.printf("\n");
	}

	/**
	 * 获取i的终点
	 * 
	 * @param edges
	 * @param p
	 * @return
	 */
	public static int getEnd(int[] edges, int p) {
		while (edges[p] != 0)
			p = edges[p];
		return p;
	}

	/**
	 * 快速排序，该算法需要对其不同边的权值进行递增排序
	 * 
	 * @param weight
	 */
	public static void quickSort(Edge[] weight, int low, int high) {
		// 快速排序的思路如下：
		// 1.首先以第一个数为初始节点，然后不断的着其合适的位置，
		// 最后是的其后面的元素都比其大，在其前面数都比其小
		// 2.然后再左右区间进行递归即可
		int i = low, j = high;
		Edge temp = weight[low];
		while (i != j) {
			while (i < j && temp.weight <= weight[j].weight)
				j--;
			if (i < j) {
				weight[i] = weight[j];
				i++;
			}
			while (i < j && temp.weight >= weight[i].weight)
				i++;
			if (i < j) {
				weight[j] = weight[i];
				j--;
			}
		}
		// 循环退出时表示找到了数字temp的合适的位置
		weight[i] = temp;
		// 然后再左右区间进行递归排序
		if (low < i) {
			quickSort(weight, low, i - 1);
		}
		if (i < high) {
			quickSort(weight, i + 1, high);
		}
	}

	/**
	 * 边的信息
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Edge {
		public Object start;
		public Object end;
		public int weight;

		public Edge() {
			// TODO Auto-generated constructor stub
		}

		public Edge(Object start, Object end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight="
					+ weight + "]";
		}

	}

	public static void main(String[] args) {
		int n = 7;
		MatrixGraphic graphic = new MatrixGraphic(7, MAX);
		graphic.addVertex(new Object[] { "A", "B", "C", "D", "E", "F", "G" });

		graphic.addEdge(0, 1, 50);
		graphic.addEdge(0, 2, 60);

		graphic.addEdge(1, 3, 65);
		graphic.addEdge(1, 4, 40);

		graphic.addEdge(2, 3, 52);
		graphic.addEdge(2, 6, 45);

		graphic.addEdge(3, 4, 50);
		graphic.addEdge(3, 5, 30);
		graphic.addEdge(3, 6, 42);

		graphic.addEdge(4, 5, 70);

		System.out.println("图的邻接矩阵：");
		graphic.printGraphic();
		KruskalMinTree trees = new KruskalMinTree();
		trees.weight = new int[n - 1];
		trees.vertex = new Object[n];
		 kruskalCreateMinTree(graphic, trees);
	}
}
