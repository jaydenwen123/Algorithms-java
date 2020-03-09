package com.wxf.data.graphicSearch;

public class PrimMinTree {

	// 该节点用来模拟表示无穷大
	public static final int MAX = 100000;
	// 该属性表示的是最小生成树的对应的起点
	public Object start;
	// 该属性表示的是最小生成树的对应的边的权值
	public int weight;

	public PrimMinTree() {
		// TODO Auto-generated constructor stub
	}

	public PrimMinTree(Object start, int weight) {
		super();
		this.start = start;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "PrimMinTree [start=" + start + ", weight=" + weight + "]";
	}

	/**
	 * 根据邻接矩阵存储的图的信息来构建最小生成树
	 * 
	 * @param graphic
	 * @param trees
	 *            该数组主要存储的是不同顶点和边的权值
	 * @param start
	 */
	public static void primCreateMinTree(MatrixGraphic graphic,
			PrimMinTree[] trees, int s) {

		// 通过普利姆算法来构建最小生成树的思路如下：
		// 1.首先找一个初始节点。并且设计其权值为-1，表示是初始节点
		// 2.然后将该节点添加到trees数组中
		// 3.从该节点开始找权值最小的边，找到后将边的终点和权值分别赋值为一个新的节点，并添加到数组中
		// 4.一直循环直到最后数组的长度为顶点的个数时，结束算法

		// 获取顶点的个数
		int n = graphic.getN();
		// 用来保存点的权值
		int low[] = new int[n];
		// 初始化数组
		for (int i = 0; i < n; i++) {
			low[i] = graphic.getEdge()[s][i];
		}

		Object start = graphic.getVertex(s);
		// 构建初始节点
		PrimMinTree first = new PrimMinTree(start, -1);
		PrimMinTree node = null;
		trees[0] = first;

		// 在剩下的顶点中找权值最小的
		for (int i = 1; i < n; i++) {
			int min = MAX;
			int index = -1;
			// 找最小的权值
			for (int j = 0; j < n; j++) {
				if (low[j] > 0 && low[j] < min) {
					min = low[j];
					index = j;
				}
			}
			// 创建节点，并将其添加到数组中
			node = new PrimMinTree(graphic.getVertex(index), min);
			trees[i] = node;
			// 表示该边已经找过
			low[index] = -1;

			// 修改low中的数值
			for (int k = 0; k < n; k++) {
				int weight = graphic.getEdge()[index][k];
				if (weight < low[k]) {
					low[k] = weight;
				}
			}

		}

	}

	/**
	 * 显示结果
	 * 
	 * @param trees
	 */
	public static void display(PrimMinTree[] trees) {
		PrimMinTree node = trees[0];
		System.out.println("构建的最小生成树如下：");
		System.out.println("开始节点为：" + node.start);
		for (int i = 1; i < trees.length; i++) {
			node = trees[i];
			System.out.println("第" + i + "个顶点：" + node.start + ",权值为："
					+ node.weight);

		}
		System.err.println("注意：在解决实际问题时，需要结合原问题的带权图来构造出最小生成树图");
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
		PrimMinTree[] trees = new PrimMinTree[n];
		primCreateMinTree(graphic, trees, 0);
		display(trees);
	}
}
