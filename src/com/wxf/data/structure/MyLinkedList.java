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
	 *            头插法，每次将元素插到最开始的位置。
	 */
	public void linkFirst(Object obj) {
		MyNode node = null;
		// 若果第一个节点不为空，则只需要将该节点的next节点置为first然后first的previous节点置为node，最后再讲node
		// 设置为第一给节点。
		if (first != null) {
			node = new MyNode(null, obj, first);
			// node.next=first;
			first.previous = node;
			first = node;
		} else {
			// 首先判断，第一个头结点是否为空，如果为空，则将第一个节点和最后一个节点。都置为node
			node = new MyNode(null, obj, null);
			first = node;
			last = node;
		}
		size++;
	}

	/**
	 * @param obj
	 *            尾插法，将要插入的元素保存到末尾。该方法的实现和源码相同。
	 */
	public void linkLast(Object obj) {

		MyNode tempt = last;
		// 将last设置为该节点的上一个节点。
		MyNode node = new MyNode(last, obj, null);
		// 将该节点置为最后一个节点。
		last = node;
		if (tempt == null) {
			first = node;
		} else {
			// 切记该位置，不能用last来进行赋值，因为在前面已经对last进行赋值了。
			tempt.next = node;
		}
		size++;
	}

	/**
	 * @param obj
	 * 
	 *            将元素插入到链表中，默认是采用的尾插法。
	 */
	public void add(Object obj) {
		linkLast2(obj);
	}

	/**
	 * @param obj
	 *            尾插法，这种方式更加容易让人理解。
	 */
	private void linkLast2(Object obj) {
		MyNode node = null;
		// 首先判断是否第一个节点为空，
		if (first == null) {
			node = new MyNode(null, obj, null);
			// 如果第一个节点为空，则将第一个和最后一个节点都置为当前的节点。
			first = node;
			last = node;
		} else {
			// 否则，只需要讲该节点插入到最后节点的末尾。
			node = new MyNode(last, obj, null);
			// 下面两行的代码不可以交换。
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
			// size>>1相当于除以2
			node = IteratLinkList(index);
			obj = node.obj;
		}
		return obj;
	}

	/**
	 * @param index
	 * @return 通过传入的索引来遍历该链表
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
				// 依次逆向循环。
				node = node.previous;
			}
			return node;
		}
	}

	/**
	 * @param index
	 * @return 删除链表中的元素
	 */
	public Object remove(int index) {
		Object obj = null;
		MyNode node = null;
		boolean flag = checkIndex(index);
		if (flag) {
			// 首先获取该节点
			node = IteratLinkList(index);
			// 然后获取该节点的前驱结点
			MyNode preNode = node.previous;// bbb
			// 同时获取该节点的后继节点。
			MyNode nextNode = node.next;// ddd
			// 在或去该节点的值。
			obj = node.obj;
			// 下面两行代码的含义是。首先将该节点的前驱节点的下一个节点赋值为该节点的下一个节点。
			// 同时将该节点的下一个节点的前驱节点赋值为该节点的前驱节点。、
			// 这样通过跳过该节点而实现将节点删除。
			preNode.next = nextNode;
			nextNode.previous = preNode;
			node.previous = null;
			node.obj = null;
			node.next = null;
		}
		// 最后将该节点的值返回
		size--;
		return obj;

	}

	/**
	 * @param index
	 * @return 检查要元素的索引
	 */
	private boolean checkIndex(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size)
			throw new IllegalArgumentException();
		else
			return true;
	}

	/**
	 * @return 返回插入的个数。
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param index
	 * @param obj
	 *            在index位置插入元素obj
	 */
	public void add(int index, Object obj) {

		MyNode node;
		MyNode newNode;
		boolean flag = checkIndex(index);
		if (flag) {
			// 获取index位置的元素
			node = IteratLinkList(index);
			// 获取该节点的前驱节点和后继节点。
			MyNode preNode = node.previous;
			MyNode nextNode = node.next;
			// 构建新的节点。并制定其前驱结点和后继节点。
			newNode = new MyNode(preNode, obj, nextNode);
			// 同时将该节点指定为前面两个节点的前驱节点和后继节点。
			preNode.next = newNode;
			nextNode.previous = newNode;
		}
	}

	/**
	 * @author Administrator 内部类，定义的节点类。
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