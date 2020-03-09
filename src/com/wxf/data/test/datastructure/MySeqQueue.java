package com.wxf.data.test.datastructure;


public class MySeqQueue {

	private Object[] data;
	private int size;
	private int front;
	private int rear;
	private int maxSize;

	private static final int DEFAULTSIZE = 4;

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFront() {
		return front;
	}

	public void setFront(int front) {
		this.front = front;
	}

	public int getRear() {
		return rear;
	}

	public void setRear(int rear) {
		this.rear = rear;
	}

	public static int getDefaultsize() {
		return DEFAULTSIZE;
	}

	/**
	 * ������
	 */
	public MySeqQueue() {
		// TODO Auto-generated constructor stub
		front = rear = size = 0;
		data = new Object[DEFAULTSIZE];
	}

	/**
	 * ������
	 * 
	 * @param capacity
	 */
	public MySeqQueue(int capacity) {
		data = new Object[capacity];
		rear = front = size = 0;
		maxSize = capacity;
	}

	/**
	 * ��ӵĲ���
	 * 
	 * @param obj
	 */
	public void enQueue(Object obj) {
		// 1.�ж϶����Ƿ�����2.�ڽ������
		if (size > data.length - 1) {
			throw new RuntimeException(" the queue is full ");
		} else {
			data[size++] = obj;
			rear = (rear + 1) % maxSize;
		}
	}

	/**
	 * ���ӵĲ���
	 * 
	 * @return
	 */
	public Object deQueue() {
		Object el = null;
		if (!isEmpty()) {
			el = data[front];
			front = (front + 1) % maxSize;
			size--;
		}
		return el;
	}

	/**
	 * ��ȡ��ͷ��Ԫ��
	 * 
	 * @return
	 */
	public Object getQueue() {
		return data[front];
	}

	/**
	 * ��ȡ���е�Ԫ�صĸ���
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * �ж϶����Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	public static void main(String[] args) {
		MySeqQueue queue = new MySeqQueue(5);
		queue.enQueue(1);
		queue.enQueue(12);
		queue.enQueue(123);
		queue.enQueue(1234);
		queue.enQueue(12345);
		// System.out.println("test enqueue");
		// System.out.println(queue.getQueue());
		// System.out.println(queue.size());
		// System.out.println("test dequeue");
		// System.out.println(queue.deQueue());
		// System.out.println(queue.size());
		while(!queue.isEmpty()){
			System.out.println(queue.deQueue());
		}
	}
}
