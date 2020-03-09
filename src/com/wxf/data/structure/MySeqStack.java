package com.wxf.data.structure;

/**
 * ͨ��������ʵ��ջ
 * 
 * @author Administrator
 * 
 */
public class MySeqStack implements MyStack {

	// �洢���ݵ�����
	private Object[] element;
	// Ԫ�صĸ���
	private int size;
	// ����Ĭ�ϵķ���ռ�Ĵ�С
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
	 * ���stack�Ƿ�Ϊ��
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
	 * ����Ԫ�صĸ���
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * �ж�ջ�Ƿ�Ϊ��
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
