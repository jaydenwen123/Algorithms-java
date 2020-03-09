package com.wxf.data.structure;

/**
 * ����ʵ�ֵ�ѭ������
 * 
 * @author Administrator
 * 
 */
public class MyLinkQueue implements MyQueue {
	// ��ͷ�ڵ�
	private MyNode front;
	// ��β�ڵ�
	private MyNode rear;
	// ����Ԫ�صĸ���
	private int size;

	public MyLinkQueue() {
		// TODO Auto-generated constructor stub
		front = rear = null;
		size = 0;
	}

	/**
	 * ����ڵ���
	 * 
	 * @author Administrator
	 * 
	 */
	class MyNode {
		Object data;
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
	public void enQueue(Object object) {
		// TODO Auto-generated method stub
		MyNode node = new MyNode(object, null);
		// �ж��Ƿ��ǵ�һ�β���,�������Ƿ�Ϊ��
		if (size == 0) {
			front = rear = node;
		} else {
			rear.next = node;
			rear = node;
		}
		size++;
	}

	@Override
	public Object deQueue() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new RuntimeException(
					" the queue is empty,cannot to execute this operation");
		MyNode node = front;
		front = front.next;
		size--;
		return node.data;
	}

	@Override
	public Object getQueue() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new RuntimeException(
					" the queue is empty,cannot to execute this operation");
		return front.data;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
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
		System.out.println("��������");
		MyLinkQueue queue = new MyLinkQueue();
		queue.enQueue(1);
		queue.enQueue("heool");
		queue.enQueue(21);
		queue.enQueue("heo222ol");
		System.out.println("�����е�����Ԫ�صĸ�����");
		System.out.println(queue.size());
		System.out.println("��ȡ��ͷ��Ԫ�أ�");
		System.out.println(queue.getQueue());
		System.out.println("�����������е�����Ԫ�أ�");
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
}
