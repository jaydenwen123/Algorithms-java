package com.wxf.data.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

/**
 * 二叉排序树
 * 
 * @author Administrator
 * 
 */
/**
 * @author Administrator
 * 
 */
public class BiSearchTree {

	// 根节点
	private BiTreeNode root;
	// 保存数据。，如中序和先序后序的遍历结果值
	private List<Integer> preList = new ArrayList<Integer>();
	private List<Integer> inList = new ArrayList<Integer>();
	private List<Integer> postList = new ArrayList<Integer>();

	/**
	 * 先序遍历
	 * 
	 * @param t
	 *            要进行遍历的根节点
	 * @return 返回值为存储在list集合中的遍历后的值
	 */
	public List<Integer> preOrder(BiTreeNode t) {
		if (t != null) {
			preList.add(t.data);
			// System.out.print(t.data+"\t");
			// System.out.println();
			preOrder(t.leftchild);
			preOrder(t.rightchild);
			return preList;
		}
		return null;
	}

	public List<Integer> preOrder() {
		return preOrder(root);
	}

	/**
	 * 中序遍历
	 * 
	 * @param t
	 *            要遍历的根节点
	 * @return 返回的list集合中存储的是遍历后的data数据
	 */
	public List<Integer> inOrder(BiTreeNode t) {
		if (t != null) {
			inOrder(t.leftchild);
			inList.add(t.data);
			inOrder(t.rightchild);
			return inList;
		}
		return null;
	}

	/**
	 * 中序遍历
	 */
	public List<Integer> inOrder() {
		return inOrder(root);
	}

	/**
	 * 二叉树的后序遍历
	 * 
	 * @param t
	 *            要遍历的根节点
	 * @return 返回的list集合中存储的是每个节点的data数据值
	 */
	public List<Integer> postOrder(BiTreeNode t) {
		if (t != null) {
			postOrder(t.leftchild);
			postOrder(t.rightchild);
			postList.add(t.data);
			return postList;
		}
		return null;
	}

	/**
	 * 后序遍历
	 */
	public List<Integer> postOrder() {
		return postOrder(root);
	}

	/**
	 * 二叉排序树的插入
	 * 
	 * @param t
	 * @param item
	 */
	public void insert(BiTreeNode t, int item) {
		if (t != null) {
			// 如果当前的数小于根节点的数，则表示该数应在该该节点的左子树中,否则在右子树中，
			if (item < t.data) {
				// 如果该数的左子树存在，则进行递归的插入，否则表示左孩子节点不存在，然后创建左孩子节点
				if (t.leftchild != null)
					insert(t.leftchild, item);
				else {
					// 创建左孩子节点
					BiTreeNode leftNode = new BiTreeNode(item);
					leftNode.parent = t;
					t.leftchild = leftNode;
				}
				// 以下是在右子树中进行插入的操作
			} else if (item > t.data) {
				if (t.rightchild == null) {
					BiTreeNode rightNode = new BiTreeNode(item);
					rightNode.parent = t;
					t.rightchild = rightNode;
				} else {
					insert(t.rightchild, item);
				}
			}
			return;
		}
	}

	/**
	 * 二叉排序树的删除
	 * 
	 * @param t
	 * @param item
	 */
	public void delete(BiTreeNode t, int item) {

		// 二叉排序树的删除分为以下四种情况
		// 1.当删除的节点无孩子节点时，直接删除该节点
		// 2.要删除的节点只有左孩子节点时，删除该节点，同时使被删除的节点的双亲节点指向 被删除的左孩子节点
		// 3.要删除的节点，只有右孩子节点时，删除该节点，同时使被删除的节点的双亲节点指向被删除的右孩子节点
		// 4.要删除的节点有左右孩子节点时，分为以下三个步骤完成
		// 首先寻找数据元素值大于要删除的节点数据元素关键字的最小值，即寻找删除节点的右子树的最左节点。
		// 然后再把右子树的最左节点的数据元素复制到要删除的节点上，最后删除右子树的最左节点。
		if (t != null) {

			if (item < t.data)
				delete(t.leftchild, item);
			else if (item > t.data)
				delete(t.rightchild, item);
			// 对应的是左右子树都不为空的情况
			else if (t.leftchild != null && t.rightchild != null) {
				BiTreeNode min = t.rightchild;
				// 找到该节点的右子树中的最左节点。
				while (min.leftchild != null) {
					min = min.leftchild;
				}
				t.data = min.data;
				// 删除该节点右子树中的最左节点。
				delete(t.rightchild, min.data);
			} else {
				// 对于只有右孩子节点的情况
				if (t.leftchild == null && t.rightchild != null) {
					// 如果要删除的节点的父节点的左孩子为空，同时右节点为该节点，则进行一下的操作
					if (t.parent.rightchild == t) {
						t.parent.rightchild = t.rightchild;
						// 如果要删除的节点的父节点的左孩子为不空，同时右节点为该节点，则进行一下的操作
					} else if (t.parent.leftchild == t) {
						t.parent.leftchild = t.rightchild;
					}
					t.rightchild.parent = t.parent;
					// 对应只有左孩子节点的情况
				} else if (t.rightchild == null && t.leftchild != null) {
					// 以下还需要判断到底该节点是父节点的左孩子还是右孩子。因为
					if (t.parent.rightchild == t) {
						t.parent.rightchild = t.leftchild;
						// 如果要删除的节点的父节点的左孩子为不空，同时右节点为该节点，则进行一下的操作
					} else if (t.parent.leftchild == t) {
						t.parent.leftchild = t.leftchild;
					}
					t.leftchild.parent = t.parent;
					// 对应叶子节点的情况，既无左孩子有无右孩子
				} else {
					BiTreeNode parent = t.parent;
					if (parent.leftchild == t) {
						parent.leftchild = null;
					} else {
						parent.rightchild = null;
					}
				}
			}
		}
	}

