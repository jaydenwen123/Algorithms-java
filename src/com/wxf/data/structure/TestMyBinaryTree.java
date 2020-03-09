package com.wxf.data.structure;


public class TestMyBinaryTree {

	public static void main(String[] args) {

		MyBinaryTree tree1 = new MyBinaryTree("A");
		MyBinaryTree tree2 = new MyBinaryTree("B");
		MyBinaryTree tree3 = new MyBinaryTree("H");
		MyBinaryTree tree4 = new MyBinaryTree("P");
		MyBinaryTree tree5 = new MyBinaryTree("D", tree2, null);
		MyBinaryTree tree6 = new MyBinaryTree("C", tree1, tree5);
		MyBinaryTree tree7 = new MyBinaryTree("E", null, new MyBinaryTree("G", tree3, tree4));
		// 构建根节点
		/**
		 * 
		 *构建的二叉树为如下的结构：
		 *			  F
		 *			/  \
		 *         C    E
		 *        / \    \
		 *       A   D    G
		 *          /    / \
		 *          B   H   P
		 */
		MyBinaryTree tree = new MyBinaryTree("F", tree6, tree7);

		tree.printBinaryTree(tree, 4);
		System.out.println("pre order sysout");
		// 测试该二叉树的先序遍历
		System.out.println("the preOrder first way:");
		tree.preOrder1(tree);
		System.out.println("the preOrder second way:");
		tree.preOrder2(tree);
		System.out.println("in order sysout");
		// 测试该二叉树的中序遍历
		tree.inOrder1(tree);
		System.out.println("second way:");
		tree.inOrder2(tree);
		System.out.println("suf order sysout");
		// 测试该二叉树的后序遍历
		tree.sufOrder1(tree);
		System.out.println("second way:");
		tree.sufOrder2(tree);
		System.out.println("third way:");
		tree.sufOrder3(tree);
		// 测试该二叉树的层次遍历
		System.out.println("test layout order");
		System.out.println("first way:");
		tree.layoutOrder1(tree);
		System.out.println("second way:");
		tree.layoutOrder2(tree);
		// 测试该二叉树的叶子节点的个数
		System.out.println("********get levels*******");
		System.out.println(tree.getLevel(tree));
		// 测试该二叉树的深度
		System.out.println("***********get depth()***********");
		System.out.println(tree.getDepth(tree));
		// 测试该二叉树的某个节点的等级
		System.out.println("**********get level **********");
		System.out.println(tree.level(tree, "F"));
		System.out.println("************交换左右孩子*******************");
		tree.changeLeftAndRightChild(tree);
		tree.layoutOrder2(tree);
		
		System.out.println("***************************************");
		int i=tree.getTreeCounts(tree);
		System.out.println("counts:"+i);
		System.out.println("**************************************");
		System.out.println("遍历树的值：");
		for(MyBinaryTree tr:tree.forEachTree(tree)){
			System.out.println(tr.getData());
		}
	}

}
