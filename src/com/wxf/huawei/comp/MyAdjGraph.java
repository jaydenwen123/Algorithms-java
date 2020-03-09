package com.wxf.huawei.comp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 通过邻接矩阵来存储图的信息
 * @author Administrator
 *
 */
public class MyAdjGraph {

	// 图包含以下信息：
	// 1.点
	// 2.边以及权值
	// 3.点的个数
	private Object[] vertex;
	private int[][] edges;
	private int verCounts;

	// 判断是有向图还是无向图
	private boolean hasDir;

	// 保存图的节点是否已访问
	private boolean[] visit;

	public Object[] getVertex() {
		return vertex;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {

		this.edges = edges;
	}

	public int getVerCounts() {
		return verCounts;
	}

	public void setVerCounts(int verCounts) {
		this.verCounts = verCounts;
	}

	public MyAdjGraph() {
		// TODO Auto-generated constructor stub
	}

	public MyAdjGraph(int verCounts, boolean hasDir) {
		super();
		this.verCounts = verCounts;
		this.hasDir = hasDir;
		this.visit = new boolean[verCounts];
		edges = new int[verCounts][verCounts];
		// 如果是无向图进行处理
		if (!hasDir) {
			init();
		}
	}

	public MyAdjGraph(int verCounts, Object[] vertex, boolean hasDir) {
		this();
		this.vertex = vertex;
	}

	/**
	 * 初始化边的信息（这是关于无向图的处理）
	 */
	public void init() {
		for (int i = 0; i < verCounts; i++) {
			for (int j = 0; j < verCounts; j++) {
				if (i == j)
					edges[i][j] = 0;
			}
		}
	}

	/**
	 * 添加边的信息
	 * 
	 * @param i
	 * @param j
	 * @param weight
	 */
	public void addEdge(int i, int j, int weight) {
		if (i >= 0 && i < verCounts && j < verCounts && j >= 0) {
			if (!hasDir) {
				edges[i][j] = edges[j][i] = weight;
			} else {
				edges[i][j] = weight;
			}
		}

	}

	/**
	 * 添加点的信息
	 * 
	 * @param vertex
	 */
	public void addVertex(Object[] vertex) {
		this.vertex = vertex;
	}

	/**
	 * 为方便调试，显示图的邻接矩阵的信息
	 */
	public void printMatrix() {

		for (int i = 0; i < verCounts; i++) {
			for (int j = 0; j < verCounts; j++) {
				System.out.print(edges[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 获取某节点的第一个邻接节点
	 * 
	 * @return
	 */
	public int getFirstAdj(int i) {
		int k = 0;
		while (k < verCounts && edges[i][k] == 0)
			k++;
		if (k < verCounts)
			return k;
		return -1;
	}

	/**
	 * 获取i节点的j邻接节点的下一个邻接节点
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int getNextAdj(int i, int j) {
		for (int k = j + 1; k < verCounts; k++) {
			if (edges[i][k] != 0)
				return k;
		}
		return -1;
	}

	/**
	 * 获取i节点的最后一个邻接节点
	 * 
	 * @param i
	 * @return
	 */
	public int getLastAdj(int i) {
		int j = verCounts - 1;
		while (j >= 0 && edges[i][j] == 0)
			j--;
		if (j > 0)
			return j;
		return -1;
	}

	/**
	 * 获取i节点的j临节节点的最后一个邻接节点
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int getPrevAdj(int i, int j) {
		int k = j - 1;
		while (k >= 0 && edges[i][k] == 0)
			k--;
		if (k >= 0)
			return k;
		return -1;
	}

	/**
	 * 图的深度优先遍历 （递归）
	 */
	public void depthTraverse() {
		for (int i = 0; i < verCounts; i++)
			visit[i] = false;
		for (int i = 0; i < verCounts; i++)
			if (!visit[i])
				traverseOne(i);
		System.out.println();
	}

	/**
	 * 遍历某个节点
	 */
	public void traverseOne(int i) {
		visit[i] = true;
		System.out.print(vertex[i] + "\t");
		for (int j = getFirstAdj(i); j > 0; j = getNextAdj(i, j)) {
			if (!visit[j]) {
				traverseOne(j);
			}
		}
	}

	/**
	 * 图的深度优先遍历（采用栈）
	 */
	public void depthTraverseStack() {
		reset();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < verCounts; i++) {
			if (!visit[i]) {
				stack.push(i);
				visit[i] = true;
				while (!stack.isEmpty()) {
					int el = stack.pop().intValue();
					System.out.print(vertex[el] + "\t");
					int k = getLastAdj(el);
					while (k >= 0) {
						if (!visit[k]) {
							stack.push(k);
							visit[k] = true;
						}
						k = getPrevAdj(el, k);
					}
				}
			}
		}
		System.out.println();
	}

	/**
	 * 图的广度优先遍历（队列）
	 */
	public void broadTraverse() {
		// 1.初始化，2.查找
		Queue<Integer> queue = new LinkedList<Integer>();
		reset();
		for (int i = 0; i < verCounts; i++) {
			if (!visit[i]) {
				queue.add(i);
				visit[i] = true;
				while (!queue.isEmpty()) {
					int el = queue.poll().intValue();
					System.out.print(vertex[el] + "\t");
					for (int k = getFirstAdj(el); k >= 0; k = getNextAdj(el, k)) {
						if (!visit[k]) {
							queue.add(k);
							visit[k] = true;
						}
					}
				}
			}
		}
	}

	/**
	 * 将保存是否遍历的数组进行初始化
	 */
	public void reset() {
		for (int i = 0; i < verCounts; i++)
			visit[i] = false;
	}

	public static void main(String[] args) {
		int verCount = 5;
		Object[] vertex = new Object[] { "A", "B", "C", "D", "E" };
		// 创建无向图
		MyAdjGraph graph = new MyAdjGraph(verCount, true);

		graph.addVertex(vertex);

		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 4, 20);

		graph.addEdge(1, 3, 30);
		graph.addEdge(2, 1, 40);

		graph.addEdge(3, 2, 50);
		System.out.println("print the graph Matrix!!!");
		graph.printMatrix();

		System.out.println("test ... the method");
		System.out.println(graph.getLastAdj(0));
		System.out.println(graph.getPrevAdj(0, graph.getLastAdj(1)));

		System.out.println("test the depth traverse!!!");
		graph.depthTraverse();
		System.out.println("test the depth traverse with stack!!!");
		graph.depthTraverseStack();
		System.out.println("test teh broad traverse!!!");
		graph.broadTraverse();

	}
}