	/**
	 * 二叉排序树的查找 通过循环的方式来查找
	 * 
	 * @param item
	 */
	public BiTreeNode find(int item) {
		if (root != null) {
			BiTreeNode temp = root;
			while (temp != null) {
				if (item == temp.data)
					return temp;
				else if (item < temp.data) {
					temp = temp.leftchild;
				} else {
					temp = temp.rightchild;
				}
			}

		}
		return null;
	}

	/**
	 * 二叉排序树的查找 通过递归的方式查找
	 * 
	 * @param item
	 */
	public BiTreeNode find(BiTreeNode t, int item) {
		if (t != null) {
			if (t.data == item)
				return t;
			else if (t.data < item) {
				return find(t.rightchild, item);
			} else {
				return find(t.leftchild, item);
			}

		}
		return null;
	}

	/**
	 * 取得左孩子节点
	 * 
	 * @param currentTreeNode
	 * @return
	 */
	public BiTreeNode getLeftChild(BiTreeNode currentTreeNode) {
		return currentTreeNode == null ? null : currentTreeNode.leftchild;
	}

	/**
	 * 取得当前节点的右孩子节点
	 * 
	 * @param currentTreeNode
	 * @return
	 */
	public BiTreeNode getRightChild(BiTreeNode currentTreeNode) {
		return currentTreeNode == null ? null : currentTreeNode.rightchild;
	}

	public BiTreeNode getRoot() {
		return root;
	}

	public void setRoot(BiTreeNode root) {
		this.root = root;
	}

	public BiSearchTree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 二叉排序树的节点类该节点类的设计如下。 1首先除了包含左右孩子孩子节点和数据元素之外， 2还包括一个双亲节点
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BiTreeNode {

		public BiTreeNode parent;
		public BiTreeNode leftchild;
		public BiTreeNode rightchild;
		public int data;

		public BiTreeNode() {
			this.leftchild = null;
			this.rightchild = null;
		}

		public BiTreeNode(BiTreeNode parent, BiTreeNode leftchild,
				BiTreeNode rightchild, int data) {
			super();
			this.parent = parent;
			this.leftchild = leftchild;
			this.rightchild = rightchild;
			this.data = data;
		}

		public BiTreeNode(int data) {
			this(null, null, data);
		}

		public BiTreeNode(BiTreeNode leftchild, BiTreeNode rightchild, int data) {
			this.leftchild = leftchild;
			this.rightchild = rightchild;
			this.data = data;
		}

	}

	public static void main(String[] args) {
		// int array[] = new int[] { 4, 5, 7, 2, 1, 9, 8, 11, 3 };
		int array[] = new int[] { 18, 14, 5, 16, 7, 10, 24, 20, 38, 30, 35 };
		BiSearchTree biSearchTree = new BiSearchTree();
		BiTreeNode treeNode = new BiTreeNode(array[0]);
		for (int i = 1; i < array.length; i++) {
			biSearchTree.insert(treeNode, array[i]);
		}
		biSearchTree.setRoot(treeNode);
		System.out.println("中序遍历结果如下：");
		List<Integer> list = biSearchTree.inOrder();
		System.out.println(list.toString());
		System.out.println("先序遍历结果如下：");
		list = biSearchTree.preOrder();
		System.out.println(list.toString());
		System.out.println("后序遍历的记过如下：");
		list = biSearchTree.postOrder();
		System.out.println(list.toString());

		BiTreeNode node = biSearchTree.find(biSearchTree.root, 7);
		System.out.println(node.data);

		biSearchTree.delete(biSearchTree.root, 38);
		System.out.println("**********************");
		System.out.println("中序遍历结果如下：");
		list = biSearchTree.inOrder();
		System.out.println(list.toString());
	}

}
