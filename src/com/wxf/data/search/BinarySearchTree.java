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
	 * �����������Ĳ���ڵ�
	 * 
	 * @param item
	 */
	public void insert(BinaryTreeNode tree, int item) {
		// �����������Ĳ���˼·���£�
		// 1.������Ҫ�ж�Ҫ����Ľڵ㵽�״��ڻ���С�ڸ��ڵ����ֵ
		// 2.������ڵ㲻Ϊ�գ�ͬʱ�����ֵ���ڸ��ڵ��ֵ����˵���ýڵ�Ӧ��λ�ڸ�������������
		// 3.Ȼ�������������ж��������Ƿ�Ϊ��һ�ν��в���
		// 4.ͬ�����С�ڸ��ڵ��ֵ����˵��λ����������Ȼ�����жϸø��ڵ�������Ƿ�Ϊ�գ����в���
		if (tree != null) {
			if (item > tree.data) {
				// λ�ڸ��ڵ����������
				if (tree.rightChild != null) {
					// �����������еݹ�Ĳ���
					insert(tree.rightChild, item);
				} else {
					// ���ýڵ���뵽���ڵ���Һ���λ��
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
	 * ������������ɾ���ڵ�
	 * 
	 * @param item
	 */
	public void delete(int item) {
		delete(root, item);
	}

	/**
	 * ����������ɾ���ڵ�
	 * 
	 * @param tree
	 * @param item
	 * @return
	 */
	private void delete(BinaryTreeNode tree, int item) {
		// ������������ɾ����Ϊ�����������
		// 1.��ɾ���Ľڵ��޺��ӽڵ�ʱ��ֱ��ɾ���ýڵ�
		// 2.Ҫɾ���Ľڵ�ֻ�����ӽڵ�ʱ��ɾ���ýڵ㣬ͬʱʹ��ɾ���Ľڵ��˫�׽ڵ�ָ�� ��ɾ�������ӽڵ�
		// 3.Ҫɾ���Ľڵ㣬ֻ���Һ��ӽڵ�ʱ��ɾ���ýڵ㣬ͬʱʹ��ɾ���Ľڵ��˫�׽ڵ�ָ��ɾ�����Һ��ӽڵ�
		// 4.Ҫɾ���Ľڵ������Һ��ӽڵ�ʱ����Ϊ���������������
		// ����Ѱ������Ԫ��ֵ����Ҫɾ���Ľڵ�����Ԫ�عؼ��ֵ���Сֵ����Ѱ��ɾ���ڵ��������������ڵ㡣
		// Ȼ���ٰ�������������ڵ������Ԫ�ظ��Ƶ�Ҫɾ���Ľڵ��ϣ����ɾ��������������ڵ㡣
		if (tree != null) {
			if (tree.data > item) {
				delete(tree.leftChild, item);
			} else if (tree.data < item)
				delete(tree.rightChild, item);
			// ��Ӧ����������������Ϊ�յ����
			else if (tree.leftChild != null && tree.rightChild != null) {
				// 1.����Ѱ������Ԫ��ֵ����Ҫɾ���Ľڵ������Ԫ�صĹؼ��ֵ���Сֵ��
				// ��Ѱ��Ҫɾ���ڵ��������������ڵ�
				BinaryTreeNode node = tree.rightChild;
				while (node.leftChild != null) {
					node = node.leftChild;
				}
				// 2.Ȼ���������������ڵ������Ԫ��ֵ������Ҫɾ���Ľڵ���
				tree.data = node.data;
				// 3.ɾ��������������ڵ�
				delete(tree.rightChild, node.data);
			} else {
				BinaryTreeNode parent = tree.parent;
				// һ�¶�����������п��ǣ�
				// 1.������Ϊ�գ���������Ϊ��
				if (tree.leftChild == null && tree.rightChild != null) {
					// �ҵ��ýڵ�ĸ��ڵ�
					// ���±�������жϣ����׸ýڵ����丸�ڵ�����ӻ����Һ��ӣ���Ϊλ�ò�ͬ��������ͬ
					if (parent.rightChild == tree) {
						parent.rightChild = tree.rightChild;
					} else {
						parent.leftChild = tree.rightChild;
					}
					tree.rightChild.parent = parent;
				}

				// 2.������Ϊ�գ���������Ϊ��
				else if (tree.leftChild != null && tree.rightChild == null) {
					if (parent.leftChild == tree) {
						parent.leftChild = tree.leftChild;
					} else {
						parent.rightChild = tree.leftChild;
					}
					tree.leftChild.parent = parent;
				}
				// 3.����������Ϊ�գ���Ҷ�ӽڵ�
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
	 * �����������ĵݹ����
	 * 
	 * @param item
	 * @return
	 */
	public BinaryTreeNode search(int item) {
		return search(root, item);
	}

	/**
	 * �����������ķǵݹ����
	 * 
	 * @param item
	 * @return
	 */
	public BinaryTreeNode search2(int item) {
		return search2(root, item);
	}

	/**
	 * �����������Ĳ���
	 * 
	 * @param item
	 * @return
	 */
	private BinaryTreeNode search(BinaryTreeNode tree, int item) {

		// �����������Ĳ���˼·��
		// 1.ͨ���ݹ�ķ�ʽ������
		if (tree != null) {
			if (tree.data == item)
				return tree;
			else if (tree.data < item) {
				return search(tree.rightChild, item);
			} else {
				return search(tree.leftChild, item);
			}
		}
		// 2.���������Ϊ�գ��򽫵�ǰԪ�غ����ĸ��ڵ��Ԫ�ؼ��ԱȽ�
		// 3.���С�ڸ��ڵ�����ݣ�����������������²��ң�������������
		// 4.���������ȣ���ֱ�ӷ��ء��㷨����

		return null;
	}

	/**
	 * ѭ���ṹ�Ĳ���
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
	 * �������
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
	 * �������
	 */
	public void preOrder() {
		preOrder(root);
	}

	/**
	 * �������
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
	 * �������
	 */
	public void inOrder() {
		inOrder(root);
	}

	/**
	 * �������
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
	 * �������
	 */
	public void sufOrder() {
		sufOrder(root);
	}

	/**
	 * ������������������������Ľڵ������
	 * 
	 * @author Administrator
	 * 
	 */
	public static class BinaryTreeNode {
		// 1.���ڵ�
		public BinaryTreeNode parent;
		// 2.���ӽڵ�
		public BinaryTreeNode leftChild;
		// 3.�Һ��ӽڵ�
		public BinaryTreeNode rightChild;
		// 4.����Ԫ��
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
	 * �������Һ�Ľ��
	 * 
	 * @param node
	 * @return
	 */
	public static String parseSearch(BinaryTreeNode node) {
		String result = "";
		result = (node == null) ? "û���ҵ��ýڵ㣬�ýڵ㲻����" : "�ҵ��˸ýڵ㣬�ýڵ����ֵΪ��"
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
		System.out.println("�������������£�");
		biSearchTree.inOrder();
		// List<Integer> list = biSearchTree.inOrder();
		// System.out.println(list.toString());
		System.out.println("�������������£�");
		biSearchTree.preOrder();
		// list = biSearchTree.preOrder();
		// System.out.println(list.toString());
		System.out.println("��������ļǹ����£�");
		biSearchTree.sufOrder();
		System.out.println("�����������Ĳ��ң��ݹ�ṹ");
		System.out.println(parseSearch(biSearchTree.search(34)));
		System.out.println(parseSearch(biSearchTree.search(35)));
		System.out.println("�����������Ĳ��ң�ѭ���ṹ");
		System.out.println(parseSearch(biSearchTree.search2(34)));
		System.out.println(parseSearch(biSearchTree.search2(35)));
		System.out.println("������������ɾ��������");
		biSearchTree.delete(38);
		System.out.println("�������������£�");
		biSearchTree.inOrder();
		
	}

}
