package com.wxf.data.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;
import java.util.PriorityQueue;

/**
 * 
 * 关于处理哈弗曼树的编解码的操作，思路如下： 1.首先进行对要编码的数据进行统计，统计后的结果由map集合来存储
 * 2.通过统计后的信息来构建哈弗曼树，返回编码后的信息
 * 
 * @author Administrator
 * 
 */
public class SolveHuffman {

	// 哈弗曼树的根节点
	public HuffmanBTreeNode root;

	public SolveHuffman() {
		// TODO Auto-generated constructor stub
	}

	public SolveHuffman(HuffmanBTreeNode root) {
		super();
		this.root = root;
	}

	/**
	 * 哈弗曼树对应的节点类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class HuffmanBTreeNode implements
			Comparable<HuffmanBTreeNode> {
		// 哈弗曼树的父节点
		public HuffmanBTreeNode parent;
		// 哈弗曼树的左孩子节点
		public HuffmanBTreeNode leftChild;
		// 哈弗曼树的右孩子节点
		public HuffmanBTreeNode rightChild;
		// 哈弗曼树的节点对应的字符
		public String symbol;
		// 哈弗曼树的节点的权值
		public int weigh;

		public HuffmanBTreeNode() {
			// TODO Auto-generated constructor stub
		}

		public HuffmanBTreeNode(HuffmanBTreeNode parent,
				HuffmanBTreeNode leftChild, HuffmanBTreeNode rightChild,
				String symbol, int weigh) {
			super();
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.symbol = symbol;
			this.weigh = weigh;
		}

		/**
		 * 用来构造叶子节点
		 * 
		 * @param symbol
		 * @param weigh
		 */
		public HuffmanBTreeNode(String symbol, int weigh) {
			super();
			this.symbol = symbol;
			this.weigh = weigh;
		}

		@Override
		public int compareTo(HuffmanBTreeNode o) {
			// TODO Auto-generated method stub
			return this.weigh - o.weigh;
		}

		/**
		 * 判断该节点是左节点
		 * 
		 * @return
		 */
		public boolean isLeft() {
			return this.parent != null && parent.leftChild == this;
		}

