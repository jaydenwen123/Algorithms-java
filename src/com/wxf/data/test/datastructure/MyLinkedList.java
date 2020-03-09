package com.wxf.data.test.datastructure;

public class MyLinkedList {

	// 头结点
	private Node first;
	// 尾节点
	private Node last;
	// 链表的长度
	private int size;

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	/**
	 * 该函数为构造器
	 */
	public MyLinkedList() {
		// TODO Auto-generated constructor stub
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * 网链表中添加一个元素
	 * 
	 * @param el
	 */
	public void insert(Object el) {

		// 默认是采用尾插法
		linkedFirst(el);
	}

	/**
	 * 头插法
	 * 
	 * @param el
	 */
	public void linkedFirst(Object el) {
		// 首先判断该链表中的头结点是否为空

		Node node = new Node(el);
		// 当头节点不为空时，直接添加
		Node temp = first;
		if (temp != null) {
			node.next = temp;
			node.previous = null;
			first.previous = node;
			first = node;
		} else {
			// 头节点为空时，需要先创建头结点
			node.previous = node.next = null;
			first = last = node;
		}
		size++;
	}

	/**
	 * 尾插法
	 * 
	 * @param el
	 */
	public void linkedLast(Object el) {
		// 1.创建2.判断3.赋值指定
		Node node = new Node(el);
		Node temp = last;
		if (temp != null) {
			node.previous = temp;
			node.next = null;
			last.next = node;
			last = node;
		} else {
			first = last = node;
		}
		size++;
	}

	/**
	 * 从链表中删除一个元素
	 * 
	 * @param el
	 */
	public Object delete(Object el) {
		// 1找，2.删除
		Node temp = first;
		if (temp != null) {
			while (temp.next != null && !el.equals(temp.data)) {
				temp = temp.next;
			}
			if (temp.next != null) {
				// 表示的是找到
				temp.previous.next = temp.next;
				temp.next.previous = temp.previous;
				size--;
			}
		}

		return el;
	}

	/**
	 * 判断该联表示否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	/**
	 * 判断链表中是否包含该元素
	 * 
	 * @param el
	 * @return
	 */
	public boolean Contain(Object el) {
		Node temp = last;
		if (temp != null) {
			while (temp.previous != null) {
				if (el.equals(temp.data)) {
					return true;
				}
				temp = temp.previous;
			}
			if (el.equals(temp.data))
				return true;
			else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 获取该元素的位置，如果不存在，则返回-1
	 * 
	 * @param el
	 * @return
	 */
	public int getIndex(Object el) {
		Node temp = first;
		int index = 0;
		if (temp != null) {
			while (temp.next != null && !el.equals(temp.data)) {
				temp = temp.next;
				index++;
			}
			if (temp.next != null) {
				// 表示找到元素
				return index;
			}
			return -1;
		}
		return -1;
	}

	/**
	 * 获取链表的长度
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * 遍历链表
	 */
	public void print() {
		Node temp = first;
		if (temp != null) {
			while (temp.next != null) {
				System.out.print(temp.data + "\t");
				temp = temp.next;
			}

			System.out.println(temp.data);
		}
	}

	public void print2() {
		Node temp = last;
		if (temp != null) {
			while (temp.previous != null) {
				System.out.print(temp.data + "\t");
				temp = temp.previous;
			}
			System.out.println(temp.data);
		}
	}

	/**
	 * 
	 * 定义节点类
	 * 
	 * @author Administrator
	 * 
	 */
	class Node {
		public Node previous;
		public Node next;
		public Object data;

		/**
		 * 
		 */
		public Node() {
			// TODO Auto-generated constructor stub
			previous = null;
			next = null;
		}

		/**
		 * @param previous
		 * @param next
		 * @param data
		 */
		public Node(Node previous, Node next, Object data) {
			super();
			this.previous = previous;
			this.next = next;
			this.data = data;
		}

		/**
		 * @param data
		 */
		public Node(Object data) {
			this();
			this.data = data;

		}

	}

	public static void main(String[] args) {

		MyLinkedList linkedList = new MyLinkedList();
		System.out.println("test linkedFirst");
		linkedList.linkedFirst(1);
		linkedList.linkedFirst(2);
		linkedList.linkedFirst(3);
		linkedList.linkedFirst("hello");
		System.out.println("test size");
		System.out.println(linkedList.size());
		System.out.println("test print");
		linkedList.print();
		System.out.println("*********************print2");
		linkedList.print2();
		System.out.println("test linkedLast");
		MyLinkedList linkedList2 = new MyLinkedList();
		linkedList2.linkedLast("s1");
		linkedList2.linkedLast("s2");
		linkedList2.linkedLast("s3");
		linkedList2.linkedLast("s4");
		System.out.println(linkedList2.size());
		System.out.println("test print");
		linkedList2.print();
		System.out.println("**********************");
		linkedList2.print2();
		linkedList2.delete("s2");
		System.out.println(linkedList2.size());
		linkedList2.print();
		System.out.println("test getIndex");
		System.out.println(linkedList2.getIndex("s3"));
		System.out.println("test contain");
		System.out.println(linkedList2.Contain("s1"));
		System.out.println(linkedList2.Contain("s2"));
		System.out.println(linkedList2.Contain("s4"));
	}

}
