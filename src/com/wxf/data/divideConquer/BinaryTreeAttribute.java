package com.wxf.data.divideConquer;

public class BinaryTreeAttribute {

	// 二叉树的根节点
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
	 * 递归先序遍历
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
	 * 递归中序遍历
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
	 * 递归后序遍历
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
	 * 获取二叉树的高度
	 * 
	 * @param root2
	 * @return
	 */
	private int getTreeHeight(BTreeNode root2) {
		// TODO Auto-generated method stub
		int count = 0;
		if (root2 == null)
			return -1;
		// 通过分治法求二叉树的高度，在其左子树有右子树中递归，然后返回较大的那个的树 的高度
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
	 * 求二叉树的节点的个数
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
	 * 获取二叉树的总的节点数目
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
		// 通过分治法计算二叉树总的节点数目的思路如下：
		// 1。首先对更节点判断，如果为空，则返回0
		// 2、在根节点不为空的情况下，对其左孩子和右孩子是否为空，进行判断。分为四种情况
		// 3.左孩子为空，右孩子不为空|左孩子为空，右孩子为空|左孩子不为空，右孩子不为空|左孩子不为空，右孩子为空
		// 4.最后在对其左右孩子同时不为空时递归累加，然后返回结果
		return count;
	}

	public int getNodeTwoCount() {
		return getNodeTwoCount(root);
	}

	/**
	 * 返回二叉树中节点度为2的节点个数 通过分治法来求解
	 * 
	 * @param root2
	 */
	private int getNodeTwoCount(BTreeNode root2) {
		// TODO Auto-generated method stub
		int count = 0;
		// 该算法的思路如下：
		// 1.首先找到递归体和递归出口
		// 2、递归体为：对其左子树和右子树进行同样的遍历
		// 3、递归出口：当该树为空时，返回0，当该树的左子树不为空，同时右子树不为空时，返回1
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
	 * 二叉树的节点类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BTreeNode {
		// 左孩子节点
		public BTreeNode leftNode;
		// 右孩子节点
		public BTreeNode rightNode;
		// 数据
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

		// 以下进行构造一个形式如下的二叉树：
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
		// 二叉树的递归先序遍历
		System.out.println("二叉树的递归先序遍历：");
		tree.preOrder();
		// 二叉树的递归中序遍历
		System.out.println("二叉树的递归中序遍历：");
		tree.inOrder();
		// 二叉树的递归后序遍历
		System.out.println("二叉树的递归后序遍历：");
		tree.postOrder();
		System.out.println("获取二叉树的高度：");
		System.out.println(tree.getTreeHeight());
		System.out.println("获取二叉树的总的节点数目：");
		System.out.println(tree.getTreeNodeCount());
		System.out.println("获取二叉树的节点的度为2的节点数目：");
		System.out.println(tree.getNodeTwoCount());
		System.out.println("***********************");
		System.out.println(tree.getSize(tree.getRoot()));
	}
}
