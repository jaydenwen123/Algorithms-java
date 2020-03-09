package com.wxf.data.structure;

/**
 * 链表实现的循环队列
 * 
 * @author Administrator
 * 
 */
public class MyLinkQueue implements MyQueue {
	// 队头节点
	private MyNode front;
	// 队尾节点
	private MyNode rear;
	// 数据元素的个数
	private int size;

	public MyLinkQueue() {
		// TODO Auto-generated constructor stub
		front = rear = null;
		size = 0;
	}

	/**
	 * 定义节点类
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
		// 判断是否是第一次插入,即队列是否为空
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
	 * 获取元素的个数
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		System.out.println("创建队列");
		MyLinkQueue queue = new MyLinkQueue();
		queue.enQueue(1);
		queue.enQueue("heool");
		queue.enQueue(21);
		queue.enQueue("heo222ol");
		System.out.println("队列中的数据元素的个数：");
		System.out.println(queue.size());
		System.out.println("获取队头的元素：");
		System.out.println(queue.getQueue());
		System.out.println("遍历整个队列的数据元素：");
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
}
