package com.wxf.data.structure;

/**
 * 通过数组来实现栈
 * 
 * @author Administrator
 * 
 */
public class MySeqStack implements MyStack {

	// 存储数据的数组
	private Object[] element;
	// 元素的个数
	private int size;
	// 定义默认的分配空间的大小
	private final static int MAXSIZE = 3;

	/**
	 * default constructor
	 */
	public MySeqStack() {
		// TODO Auto-generated constructor stub
		element = new Object[MAXSIZE];
		size = 0;
	}

	/**
	 * @param initCapacity
	 */
	public MySeqStack(int initCapacity) {
		element = new Object[initCapacity];
		size = 0;
	}

	/**
	 * @param element
	 * @param size
	 */
	public MySeqStack(Object[] element, int size) {
		super();
		this.element = element;
		this.size = size;
	}

	public Object[] getElement() {
		return element;
	}

	public void setElement(Object[] element) {
		this.element = element;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static int getMaxsize() {
		return MAXSIZE;
	}

	@Override
	public void push(Object object) {
		if (element.length == size) {
			throw new RuntimeException(" the stack is full");
		}
		element[size++] = object;
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		check();
		// int i=size-1;
		Object data = element[size - 1];
		size--;
		return data;
	}

	/**
	 * 检测stack是否为空
	 */
	private void check() {
		if (size == 0)
			throw new RuntimeException("the stack is empty");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wxf.data.structure.MyStack#getTop()
	 */
	@Override
	public Object getTop() {
		// TODO Auto-generated method stub
		check();
		return element[size - 1];
	}

	/**
	 * 返回元素的个数
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 判断栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	public static void main(String[] args) {
		MySeqStack stack = new MySeqStack(5);
		stack.push("hello");
		stack.push("34");
		stack.push("333");
		stack.push("1111111");
		stack.push("222");
		// System.out.println(stack.size());
		// System.out.println(stack.getTop());
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
