package com.wxf.data.test.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * ͼ���ڽӱ�洢�ṹ
 * 
 * @author Administrator
 * 
 */
public class MyAdjLinkedGraph {

	// ͼ�нڵ�ĸ���
	private int n;
	// ��ͷ�ڵ�ļ���
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
	 * ���ñ�ͷ�ڵ������
	 * 
	 * @param nodes
	 */
	public void setAnode(Anode[] nodes) {
		this.vexNodes = nodes;
	}

	/**
	 * ͼ��������ȱ������ݹ����
	 */
	public void depthTraverse() {

		// 1���ȳ�ʼ��
		// 2.Ȼ��㶨�����ڵ�ı���
		// 3.�ݹ����ִ��
		initVisit();
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				traverseOne(vexNodes[i]);
			}
		}
	}

	/**
	 * ����������ͷ�ڵ�
	 * 
	 * @param node
	 */
	public void traverseOne(Anode node) {

		// ��ȡ��Ԫ���ڱ�ͷ�ڵ������е�λ��
		int index = getIndex(node);
		// ��־λ�ѱ�����
		visit[index] = true;
		// ����
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
	 * ��ȡ�ñ�ͷ�ڵ��������е�λ��
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
	 * ͼ�Ĺ�����ȱ�����ͨ�����У�
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
	 * ͼ��������ȱ������ǵݹ飨ͨ��ջ��
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
			// һ���ǶԱ�ͷ�ڵ�ĵ�һ���ٽڽڵ���ջ
			if (!visit[vnode.adjvex]) {
				visit[vnode.adjvex] = true;
				stack.push(vexNodes[vnode.adjvex]);
			}

		}
		System.out.println();
	}

	/**
	 * ��ȡ����ͷ�������һ���ڽӽڵ�
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
	 * ��ȡ��ͷ����е�vnode��ǰһ���߽ڵ�
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
	 * ��ӡͼ���ڽӱ����Ϣ
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
		// ��������ͼ
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
 * �����ͷ�ڵ�
 * 
 * @author Administrator
 * 
 */
class Anode {

	/**
	 * �ýڵ�ķ���
	 */
	public Object data;
	// ��һ���ڽӽڵ�
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
 * ����߽ڵ�
 * 
 * @author Administrator
 * 
 */
class Vnode {
	// �ñߵ��յ���
	public int adjvex;
	// Ȩֵ
	public int weight;
	// ��һ���߽ڵ�
	public Vnode nextVex;

	// ��ʾ�ñ߽ڵ����һ���ڽӽڵ���±�
	public int prevVex;
	// �жϸñ߽ڵ��Ƿ��ǵ�һ���ڽӽڵ�
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
