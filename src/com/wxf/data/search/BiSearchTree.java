package com.wxf.data.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

/**
 * ����������
 * 
 * @author Administrator
 * 
 */
/**
 * @author Administrator
 * 
 */
public class BiSearchTree {

	// ���ڵ�
	private BiTreeNode root;
	// �������ݡ�����������������ı������ֵ
	private List<Integer> preList = new ArrayList<Integer>();
	private List<Integer> inList = new ArrayList<Integer>();
	private List<Integer> postList = new ArrayList<Integer>();

	/**
	 * �������
	 * 
	 * @param t
	 *            Ҫ���б����ĸ��ڵ�
	 * @return ����ֵΪ�洢��list�����еı������ֵ
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
	 * �������
	 * 
	 * @param t
	 *            Ҫ�����ĸ��ڵ�
	 * @return ���ص�list�����д洢���Ǳ������data����
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
	 * �������
	 */
	public List<Integer> inOrder() {
		return inOrder(root);
	}

	/**
	 * �������ĺ������
	 * 
	 * @param t
	 *            Ҫ�����ĸ��ڵ�
	 * @return ���ص�list�����д洢����ÿ���ڵ��data����ֵ
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
	 * �������
	 */
	public List<Integer> postOrder() {
		return postOrder(root);
	}

	/**
	 * �����������Ĳ���
	 * 
	 * @param t
	 * @param item
	 */
	public void insert(BiTreeNode t, int item) {
		if (t != null) {
			// �����ǰ����С�ڸ��ڵ���������ʾ����Ӧ�ڸøýڵ����������,�������������У�
			if (item < t.data) {
				// ������������������ڣ�����еݹ�Ĳ��룬�����ʾ���ӽڵ㲻���ڣ�Ȼ�󴴽����ӽڵ�
				if (t.leftchild != null)
					insert(t.leftchild, item);
				else {
					// �������ӽڵ�
					BiTreeNode leftNode = new BiTreeNode(item);
					leftNode.parent = t;
					t.leftchild = leftNode;
				}
				// ���������������н��в���Ĳ���
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
	 * ������������ɾ��
	 * 
	 * @param t
	 * @param item
	 */
	public void delete(BiTreeNode t, int item) {

		// ������������ɾ����Ϊ�����������
		// 1.��ɾ���Ľڵ��޺��ӽڵ�ʱ��ֱ��ɾ���ýڵ�
		// 2.Ҫɾ���Ľڵ�ֻ�����ӽڵ�ʱ��ɾ���ýڵ㣬ͬʱʹ��ɾ���Ľڵ��˫�׽ڵ�ָ�� ��ɾ�������ӽڵ�
		// 3.Ҫɾ���Ľڵ㣬ֻ���Һ��ӽڵ�ʱ��ɾ���ýڵ㣬ͬʱʹ��ɾ���Ľڵ��˫�׽ڵ�ָ��ɾ�����Һ��ӽڵ�
		// 4.Ҫɾ���Ľڵ������Һ��ӽڵ�ʱ����Ϊ���������������
		// ����Ѱ������Ԫ��ֵ����Ҫɾ���Ľڵ�����Ԫ�عؼ��ֵ���Сֵ����Ѱ��ɾ���ڵ��������������ڵ㡣
		// Ȼ���ٰ�������������ڵ������Ԫ�ظ��Ƶ�Ҫɾ���Ľڵ��ϣ����ɾ��������������ڵ㡣
		if (t != null) {

			if (item < t.data)
				delete(t.leftchild, item);
			else if (item > t.data)
				delete(t.rightchild, item);
			// ��Ӧ����������������Ϊ�յ����
			else if (t.leftchild != null && t.rightchild != null) {
				BiTreeNode min = t.rightchild;
				// �ҵ��ýڵ���������е�����ڵ㡣
				while (min.leftchild != null) {
					min = min.leftchild;
				}
				t.data = min.data;
				// ɾ���ýڵ��������е�����ڵ㡣
				delete(t.rightchild, min.data);
			} else {
				// ����ֻ���Һ��ӽڵ�����
				if (t.leftchild == null && t.rightchild != null) {
					// ���Ҫɾ���Ľڵ�ĸ��ڵ������Ϊ�գ�ͬʱ�ҽڵ�Ϊ�ýڵ㣬�����һ�µĲ���
					if (t.parent.rightchild == t) {
						t.parent.rightchild = t.rightchild;
						// ���Ҫɾ���Ľڵ�ĸ��ڵ������Ϊ���գ�ͬʱ�ҽڵ�Ϊ�ýڵ㣬�����һ�µĲ���
					} else if (t.parent.leftchild == t) {
						t.parent.leftchild = t.rightchild;
					}
					t.rightchild.parent = t.parent;
					// ��Ӧֻ�����ӽڵ�����
				} else if (t.rightchild == null && t.leftchild != null) {
					// ���»���Ҫ�жϵ��׸ýڵ��Ǹ��ڵ�����ӻ����Һ��ӡ���Ϊ
					if (t.parent.rightchild == t) {
						t.parent.rightchild = t.leftchild;
						// ���Ҫɾ���Ľڵ�ĸ��ڵ������Ϊ���գ�ͬʱ�ҽڵ�Ϊ�ýڵ㣬�����һ�µĲ���
					} else if (t.parent.leftchild == t) {
						t.parent.leftchild = t.leftchild;
					}
					t.leftchild.parent = t.parent;
					// ��ӦҶ�ӽڵ��������������������Һ���
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
	 * �����������Ĳ��� ͨ��ѭ���ķ�ʽ������
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
	 * �����������Ĳ��� ͨ���ݹ�ķ�ʽ����
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
	 * ȡ�����ӽڵ�
	 * 
	 * @param currentTreeNode
	 * @return
	 */
	public BiTreeNode getLeftChild(BiTreeNode currentTreeNode) {
		return currentTreeNode == null ? null : currentTreeNode.leftchild;
	}

	/**
	 * ȡ�õ�ǰ�ڵ���Һ��ӽڵ�
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
	 * �����������Ľڵ���ýڵ����������¡� 1���ȳ��˰������Һ��Ӻ��ӽڵ������Ԫ��֮�⣬ 2������һ��˫�׽ڵ�
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
		System.out.println("�������������£�");
		List<Integer> list = biSearchTree.inOrder();
		System.out.println(list.toString());
		System.out.println("�������������£�");
		list = biSearchTree.preOrder();
		System.out.println(list.toString());
		System.out.println("��������ļǹ����£�");
		list = biSearchTree.postOrder();
		System.out.println(list.toString());

		BiTreeNode node = biSearchTree.find(biSearchTree.root, 7);
		System.out.println(node.data);

		biSearchTree.delete(biSearchTree.root, 38);
		System.out.println("**********************");
		System.out.println("�������������£�");
		list = biSearchTree.inOrder();
		System.out.println(list.toString());
	}

}
