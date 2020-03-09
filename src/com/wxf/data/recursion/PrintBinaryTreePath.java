package com.wxf.data.recursion;

public class PrintBinaryTreePath {

	// 根节点
	private BTNode root;

	public BTNode getRoot() {
		return root;
	}

	public void setRoot(BTNode root) {
		this.root = root;
	}

	/**
	 * 递归输出节点的路径
	 * 
	 * @param node
	 */
	public void printNodePath(BTNode node) {
		if(!node.symbol.equals(root.symbol)){
			printNodePath(node.parent);
		}
		System.out.println(node.symbol);
	}

	public static class BTNode {
		public BTNode parent;
		public BTNode leftchild;
		public BTNode rightchild;
		public Object symbol;

		public BTNode() {
			// TODO Auto-generated constructor stub
			parent = leftchild = rightchild = null;
		}

		public BTNode(BTNode parent, BTNode leftchild, BTNode rightchild,
				Object symbol) {
			super();
			this.parent = parent;
			this.leftchild = leftchild;
			this.rightchild = rightchild;
			this.symbol = symbol;
		}

		public BTNode(BTNode leftchild, BTNode rightchild, Object symbol) {
			super();
			this.leftchild = leftchild;
			this.rightchild = rightchild;
			this.symbol = symbol;
		}

		public BTNode(Object symbol) {
			super();
			parent = null;
			this.symbol = symbol;
			leftchild = rightchild = null;
		}

	}

	public static void main(String[] args) {

		BTNode btNode11 = new BTNode("I");
		BTNode btNode22 = new BTNode("J");
		BTNode btNode0=new BTNode(btNode11,btNode22, "H");
		btNode22.parent=btNode11.parent=btNode0;
		BTNode btNode1 = new BTNode("D");
		BTNode btNode2 = new BTNode("E");
		BTNode btNode3 = new BTNode("F");
		BTNode btNode4 = new BTNode(btNode0, null, "G");
		btNode0.parent=btNode4;
		BTNode btNode5 = new BTNode(btNode1, btNode2, "B");
		BTNode btNode6 = new BTNode(btNode3, btNode4, "C");
		btNode1.parent = btNode2.parent = btNode5;
		btNode3.parent = btNode4.parent = btNode6;
		BTNode btNode7 = new BTNode(btNode5, btNode6, "A");
		btNode5.parent = btNode6.parent = btNode7;

		// 创建二叉树
		PrintBinaryTreePath tree = new PrintBinaryTreePath();
		tree.setRoot(btNode7);
		tree.printNodePath(btNode11);
		System.out.println("*****************");
		tree.printNodePath(btNode22);
	}
}
