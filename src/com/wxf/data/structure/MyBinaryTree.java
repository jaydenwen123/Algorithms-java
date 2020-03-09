package com.wxf.data.structure;

import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * ������
 * 
 * @author Administrator
 * 
 */
public class MyBinaryTree {

	private static final int MAX = 40;
	// ���ڱ����α����ǵ�����
	private MyBinaryTree[] elements = new MyBinaryTree[MAX];
	// ��α����Ķ���
	private int front;
	// ��α����Ķ�β
	private int rear;
	private Object data;
	private MyBinaryTree leftChild;
	private MyBinaryTree rightChild;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public MyBinaryTree getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(MyBinaryTree leftChild) {
		this.leftChild = leftChild;
	}

	public MyBinaryTree getRightChild() {
		return rightChild;
	}

	public void setRightChild(MyBinaryTree rightChild) {
		this.rightChild = rightChild;
	}

	public MyBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param data
	 * @param leftChild
	 * @param rightChild
	 *            ����һ��ӵ�к��ӵĽڵ㡣
	 */
	public MyBinaryTree(Object data, MyBinaryTree leftChild,
			MyBinaryTree rightChild) {
		super();
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	/**
	 * @param data
	 *            ����Ҷ�ӽڵ㡣
	 */
	public MyBinaryTree(Object data) {
		this(data, null, null);
	}

	/**
	 * �ݹ���Ƶ��������
	 * 
	 * @param tree
	 *            ���ڶ�������������� ���򣺸��ڵ㣬���ӣ��Һ���
	 */
	public void preOrder1(MyBinaryTree tree) {
		if (tree != null) {
			System.out.println(tree.data);
			preOrder1(tree.leftChild);
			preOrder1(tree.rightChild);
		} else {
			return;
		}
	}

	/**
	 * �ǵݹ���Ƶ��������
	 * 
	 * @param tree
	 * 
	 *            ���ڶ�������������� ���򣺸��ڵ㣬���ӣ��Һ���
	 */
	public void preOrder2(MyBinaryTree tree) {
		// 1.��ʼ��һ����ջ
		MySeqStack stack = new MySeqStack();
		// 2.�жϸ��ڵ��Ƿ�Ϊ�ղ�Ϊ�գ�����ջ
		if (tree != null) {
			stack.push(tree);
			MyBinaryTree node;
			// 3.���ջ��Ϊ�գ�ѭ��ִ�����²���
			while (!stack.isEmpty()) {
				// a.��ȡջ��Ԫ�أ�Ȼ����ʸ�Ԫ��
				node = (MyBinaryTree) stack.pop();
				System.out.print(node.data + "\t");
				// b.����Ԫ�ص��Һ��ӽڵ㲻Ϊ�գ�����ջ
				if (node.rightChild != null)
					stack.push(node.rightChild);
				// c.����Ԫ�ص����ӽڵ㲻Ϊ�գ�����ջ
				if (node.leftChild != null)
					stack.push(node.leftChild);
			}
			// 4.�������
		}
		System.out.println();
	}

	/**
	 * �ǵݹ���ƵĶ�������������㷨
	 * 
	 * @param root
	 */
	public static void preOrder3(MyBinaryTree root) {// �������

		MySeqStack stack = new MySeqStack();
		MyBinaryTree node = root;

		while (node != null || stack.size() > 0) {// ����������ѹջ
			if (node != null) {// ѹջ֮ǰ�ȷ���
				System.out.println(node.data);
				stack.push(node);
				node = node.leftChild;
			} else {
				node = (MyBinaryTree) stack.pop();
				node = node.rightChild;
			}
		}
	}

	/**
	 * �ݹ���Ƶ��������
	 * 
	 * @param tree
	 *            ���ڶ�������������� �������ӣ����ڵ㣬�Һ���
	 */
	public void inOrder1(MyBinaryTree tree) {

		if (tree == null)
			return;
		else {
			inOrder1(tree.leftChild);
			System.out.println(tree.data);
			inOrder1(tree.rightChild);
		}
	}

	/**
	 * �ǵݹ���Ƶ��������
	 * 
	 * @param tree
	 *            ���ڶ�������������� �������ӣ����ڵ㣬�Һ���
	 */
	public void inOrder2(MyBinaryTree tree) {
		MySeqStack stack = new MySeqStack();
		MyBinaryTree node = tree;

		while (node != null || stack.size() > 0) {
			if (node != null) {
				// ֱ��ѹջ
				// �����з��ʣ�Ȼ��ջʱ�ڷ���
				stack.push(node);
				node = node.leftChild;
			} else {
				// ��ջ������
				node = (MyBinaryTree) stack.pop();
				System.out.println(node.data);
				node = node.rightChild;
			}
		}
	}

	/**
	 * �ݹ���Ƶĺ������
	 * 
	 * @param tree
	 * 
	 *            ���ڶ������ĺ������ �������ӣ��Һ��ӣ����ڵ�
	 */
	public void sufOrder1(MyBinaryTree tree) {
		if (tree == null)
			return;
		else {
			sufOrder1(tree.leftChild);
			sufOrder1(tree.rightChild);
			System.out.println(tree.data);
		}
	}

	/**
	 * �ǵݹ���Ƶĺ������
	 * 
	 * @param tree
	 * 
	 *            ���ڶ������ĺ������ �������ӣ��Һ��ӣ����ڵ�
	 */
	public void sufOrder2(MyBinaryTree tree) {

		MyLinkStack stack = new MyLinkStack();
		// ����һ���м�ջ���洢����������Ľ��
		MyLinkStack output = new MyLinkStack();
		MyBinaryTree node = tree;
		while (node != null || stack.size() > 0) {
			if (node != null) {
				output.push(node);
				stack.push(node);
				node = node.rightChild;
			} else {
				node = (MyBinaryTree) stack.pop();
				node = node.leftChild;
			}
		}

		while (output.size() > 0) {
			System.out.println(((MyBinaryTree) output.pop()).data);
		}
	}

	/**
	 * �������ķǵݹ�������
	 * 
	 * @param tree
	 */
	public void sufOrder3(MyBinaryTree tree) {

		// ���������˳���� ����--��--��
		// ˼·���£�
		// ���ȿ��Եõ� ��--��--�� �Ľ��
		// Ȼ����ͨ��һ��ջ������
		MyLinkStack stack = new MyLinkStack();
		// ����
		MyLinkStack outStack = new MyLinkStack();
		if (tree == null)
			return;
		stack.push(tree);
		MyBinaryTree node;
		while (!stack.isEmpty()) {
			// ������ĸ������˳��Ľڵ㱣�浽ջ��
			node = (MyBinaryTree) stack.pop();
			outStack.push(node);
			if (node.leftChild != null)
				stack.push(node.leftChild);
			if (node.rightChild != null)
				stack.push(node.rightChild);
		}
		// System.out.println("stack size:"+outStack.size());
		// ����ڽ����������
		while (!outStack.isEmpty()) {
			System.out.println(((MyBinaryTree) outStack.pop()).data);
		}
	}

	/**
	 * �������Ĳ�α�����ͨ������������±�ʵ��
	 * 
	 * @param tree
	 */
	public void layoutOrder1(MyBinaryTree tree) {
		elements[0] = tree;
		front = 0;
		rear = 1;
		while (front < rear) {
			try {
				if (elements[front] != null) {
					System.out.println(elements[front].data);
					if (elements[front].leftChild != null) {
						elements[rear++] = elements[front].leftChild;
					}
					if (elements[front].rightChild != null) {
						elements[rear++] = elements[front].rightChild;
					}
					front++;
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * ���ڶ���ʵ�ֶ������Ĳ�α���
	 * 
	 * @param tree
	 */
	public void layoutOrder2(MyBinaryTree tree) {
		// ����һ������
		MyLinkQueue queen = new MyLinkQueue();
		MyBinaryTree node;
		// �жϸ������Ƿ�Ϊ�գ���Ϊ�������
		if (tree == null)
			return;
		queen.enQueue(tree);
		// ������в�Ϊ��
		while (!queen.isEmpty()) {
			// ��ͷ�ڵ���ӣ�Ȼ����ʸýڵ�
			node = (MyBinaryTree) queen.deQueue();
			System.out.print(node.data + "\t");
			// �ֱ���ʸýڵ�����ҽڵ�
			if (node.leftChild != null)
				queen.enQueue(node.leftChild);
			if (node.rightChild != null)
				queen.enQueue(node.rightChild);
		}
		System.out.println();
	}

	/**
	 * @return ����Ҷ�ӽڵ����Ŀ����������������������Ϊ�գ����1
	 */
	public int getLevel(MyBinaryTree tree) {
		int levelCounts = 0;
		if (tree == null)
			return levelCounts;
		if (tree.leftChild == null && tree.rightChild == null)
			return 1;

		return (tree.leftChild == null ? 0 : getLevel(tree.leftChild))
				+ (tree.rightChild == null ? 0 : getLevel(tree.rightChild));
	}

	/**
	 * @param tree
	 * @return ��ȡ����������ȣ�������ȵ��ڸ��ڵ㵽Ҷ�ӽڵ���·���ĸ���
	 */
	public int getDepth(MyBinaryTree tree) {
		int depth = 0;
		if (tree == null) {
			return -1;
		}
		int leftHeight = (tree.leftChild == null ? 0 : getDepth(tree.leftChild));
		int rightHeight = (tree.rightChild == null ? 0
				: getDepth(tree.rightChild));
		depth = (leftHeight <= rightHeight ? rightHeight : leftHeight);
		return depth + 1;
	}

	/**
	 * @param tree
	 *            ��������������λ��
	 */
	public void changeLeftAndRightChild(MyBinaryTree tree) {

		if (tree == null) {
			return;
		}
		if (tree.leftChild != null) {
			changeLeftAndRightChild(tree.leftChild);
		}
		if (tree.rightChild != null) {
			changeLeftAndRightChild(tree.rightChild);
		}
		// �������ߵ�λ��
		MyBinaryTree temp = leftChild;
		leftChild = rightChild;
		rightChild = temp;
	}

	/**
	 * @param tree
	 * @param obj
	 * @return ��ȡĳ���ڵ��ڸö������еĵȼ�������ýڵ㲻���ڣ��򷵻�-1�������ڣ��򷵻صȼ�
	 */
	public int level(MyBinaryTree tree, Object obj) {
		int nodeLevel = 0;
		if (tree == null)
			return -1;
		if (obj.equals(tree.data))
			return 1;
		int leftLevel = (tree.leftChild == null ? -1 : level(tree.leftChild,
				obj));
		int rightLevel = (tree.rightChild == null ? -1 : level(tree.rightChild,
				obj));
		if (leftLevel < 0 && rightLevel < 0)
			return -1;
		nodeLevel = leftLevel <= rightLevel ? rightLevel : leftLevel;
		return nodeLevel + 1;
	}

	/**
	 * @param tree
	 *            ����������ҽ������е����ҽڵ㶼�ÿ�
	 */
	public void clear(MyBinaryTree tree) {
		if (tree == null)
			return;
		if (tree.leftChild == null && tree.rightChild == null) {
			tree.data = null;
		}
		if (tree.leftChild != null) {
			clear(tree.leftChild);
			tree.leftChild = null;
		}
		if (tree.rightChild != null) {
			clear(tree.rightChild);
			tree.rightChild = null;
		}
		tree.data = null;
	}

	/**
	 * @param tree
	 * @return ��ȡ����ȫ���ڵ�ĸ���
	 */
	public int getTreeCounts(MyBinaryTree tree) {
		// Queue<MyBinaryTree> queue=new ArrayDeque<MyBinaryTree>();
		ArrayList<MyBinaryTree> queue = forEachTree(tree);
		return queue.size();
	}

	/**
	 * @param tree
	 * @return �������������洢��arrayList�У����ظ�arrayList����
	 */
	public ArrayList<MyBinaryTree> forEachTree(MyBinaryTree tree) {
		ArrayList<MyBinaryTree> queue = new ArrayList<MyBinaryTree>();
		queue.add(tree);
		int counts = 0;
		front = 0;
		rear = 1;
		while (front < rear) {
			if (queue.get(front).leftChild != null) {
				queue.add(queue.get(front).leftChild);
				rear++;
			}
			if (queue.get(front).rightChild != null) {
				queue.add(queue.get(front).rightChild);
				rear++;
			}
			front++;
		}
		return queue;
	}

	/**
	 * ʵ�ֵĹ��ܾ������£���һ����������ʱ����ת90.Ȼ����� ��������tree�ĵ�level��Ľڵ�������
	 * 
	 * 
	 * --P --G --H --E --F --D --B --C --A
	 * 
	 * @param tree
	 *            ������
	 * @param level
	 *            ��level��
	 */

	public void printBinaryTree(MyBinaryTree tree, int level) {

		if (tree != null) {
			printBinaryTree(tree.rightChild, level + 1);
			if (level != 0) {
				for (int i = 0; i < 6 * (level - 1); i++) {
					System.out.print(" ");
				}
				System.out.print("--");
			}
			System.out.println(tree.data);
			printBinaryTree(tree.leftChild, level + 1);
		}
	}
}
