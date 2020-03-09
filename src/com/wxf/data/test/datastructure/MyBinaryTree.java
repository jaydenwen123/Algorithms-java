package com.wxf.data.test.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树
 * 
 * @author Administrator
 * 
 */
public class MyBinaryTree {

	// 根节点
	private MyBinaryNode root;

	public MyBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 先序遍历
	 */
	public void preOrder(MyBinaryNode node) {
		if (node != null) {
			System.out.print(node.data + "\t");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	/**
	 * 递归先序遍历
	 */
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * 递归中序遍历
	 */
	public void inOrder() {
		inOrder(root);
	}

	public void inOrder(MyBinaryNode node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.data + "\t");
			inOrder(node.right);
		}
	}

	/**
	 * 递归后序遍历
	 */
	public void sufOrder() {
		sufOrder(root);
	}

	public void sufOrder(MyBinaryNode node) {
		if (node != null) {
			sufOrder(node.left);
			sufOrder(node.right);
			System.out.print(node.data + "\t");
		}
	}

	/**
	 * 非递归先序遍历
	 */
	public void preOrder_stack() {
		// 1.初始化，2.入栈，3，访问。4，不断入栈和出栈
		if (root != null) {
			Stack<MyBinaryNode> stack = new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				MyBinaryNode node = stack.pop();
				System.out.print(node.data + "\t");
				if (node.right != null)
					stack.push(node.right);
				if (node.left != null)
					stack.push(node.left);
			}
		}
		System.out.println();
	}

	/**
	 * 非递归中序遍历
	 */
	public void inOrder_stack() {

		Stack<MyBinaryNode> stack = new Stack<MyBinaryNode>();
		MyBinaryNode node = root;
		while (node != null || stack.size() > 0) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.data + "\t");
				node = node.right;
			}
		}
		System.out.println();
	}

	/**
	 * 非递归后序遍历
	 */
	public void sufOrder_stack() {

		// 1.初始化两个栈2.入栈
		// 该栈用来存储根右左顺序的节点
		Stack<MyBinaryNode> stack1 = new Stack<MyBinaryNode>();
		// 用来逆序输出
		Stack<MyBinaryNode> stack2 = new Stack<MyBinaryNode>();
		if (root != null) {
			stack1.push(root);
			while (!stack1.isEmpty()) {
				MyBinaryNode node = stack1.pop();
				stack2.push(node);
				if (node.left != null)
					stack1.push(node.left);
				if (node.right != null)
					stack1.push(node.right);
			}

			while (!stack2.isEmpty()) {
				System.out.print(stack2.pop().data + "\t");
			}
			System.out.println();
		}

	}

	/**
	 * 广度优先遍历（通过队列来实现）
	 */
	public void layOrder() {
		// 通过队列来实现,1初始化，3入队，2访问,4,不断执行入队和出队
		if (root != null) {
			Queue<MyBinaryNode> queue = new LinkedList<MyBinaryNode>();
			System.out.print(root.data + "\t");
			queue.add(root);
			while (!queue.isEmpty()) {
				MyBinaryNode node = queue.poll();
				if (node.left != null) {
					System.out.print(node.left.data + "\t");
					queue.add(node.left);
				}
				if (node.right != null) {
					System.out.print(node.right.data + "\t");
					queue.add(node.right);
				}
			}
		}
	}

	public MyBinaryNode getRoot() {
		return root;
	}

	public void setRoot(MyBinaryNode root) {
		this.root = root;
	}

	public static void main(String[] args) {
		System.out.println("creat the tree node!!!");
		MyBinaryNode nodeA = new MyBinaryNode("A");
		MyBinaryNode nodeB = new MyBinaryNode("B");
		MyBinaryNode nodeH = new MyBinaryNode("H");
		MyBinaryNode nodeP = new MyBinaryNode("P");

		MyBinaryNode nodeD = new MyBinaryNode("D", nodeB, null);
		MyBinaryNode nodeG = new MyBinaryNode("G", nodeH, nodeP);

		MyBinaryNode nodeC = new MyBinaryNode("C", nodeA, nodeD);
		MyBinaryNode nodeE = new MyBinaryNode("E", null, nodeG);

		MyBinaryNode nodeF = new MyBinaryNode("F", nodeC, nodeE);

		MyBinaryTree tree = new MyBinaryTree();
		tree.setRoot(nodeF);
		System.out.println("test preOrder  digui");
		tree.preOrder();
		System.out.println("\ntest inOrder   digui");
		tree.inOrder();
		System.out.println("\ntest sufOrder  digui ");
		tree.sufOrder();
		System.out.println();
		System.out.println("test preOrder  feidigui");
		tree.preOrder_stack();
		System.out.println("test inOrder  feidigui");
		tree.inOrder_stack();
		System.out.println("test sufOrder  feidigui ");
		tree.sufOrder_stack();
		System.out.println("test layOrder with queue");
		tree.layOrder();

	}
}
