package com.wxf.huawei.comp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * ͨ���ڽӾ������洢ͼ����Ϣ
 * @author Administrator
 *
 */
public class MyAdjGraph {

	// ͼ����������Ϣ��
	// 1.��
	// 2.���Լ�Ȩֵ
	// 3.��ĸ���
	private Object[] vertex;
	private int[][] edges;
	private int verCounts;

	// �ж�������ͼ��������ͼ
	private boolean hasDir;

	// ����ͼ�Ľڵ��Ƿ��ѷ���
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
		// ���������ͼ���д���
		if (!hasDir) {
			init();
		}
	}

	public MyAdjGraph(int verCounts, Object[] vertex, boolean hasDir) {
		this();
		this.vertex = vertex;
	}

	/**
	 * ��ʼ���ߵ���Ϣ�����ǹ�������ͼ�Ĵ���
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
	 * ��ӱߵ���Ϣ
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
	 * ��ӵ����Ϣ
	 * 
	 * @param vertex
	 */
	public void addVertex(Object[] vertex) {
		this.vertex = vertex;
	}

	/**
	 * Ϊ������ԣ���ʾͼ���ڽӾ������Ϣ
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
	 * ��ȡĳ�ڵ�ĵ�һ���ڽӽڵ�
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
	 * ��ȡi�ڵ��j�ڽӽڵ����һ���ڽӽڵ�
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
	 * ��ȡi�ڵ�����һ���ڽӽڵ�
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
	 * ��ȡi�ڵ��j�ٽڽڵ�����һ���ڽӽڵ�
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
	 * ͼ��������ȱ��� ���ݹ飩
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
	 * ����ĳ���ڵ�
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
	 * ͼ��������ȱ���������ջ��
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
	 * ͼ�Ĺ�����ȱ��������У�
	 */
	public void broadTraverse() {
		// 1.��ʼ����2.����
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
	 * �������Ƿ������������г�ʼ��
	 */
	public void reset() {
		for (int i = 0; i < verCounts; i++)
			visit[i] = false;
	}

	public static void main(String[] args) {
		int verCount = 5;
		Object[] vertex = new Object[] { "A", "B", "C", "D", "E" };
		// ��������ͼ
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
