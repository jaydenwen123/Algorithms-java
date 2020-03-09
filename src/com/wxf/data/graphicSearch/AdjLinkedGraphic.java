package com.wxf.data.graphicSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.soap.Node;

import org.omg.CORBA.OBJ_ADAPTER;

/**
 * 通过邻接表来存储图的信息
 * 
 * @author Administrator
 * 
 */
public class AdjLinkedGraphic {

	// 邻接表的数组
	private ANode[] listNodes;
	// 节点的个数
	private int n;

	// 用来保存节点是否访问
	private boolean[] visit;

	public void setVisit(boolean[] visit) {
		this.visit = visit;
	}

	public ANode[] getListNodes() {
		return listNodes;
	}

	public void setListNodes(ANode[] listNodes) {
		this.listNodes = listNodes;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public AdjLinkedGraphic() {
		// TODO Auto-generated constructor stub
	}

	public AdjLinkedGraphic(int n) {
		super();
		this.n = n;
		listNodes = new ANode[n];
		visit = new boolean[n];
	}

	/**
	 * 邻接表的表头结点
	 * 
	 * @author Administrator
	 * 
	 */
	class ANode {
		// 对应的是该节点的符号
		public Object data;
		// 该节点指向的第一条边
		public ENode firstEdge;

		public ANode() {
			// TODO Auto-generated constructor stub
		}

		public ANode(Object data, ENode firstEdge) {
			super();
			this.data = data;
			this.firstEdge = firstEdge;
		}

		public ANode(Object data) {
			super();
			this.data = data;
			firstEdge = null;
		}

		@Override
		public String toString() {
			return "ANode [data=" + data + ", firstEdge=" + firstEdge + "]";
		}

	}

	/**
	 * 定义邻接表的边节点
	 * 
	 * @author Administrator
	 * 
	 */
	class ENode {
		// 该边的终点
		public Object adjvex;
		// 该边的权值
		public int weight;
		// 指向下一条边的指针
		public ENode nextEdge;

		public ENode(Object adjvex, int weight, ENode nextEdge) {
			super();
			this.adjvex = adjvex;
			this.weight = weight;
			this.nextEdge = nextEdge;
		}

		public ENode(Object adjvex, ENode nextEdge) {
			super();
			this.adjvex = adjvex;
			this.nextEdge = nextEdge;
		}

		public ENode() {
			// TODO Auto-generated constructor stub
		}

		public ENode(Object adjvex) {
			super();
			this.adjvex = adjvex;
			nextEdge = null;
		}

		@Override
		public String toString() {
			return "ENode [adjvex=" + adjvex + ", nextEdge=" + nextEdge + "]";
		}

	}

	/**
	 * 打印图的邻接表
	 */
	public void printGraph() {
		for (int i = 0; i < listNodes.length; i++) {
			ANode node = listNodes[i];
			// 获取该表头节点的信息
			Object data = node.data;
			ENode node2 = node.firstEdge;
			System.out.print(data + "->");
			while (node2 != null) {
				System.out.print(node2.adjvex + "-->");
				node2 = node2.nextEdge;
			}
			System.out.println();
		}

	}

	/**
	 * 通过邻接表来创建图
	 * 
	 * @param vertex
	 * @param edge
	 */
	public void createGraph(Object[] vertex, int[][] edge) {
		ANode node = null;
		for (int i = 0; i < vertex.length; i++) {
			// 构建表头节点
			node = new ANode(vertex[i]);
			// 将其赋值给lsitNOdes
			// 下面不断的从边的信息中，构建邻接表
			for (int j = edge[0].length - 1; j >= 0; j--) {
				if (edge[i][j] != 0) {
					ENode eNode = new ENode();
					eNode.adjvex = vertex[j];
					eNode.weight = edge[i][j];
					if (node.firstEdge == null) {
						node.firstEdge = eNode;
					} else {
						// 获取尾节点
						ENode last = getLast(node.firstEdge);
						// 进行尾插入
						last.nextEdge = eNode;
						last = eNode;
					}
				}
			}
			listNodes[i] = node;
		}
	}

	/**
	 * 获取尾节点
	 * 
	 * @param node
	 * @return
	 */
	public ENode getLast(ENode node) {
		if (node == null)
			return null;
		else {
			ENode temp = node;
			while (temp.nextEdge != null) {
				temp = temp.nextEdge;
			}
			return temp;
		}
	}

	/**
	 * 广度优先遍历
	 * 
	 * @param i
	 */
	public void braodSearch(int i) {
		// 首先初始化保存访问信息的数组
		for (int j = 0; j < visit.length; j++) {
			visit[j] = false;
		}

		// 广度优先遍历的思路如下：
		// 1.首先初始化一个队列，用来组织节点
		// 2.将初始节点保存到队列中，当队列不为空时，一直循环，执行出队操作
		// 3.然后依次从初始节点开始逐个遍历
		//
		//
		Queue<ANode> queue = new LinkedList<>();
		ANode node = listNodes[i];
		queue.offer(node);
		visit[i] = true;
		while (!queue.isEmpty()) {
			node = queue.poll();
			System.out.print(node.data + "\t");
			ENode temp = node.firstEdge;
			while (temp != null) {
				int index = getANode(temp.adjvex);
				if (!visit[index]) {
					queue.offer(listNodes[index]);
					visit[index] = true;
				}
				temp = temp.nextEdge;
			}
		}
		System.out.println();
	}

	/**
	 * 邻接表存储图的信息，的深度优先遍历
	 */
	public void depthSearch() {
		// 首先初始化访问的节点数组
		for (int i = 0; i < visit.length; i++) {
			visit[i] = false;
		}
		depthSearch(0);
		System.out.println();
	}

	/**
	 * 遍历单个节点
	 * 
	 * @param node
	 */
	public void depthSearch(int k) {

		// 单个节点的遍历
		// 1.首先遍历该节点
		// 2.然后修改信息，表示该节点已经被访问
		// 3.找到该节点的第一条边
		// 4.根据第一条边的信息找到下一个要遍历的邻接表的表头节点
		// 递归遍历
		ANode node = listNodes[k];
		visit[k] = true;
		System.out.print(node.data + "\t");
		ENode first = node.firstEdge;
		while (first != null) {
			// 获取该邻接表镖头结点对应的第一条边的节点
			int j = getANode(first.adjvex);
			if (!visit[j]) {
				depthSearch(j);
			}
			first = first.nextEdge;
		}

	}

	/**
	 * 根据节点的值获取该邻接表的表头结点
	 * 
	 * @param adject
	 * @return
	 */
	public int getANode(Object adject) {
		for (int i = 0; i < listNodes.length; i++) {
			ANode node = listNodes[i];
			if (node.data.equals(adject))
				return i;
		}
		return -1;
	}

	/**
	 * 判断从i节点到j节点是否存在路径
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean ExistPath(int i, int j, List list) {

		// 求解该问题的思路如下：
		// 1.采用深度优先遍历
		// 2.递归体：首先从i节点找下一个节点，找完后，在从下一个节点找到j节点的路径
		// 3。递归出口：当i==j时，表示找到了路径
		//
		if (list.size() <= n) {
			visit[i] = true;
			ANode node = listNodes[i];
			System.out.print(node.data + "\t");
			// 保存路径
			list.add(node.data);
			ENode first = node.firstEdge;
			while (first != null) {
				int k = getANode(first.adjvex);
				if (k == j) {
					System.out.println(listNodes[j].data + "\t");
					list.add(listNodes[j].data);
					break;
				} else if (!visit[k]) {
					ExistPath(k, j, list);
				}
				first = first.nextEdge;
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int n = 5;
		Object[] vertex = { 0, 1, 2, 3, 4 };
		int[][] weight = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				weight[i][j] = 0;
			}
		}
		weight[0][1] = 1;
		weight[0][4] = 1;

		weight[1][2] = 1;
		weight[1][3] = 1;
		weight[1][4] = 1;

		weight[3][2] = 1;
		weight[3][4] = 1;
		AdjLinkedGraphic graphic = new AdjLinkedGraphic(n);
		graphic.createGraph(vertex, weight);
		System.out.println("图的邻接表如下：");
		graphic.printGraph();
		System.out.println(Arrays.toString(graphic.listNodes));
		System.out.println("图的深度优先遍历（递归）：");
		graphic.depthSearch();
		System.out.println("图的广度优先遍历（队列）：");
		graphic.braodSearch(0);
		System.out.println("图的深度优先遍历（非递归）：");
		System.out.println("待续。。。。。。。。。。。。。");
		System.out.println("找出从i到j的简单路径：");
		boolean[] visit = new boolean[n];
		for (int i = 0; i < visit.length; i++)
			visit[i] = false;
		graphic.setVisit(visit);

		List list = new ArrayList<>();
		boolean result = graphic.ExistPath(0, 3, list);
		System.out.println("是否有解 ：" + result);
		System.out.println("该路径为：");
		System.out.println(Arrays.asList(list));
	}
}
