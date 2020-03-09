package com.wxf.data.structure;

/**
 * 优先级队列通过数组来实现
 * 
 * @author Administrator
 * 
 */
public class MyPrioriQueue implements MyQueue {

	// 存储数据元素的数组
	private Element[] data;
	// 默认的分配的数组空间大小
	private static int defaultSize = 10;
	// 元素的个数
	private int size;
	// 队头记录
	private int front;
	// 队尾记录
	private int rear;

	public MyPrioriQueue() {
		// TODO Auto-generated constructor stub
		init(defaultSize);
	}

	public MyPrioriQueue(int size) {
		init(size);
	}

	private void init(int maxSize) {
		front = rear = 0;
		size = 0;
		data = new Element[maxSize];

	}

	/**
	 * 定义内部类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Element {
		// 存储的数据
		public Object el;
		// 定义优先级，数字越小，表示优先级越高
		public int priority;

		public Element() {
			// TODO Auto-generated constructor stub
		}

		public Element(Object el, int priority) {
			super();
			this.el = el;
			this.priority = priority;
		}

	}

	public Element[] getData() {
		return data;
	}

	public void setData(Element[] data) {
		this.data = data;
	}

	public static int getDefaultSize() {
		return defaultSize;
	}

	public static void setDefaultSize(int defaultSize) {
		MyPrioriQueue.defaultSize = defaultSize;
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

	@Override
	public void enQueue(Object object) {
		// TODO Auto-generated method stub
		if (size == data.length)
			throw new RuntimeException("the queue is full ,cannot to enqueue ");
		data[rear++] = (Element) object;
		size++;
	}

	/*
	 * 出队时，是有要求的，根据优先级不同来执行出队，优先级越高的先出队列
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.wxf.data.structure.MyQueue#deQueue()
	 */
	@Override
	public Object deQueue() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new RuntimeException(
					" the queue is empty ,so cannot to deQueue");
		Element element = data[front];
		int index = front;
		// 在所有入队的数据中，找优先级最高的元素，
		// 同时记录该元素和它的位置，然后将其后面的元素一次往前移动
		for (int i = 0; i < size; i++) {
			if (element.priority > data[i].priority) {
				element = data[i];
				index = i;
			}
		}
		for (int i = index; i < size - 1; i++)
			data[i] = data[i + 1];
		size--;
		return element.el;
	}

	@Override
	public Object getQueue() {
		// TODO Auto-generated method stub
		if (size == 0)
			throw new RuntimeException(
					" the queue is empty ,so cannot to deQueue");
		Element element = data[front];
		// 在所有入队的数据中，找优先级最高的元素，
		// 同时记录该元素和它的位置，然后将其后面的元素一次往前移动
		for (int i = 0; i < size; i++) {
			if (element.priority > data[i].priority) {
				element = data[i];
			}
		}
		return element.el;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {

		MyPrioriQueue queue = new MyPrioriQueue();
		queue.enQueue(new Element("A", 12));
		queue.enQueue(new Element("B", 2));
		queue.enQueue(new Element("C", 3));
		queue.enQueue(new Element("D", 4));
		queue.enQueue(new Element("E", 4));
		queue.enQueue(new Element("F", 6));
		queue.enQueue(new Element("G", 1));
		System.out.println("获取优先级最高的元素：");
		System.out.println(queue.getQueue());
		queue.deQueue();
		System.out.println("获取优先级次最高的元素：");
		System.out.println(queue.getQueue());
		// 遍历优先级队列，模仿操作系统中的进程管理问题优先级高的先服务，
		// 优先级相同的先到先服务
		System.out.println("遍历优先级队列，模仿操作系统中的进程管理问题：");
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}

	}

}
