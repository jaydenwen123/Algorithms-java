package com.wxf.data.test.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * 图的邻接表存储结构
 * 
 * @author Administrator
 * 
 */
public class MyAdjLinkedGraph {

	// 图中节点的个数
	private int n;
	// 表头节点的集合
	private Anode[] vexNodes;

	private boolean visit[];

	//

	public MyAdjLinkedGraph() {
		// TODO Auto-generated constructor stub
	}

	public MyAdjLinkedGraph(int n, Anode[] vexNodes) {
		super();
		this.n = n;
		this.vexNodes = vexNodes;
		this.visit = new boolean[n];
	}

	public MyAdjLinkedGraph(int n) {
		super();
		this.n = n;
		this.visit = new boolean[n];
	}

	/**
	 * 设置表头节点的数组
	 * 
	 * @param nodes
	 */
	public void setAnode(Anode[] nodes) {
		this.vexNodes = nodes;
	}

	/**
	 * 图的深度优先遍历，递归设计
	 */
	public void depthTraverse() {

		// 1首先初始化
		// 2.然后搞定单个节点的遍历
		// 3.递归调用执行
		initVisit();
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				traverseOne(vexNodes[i]);
			}
		}
	}

	/**
	 * 遍历单个表头节点
	 * 
	 * @param node
	 */
	public void traverseOne(Anode node) {

		// 获取该元素在表头节点数组中的位置
		int index = getIndex(node);
		// 标志位已被访问
		visit[index] = true;
		// 访问
		System.out.print(node.data + "\t");
		Vnode n = node.firstVex;
		while (n != null) {
			int i = n.adjvex;
			if (!visit[i]) {
				traverseOne(vexNodes[i]);
			}
			n = n.nextVex;

		}
	}

	/**
	 * 获取该表头节点在数组中的位置
	 * 
	 * @param node
	 * @return
	 */
	public int getIndex(Anode node) {
		for (int i = 0; i < n; i++) {
			if (node.data.equals(vexNodes[i].data))
				return i;
		}
		return -1;
	}

	/**
	 * 图的广度优先遍历（通过队列）
	 */
	public void braodTraverse() {
		initVisit();
		Queue<Anode> queue = new LinkedList<Anode>();
		queue.add(vexNodes[0]);
		visit[0] = true;
		while (!queue.isEmpty()) {
			Anode nAnode = queue.poll();
			Vnode node = nAnode.firstVex;
			System.out.print(nAnode.data + "\t");
			while (node != null) {
				if (!visit[node.adjvex]) {
					queue.add(vexNodes[node.adjvex]);
					visit[node.adjvex] = true;
				}
				node = node.nextVex;
			}

		}
		System.out.println();
	}

	/**
	 * 图的深度优先遍历，非递归（通过栈）
	 */
	public void depthTraverseWithStack() {

		initVisit();
		Stack<Anode> stack = new Stack<>();
		stack.push(vexNodes[0]);
		visit[0] = true;
		while (!stack.isEmpty()) {
			Anode node = stack.pop();
			System.out.print(node.data + "\t");
			Vnode vnode = getLastNode(node);
			while (!vnode.isFirst) {
				if (!visit[vnode.adjvex]) {
					visit[vnode.adjvex] = true;
					stack.push(vexNodes[vnode.adjvex]);
				}
				vnode = getVnode(node, vnode);
			}
			// 一下是对表头节点的第一个临节节点入栈
			if (!visit[vnode.adjvex]) {
				visit[vnode.adjvex] = true;
				stack.push(vexNodes[vnode.adjvex]);
			}

		}
		System.out.println();
	}

	/**
	 * 获取该镖头结点的最后一个邻接节点
	 * 
	 * @param node
	 * @return
	 */
	public Vnode getLastNode(Anode node) {
		Vnode n = null;
		n = node.firstVex;
		while (n.nextVex != null)
			n = n.nextVex;
		return n;
	}

	public void initVisit() {
		for (int i = 0; i < n; i++) {
			visit[i] = false;
		}
	}

	/**
	 * 获取镖头结点中的vnode的前一个边节点
	 * 
	 * @param node
	 * @param pre
	 * @return
	 */
	public Vnode getVnode(Anode node, Vnode vnode) {
		Vnode n = node.firstVex;
		while (n != null) {
			if (n.adjvex == vnode.prevVex)
				return n;
			n = n.nextVex;
		}
		return n;
	}

	/**
	 * 打印图的邻接表的信息
	 */
	public void print() {
		if (vexNodes != null && vexNodes.length > 0) {
			for (Anode node : vexNodes) {
				System.out.println(node.toString());
			}
		}
	}

	public static void main(String[] args) {

		int n = 5;
		// 构建无向图
		// Object[] nodes = new Object[] { 0, 1, 2, 3, 4 };
		Anode nodes[] = new Anode[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Anode(i);
		}
		nodes[0].firstVex = new Vnode(0, 4, 1, new Vnode(4, 1, 1, null, false),
				true);
		nodes[1].firstVex = new Vnode(1, 4, 1, new Vnode(4, 3, 1, new Vnode(3,
				2, 1, new Vnode(2, 0, 1, null, false), false), false), true);
		nodes[2].firstVex = new Vnode(2, 3, 1, new Vnode(3, 1, 1, null, false),
				true);
		nodes[3].firstVex = new Vnode(3, 4, 1, new Vnode(4, 2, 1, new Vnode(2,
				1, 1, null, false), false), true);
		nodes[4].firstVex = new Vnode(4, 3, 1, new Vnode(3, 1, 1, new Vnode(1,
				0, 1, null, false), false), true);
		MyAdjLinkedGraph graph = new MyAdjLinkedGraph(n);
		graph.setAnode(nodes);
		System.out.println("test the adjLinkedtable!!!");
		graph.print();

		System.out.println("test the depthTraverse!!!");
		graph.depthTraverse();
		System.out.println();
		System.out.println("test teh depthTraverseWithStack!!!");
		graph.depthTraverseWithStack();
		System.out.println("test the broadTraverse!!!");

		graph.braodTraverse();

		System.out.println("test the getLastNode!!!");
		System.out.println(graph.getLastNode(nodes[1]).adjvex);

		System.out.println("************************");

		System.out
				.println(graph.getVnode(nodes[1], graph.getLastNode(nodes[1])).adjvex);

	}
}

