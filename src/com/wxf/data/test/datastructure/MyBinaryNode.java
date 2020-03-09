package com.wxf.data.test.datastructure;

public class MyBinaryNode {

	public Object data;
	public MyBinaryNode left;
	public MyBinaryNode right;

	public MyBinaryNode() {
		// TODO Auto-generated constructor stub
	}

	public MyBinaryNode(Object data, MyBinaryNode left, MyBinaryNode right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * 创建叶子节点
	 * 
	 * @param data
	 */
	public MyBinaryNode(Object data) {
		this(data, null, null);
	}

}
