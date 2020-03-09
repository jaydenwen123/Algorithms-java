package com.wxf.data.graphicSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ������Ҫ��ʵ���˶�����ͼͨ���ڽӾ���洢���Լ�һЩ�����ķ���
 * 
 * ͬʱ����Ҫ��Ϊ�˺������С������ ���·�� �������ʵ����Ϊ�̵�
 * 
 * @author Administrator
 * 
 */
public class MatrixGraphic {

	/**
	 * ͨ���ڽӾ������洢ͼ����Ϣ
	 * 
	 * @author Administrator
	 * 
	 */

	// ͼ���ڽӾ���Ĵ洢
	// 1.�������ݽṹ

	// c�������
	private int n;
	// a���㼯��
	private Object[] vertex;
	// b���߼���
	private int[][] edge;
	// d��������������¼�Ƿ����
	private boolean[] visit;

	public MatrixGraphic() {
		// TODO Auto-generated constructor stub
	}

	public int getN() {
		return n;
	}

	/**
	 * ���ݽڵ��±꣬��ȡ�����Ԫ��
	 * 
	 * @param s
	 * @return
	 */
	public Object getVertex(int s) {
		return vertex[s];
	}

	public int[][] getEdge() {
		return edge;
	}

	/**
	 * ͨ��ֱ�Ӵ���ά�����������ߵ���Ϣ
	 * 
	 * @param weight
	 */
	public void addEdge(int[][] weight) {
		this.edge = weight;
	}

