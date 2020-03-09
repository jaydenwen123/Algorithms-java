package com.wxf.data.divideConquer;

public class BinaryTreeAttribute {

	// �������ĸ��ڵ�
	private BTreeNode root;

	public BTreeNode getRoot() {
		return root;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}

	public BinaryTreeAttribute() {
		// TODO Auto-generated constructor stub
	}

	public void preOrder() {
		preOrder(root);
		System.out.println();
	}

	/**
	 * �ݹ��������
	 * 
	 * @param node
	 */
	private void preOrder(BTreeNode node) {
		if (node != null) {
			System.out.print(node.data + "\t");
			preOrder(node.leftNode);
			preOrder(node.rightNode);
		}
	}

	public void inOrder() {
		inOrder(root);
		System.out.println();
	}

	/**
	 * �ݹ��������
	 * 
	 * @param root2
	 */
	private void inOrder(BTreeNode root2) {
		// TODO Auto-generated method stub
		if (root2 != null) {
			inOrder(root2.leftNode);
			System.out.print(root2.data + "\t");
			inOrder(root2.rightNode);
		}
	}

	public void postOrder() {
		postOrder(root);
		System.out.println();
	}

	/**
	 * �ݹ�������
	 * 
	 * @param root2
	 */
	private void postOrder(BTreeNode root2) {
		// TODO Auto-generated method stub

		if (root2 != null) {
			postOrder(root2.leftNode);
			postOrder(root2.rightNode);
			System.out.print(root2.data + "\t");
		}
	}

	public int getTreeHeight() {
		return getTreeHeight(root);
	}

	/**
	 * ��ȡ�������ĸ߶�
	 * 
	 * @param root2
	 * @return
	 */
	private int getTreeHeight(BTreeNode root2) {
		// TODO Auto-generated method stub
		int count = 0;
		if (root2 == null)
			return -1;
		// ͨ�����η���������ĸ߶ȣ��������������������еݹ飬Ȼ�󷵻ؽϴ���Ǹ����� �ĸ߶�
		int leftHeight = ((root2.leftNode == null) ? 0
				: getTreeHeight(root2.leftNode));
		int rightHeight = ((root2.rightNode == null) ? 0
				: getTreeHeight(root2.rightNode));
		count = (leftHeight > rightHeight ? leftHeight : rightHeight);
		return count + 1;
	}

	public int getTreeNodeCount() {
		return getTreeNodeCount(root);
	}

	/**
	 * ��������Ľڵ�ĸ���
	 * 
	 * @param node
	 * @return
	 */
	public int getSize(BTreeNode node) {
		int count = 0;
		if (node == null) {
			return 0;
		}
		int left = (node.leftNode == null ? 0 : getSize(node.leftNode));
		int right = (node.rightNode == null ? 0 : getSize(node.rightNode));
		count = left + right + 1;
		return count;
	}

	/**
	 * ��ȡ���������ܵĽڵ���Ŀ
	 * 
	 * @param node
	 * @return
	 */
	private int getTreeNodeCount(BTreeNode node) {
		int count = 0;
		if (node != null) {
			count += getTreeNodeCount(node.leftNode)
					+ getTreeNodeCount(node.rightNode) + 1;
		}
		// ͨ�����η�����������ܵĽڵ���Ŀ��˼·���£�
		// 1�����ȶԸ��ڵ��жϣ����Ϊ�գ��򷵻�0
		// 2���ڸ��ڵ㲻Ϊ�յ�����£��������Ӻ��Һ����Ƿ�Ϊ�գ������жϡ���Ϊ�������
		// 3.����Ϊ�գ��Һ��Ӳ�Ϊ��|����Ϊ�գ��Һ���Ϊ��|���Ӳ�Ϊ�գ��Һ��Ӳ�Ϊ��|���Ӳ�Ϊ�գ��Һ���Ϊ��
		// 4.����ڶ������Һ���ͬʱ��Ϊ��ʱ�ݹ��ۼӣ�Ȼ�󷵻ؽ��
		return count;
	}

	public int getNodeTwoCount() {
		return getNodeTwoCount(root);
	}

	/**
	 * ���ض������нڵ��Ϊ2�Ľڵ���� ͨ�����η������
	 * 
	 * @param root2
	 */
	private int getNodeTwoCount(BTreeNode root2) {
		// TODO Auto-generated method stub
		int count = 0;
		// ���㷨��˼·���£�
		// 1.�����ҵ��ݹ���͵ݹ����
		// 2���ݹ���Ϊ������������������������ͬ���ı���
		// 3���ݹ���ڣ�������Ϊ��ʱ������0������������������Ϊ�գ�ͬʱ��������Ϊ��ʱ������1
		if (root2 == null)
			return 0;
		if (root2.leftNode != null && root2.rightNode != null)
			count += getNodeTwoCount(root2.leftNode)
					+ getNodeTwoCount(root2.rightNode) + 1;
		else {
			if (root2.leftNode != null) {
				count += getNodeTwoCount(root2.leftNode);
			}
			if (root2.rightNode != null) {
				count += getNodeTwoCount(root2.rightNode);
			}
		}
		return count;
	}

	/**
	 * �������Ľڵ���
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BTreeNode {
		// ���ӽڵ�
		public BTreeNode leftNode;
		// �Һ��ӽڵ�
		public BTreeNode rightNode;
		// ����
		public Object data;

		public BTreeNode() {

		}

		public BTreeNode(BTreeNode leftNode, BTreeNode rightNode, Object data) {
			super();
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			this.data = data;
		}

		public BTreeNode(Object data) {
			super();
			this.data = data;
			leftNode = rightNode = null;
		}

	}

	public static void main(String[] args) {

		// ���½��й���һ����ʽ���µĶ�������
		// A
		// / \
		// B C
		// / \ /
		// D E F
		// \ / / \
		// G H Q P
		BTreeNode nodeG = new BTreeNode("G");
		BTreeNode nodeH = new BTreeNode("H");
		BTreeNode nodeP = new BTreeNode("P");
		BTreeNode nodeQ = new BTreeNode("Q");

		BTreeNode nodeD = new BTreeNode(null, nodeG, "D");
		BTreeNode nodeE = new BTreeNode(nodeH, null, "E");
		BTreeNode nodeF = new BTreeNode(nodeQ, nodeP, "F");

		BTreeNode nodeB = new BTreeNode(nodeD, nodeE, "B");
		BTreeNode nodeC = new BTreeNode(nodeF, null, "C");
		BTreeNode nodeA = new BTreeNode(nodeB, nodeC, "A");
		BinaryTreeAttribute tree = new BinaryTreeAttribute();
		tree.setRoot(nodeA);
		// �������ĵݹ��������
		System.out.println("�������ĵݹ����������");
		tree.preOrder();
		// �������ĵݹ��������
		System.out.println("�������ĵݹ����������");
		tree.inOrder();
		// �������ĵݹ�������
		System.out.println("�������ĵݹ���������");
		tree.postOrder();
		System.out.println("��ȡ�������ĸ߶ȣ�");
		System.out.println(tree.getTreeHeight());
		System.out.println("��ȡ���������ܵĽڵ���Ŀ��");
		System.out.println(tree.getTreeNodeCount());
		System.out.println("��ȡ�������Ľڵ�Ķ�Ϊ2�Ľڵ���Ŀ��");
		System.out.println(tree.getNodeTwoCount());
		System.out.println("***********************");
		System.out.println(tree.getSize(tree.getRoot()));
	}
}
