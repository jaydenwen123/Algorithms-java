package com.wxf.data.structure;

/**
 * 通过链表来实现堆栈
 * 
 * @author Administrator
 * 
 */
public class MyLinkStack implements MyStack {

	// 定义头节点
	private MyNode head;
	private int size;

	public MyLinkStack() {
		// TODO Auto-generated constructor stub
		head = null;
		size = 0;
	}

	public MyLinkStack(MyNode head) {
		super();
		this.head = head;
	}

	/**
	 * 定义节点类
	 * 
	 * @author Administrator
	 * 
	 */
	class MyNode {
		// 节点的数据
		Object data;
		// 该节点的下一个节点
		MyNode next;

		public MyNode() {
			// TODO Auto-generated constructor stub
		}

		public MyNode(Object data, MyNode next) {
			super();
			this.data = data;
			this.next = next;
		}

	}

	@Override
	public void push(Object object) {
		// TODO Auto-generated method stub
		// 不管是首次入栈还是与否，都只需要进行如下的操作即可
		MyNode node = new MyNode(object, head);
		head = node;
		size++;
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		MyNode node = head;
		// 将下一个节点指定为头结点
		head = head.next;
		size--;
		return node.data;
	}

	@Override
	public Object getTop() {
		// TODO Auto-generated method stub
		return head.data;
	}

	/**
	 * 判断堆栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 获取元素的个数
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		MyLinkStack stack = new MyLinkStack();
		stack.push("hello");
		stack.push("34");
		stack.push("333");
		stack.push("1111111");
		stack.push("222");
		System.out.println("栈中的元素个数：");
		System.out.println(stack.size());
		System.out.println("栈顶元素为：");
		System.out.println(stack.getTop());
		System.out.println("栈中的元素个数：");
		System.out.println(stack.size());
		System.out.println("弹出栈顶元素：");
		System.out.println(stack.pop());
		System.out.println("栈中的元素个数：");
		System.out.println(stack.size());
		System.out.println("遍历整个stack中的元素：");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
