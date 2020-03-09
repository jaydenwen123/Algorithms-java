package com.wxf.data.structure;

import java.awt.Image;
import java.rmi.Remote;

import javax.xml.soap.Node;

public class MyLinkedList {
	private MyNode first;
	private MyNode last;
	private int size;

	public MyLinkedList() {
		size = 0;
	}

	/**
	 * @param obj
	 * 
	 *            ͷ�巨��ÿ�ν�Ԫ�ز嵽�ʼ��λ�á�
	 */
	public void linkFirst(Object obj) {
		MyNode node = null;
		// ������һ���ڵ㲻Ϊ�գ���ֻ��Ҫ���ýڵ��next�ڵ���ΪfirstȻ��first��previous�ڵ���Ϊnode������ٽ�node
		// ����Ϊ��һ���ڵ㡣
		if (first != null) {
			node = new MyNode(null, obj, first);
			// node.next=first;
			first.previous = node;
			first = node;
		} else {
			// �����жϣ���һ��ͷ����Ƿ�Ϊ�գ����Ϊ�գ��򽫵�һ���ڵ�����һ���ڵ㡣����Ϊnode
			node = new MyNode(null, obj, null);
			first = node;
			last = node;
		}
		size++;
	}

	/**
	 * @param obj
	 *            β�巨����Ҫ�����Ԫ�ر��浽ĩβ���÷�����ʵ�ֺ�Դ����ͬ��
	 */
	public void linkLast(Object obj) {

		MyNode tempt = last;
		// ��last����Ϊ�ýڵ����һ���ڵ㡣
		MyNode node = new MyNode(last, obj, null);
		// ���ýڵ���Ϊ���һ���ڵ㡣
		last = node;
		if (tempt == null) {
			first = node;
		} else {
			// �мǸ�λ�ã�������last�����и�ֵ����Ϊ��ǰ���Ѿ���last���и�ֵ�ˡ�
			tempt.next = node;
		}
		size++;
	}

	/**
	 * @param obj
	 * 
	 *            ��Ԫ�ز��뵽�����У�Ĭ���ǲ��õ�β�巨��
	 */
	public void add(Object obj) {
		linkLast2(obj);
	}

	/**
	 * @param obj
	 *            β�巨�����ַ�ʽ��������������⡣
	 */
	private void linkLast2(Object obj) {
		MyNode node = null;
		// �����ж��Ƿ��һ���ڵ�Ϊ�գ�
		if (first == null) {
			node = new MyNode(null, obj, null);
			// �����һ���ڵ�Ϊ�գ��򽫵�һ�������һ���ڵ㶼��Ϊ��ǰ�Ľڵ㡣
			first = node;
			last = node;
		} else {
			// ����ֻ��Ҫ���ýڵ���뵽���ڵ��ĩβ��
			node = new MyNode(last, obj, null);
			// �������еĴ��벻���Խ�����
			last.next = node;
			last = node;
		}
		size++;
	}

	public Object get(int index) {
		Object obj = null;
		MyNode node = null;
		boolean flag = checkIndex(index);
		if (flag) {
			// size>>1�൱�ڳ���2
			node = IteratLinkList(index);
			obj = node.obj;
		}
		return obj;
	}

	/**
	 * @param index
	 * @return ͨ�����������������������
	 */
	private MyNode IteratLinkList(int index) {
		MyNode node;
		if (index < (size >> 1)) {
			node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {
			node = last;
			for (int i = size - 1; i > index; i--) {
				// ��������ѭ����
				node = node.previous;
			}
			return node;
		}
	}

	/**
	 * @param index
	 * @return ɾ�������е�Ԫ��
	 */
	public Object remove(int index) {
		Object obj = null;
		MyNode node = null;
		boolean flag = checkIndex(index);
		if (flag) {
			// ���Ȼ�ȡ�ýڵ�
			node = IteratLinkList(index);
			// Ȼ���ȡ�ýڵ��ǰ�����
			MyNode preNode = node.previous;// bbb
			// ͬʱ��ȡ�ýڵ�ĺ�̽ڵ㡣
			MyNode nextNode = node.next;// ddd
			// �ڻ�ȥ�ýڵ��ֵ��
			obj = node.obj;
			// �������д���ĺ����ǡ����Ƚ��ýڵ��ǰ���ڵ����һ���ڵ㸳ֵΪ�ýڵ����һ���ڵ㡣
			// ͬʱ���ýڵ����һ���ڵ��ǰ���ڵ㸳ֵΪ�ýڵ��ǰ���ڵ㡣��
			// ����ͨ�������ýڵ��ʵ�ֽ��ڵ�ɾ����
			preNode.next = nextNode;
			nextNode.previous = preNode;
			node.previous = null;
			node.obj = null;
			node.next = null;
		}
		// ��󽫸ýڵ��ֵ����
		size--;
		return obj;

	}

	/**
	 * @param index
	 * @return ���ҪԪ�ص�����
	 */
	private boolean checkIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size)
			throw new IllegalArgumentException();
		else
			return true;
	}

	/**
	 * @return ���ز���ĸ�����
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param index
	 * @param obj
	 *            ��indexλ�ò���Ԫ��obj
	 */
	public void add(int index, Object obj) {

		MyNode node;
		MyNode newNode;
		boolean flag = checkIndex(index);
		if (flag) {
			// ��ȡindexλ�õ�Ԫ��
			node = IteratLinkList(index);
			// ��ȡ�ýڵ��ǰ���ڵ�ͺ�̽ڵ㡣
			MyNode preNode = node.previous;
			MyNode nextNode = node.next;
			// �����µĽڵ㡣���ƶ���ǰ�����ͺ�̽ڵ㡣
			newNode = new MyNode(preNode, obj, nextNode);
			// ͬʱ���ýڵ�ָ��Ϊǰ�������ڵ��ǰ���ڵ�ͺ�̽ڵ㡣
			preNode.next = newNode;
			nextNode.previous = newNode;
		}
	}

	/**
	 * @author Administrator �ڲ��࣬����Ľڵ��ࡣ
	 */
	public class MyNode {
		public MyNode previous;
		public Object obj;
		public MyNode next;

		public MyNode() {
			// TODO Auto-generated constructor stub
		}

		public MyNode(MyNode previous, Object obj, MyNode next) {
			super();
			this.previous = previous;
			this.obj = obj;
			this.next = next;
		}

	}

	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		list.add("fff");
		/*
		 * for (int i = 0; i < list.getSize(); i++) {
		 * System.out.println(list.get(i)); }
		 */
		/*
		 * list.linkFirst("aaa"); list.linkFirst("bbb"); list.linkFirst("ccc");
		 * list.linkFirst("ddd"); list.linkFirst("eee"); list.linkFirst("fff");
		 */

		/*
		 * for (int i = 0; i < list.getSize(); i++) {
		 * System.out.println(list.get(i)); }
		 */
		// System.out.println(list.get(6));
		// System.out.println(list.getSize());
		// Object oj = list.remove(3);
		// System.out.println(oj);
		for (int i = 0; i < list.getSize(); i++) {
			System.out.println(list.get(i));
		}
		list.add(3, "gggg");
		System.out.println("************************");

		for (int i = 0; i < list.getSize(); i++) {
			System.out.println(list.get(i));
		}

	}

}