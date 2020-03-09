package com.wxf.data.recursion;

import com.wxf.data.structure.MyBinarySerachTree.BinaryNode;

public class DeleteBinaryTreeNode {

	// 二叉树的根节点
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
	 * 删除二叉树的子树节点
	 * 
	 * @param nodeD
	 */
	private void deleteSubTreeNode(BinaryNode nodeD) {

		// 删除二叉树的子树的思路如下：
		// 1.如果是叶子节点，则直接进行删除即可
		// 2、如果该子树的左孩子为空，但右孩子不为空，则需要进行判断然后再进行移动
		// 3.如果该子树的右孩子为空，但左孩子不为空，则需要进行判断然后再进行移动
		//	4.如果该数的左子树和右子树都不为空
		//
		//

	}

	public static void main(String[] args) {
		// 构建二叉树
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
