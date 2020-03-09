package com.wxf.data.huffman;

/**
 * 定义完全哈弗曼树的每一个节点
 * 
 * @author Administrator
 * 
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {

	// 父亲节点
	public BinaryTreeNode parent;
	// 左孩子节点
	public BinaryTreeNode left;
	// 右孩子节点
	public BinaryTreeNode right;
	// 对应测字符
	public String chars;
	// 出现的频率
	public int frequency;

	/**
	 * 判断是否为叶子节点
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return chars.length() == 1;
	}

	/**
	 * 判断是否为父节点
	 * 
	 * @return
	 */
	public boolean isParent() {
		return parent == null;
	}

	/**
	 * 判断是否为做节点
	 * 
	 * @return
	 */
	public boolean isLeftNode() {
		return parent != null && this == parent.left;
	}

	public BinaryTreeNode getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public String getChars() {
		return chars;
	}

	public void setChars(String chars) {
		this.chars = chars;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public int compareTo(BinaryTreeNode o) {
		// TODO Auto-generated method stub
		return frequency - o.frequency;
	}

	//

}
