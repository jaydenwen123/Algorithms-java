package com.wxf.data.structure;

/**
 * 顺序循环队列
 * 
 * @author Administrator
 * 
 */
public class MySeqQueue implements MyQueue {
	// 存储数据元素的数组
	private Object[] data;
	// 默认的分配的数组空间大小
	private static int defaultSize = 10;
	// 元素的个数
	private int size;
	// 队头记录
	private int front;
	// 队尾记录
	private int rear;
	// 指定的分配的队列长度
	private int maxSize;

	public MySeqQueue() {
		// TODO Auto-generated constructor stub
		init(defaultSize);
	}

	public MySeqQueue(int maxSize) {
		init(maxSize);
	}

	/**
	 * 初始化队列
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
		// 判断队列是否已满
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
		// 该行代码主要作用是不断的切换队头
		front = (front + 1) % maxSize;
		size--;
		return el;
	}

	@Override
	public Object getQueue() {
		// TODO Auto-generated method stub
		// 判断队列是否为空
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
	 * 获取元素的个数
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		System.out.println("创建队列");
		MySeqQueue queue = new MySeqQueue();
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