	/**
	 * ������
	 * 
	 * @param n
	 */
	public MatrixGraphic(int n) {
		super();
		this.n = n;
		vertex = new Object[n];
		edge = new int[n][n];
		// �Աߵ���Ϣ���г�ʼ��
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				edge[i][j] = 0;
			}
		}
		visit = new boolean[n];
	}

	/**
	 * ������
	 * 
	 * @param n
	 */
	public MatrixGraphic(int n, int initWeight) {
		super();
		this.n = n;
		vertex = new Object[n];
		edge = new int[n][n];
		// �Աߵ���Ϣ���г�ʼ��
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					edge[i][j] = 0;
				else {
					edge[i][j] = initWeight;
				}
			}
		}
		visit = new boolean[n];
	}

	/**
	 * ��ӱߵ���Ϣ
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j) {
		if (i < 0 || i >= n || j < 0 || j >= n) {
			return;
		}
		edge[i][j] = 1;
		// ���������仰����ʾ��������ͼ�������ʾ��������ͼ
		// edge[j][i] = 1;
	}

	/**
	 * ��ӱߵ���Ϣ,weight ��ʾ����Ȩֵ
	 * 
	 * @param i
	 * @param j
	 */
	public void addEdge(int i, int j, int weight) {
		if (i < 0 || i >= n || j < 0 || j >= n) {
			return;
		}
		edge[i][j] = weight;
		// ���������仰����ʾ��������ͼ�������ʾ��������ͼ
		edge[j][i] = weight;
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
	 * ͼ��������ȱ������ǵݹ�ṹ
	 */
	public void depthSearchNoStack(int i) {

		// ͼ��������ȱ����ǵݹ�ṹ�����˼·���£�
		// 1.ͨ��ջ��ģ�⣬���ȳ�ʼ��ջ
		// 2.����ʼ���Ľڵ���뵽ջ��
		// 3.��ջ��Ϊ��ʱ��һֱѭ��
		// 4.��ջ�е����ڵ�
		for (int j = 0; j < n; j++) {
			visit[j] = false;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(i);
		visit[i] = true;
		int k = 0;
		while (!stack.isEmpty()) {
			i = stack.pop();
			System.out.print(vertex[i] + "\t");
			// �Ӹýڵ�����һ���ڽӽڵ��ң�Ȼ�����δ���ʣ�����ջ,ֱ���Ѹ�Ԫ�ص����нڵ㶼������Ϊֹ
			k = getLastAdj(i);
			while (k >= 0) {
				if (!visit[k]) {
					visit[k] = true;
					stack.push(k);
				}
				k = getPreAdj(i, k);
			}
		}
		System.out.println();
	}

	/**
	 * ͼ��������ȱ��� �ݹ�ṹ
	 * 
	 * ע�⣺��ʵ����ĵڶ���ѭ����û�б�Ҫ�ģ���ֻ�Ǳ�֤��һ����ʼ��������
	 */
	public void depthSearch() {
		// ͼ��������ȱ�����˼·���£�
		// 1.������ȱ������õݹ�ṹ
		// 2.�ݹ���Ϊ���Ӹ����Ľڵ㿪ʼ�ҴӸýڵ��������һ��δ�����ʹ��Ľڵ㣬Ȼ���ٵݹ�
		// 3.�ݹ����Ϊ�������еĽڵ㶼������Ϊֹ���㷨����
		for (int i = 0; i < n; i++) {
			visit[i] = false;
		}
		// ��ʵ���forѭ�� ���Բ�Ҫ����Ϊ���£���Ϊ����������ͼ����ʱ�����Բ���Ҫ��
		// ���Ƕ�������ͼ���п����ڱ����Ĺ�����
		// ��������ͨ���������˸�ѭ�����Ա�֤�����Ķ�������ͨ��ͼ�ı���
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				depthSearch(i);
			}
		}
		System.out.println();
	}

	/**
	 * �����ýڵ�
	 * 
	 * @param graphic
	 * @param i
	 */
	private void depthSearch(int i) {
		// ���ȷ��ʸýڵ�
		System.out.print(vertex[i] + "\t");
		// Ȼ���ٰѸýڵ���Ϊ�ѷ���
		visit[i] = true;
		// �ҵ���Ԫ�صĵ�һ���ڽӽڵ㣬Ȼ��
		int k = getFirstAdj(i);
		while (k >= 0) {
			if (!visit[k]) {
				// visit[k] = true;
				depthSearch(k);
			}
			// ��i�ڵ�ĵ�һ���ڽӽڵ��������ڻ��ݣ���k�ڵ����һ���ڽӽڵ����
			k = getNextAdj(i, k);
		}
	}

	/**
	 * ��ø�Ԫ�صĵ�һ���ڽӽڵ�
	 * 
	 * @param i
	 * @return
	 */
	private int getFirstAdj(int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j < n; j++) {
			if (edge[i][j] != 0)
				return j;
		}
		return -1;
	}

	/**
	 * ���i�ڵ��j�ڵ����һ���ٽڽڵ�
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int getNextAdj(int i, int j) {
		for (int k = j + 1; k < n; k++) {
			if (edge[i][k] != 0)
				return k;
		}
		return -1;
	}

	/**
	 * ��ȡ�ڵ�i�����һ���ڽӽڵ�
	 * 
	 * @param i
	 * @return
	 */
	private int getLastAdj(int i) {
		for (int k = n - 1; k >= 0; k--) {
			if (edge[i][k] != 0)
				return k;
		}
		return -1;
	}

	/**
	 * ��ȡԪ��i�Ľڵ�j����һ���ڵ�Ԫ��
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private int getPreAdj(int i, int j) {

		for (int k = j - 1; k >= 0; k--) {
			if (edge[i][k] != 0) {
				return k;
			}
		}
		return -1;
	}

	/**
	 * ͼ�Ĺ�����ȱ���
	 */
	public void broadSearch(int i) {
		// ͼ�Ĺ�����ȱ���˼·���£�
		// 0.��ʼ���洢������Ϣ��visit����
		// 1.���Ȳ��ö�������֯���ڵ㣬����ʼ�ڵ���뵽������
		// 2.�����в�Ϊ��ʱ��һֱѭ��
		// 3.����ʼ�ڵ������δ���ʵĽڵ���뵽������
		// ֱ�����еĽڵ��Ѿ�������Ϊֹ���㷨����
		for (int j = 0; j < n; j++) {
			visit[j] = false;
		}
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(i);
		visit[i] = true;
		int k = 0;
		while (!queue.isEmpty()) {
			// ����ͷ��Ԫ�ؽ��г�ջ
			i = queue.poll();
			System.out.print(vertex[i] + "\t");
			k = getFirstAdj(i);
			while (k >= 0) {
				if (!visit[k]) {
					queue.offer(k);
					visit[k] = true;
				}
				k = getNextAdj(i, k);
			}
		}
		System.out.println();
	}

	/**
	 * ��ӡͼ���ڽӾ���
	 */
	public void printGraphic() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(edge[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * ��ȡ�ڽӾ���ı���
	 * 
	 * @return
	 */
	public int getEdgeCount() {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (edge[i][j] > 0 && edge[i][j] < PrimMinTree.MAX) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * ��ȡ����ͼ���ڽӾ���ı���
	 * 
	 * @return
	 */
	public int getEdgeCount(boolean hasOri) {
		if (hasOri) {
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (edge[i][j] > 0 && edge[i][j] < PrimMinTree.MAX) {
						count++;
					}
				}
			}
			return count;
		}
		return -1;
	}

	/**
	 * ���ݱߵ�ȡֵ��ȡ�ñߵĶ˵�
	 * 
	 * @param weight
	 * @return
	 */
	public Object[] getVertexByEdge(int weight) {
		Object[] info = new Object[2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (edge[i][j] == weight) {
					info[0] = vertex[i];
					info[1] = vertex[j];
					edge[i][j] = -1;
					break;
				}
			}
		}
		return info;
	}

	/**
	 * ��ȡ����±�
	 * 
	 * @param obj
	 * @return
	 */
	public int getPosition(Object obj) {
		for (int i = 0; i < vertex.length; i++) {
			if (obj.equals(vertex[i]))
				return i;
		}
		return -1;
	}

	public Object[] getVertex() {
		return vertex;
	}

	public static void main(String[] args) {
		// ���²�������ͼ
		MatrixGraphic graphic = new MatrixGraphic(5);
		Object[] vertex = { 0, 1, 2, 3, 4 };
		graphic.addVertex(vertex);
		graphic.addEdge(0, 1);
		graphic.addEdge(0, 4);

		graphic.addEdge(1, 2);
		graphic.addEdge(1, 3);
		graphic.addEdge(1, 4);

		graphic.addEdge(3, 2);
		graphic.addEdge(3, 4);

		// ��ӡ����ڽӾ���
		System.out.println("ͼ���ڽӾ���");
		graphic.printGraphic();

		// ͼ��������ȱ���
		System.out.println("ͼ��������ȱ���(�ݹ�ṹ)��");
		graphic.depthSearch();
		System.out.println("ͼ��������ȱ���(�ǵݹ�ṹ)��");
		graphic.depthSearchNoStack(0);
		System.out.println("ͼ�Ĺ�����ȱ�����");
		graphic.broadSearch(0);
	}
}
