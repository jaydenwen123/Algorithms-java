package com.wxf.data.structure;

import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 二叉树
 * 
 * @author Administrator
 * 
 */
public class MyBinaryTree {

	private static final int MAX = 40;
	// 用于保存层次遍历是的数据
	private MyBinaryTree[] elements = new MyBinaryTree[MAX];
	// 层次遍历的队首
	private int front;
	// 层次遍历的队尾
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
	 *            构建一个拥有孩子的节点。
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
	 *            构建叶子节点。
	 */
	public MyBinaryTree(Object data) {
		this(data, null, null);
	}

	/**
	 * 递归设计的先序遍历
	 * 
	 * @param tree
	 *            对于二叉树的先序遍历 规则：根节点，左孩子，右孩子
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
	 * 非递归设计的先序遍历
	 * 
	 * @param tree
	 * 
	 *            对于二叉树的先序遍历 规则：根节点，左孩子，右孩子
	 */
	public void preOrder2(MyBinaryTree tree) {
		// 1.初始化一个堆栈
		MySeqStack stack = new MySeqStack();
		// 2.判断根节点是否为空不为空，则入栈
		if (tree != null) {
			stack.push(tree);
			MyBinaryTree node;
			// 3.如果栈不为空，循环执行以下操作
			while (!stack.isEmpty()) {
				// a.获取栈顶元素，然后访问该元素
				node = (MyBinaryTree) stack.pop();
				System.out.print(node.data + "\t");
				// b.若该元素的右孩子节点不为空，则入栈
				if (node.rightChild != null)
					stack.push(node.rightChild);
				// c.若该元素的左孩子节点不为空，则入栈
				if (node.leftChild != null)
					stack.push(node.leftChild);
			}
			// 4.程序结束
		}
		System.out.println();
	}

	/**
	 * 非递归设计的二叉树先序遍历算法
	 * 
	 * @param root
	 */
	public static void preOrder3(MyBinaryTree root) {// 先序遍历

		MySeqStack stack = new MySeqStack();
		MyBinaryTree node = root;

		while (node != null || stack.size() > 0) {// 将所有左孩子压栈
			if (node != null) {// 压栈之前先访问
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
	 * 递归设计的中序遍历
	 * 
	 * @param tree
	 *            对于二叉树的中序遍历 规则：左孩子，根节点，右孩子
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
	 * 非递归设计的中序遍历
	 * 
	 * @param tree
	 *            对于二叉树的中序遍历 规则：左孩子，根节点，右孩子
	 */
	public void inOrder2(MyBinaryTree tree) {
		MySeqStack stack = new MySeqStack();
		MyBinaryTree node = tree;

		while (node != null || stack.size() > 0) {
			if (node != null) {
				// 直接压栈
				// 不进行访问，然出栈时在访问
				stack.push(node);
				node = node.leftChild;
			} else {
				// 出栈并访问
				node = (MyBinaryTree) stack.pop();
				System.out.println(node.data);
				node = node.rightChild;
			}
		}
	}

	/**
	 * 递归设计的后序遍历
	 * 
	 * @param tree
	 * 
	 *            对于二叉树的后序遍历 规则：左孩子，右孩子，根节点
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
	 * 非递归设计的后序遍历
	 * 
	 * @param tree
	 * 
	 *            对于二叉树的后序遍历 规则：左孩子，右孩子，根节点
	 */
	public void sufOrder2(MyBinaryTree tree) {

		MyLinkStack stack = new MyLinkStack();
		// 构造一个中间栈来存储逆后续遍历的结果
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
	 * 二叉树的非递归后序遍历
	 * 
	 * @param tree
	 */
	public void sufOrder3(MyBinaryTree tree) {

		// 后序遍历的顺序是 ：左--右--根
		// 思路如下：
		// 首先可以得到 根--右--左 的结果
		// 然后再通过一个栈来逆序
		MyLinkStack stack = new MyLinkStack();
		// 用来
		MyLinkStack outStack = new MyLinkStack();
		if (tree == null)
			return;
		stack.push(tree);
		MyBinaryTree node;
		while (!stack.isEmpty()) {
			// 将输出的根右左的顺序的节点保存到栈中
			node = (MyBinaryTree) stack.pop();
			outStack.push(node);
			if (node.leftChild != null)
				stack.push(node.leftChild);
			if (node.rightChild != null)
				stack.push(node.rightChild);
		}
		// System.out.println("stack size:"+outStack.size());
		// 最后在将其逆序输出
		while (!outStack.isEmpty()) {
			System.out.println(((MyBinaryTree) outStack.pop()).data);
		}
	}

	/**
	 * 二叉树的层次遍历，通过数组和两个下标实现
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
	 * 基于队列实现二叉树的层次遍历
	 * 
	 * @param tree
	 */
	public void layoutOrder2(MyBinaryTree tree) {
		// 创建一个队列
		MyLinkQueue queen = new MyLinkQueue();
		MyBinaryTree node;
		// 判断该树的是否为空，不为空则入队
		if (tree == null)
			return;
		queen.enQueue(tree);
		// 如果队列不为空
		while (!queen.isEmpty()) {
			// 队头节点出队，然后访问该节点
			node = (MyBinaryTree) queen.deQueue();
			System.out.print(node.data + "\t");
			// 分别访问该节点的左右节点
			if (node.leftChild != null)
				queen.enQueue(node.leftChild);
			if (node.rightChild != null)
				queen.enQueue(node.rightChild);
		}
		System.out.println();
	}

	/**
	 * @return 返回叶子节点的数目，输入左子树和右子树均为空，则加1
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
	 * @return 获取二叉树的深度，树的深度等于根节点到叶子节点的最长路径的个数
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
	 *            交换左右子树的位置
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
		// 交换二者的位置
		MyBinaryTree temp = leftChild;
		leftChild = rightChild;
		rightChild = temp;
	}

	/**
	 * @param tree
	 * @param obj
	 * @return 获取某个节点在该二叉树中的等级。如果该节点不存在，则返回-1；若存在，则返回等级
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
	 *            清空树，并且将其所有的左右节点都置空
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
	 * @return 获取树的全部节点的个数
	 */
	public int getTreeCounts(MyBinaryTree tree) {
		// Queue<MyBinaryTree> queue=new ArrayDeque<MyBinaryTree>();
		ArrayList<MyBinaryTree> queue = forEachTree(tree);
		return queue.size();
	}

	/**
	 * @param tree
	 * @return 将整个二叉树存储到arrayList中，返回该arrayList数组
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
	 * 实现的功能具体如下，将一个二叉树逆时针旋转90.然后输出 将二叉树tree的第level层的节点横向输出
	 * 
	 * 
	 * --P --G --H --E --F --D --B --C --A
	 * 
	 * @param tree
	 *            二叉树
	 * @param level
	 *            第level层
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
