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
	 * ��ջ�Ĳ���
	 * 
	 * @param obj
	 */
	public void push(Object obj) {
		// ���Ԫ��
		// ��������
		data[size++] = obj;
	}

	/**
	 * ��ջ�Ĳ���
	 */
	public Object pop() {
		// ɾ�����һ��Ԫ��

		return data[--size];
	}

	/**
	 * ��ȡջ��Ԫ��
	 * 
	 * @return
	 * 
	 */
	public Object getTop() {
		return data[size - 1];
	}

	/**
	 * �ж��Ƿ�Ϊ��
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	/**
	 * ����ջ
	 */
	public void print() {
		// 1.����ʹ��forѭ��
		// 2.������isempty
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
