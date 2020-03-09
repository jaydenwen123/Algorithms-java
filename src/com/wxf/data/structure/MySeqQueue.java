package com.wxf.data.structure;

/**
 * ˳��ѭ������
 * 
 * @author Administrator
 * 
 */
public class MySeqQueue implements MyQueue {
	// �洢����Ԫ�ص�����
	private Object[] data;
	// Ĭ�ϵķ��������ռ��С
	private static int defaultSize = 10;
	// Ԫ�صĸ���
	private int size;
	// ��ͷ��¼
	private int front;
	// ��β��¼
	private int rear;
	// ָ���ķ���Ķ��г���
	private int maxSize;

	public MySeqQueue() {
		// TODO Auto-generated constructor stub
		init(defaultSize);
	}

	public MySeqQueue(int maxSize) {
		init(maxSize);
	}

	/**
	 * ��ʼ������
	 * 
	 * @param maxSize
	 */
	private void init(int maxSize) {
		this.maxSize = maxSize;
		front = rear = 0;
		size = 0;
		data = new Object[maxSize];
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public static int getDefaultSize() {
		return defaultSize;
	}

	public static void setDefaultSize(int defaultSize) {
		MySeqQueue.defaultSize = defaultSize;
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

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public void enQueue(Object object) {
		// TODO Auto-generated method stub
		// �ж϶����Ƿ�����
		if (size > 0 && front == rear) {
			throw new RuntimeException(" the queue is full! ,cannot to enqueue");
		}
		data[rear] = object;
		size++;
		rear = (rear + 1) % maxSize;
	}

	@Override
	public Object deQueue() {
		// TODO Auto-generated method stub
		Object el = data[front];
		// ���д�����Ҫ�����ǲ��ϵ��л���ͷ
		front = (front + 1) % maxSize;
		size--;
		return el;
	}

	@Override
	public Object getQueue() {
		// TODO Auto-generated method stub
		// �ж϶����Ƿ�Ϊ��
		if (size == 0)
			throw new RuntimeException(
					" the queue is empty,so cannot to operate this funciton");
		return data[front];
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
		MySeqQueue queue = new MySeqQueue();
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
