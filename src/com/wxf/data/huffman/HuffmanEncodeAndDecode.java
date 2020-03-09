package com.wxf.data.huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * 该类主要用来完成哈弗曼的编解码 哈弗曼编解码的思路如下： 
 * 1.首先需要构建哈弗曼树 
 * 1）首先定义节点类，其中包括左孩子，右孩子，父孩子，对应的字符，权值
 * 2）然后构建树，构建时通过优先级队列来进行构建
 * 
 * @author Administrator
 * 
 */
public class HuffmanEncodeAndDecode {

	// 哈弗曼的根节点
	private binaryTreeNode root;

	public static class binaryTreeNode implements Comparable<binaryTreeNode> {
		// 左孩子
		public binaryTreeNode leftchild;
		// 右孩子
		public binaryTreeNode rightchild;
		// 父亲节点
		public binaryTreeNode parent;
		// 该节点的字符
		public String symbol;
		// 权值
		public int frequency;

		/**
		 * 判断是否为叶子节点
		 * 
		 * @return
		 */
		public boolean isLeaf() {
			return symbol.length() == 1;
		}

		/**
		 * @return
		 */
		public boolean isLeft() {
			return parent != null && this == parent.leftchild;
		}

		/**
		 * @return
		 */
		public boolean isParent() {
			return leftchild != null || rightchild != null;
		}

		@Override
		public int compareTo(binaryTreeNode o) {
			// TODO Auto-generated method stub
			return this.frequency - o.frequency;
		}
	}

	public HuffmanEncodeAndDecode(binaryTreeNode root) {
		super();
		this.root = root;
	}

	public binaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(binaryTreeNode root) {
		this.root = root;
	}

	/**
	 * @param statistic
	 * @param nodes
	 * @return
	 */
	public static HuffmanEncodeAndDecode buildHuffmanTree(
			Map<Character, Integer> statistic, List<binaryTreeNode> nodes) {
		// 创建优先级队列
		PriorityQueue<binaryTreeNode> queue = new PriorityQueue<>();
		Character[] arrs = statistic.keySet().toArray(new Character[0]);
		for (Character c : arrs) {
			binaryTreeNode node = new binaryTreeNode();
			node.frequency = statistic.get(c);
			node.symbol = new Character(c).toString();
			nodes.add(node);
			// 将该叶子节点添加到优先级队列中
			queue.add(node);
		}

		int size = queue.size();
		for (int i = 0; i < size - 1; i++) {
			// 弹出两个权值较小的两个节点
			binaryTreeNode node1 = queue.poll();
			binaryTreeNode node2 = queue.poll();
			binaryTreeNode parNode = new binaryTreeNode();
			parNode.frequency = node1.frequency + node2.frequency;
			parNode.symbol = node1.symbol + node2.symbol;
			// 一下指定二者的关系
			parNode.leftchild = node1;
			parNode.rightchild = node2;
			node1.parent = parNode;
			node2.parent = parNode;
			// 将其添加到优先级队列中
			queue.add(parNode);
		}
		HuffmanEncodeAndDecode huffman = new HuffmanEncodeAndDecode(
				queue.poll());
		return huffman;
	}

	/**
	 * 
	 * 哈弗曼编码
	 * 
	 * @param resStr
	 *            源字符串
	 * @param statistic
	 *            统计结果
	 * @return
	 */
	public static String encode(String resStr, Map<Character, Integer> statistic) {
		char[] arrs = resStr.toCharArray();
		List<binaryTreeNode> nodes = new ArrayList<binaryTreeNode>();
		// 构建哈弗曼树
		buildHuffmanTree(statistic, nodes);
		// 获取编码后的信息
		Map<String, String> map = encodeInfo(nodes);
		// 通过buffer来构建编码后的结果
		StringBuffer buffer = new StringBuffer();
		for (char c : arrs) {
			buffer.append(map.get(new Character(c).toString()));
			// System.out.println(c);
		}
		return buffer.toString();
	}

	/**
	 * 对每个叶子节点进行编值 思路是：自下而上的扫描
	 * 
	 * @param nodes
	 * @return
	 */
	public static Map<String, String> encodeInfo(List<binaryTreeNode> nodes) {
		Map<String, String> map = new HashMap<String, String>();
		for (binaryTreeNode node : nodes) {
			String codeWord = new String();
			// 获取该叶子节点的字符
			binaryTreeNode currNode = node;
			String s = currNode.symbol;
			do {
				if (currNode.isLeft()) {
					codeWord = "0" + codeWord;
				} else {
					codeWord = "1" + codeWord;
				}
				currNode = currNode.parent;
			} while (currNode.parent != null);
			map.put(s, codeWord);
		}
		return map;
	}

	/**
	 * 
	 * 哈弗曼解码
	 * 
	 * @return
	 */
	public static String decode(String binaryStr,
			Map<Character, Integer> statistic) {

		LinkedList<binaryTreeNode> nodes = new LinkedList<>();
		LinkedList<Character> list = new LinkedList<>();
		// 建立哈弗曼树
		HuffmanEncodeAndDecode tree = buildHuffmanTree(statistic, nodes);
		StringBuffer buffer = new StringBuffer();
		char[] arrs = binaryStr.toCharArray();
		for (char c : arrs)
			list.addLast(c);
		while (list.size() > 0) {
			binaryTreeNode node = tree.root;
			while (!node.isLeaf()) {
				Character character = list.removeFirst();
				if (character == '0') {
					node = node.leftchild;
				} else {
					node = node.rightchild;
				}
				// 自上而下的扫描
			}
			buffer.append(node.symbol);
		}
		return buffer.toString();
	}

	/**
	 * 将一个字符串进行统计，并将统计后的信息存放到map中
	 * 
	 * @param data
	 * @return
	 */
	public static Map<Character, Integer> statsticInfo(String data) {
		// 将信息保存到map中
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] arrs = data.toCharArray();
		// 进行统计信息
		for (char c : arrs) {
			if (map.containsKey(c))
				map.put(new Character(c), map.get(c) + 1);
			else {
				map.put(new Character(c), 1);
			}
		}
		return map;
	}

	public static void main(String[] args) {

		String str = "7324234@#$%$^%&$@#%#$$Fsdfsadfisajcisadjfiosaduf2#$#@$#%$foejfiasdmiadjsfjsadifjs"
				+ "fsfjisdjfiowejrafasdjfawejdfisjdf";
		Map<Character, Integer> info = statsticInfo(str);
		// for (Entry<Character, Integer> entry : info.entrySet()) {
		// System.out.println(entry.getKey() + "," + entry.getValue());
		// }
		System.out.println(str);
		System.out.println(encode(str, info));
		String binaryStr = encode(str, info);
		String decodeStr = decode(binaryStr, info);
		System.out.println(decodeStr);
	}
}