		/**
		 * 判断该节点是否是叶子节点
		 * 
		 * @return
		 */
		public boolean isLeaf() {
			return this.leftChild == null && this.rightChild == null;
		}
	}

	/**
	 * 根据传入的字符串来统计不同的字符出现的次数，并返回统计后的信息
	 * 
	 * @param data
	 * @return
	 */
	public static Map<Character, Integer> statistic(String data) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] chars = data.toCharArray();
		for (char c : chars) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

	/**
	 * 建立哈弗曼树
	 * 
	 * @param statistics
	 * @return
	 */
	public static SolveHuffman createHuffmanTree(
			Map<Character, Integer> statistics, List<HuffmanBTreeNode> nodes) {
		SolveHuffman tree = null;
		// 通过优先级队列来实现
		PriorityQueue<HuffmanBTreeNode> queue = new PriorityQueue<SolveHuffman.HuffmanBTreeNode>();
		// 哈弗曼建树的思路如下：
		// １.首先根据统计的信息进行创建叶子节点
		// 2、然后将所有的叶子节点不断的入队和出队，最后只留下一颗节点，即为根节点
		Character[] chars = statistics.keySet().toArray(new Character[0]);
		// 定义三个变量
		HuffmanBTreeNode leftChild, rightChild, parent = null;
		for (Character c : chars) {
			// 创建叶子节点
			HuffmanBTreeNode node = new HuffmanBTreeNode(c + "",
					statistics.get(c));
			queue.add(node);
			// 将其添加到list集合中，方便编码
			nodes.add(node);

		}
		// 记录当前的队列中的元素的个数
		int n = queue.size();
		for (int i = 1; i < n; i++) {
			// 从优先级队列中弹出两个权值较小的节点
			leftChild = queue.poll();
			rightChild = queue.poll();
			// 然后根据这两个节点来构建新的父节点，其权值为左右孩子节点的权值之和，字符也为左右孩子字符之和
			parent = new HuffmanBTreeNode(leftChild.symbol + rightChild.symbol,
					leftChild.weigh + rightChild.weigh);
			// 以下进行指定二者的关系
			leftChild.parent = parent;
			rightChild.parent = parent;
			parent.leftChild = leftChild;
			parent.rightChild = rightChild;
			// 将新创建的父节点也加入到队列中
			queue.add(parent);
		}
		// 弹出最后一个节点
		HuffmanBTreeNode root = queue.poll();
		tree = new SolveHuffman(root);
		return tree;
	}

	/**
	 * 哈弗曼编码
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(String data, Map<Character, Integer> pathLength) {
		String encodeStr = "";
		Map<Character, Integer> statistics = statistic(data);
		List<HuffmanBTreeNode> nodes = new ArrayList<>();
		// 获取每个叶子节点的编码信息
		Map<Character, String> infos = getEachNodeInfo(statistics, nodes,
				pathLength);
		char[] chars = data.toCharArray();
		// 组建编码后的信息
		for (char c : chars) {
			encodeStr += infos.get(c);
		}
		return encodeStr;
	}

	/**
	 * 计算哈弗曼树的带权路径长度
	 * 
	 * @param pathLength
	 * @param nodes
	 * @return
	 */
	public static int getWPL(Map<Character, Integer> pathLength,
			List<HuffmanBTreeNode> nodes) {
		int wpl = 0;
		for (HuffmanBTreeNode node : nodes) {
			wpl += node.weigh * pathLength.get(node.symbol.charAt(0));
		}
		return wpl;
	}

	/**
	 * 返回每个节点的编码信息，通过map返回
	 * 
	 * @param statistics
	 *            统计的字符信息
	 * @param nodes
	 *            叶子节点的集合
	 * @param pathLength
	 *            每个叶子节点的路径长度
	 * @return
	 */
	public static Map<Character, String> getEachNodeInfo(
			Map<Character, Integer> statistics, List<HuffmanBTreeNode> nodes,
			Map<Character, Integer> pathLength) {
		Map<Character, String> infos = new HashMap<>();
		// 建立哈弗曼树
		createHuffmanTree(statistics, nodes);
		// 编码是从下往上扫描，当遇到根节点时，才结束
		for (int i = 0; i < nodes.size(); i++) {
			// 每个叶子节点的码字
			String codeWord = "";
			// 没个叶子节点的路径长度
			int length = 0;
			HuffmanBTreeNode currParent = nodes.get(i);
			// 获取该叶子节点的字符
			Character character = currParent.symbol.charAt(0);
			while (currParent.parent != null) {
				if (currParent.isLeft()) {
					codeWord = "0" + codeWord;
				} else {
					codeWord = "1" + codeWord;
				}
				currParent = currParent.parent;
				length++;
			}
			// 统计码字的信息
			infos.put(character, codeWord);
			// 统计节点的路径长度
			if (pathLength != null) {
				pathLength.put(character, length);
			}
		}
		return infos;
	}

	/**
	 * 哈弗曼解码
	 * 
	 * @param binStr
	 * @return
	 */
	public static String decode(String binStr,
			Map<Character, Integer> statistics) {
		String decodeStr = "";
		// 哈弗曼解码的思路如下：
		// 1.首先需要建立哈弗曼树
		// 2.其次解码的过程是子上而下的扫描的过程，每次扫描直到遇到叶子节点才为止
		List<HuffmanBTreeNode> nodes = new ArrayList<SolveHuffman.HuffmanBTreeNode>();
		// 建立哈弗曼树
		SolveHuffman tree = createHuffmanTree(statistics, nodes);
		LinkedList<Character> chars = new LinkedList<>();
		char[] data = binStr.toCharArray();
		for (char c : data) {
			chars.addLast(c);
		}
		while (!chars.isEmpty()) {
			HuffmanBTreeNode node = tree.root;
			// 自上而下的查找叶子节点
			while (!node.isLeaf()) {
				if (chars.removeFirst() == '0') {
					node = node.leftChild;
				} else {
					node = node.rightChild;
				}
			}
			decodeStr += node.symbol;
		}
		return decodeStr;
	}

	public static void main(String[] args) {
		System.out.println("测试一：主要是输出不同字符的编码结果：");
		Map<Character, Integer> statistics1 = new HashMap<Character, Integer>();
		statistics1.put('a', 4);
		statistics1.put('b', 2);
		statistics1.put('c', 1);
		statistics1.put('d', 7);
		statistics1.put('e', 3);
		List<HuffmanBTreeNode> nodes = new ArrayList<>();
		Map<Character, Integer> pathLength = new HashMap<Character, Integer>();
		Map<Character, String> infos = getEachNodeInfo(statistics1, nodes,
				pathLength);
		for (Entry<Character, String> entry : infos.entrySet()) {
			System.out.println(entry.getKey() + "," + entry.getValue());
		}
		System.out.println("哈弗曼树的带权路径长度为：" + getWPL(pathLength, nodes));
		System.out.println("测试二：该测试主要是对一段数据的编码和解码进行测试：");
		String data = "hello everyone today is a nice day int the century ,so we should to enjoy it all right?";
		Map<Character, Integer> statistics = statistic(data);
		System.out.println("原信息：");
		System.out.println(data);
		String encodeStr = encode(data, null);
		System.out.println("编码后的信息：");
		System.out.println(encodeStr);
		System.out.println("解码后的信息：");
		System.out.println(decode(encodeStr, statistics));
	}

}
