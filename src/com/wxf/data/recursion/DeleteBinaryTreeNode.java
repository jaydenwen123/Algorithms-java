package com.wxf.data.recursion;

import com.wxf.data.structure.MyBinarySerachTree.BinaryNode;

public class DeleteBinaryTreeNode {

	// �������ĸ��ڵ�
	private BinaryNode root;

	public BinaryNode getRoot() {
		return root;
	}

	public void setRoot(BinaryNode root) {
		this.root = root;
	}

	public DeleteBinaryTreeNode() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ɾ���������������ڵ�
	 * 
	 * @param nodeD
	 */
	private void deleteSubTreeNode(BinaryNode nodeD) {

		// ɾ����������������˼·���£�
		// 1.�����Ҷ�ӽڵ㣬��ֱ�ӽ���ɾ������
		// 2�����������������Ϊ�գ����Һ��Ӳ�Ϊ�գ�����Ҫ�����ж�Ȼ���ٽ����ƶ�
		// 3.������������Һ���Ϊ�գ������Ӳ�Ϊ�գ�����Ҫ�����ж�Ȼ���ٽ����ƶ�
		//	4.���������������������������Ϊ��
		//
		//

	}

	public static void main(String[] args) {
		// ����������
		BinaryNode nodeG = new BinaryNode("G");
		BinaryNode nodeE = new BinaryNode("E");
		BinaryNode nodeF = new BinaryNode("F");
		BinaryNode nodeD = new BinaryNode("D", null, nodeG);
		BinaryNode nodeC = new BinaryNode("C", nodeE, nodeF);
		BinaryNode nodeB = new BinaryNode("B", nodeD, null);
		BinaryNode nodeA = new BinaryNode("A", nodeB, nodeC);
		DeleteBinaryTreeNode tree = new DeleteBinaryTreeNode();
		tree.setRoot(nodeA);
		tree.deleteSubTreeNode(nodeD);
		System.out.println(tree.bsj(6));
	}
	static long a[]=new long[10001];
	long bsj(int n)  
	{  
	   long ans=1;  
	   if(a[n]>0)  
	       return a[n];  
	   for(int i=1;i<=n/2;i++)  
	       ans+=bsj(i);  
	  a[n]=ans;  
	  return ans;  
	}  

}
