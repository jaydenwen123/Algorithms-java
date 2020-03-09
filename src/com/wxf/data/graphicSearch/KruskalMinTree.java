package com.wxf.data.graphicSearch;

import java.util.Arrays;

import com.wxf.data.enumm.findNumber;

/**
 * ���ݿ�³˹�����㷨��������С������
 * 
 * @author Administrator
 * 
 */
public class KruskalMinTree {

	// �ýڵ�����ģ���ʾ�����
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
	 * ���ݿ�³˹�����㷨��������С������
	 * 
	 * @param graphic
	 * @param trees
	 */
	public static void kruskalCreateMinTree(MatrixGraphic graphic,
			KruskalMinTree tree) {

		// ���㷨������С��������˼·���£�
		// 1.���ȶ�ͼ�����бߵ�Ȩֵ���е������򣨸ô�ʹ�õ��ǿ�������
		// 2.Ȼ�������ҵ���ȡֵ������ȡֵ�У����ϵ�ȡ�ߣ���������������������ӵ������У�����
		// 3.��������еĸ���Ϊnʱ�㷨����
		Object[] ver = graphic.getVertex();
		// ��ȡ���б����Ϣ
		int[][] edge = graphic.getEdge();
		// ��ȡ������
		int verCount = graphic.getN();
		// ��ȡ�ߵĸ���
		int n = graphic.getEdgeCount();
		// ��������Ҫ����
		Edge[] weight = new Edge[n];
		// ������������
		Edge[] result = new Edge[n];
		// ����ߵ��յ�
		int[] vends = new int[n];

		int k = 0;
		// ��ȡ�ߵ���Ϣ
		for (int i = 0; i < edge.length; i++) {
			for (int j = i + 1; j < edge[i].length; j++) {
				if (edge[i][j] > 0 && edge[i][j] < KruskalMinTree.MAX) {
					weight[k++] = new Edge(ver[i], ver[j], edge[i][j]);
				}
			}
		}
		// �����������
		quickSort(weight, 0, n - 1);
		System.out.println("�����");
		System.out.println(Arrays.toString(weight));
		// ���������Ǵ�weight�в��ϵ�ȡ��С��Ȩֵ��Ȼ���ڹ����ڵ㲢���뵽������
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
	 * ��ȡi���յ�
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
	 * �������򣬸��㷨��Ҫ���䲻ͬ�ߵ�Ȩֵ���е�������
	 * 
	 * @param weight
	 */
	public static void quickSort(Edge[] weight, int low, int high) {
		// ���������˼·���£�
		// 1.�����Ե�һ����Ϊ��ʼ�ڵ㣬Ȼ�󲻶ϵ�������ʵ�λ�ã�
		// ����ǵ�������Ԫ�ض����������ǰ����������С
		// 2.Ȼ��������������еݹ鼴��
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
		// ѭ���˳�ʱ��ʾ�ҵ�������temp�ĺ��ʵ�λ��
		weight[i] = temp;
		// Ȼ��������������еݹ�����
		if (low < i) {
			quickSort(weight, low, i - 1);
		}
		if (i < high) {
			quickSort(weight, i + 1, high);
		}
	}

	/**
	 * �ߵ���Ϣ
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

		System.out.println("ͼ���ڽӾ���");
		graphic.printGraphic();
		KruskalMinTree trees = new KruskalMinTree();
		trees.weight = new int[n - 1];
		trees.vertex = new Object[n];
		 kruskalCreateMinTree(graphic, trees);
	}
}
