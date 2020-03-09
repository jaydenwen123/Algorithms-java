package com.wxf.data.huffman;

/**
 * ������ȫ����������ÿһ���ڵ�
 * 
 * @author Administrator
 * 
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {

	// ���׽ڵ�
	public BinaryTreeNode parent;
	// ���ӽڵ�
	public BinaryTreeNode left;
	// �Һ��ӽڵ�
	public BinaryTreeNode right;
	// ��Ӧ���ַ�
	public String chars;
	// ���ֵ�Ƶ��
	public int frequency;

	/**
	 * �ж��Ƿ�ΪҶ�ӽڵ�
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return chars.length() == 1;
	}

	/**
	 * �ж��Ƿ�Ϊ���ڵ�
	 * 
	 * @return
	 */
	public boolean isParent() {
		return parent == null;
	}

	/**
	 * �ж��Ƿ�Ϊ���ڵ�
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
