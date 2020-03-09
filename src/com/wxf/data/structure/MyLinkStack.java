package com.wxf.data.structure;

/**
 * ͨ��������ʵ�ֶ�ջ
 * 
 * @author Administrator
 * 
 */
public class MyLinkStack implements MyStack {

	// ����ͷ�ڵ�
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
	 * ����ڵ���
	 * 
	 * @author Administrator
	 * 
	 */
	class MyNode {
		// �ڵ������
		Object data;
		// �ýڵ����һ���ڵ�
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
		// �������״���ջ������񣬶�ֻ��Ҫ�������µĲ�������
		MyNode node = new MyNode(object, head);
		head = node;
		size++;
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		MyNode node = head;
		// ����һ���ڵ�ָ��Ϊͷ���
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
	 * �ж϶�ջ�Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * ��ȡԪ�صĸ���
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
		System.out.println("ջ�е�Ԫ�ظ�����");
		System.out.println(stack.size());
		System.out.println("ջ��Ԫ��Ϊ��");
		System.out.println(stack.getTop());
		System.out.println("ջ�е�Ԫ�ظ�����");
		System.out.println(stack.size());
		System.out.println("����ջ��Ԫ�أ�");
		System.out.println(stack.pop());
		System.out.println("ջ�е�Ԫ�ظ�����");
		System.out.println(stack.size());
		System.out.println("��������stack�е�Ԫ�أ�");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
