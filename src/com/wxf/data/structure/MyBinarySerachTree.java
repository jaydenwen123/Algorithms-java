package com.wxf.data.structure;


public class MyBinarySerachTree implements Comparable<Object> {

	private BinaryNode tree;

	public static class BinaryNode {
		public Object data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode() {
			// TODO Auto-generated constructor stub
		}

		public BinaryNode(Object data) {
			this(data, null, null);
		}

		public BinaryNode(Object data, BinaryNode left, BinaryNode right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}

	public BinaryNode getTree() {
		return tree;
	}

	public void setTree(BinaryNode tree) {
		this.tree = tree;
	}

	public MyBinarySerachTree(BinaryNode tree) {
		super();
		this.tree = tree;
	}

	public MyBinarySerachTree() {
		// TODO Auto-generated constructor stub
	}

	public boolean contains(Object object) {
		return contains(tree, object);
	}

	private boolean contains(BinaryNode tree2, Object object) {
		// TODO Auto-generated method stub
		if (tree2 == null) {
			return false;
		}
		/*if(this.compareTo(object)){
			
		}*/
		return false;
	}

	public void insert(Object object) {

	}

	public void makeEmpty() {
		tree = null;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub

		if (((Integer) this.tree.data) < ((Integer) o)) {
			return 1;
		} else if (((Integer) this.tree.data) < ((Integer) o)) {
			return -1;
		} else {

			return 0;
		}
	}
}
