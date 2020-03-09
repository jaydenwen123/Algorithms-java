package com.wxf.data.test.datastructure;

import org.omg.CORBA.OBJ_ADAPTER;

public class MySeqStack {

	private static final int DEFAULTSIZE = 3;
	private Object[] data;
	private int size;

	public MySeqStack() {
		// TODO Auto-generated constructor stub
		data = new Object[DEFAULTSIZE];
		size = 0;
	}

	public MySeqStack(int capacity) {
		data = new Object[capacity];
		size = 0;
	}

	public int size() {
		return size;
	}

	/**
	 * 入栈的操作
	 * 
	 * @param obj
	 */
	public void push(Object obj) {
		// 添加元素
		// 考虑扩容
		data[size++] = obj;
	}

	/**
	 * 出栈的操作
	 */
	public Object pop() {
		// 删除最后一个元素

		return data[--size];
	}

	/**
	 * 获取栈顶元素
	 * 
	 * @return
	 * 
	 */
	public Object getTop() {
		return data[size - 1];
	}

	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	/**
	 * 遍历栈
	 */
	public void print() {
		// 1.可以使用for循环
		// 2.可以用isempty
		while (!isEmpty()) {
			System.out.println(pop());
		}
	}

	public static void main(String[] args) {
		MySeqStack stack = new MySeqStack(4);
		stack.push(1);
		stack.push(12);
		stack.push(123);
		stack.push(1234);
		System.out.println(stack.size());
		System.out.println(stack.isEmpty());
		//System.out.println(stack.pop());
		System.out.println(stack.getTop());
		System.out.println("test print");
		stack.print();
	}
}
