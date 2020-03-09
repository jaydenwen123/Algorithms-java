package com.wxf.data.graphicSearch;

public class PrimMinTree {

	// �ýڵ�����ģ���ʾ�����
	public static final int MAX = 100000;
	// �����Ա�ʾ������С�������Ķ�Ӧ�����
	public Object start;
	// �����Ա�ʾ������С�������Ķ�Ӧ�ıߵ�Ȩֵ
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
	 * �����ڽӾ���洢��ͼ����Ϣ��������С������
	 * 
	 * @param graphic
	 * @param trees
	 *            ��������Ҫ�洢���ǲ�ͬ����ͱߵ�Ȩֵ
	 * @param start
	 */
	public static void primCreateMinTree(MatrixGraphic graphic,
			PrimMinTree[] trees, int s) {

		// ͨ������ķ�㷨��������С��������˼·���£�
		// 1.������һ����ʼ�ڵ㡣���������ȨֵΪ-1����ʾ�ǳ�ʼ�ڵ�
		// 2.Ȼ�󽫸ýڵ���ӵ�trees������
		// 3.�Ӹýڵ㿪ʼ��Ȩֵ��С�ıߣ��ҵ��󽫱ߵ��յ��Ȩֵ�ֱ�ֵΪһ���µĽڵ㣬����ӵ�������
		// 4.һֱѭ��ֱ���������ĳ���Ϊ����ĸ���ʱ�������㷨

		// ��ȡ����ĸ���
		int n = graphic.getN();
		// ����������Ȩֵ
		int low[] = new int[n];
		// ��ʼ������
		for (int i = 0; i < n; i++) {
			low[i] = graphic.getEdge()[s][i];
		}

		Object start = graphic.getVertex(s);
		// ������ʼ�ڵ�
		PrimMinTree first = new PrimMinTree(start, -1);
		PrimMinTree node = null;
		trees[0] = first;

		// ��ʣ�µĶ�������Ȩֵ��С��
		for (int i = 1; i < n; i++) {
			int min = MAX;
			int index = -1;
			// ����С��Ȩֵ
			for (int j = 0; j < n; j++) {
				if (low[j] > 0 && low[j] < min) {
					min = low[j];
					index = j;
				}
			}
			// �����ڵ㣬��������ӵ�������
			node = new PrimMinTree(graphic.getVertex(index), min);
			trees[i] = node;
			// ��ʾ�ñ��Ѿ��ҹ�
			low[index] = -1;

			// �޸�low�е���ֵ
			for (int k = 0; k < n; k++) {
				int weight = graphic.getEdge()[index][k];
				if (weight < low[k]) {
					low[k] = weight;
				}
			}

		}

	}

	/**
	 * ��ʾ���
	 * 
	 * @param trees
	 */
	public static void display(PrimMinTree[] trees) {
		PrimMinTree node = trees[0];
		System.out.println("��������С���������£�");
		System.out.println("��ʼ�ڵ�Ϊ��" + node.start);
		for (int i = 1; i < trees.length; i++) {
			node = trees[i];
			System.out.println("��" + i + "�����㣺" + node.start + ",ȨֵΪ��"
					+ node.weight);

		}
		System.err.println("ע�⣺�ڽ��ʵ������ʱ����Ҫ���ԭ����Ĵ�Ȩͼ���������С������ͼ");
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
		PrimMinTree[] trees = new PrimMinTree[n];
		primCreateMinTree(graphic, trees, 0);
		display(trees);
	}
}
