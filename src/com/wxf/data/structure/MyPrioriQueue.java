package com.wxf.data.structure;

/**
 * ���ȼ�����ͨ��������ʵ��
 * 
 * @author Administrator
 * 
 */
public class MyPrioriQueue implements MyQueue {

	// �洢����Ԫ�ص�����
	private Element[] data;
	// Ĭ�ϵķ��������ռ��С
	private static int defaultSize = 10;
	// Ԫ�صĸ���
	private int size;
	// ��ͷ��¼
	private int front;
	// ��β��¼
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
	 * �����ڲ���
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Element {
		// �洢������
		public Object el;
		// �������ȼ�������ԽС����ʾ���ȼ�Խ��
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
	 * ����ʱ������Ҫ��ģ��������ȼ���ͬ��ִ�г��ӣ����ȼ�Խ�ߵ��ȳ�����
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
		// ��������ӵ������У������ȼ���ߵ�Ԫ�أ�
		// ͬʱ��¼��Ԫ�غ�����λ�ã�Ȼ��������Ԫ��һ����ǰ�ƶ�
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
		// ��������ӵ������У������ȼ���ߵ�Ԫ�أ�
		// ͬʱ��¼��Ԫ�غ�����λ�ã�Ȼ��������Ԫ��һ����ǰ�ƶ�
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
		System.out.println("��ȡ���ȼ���ߵ�Ԫ�أ�");
		System.out.println(queue.getQueue());
		queue.deQueue();
		System.out.println("��ȡ���ȼ�����ߵ�Ԫ�أ�");
		System.out.println(queue.getQueue());
		// �������ȼ����У�ģ�²���ϵͳ�еĽ��̹����������ȼ��ߵ��ȷ���
		// ���ȼ���ͬ���ȵ��ȷ���
		System.out.println("�������ȼ����У�ģ�²���ϵͳ�еĽ��̹������⣺");
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}

	}

}
