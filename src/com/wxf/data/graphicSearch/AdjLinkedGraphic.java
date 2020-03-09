package com.wxf.data.graphicSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.soap.Node;

import org.omg.CORBA.OBJ_ADAPTER;

/**
 * ͨ���ڽӱ����洢ͼ����Ϣ
 * 
 * @author Administrator
 * 
 */
public class AdjLinkedGraphic {

	// �ڽӱ������
	private ANode[] listNodes;
	// �ڵ�ĸ���
	private int n;

	// ��������ڵ��Ƿ����
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
	 * �ڽӱ�ı�ͷ���
	 * 
	 * @author Administrator
	 * 
	 */
	class ANode {
		// ��Ӧ���Ǹýڵ�ķ���
		public Object data;
		// �ýڵ�ָ��ĵ�һ����
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
	 * �����ڽӱ�ı߽ڵ�
	 * 
	 * @author Administrator
	 * 
	 */
	class ENode {
		// �ñߵ��յ�
		public Object adjvex;
		// �ñߵ�Ȩֵ
		public int weight;
		// ָ����һ���ߵ�ָ��
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
	 * ��ӡͼ���ڽӱ�
	 */
	public void printGraph() {
		for (int i = 0; i < listNodes.length; i++) {
			ANode node = listNodes[i];
			// ��ȡ�ñ�ͷ�ڵ����Ϣ
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
	 * ͨ���ڽӱ�������ͼ
	 * 
	 * @param vertex
	 * @param edge
	 */
	public void createGraph(Object[] vertex, int[][] edge) {
		ANode node = null;
		for (int i = 0; i < vertex.length; i++) {
			// ������ͷ�ڵ�
			node = new ANode(vertex[i]);
			// ���丳ֵ��lsitNOdes
			// ���治�ϵĴӱߵ���Ϣ�У������ڽӱ�
			for (int j = edge[0].length - 1; j >= 0; j--) {
				if (edge[i][j] != 0) {
					ENode eNode = new ENode();
					eNode.adjvex = vertex[j];
					eNode.weight = edge[i][j];
					if (node.firstEdge == null) {
						node.firstEdge = eNode;
					} else {
						// ��ȡβ�ڵ�
						ENode last = getLast(node.firstEdge);
						// ����β����
						last.nextEdge = eNode;
						last = eNode;
					}
				}
			}
			listNodes[i] = node;
		}
	}

	/**
	 * ��ȡβ�ڵ�
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
	 * ������ȱ���
	 * 
	 * @param i
	 */
	public void braodSearch(int i) {
		// ���ȳ�ʼ�����������Ϣ������
		for (int j = 0; j < visit.length; j++) {
			visit[j] = false;
		}

		// ������ȱ�����˼·���£�
		// 1.���ȳ�ʼ��һ�����У�������֯�ڵ�
		// 2.����ʼ�ڵ㱣�浽�����У������в�Ϊ��ʱ��һֱѭ����ִ�г��Ӳ���
		// 3.Ȼ�����δӳ�ʼ�ڵ㿪ʼ�������
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
	 * �ڽӱ�洢ͼ����Ϣ����������ȱ���
	 */
	public void depthSearch() {
		// ���ȳ�ʼ�����ʵĽڵ�����
		for (int i = 0; i < visit.length; i++) {
			visit[i] = false;
		}
		depthSearch(0);
		System.out.println();
	}

	/**
	 * ���������ڵ�
	 * 
	 * @param node
	 */
	public void depthSearch(int k) {

		// �����ڵ�ı���
		// 1.���ȱ����ýڵ�
		// 2.Ȼ���޸���Ϣ����ʾ�ýڵ��Ѿ�������
		// 3.�ҵ��ýڵ�ĵ�һ����
		// 4.���ݵ�һ���ߵ���Ϣ�ҵ���һ��Ҫ�������ڽӱ�ı�ͷ�ڵ�
		// �ݹ����
		ANode node = listNodes[k];
		visit[k] = true;
		System.out.print(node.data + "\t");
		ENode first = node.firstEdge;
		while (first != null) {
			// ��ȡ���ڽӱ���ͷ����Ӧ�ĵ�һ���ߵĽڵ�
			int j = getANode(first.adjvex);
			if (!visit[j]) {
				depthSearch(j);
			}
			first = first.nextEdge;
		}

	}

	/**
	 * ���ݽڵ��ֵ��ȡ���ڽӱ�ı�ͷ���
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
	 * �жϴ�i�ڵ㵽j�ڵ��Ƿ����·��
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean ExistPath(int i, int j, List list) {

		// ���������˼·���£�
		// 1.����������ȱ���
		// 2.�ݹ��壺���ȴ�i�ڵ�����һ���ڵ㣬������ڴ���һ���ڵ��ҵ�j�ڵ��·��
		// 3���ݹ���ڣ���i==jʱ����ʾ�ҵ���·��
		//
		if (list.size() <= n) {
			visit[i] = true;
			ANode node = listNodes[i];
			System.out.print(node.data + "\t");
			// ����·��
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
		System.out.println("ͼ���ڽӱ����£�");
		graphic.printGraph();
		System.out.println(Arrays.toString(graphic.listNodes));
		System.out.println("ͼ��������ȱ������ݹ飩��");
		graphic.depthSearch();
		System.out.println("ͼ�Ĺ�����ȱ��������У���");
		graphic.braodSearch(0);
		System.out.println("ͼ��������ȱ������ǵݹ飩��");
		System.out.println("������������������������������");
		System.out.println("�ҳ���i��j�ļ�·����");
		boolean[] visit = new boolean[n];
		for (int i = 0; i < visit.length; i++)
			visit[i] = false;
		graphic.setVisit(visit);

		List list = new ArrayList<>();
		boolean result = graphic.ExistPath(0, 3, list);
		System.out.println("�Ƿ��н� ��" + result);
		System.out.println("��·��Ϊ��");
		System.out.println(Arrays.asList(list));
	}
}
