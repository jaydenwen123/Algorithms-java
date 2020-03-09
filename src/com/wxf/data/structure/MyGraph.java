package com.wxf.data.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Administrator
 * 
 *         自己实现的图的两种遍历方式 分别为深度优先遍历和广度优先遍历
 */
public class MyGraph {

	// 存储图中的点
	private Object[] elements;
	// 存储图中的边的信息
	private int[][] edges;
	// 图中的点的个数
	private int elementCounts;
	// 图中的每个点是否遍历过
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
	 * @return 获取节点k的第一个邻接节点。
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
	 * @return 获取节点k的最后一个邻接节点。
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
	 * @param k指
	 * @return 获取节点k的第i个邻接节点的下一个节点。
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
	 *         获取节点k的第i个邻接节点的前一个邻接节点。
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
	 *            连通图的递归遍历。
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
	 * 图的广度优先遍历
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
	 * 图的非递归深度遍历。采用栈来实现。
	 */
	public void depth() {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < elementCounts; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < elementCounts; i++) {
			if (!visited[i]) {
				s.add(i);
				// 设置第i个元素已经进栈
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
	 * 打印数组的邻接矩阵。
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
	 * 递归通过栈来实现 思路如下：主要是从每一个节点的最后一临节节点往前找。不断的入栈和出栈
	 */
	public void Depth_Stack() {
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		// 1.首先判断节点是否已经被访问，没有访问将节点入栈
		MyLinkStack stack = new MyLinkStack();
		for (int j = 0; j < elements.length; j++) {
			if (!visited[j]) {
				stack.push(j);
				visited[j] = true;
				// 2.判断栈是否为空，不为空则出栈
				while (!stack.isEmpty()) {
					int obj = (int) stack.pop();
					System.out.println(elements[obj]);
					// 3.访问该节点，被设置该节点已经被访问
					int k = LastAdjVex(obj);
					// 4.从该节点的最后一个节点找起，如果没有访问，则依次入栈
					while (k >= 0) {
						if (!visited[k]) {
							stack.push(k);
							visited[k] = true;
						}
						// 从后往前找
						k = PreAdjVex(obj, k);
					}
				}
			}
		}

	}

	/**
	 * 图的深度优先遍历，通过递归实现
	 */
	public void Depth_NoStack() {

		// 初始化访问的记录
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
		// 开始遍历
		for (int i = 0; i < elementCounts; i++) {
			if (!visited[i]) {
				traverseOne(i);
			}
		}
	}

	/**
	 * 主要用来实现遍历一个节点
	 * 
	 * @param i
	 */
	public void traverseOne(int i) {
		visited[i] = true;
		System.out.println(elements[i]);
		// 从该节点的第一个邻接节点找起
		int k = FirstAdjVex(i);
		while (k >= 0) {
			// 如果该节点未被访问，则进行递归访问。访问完之后
			if (!visited[k]) {
				traverseOne(k);
				visited[k] = true;
			}
			// 再去找i节点的k节点的下一个邻接节点
			k = NextAdjVex(i, k);
		}
	}

	/**
	 * 树的广度优先遍历，通过队列实现
	 */
	public void broadGraphic() {

		// 0.初始化visit[]数组
		for (int i = 0; i < visited.length; i++)
			visited[i] = false;
		// 1.首先初始化队列
		MyLinkQueue queue = new MyLinkQueue();
		for (int i = 0; i < elementCounts; i++) {

			// 2.判断是否访问，没有访问则入队
			if (!visited[i]) {

				queue.enQueue(i);
				visited[i] = true;
				// 3.判断队列是否为空，不为空进行循环出队
				while (!queue.isEmpty()) {
					int j = (int) queue.deQueue();
					// 4.访问该节点。同时从该节点的第一个邻接节点找起。
					System.out.println(elements[j]);
					// 5. 直到最后一个，如果没有访问，则进行入队
					int k = FirstAdjVex(j);
					while (k >= 0) {
						// 如果没访问过，则进行入队，否则再找下一个
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
		System.out.println("图的深度优先遍历");
		graph.depthTraverse();
		System.out.println();
		System.out.println("图的广度优先遍历");
		graph.broadTraverse();
		System.out.println("图的非递归的深度优先遍历");
		graph.depth();
		
		System.out.println("***********************");
		System.out.println("深度优先遍历递归：");
		graph.Depth_NoStack();
		System.out.println("深度优先遍历非递归，通过栈实现：");
		graph.Depth_Stack();
		System.out.println("***********************");
		System.out.println("广度优先遍历：队列实现");
		graph.broadGraphic();

	}

}
