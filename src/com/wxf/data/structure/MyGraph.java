package com.wxf.data.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Administrator
 * 
 *         �Լ�ʵ�ֵ�ͼ�����ֱ�����ʽ �ֱ�Ϊ������ȱ����͹�����ȱ���
 */
public class MyGraph {

	// �洢ͼ�еĵ�
	private Object[] elements;
	// �洢ͼ�еıߵ���Ϣ
	private int[][] edges;
	// ͼ�еĵ�ĸ���
	private int elementCounts;
	// ͼ�е�ÿ�����Ƿ������
	private boolean[] visited;

	public MyGraph(int n) {
		elementCounts = n;
		elements = new Object[elementCounts];
		edges = new int[elementCounts][elementCounts];
		visited = new boolean[elementCounts];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				edges[i][j] = 0;
			}
		}
	}

	public void addEdge(int i, int j) {
		if (i == j)
			return;
		/* edges[j][i] = */
		edges[i][j] = 1;
	}

	public void addElement(Object[] elemenObjects) {
		this.elements = elemenObjects;
	}

	/**
	 * @param k
	 * @return ��ȡ�ڵ�k�ĵ�һ���ڽӽڵ㡣
	 */
	public int FirstAdjVex(int k) {
		for (int i = 0; i < elementCounts; i++) {
			if (edges[k][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param k
	 * @return ��ȡ�ڵ�k�����һ���ڽӽڵ㡣
	 */
	public int LastAdjVex(int k) {
		for (int i = elementCounts - 1; i >= 0; i--) {
			if (edges[k][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @param i
	 * @param kָ
	 * @return ��ȡ�ڵ�k�ĵ�i���ڽӽڵ����һ���ڵ㡣
	 */
	public int NextAdjVex(int k, int i) {
		for (int j = i + 1; j < elementCounts; j++) {
			if (edges[k][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * @param i
	 * @param k
	 * @return
	 * 
	 *         ��ȡ�ڵ�k�ĵ�i���ڽӽڵ��ǰһ���ڽӽڵ㡣
	 */
	public int PreAdjVex(int k, int i) {
		for (int j = i - 1; j >= 0; j--) {
			if (edges[k][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	public void depthTraverse() {
		for (int i = 0; i < elementCounts; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < elementCounts; i++) {
			if (!visited[i]) {
				traverse(i);
			}
		}
	}

	/**
	 * @param i
	 *            ��ͨͼ�ĵݹ������
	 */
	public void traverse(int i) {
		visited[i] = true;
		System.out.print(elements[i] + "  ");
		for (int j = FirstAdjVex(i); j >= 0; j = NextAdjVex(i, j)) {
			if (!visited[j]) {
				traverse(j);
			}
		}
	}

	/**
	 * ͼ�Ĺ�����ȱ���
	 * 
	 */
	public void broadTraverse() {
		Queue<Object> queue = new LinkedList<>();
		for (int i = 0; i < elementCounts; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < elementCounts; i++) {
			if (!visited[i]) {
				visited[i] = true;
				System.out.println(elements[i] + "  ");
				queue.add(i);
				while (!queue.isEmpty()) {
					int j = ((Integer) queue.remove()).intValue();
					for (int k = FirstAdjVex(j); k >= 0; k = NextAdjVex(j, k)) {
						if (!visited[k]) {
							visited[k] = true;
							System.out.println(elements[k] + "  ");
							queue.add(k);
						}
					}
				}
			}

		}
	}

	/**
	 * ͼ�ķǵݹ���ȱ���������ջ��ʵ�֡�
	 */
	public void depth() {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < elementCounts; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < elementCounts; i++) {
			if (!visited[i]) {
				s.add(i);
				// ���õ�i��Ԫ���Ѿ���ջ
				visited[i] = true;
				while (!s.isEmpty()) {
					int j = (Integer) s.pop();
					System.out.println(elements[j] + " ");
					for (int k = this.LastAdjVex(j); k >= 0; k = this
							.PreAdjVex(j, k)) {
						if (!visited[k]) {
							s.add(k);
							visited[k] = true;
						}
					}
				}
			}
		}
	}

	/**
	 * ��ӡ������ڽӾ���
	 */
	public void printGraph() {
		for (int i = 0; i < elementCounts; i++) {
			for (int j = 0; j < elementCounts; j++) {
				System.out.print(edges[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	/**
	 * �ݹ�ͨ��ջ��ʵ�� ˼·���£���Ҫ�Ǵ�ÿһ���ڵ�����һ�ٽڽڵ���ǰ�ҡ����ϵ���ջ�ͳ�ջ
	 */
	public void Depth_Stack() {
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		// 1.�����жϽڵ��Ƿ��Ѿ������ʣ�û�з��ʽ��ڵ���ջ
		MyLinkStack stack = new MyLinkStack();
		for (int j = 0; j < elements.length; j++) {
			if (!visited[j]) {
				stack.push(j);
				visited[j] = true;
				// 2.�ж�ջ�Ƿ�Ϊ�գ���Ϊ�����ջ
				while (!stack.isEmpty()) {
					int obj = (int) stack.pop();
					System.out.println(elements[obj]);
					// 3.���ʸýڵ㣬�����øýڵ��Ѿ�������
					int k = LastAdjVex(obj);
					// 4.�Ӹýڵ�����һ���ڵ��������û�з��ʣ���������ջ
					while (k >= 0) {
						if (!visited[k]) {
							stack.push(k);
							visited[k] = true;
						}
						// �Ӻ���ǰ��
						k = PreAdjVex(obj, k);
					}
				}
			}
		}

	}

	/**
	 * ͼ��������ȱ�����ͨ���ݹ�ʵ��
	 */
	public void Depth_NoStack() {

		// ��ʼ�����ʵļ�¼
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
		// ��ʼ����
		for (int i = 0; i < elementCounts; i++) {
			if (!visited[i]) {
				traverseOne(i);
			}
		}
	}

	/**
	 * ��Ҫ����ʵ�ֱ���һ���ڵ�
	 * 
	 * @param i
	 */
	public void traverseOne(int i) {
		visited[i] = true;
		System.out.println(elements[i]);
		// �Ӹýڵ�ĵ�һ���ڽӽڵ�����
		int k = FirstAdjVex(i);
		while (k >= 0) {
			// ����ýڵ�δ�����ʣ�����еݹ���ʡ�������֮��
			if (!visited[k]) {
				traverseOne(k);
				visited[k] = true;
			}
			// ��ȥ��i�ڵ��k�ڵ����һ���ڽӽڵ�
			k = NextAdjVex(i, k);
		}
	}

	/**
	 * ���Ĺ�����ȱ�����ͨ������ʵ��
	 */
	public void broadGraphic() {

		// 0.��ʼ��visit[]����
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
		// 1.���ȳ�ʼ������
		MyLinkQueue queue = new MyLinkQueue();
		for (int i = 0; i < elementCounts; i++) {

			// 2.�ж��Ƿ���ʣ�û�з��������
			if (!visited[i]) {

				queue.enQueue(i);
				visited[i] = true;
				// 3.�ж϶����Ƿ�Ϊ�գ���Ϊ�ս���ѭ������
				while (!queue.isEmpty()) {
					int j = (int) queue.deQueue();
					// 4.���ʸýڵ㡣ͬʱ�Ӹýڵ�ĵ�һ���ڽӽڵ�����
					System.out.println(elements[j]);
					// 5. ֱ�����һ�������û�з��ʣ���������
					int k = FirstAdjVex(j);
					while (k >= 0) {
						// ���û���ʹ����������ӣ�����������һ��
						if (!visited[k]) {
							queue.enQueue(k);
							visited[k] = true;
						}
						k = NextAdjVex(j, k);
					}
				}
			}
		}
	}


	public static void main(String[] args) {

		MyGraph graph = new MyGraph(5);
		graph.addElement(new Object[] { 'A', 'B', 'C', 'D', 'E' });
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 3);
		graph.addEdge(2, 1);
		graph.addEdge(3, 2);

		// graph.printGraph();
		System.out.println("ͼ��������ȱ���");
		graph.depthTraverse();
		System.out.println();
		System.out.println("ͼ�Ĺ�����ȱ���");
		graph.broadTraverse();
		System.out.println("ͼ�ķǵݹ��������ȱ���");
		graph.depth();
		
		System.out.println("***********************");
		System.out.println("������ȱ����ݹ飺");
		graph.Depth_NoStack();
		System.out.println("������ȱ����ǵݹ飬ͨ��ջʵ�֣�");
		graph.Depth_Stack();
		System.out.println("***********************");
		System.out.println("������ȱ���������ʵ��");
		graph.broadGraphic();

	}

}
