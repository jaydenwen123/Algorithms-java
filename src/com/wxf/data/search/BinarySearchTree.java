package com.wxf.data.search;

import com.wxf.data.huffman.HuffmanEncodeAndDecode.binaryTreeNode;

/**
 * @author Administrator
 * 
 */
public class BinarySearchTree {

	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
	}

	private BinaryTreeNode root;

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}

	/**
	 * 二叉排序树的插入节点
	 * 
	 * @param item
	 */
	public void insert(BinaryTreeNode tree, int item) {
		// 二叉排序树的插入思路如下：
		// 1.首先需要判断要插入的节点到底大于还是小于根节点的数值
		// 2.如果根节点不为空，同时插入的值大于根节点的值，则说明该节点应该位于该树的右子树种
		// 3.然后再右子树中判断右子树是否为空一次进行插入
		// 4.同理，如果小于根节点的值，则说明位于左子树，然后再判断该根节点的左孩子是否为空，进行插入
		if (tree != null) {
			if (item > tree.data) {
				// 位于根节点的右子树中
				if (tree.rightChild != null) {
					// 在右子树进行递归的插入
					insert(tree.rightChild, item);
				} else {
					// 将该节点插入到根节点的右孩子位置
					BinaryTreeNode temp = new BinaryTreeNode(item);
					temp.parent = tree;
					tree.rightChild = temp;
				}
			} else if (item < tree.data) {
				if (tree.leftChild != null) {
					insert(tree.leftChild, item);
				} else {

					BinaryTreeNode node = new BinaryTreeNode(item);
					node.parent = tree;
					tree.leftChild = node;
				}
			} else {
				return;
			}
		}
	}

	/**
	 * 
	 * 二叉排序树的删除节点
	 * 
	 * @param item
	 */
	public void delete(int item) {
		delete(root, item);
	}

	/**
	 * 二叉排序树删除节点
	 * 
	 * @param tree
	 * @param item
	 * @return
	 */
	private void delete(BinaryTreeNode tree, int item) {
		// 二叉排序树的删除分为以下四种情况
		// 1.当删除的节点无孩子节点时，直接删除该节点
		// 2.要删除的节点只有左孩子节点时，删除该节点，同时使被删除的节点的双亲节点指向 被删除的左孩子节点
		// 3.要删除的节点，只有右孩子节点时，删除该节点，同时使被删除的节点的双亲节点指向被删除的右孩子节点
		// 4.要删除的节点有左右孩子节点时，分为以下三个步骤完成
		// 首先寻找数据元素值大于要删除的节点数据元素关键字的最小值，即寻找删除节点的右子树的最左节点。
		// 然后再把右子树的最左节点的数据元素复制到要删除的节点上，最后删除右子树的最左节点。
		if (tree != null) {
			if (tree.data > item) {
				delete(tree.leftChild, item);
			} else if (tree.data < item)
				delete(tree.rightChild, item);
			// 对应的是左右子树都不为空的情况
			else if (tree.leftChild != null && tree.rightChild != null) {
				// 1.首先寻找数据元素值大于要删除的节点的数据元素的关键字的最小值，
				// 即寻找要删除节点的右子树的最左节点
				BinaryTreeNode node = tree.rightChild;
				while (node.leftChild != null) {
					node = node.leftChild;
				}
				// 2.然后把右子树的最左节点的数据元素值拷贝到要删除的节点上
				tree.data = node.data;
				// 3.删除右子树的最左节点
				delete(tree.rightChild, node.data);
			} else {
				BinaryTreeNode parent = tree.parent;
				// 一下对三种情况进行考虑：
				// 1.左子树为空，右子树不为空
				if (tree.leftChild == null && tree.rightChild != null) {
					// 找到该节点的父节点
					// 以下必须进行判断，到底该节点是其父节点的左孩子还是右孩子，因为位置不同，操作不同
					if (parent.rightChild == tree) {
						parent.rightChild = tree.rightChild;
					} else {
						parent.leftChild = tree.rightChild;
					}
					tree.rightChild.parent = parent;
				}

				// 2.右子树为空，左子树不为空
				else if (tree.leftChild != null && tree.rightChild == null) {
					if (parent.leftChild == tree) {
						parent.leftChild = tree.leftChild;
					} else {
						parent.rightChild = tree.leftChild;
					}
					tree.leftChild.parent = parent;
				}
				// 3.左右子树均为空，即叶子节点
				else {
					if (parent.leftChild == tree)
						parent.leftChild = null;
					else {
						parent.rightChild = null;
					}
				}

			}
		}
		return;
	}

	/**
	 * 二叉排序树的递归查找
	 * 
	 * @param item
	 * @return
	 */
	public BinaryTreeNode search(int item) {
		return search(root, item);
	}

	/**
	 * 二叉排序树的非递归查找
	 * 
	 * @param item
	 * @return
	 */
	public BinaryTreeNode search2(int item) {
		return search2(root, item);
	}

	/**
	 * 二叉排序树的查找
	 * 
	 * @param item
	 * @return
	 */
	private BinaryTreeNode search(BinaryTreeNode tree, int item) {

		// 二叉排序树的查找思路：
		// 1.通过递归的方式来查找
		if (tree != null) {
			if (tree.data == item)
				return tree;
			else if (tree.data < item) {
				return search(tree.rightChild, item);
			} else {
				return search(tree.leftChild, item);
			}
		}
		// 2.如果该树不为空，则将当前元素和树的根节点的元素记性比较
		// 3.如果小于根节点的数据，则从树的左子树往下查找，否则右子树，
		// 4.如果正好相等，则直接返回。算法结束

		return null;
	}

	/**
	 * 循环结构的查找
	 * 
	 * @param tree
	 * @param item
	 * @return
	 */
	private BinaryTreeNode search2(BinaryTreeNode tree, int item) {
		if (tree != null) {
			BinaryTreeNode node = tree;
			while (node != null) {
				if (node.data == item)
					return node;
				else if (node.data > item) {
					node = node.leftChild;
				} else {
					node = node.rightChild;
				}
			}
		}
		return null;
	}

	/**
	 * 先序遍历
	 * 
	 * @param tree
	 */
	private void preOrder(BinaryTreeNode tree) {
		if (tree != null) {
			System.out.println(tree.data);
			preOrder(tree.leftChild);
			preOrder(tree.rightChild);
		}
	}

	/**
	 * 先序遍历
	 */
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * 中序遍历
	 * 
	 * @param tree
	 */
	private void inOrder(BinaryTreeNode tree) {
		if (tree != null) {
			inOrder(tree.leftChild);
			System.out.println(tree.data);
			inOrder(tree.rightChild);
		}
	}

	/**
	 * 中序遍历
	 */
	public void inOrder() {
		inOrder(root);
	}

	/**
	 * 后序遍历
	 * 
	 * @param tree
	 */
	private void sufOrder(BinaryTreeNode tree) {

		if (tree != null) {
			sufOrder(tree.leftChild);
			sufOrder(tree.rightChild);
			System.out.println(tree.data);
		}
	}

	/**
	 * 
	 * 后序遍历
	 */
	public void sufOrder() {
		sufOrder(root);
	}

	/**
	 * 二叉排序树（二叉查找树）的节点类设计
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BinaryTreeNode {
		// 1.父节点
		public BinaryTreeNode parent;
		// 2.左孩子节点
		public BinaryTreeNode leftChild;
		// 3.右孩子节点
		public BinaryTreeNode rightChild;
		// 4.数据元素
		public int data;

		public BinaryTreeNode(BinaryTreeNode parent, BinaryTreeNode leftChild,
				BinaryTreeNode rightChild, int data) {
			super();
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.data = data;
		}

		public BinaryTreeNode() {
			// TODO Auto-generated constructor stub
			leftChild = rightChild = null;
		}

		public BinaryTreeNode(int data) {
			this(null, null, data);
		}

		public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right,
				int data) {
			leftChild = left;
			rightChild = right;
			this.data = data;
		}

	}

	/**
	 * 解析查找后的结果
	 * 
	 * @param node
	 * @return
	 */
	public static String parseSearch(BinaryTreeNode node) {
		String result = "";
		result = (node == null) ? "没有找到该节点，该节点不存在" : "找到了该节点，该节点的数值为："
				+ node.data;
		return result;
	}

	public static void main(String[] args) {
		// int array[] = new int[] { 4, 5, 7, 2, 1, 9, 8, 11, 3 };
		int array[] = new int[] { 18, 14, 5, 16, 7, 10, 24, 20, 38, 30, 35 };
		BinarySearchTree biSearchTree = new BinarySearchTree();
		BinaryTreeNode treeNode = new BinaryTreeNode(array[0]);
		for (int i = 1; i < array.length; i++) {
			biSearchTree.insert(treeNode, array[i]);
		}
		biSearchTree.setRoot(treeNode);
		System.out.println("中序遍历结果如下：");
		biSearchTree.inOrder();
		// List<Integer> list = biSearchTree.inOrder();
		// System.out.println(list.toString());
		System.out.println("先序遍历结果如下：");
		biSearchTree.preOrder();
		// list = biSearchTree.preOrder();
		// System.out.println(list.toString());
		System.out.println("后序遍历的记过如下：");
		biSearchTree.sufOrder();
		System.out.println("二叉排序树的查找：递归结构");
		System.out.println(parseSearch(biSearchTree.search(34)));
		System.out.println(parseSearch(biSearchTree.search(35)));
		System.out.println("二叉排序树的查找：循环结构");
		System.out.println(parseSearch(biSearchTree.search2(34)));
		System.out.println(parseSearch(biSearchTree.search2(35)));
		System.out.println("二叉排序树的删除操作：");
		biSearchTree.delete(38);
		System.out.println("中序遍历结果如下：");
		biSearchTree.inOrder();
		
	}

}