/**
 * 定义表头节点
 * 
 * @author Administrator
 * 
 */
class Anode {

	/**
	 * 该节点的符号
	 */
	public Object data;
	// 第一个邻接节点
	public Vnode firstVex;

	//

	public Anode() {
		// TODO Auto-generated constructor stub
	}

	public Anode(Object data) {
		super();
		this.data = data;
	}

	public Anode(Object data, Vnode firstVex) {
		super();
		this.data = data;
		this.firstVex = firstVex;
	}

	@Override
	public String toString() {
		return "(" + data + "," + firstVex + ")";
	}

}

/**
 * 定义边节点
 * 
 * @author Administrator
 * 
 */
class Vnode {
	// 该边的终点编号
	public int adjvex;
	// 权值
	public int weight;
	// 下一个边节点
	public Vnode nextVex;

	// 表示该边节点的上一个邻接节点的下标
	public int prevVex;
	// 判断该边节点是否是第一个邻接节点
	public boolean isFirst;

	public Vnode() {
		// TODO Auto-generated constructor stub
	}

	public Vnode(int adjvex, int weight, Vnode nextVex) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
	}

	public Vnode(int prevVex, int adjvex, int weight, Vnode nextVex) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
		this.prevVex = prevVex;
	}

	public Vnode(int prevVex, int adjvex, int weight, Vnode nextVex,
			boolean isFirst) {
		super();
		this.adjvex = adjvex;
		this.weight = weight;
		this.nextVex = nextVex;
		this.prevVex = prevVex;
		this.isFirst = isFirst;
	}

	@Override
	public String toString() {
		return "(" + prevVex + "," + adjvex + "," + weight + "," + nextVex
				+ "," + isFirst + ")";
	}

}
