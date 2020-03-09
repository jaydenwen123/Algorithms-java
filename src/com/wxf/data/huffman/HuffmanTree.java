package com.wxf.data.huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.xml.soap.Node;

/**
 * 建立哈弗曼树
 * 
 * @author Administrator
 * 
 */
public class HuffmanTree {
	// 哈弗曼树的根节点
	private BinaryTreeNode root;

	/**
	 * 建立哈弗曼树
	 * 
	 * @param statistics
	 *            该参数里面存储的是每一个叶子节点的字符和出现的频率 。因此每一个字符对应一个叶子节点
	 */
	public static HuffmanTree buildTree(Map<Character, Integer> statistics,
			List<BinaryTreeNode> leafNodes) {

		if (statistics != null) {
			PriorityQueue<BinaryTreeNode> queue = new PriorityQueue<BinaryTreeNode>();
			// 首先将map集合中的字符转为一个字符数组，然后记性遍历，
			// 并且创建叶子节点并将其存储到PriorityQueue优先队列中
			Character[] chars = (Character[]) statistics.keySet().toArray(
					new Character[0]);
			// 每一个字母代表一个叶子节点。
			for (Character ch : chars) {
				// 创建叶子节点。然后将其添加到优先队列中
				BinaryTreeNode node = new BinaryTreeNode();
				node.chars = ch.toString();
				node.frequency = statistics.get(ch);
				queue.add(node);
				leafNodes.add(node);
			}
			int size = queue.size();
			for (int i = 1; i <= size - 1; i++) {
				// 从该队列中，取出两个较小的叶子节点。
				BinaryTreeNode leftNode = queue.poll();
				BinaryTreeNode rightNode = queue.poll();
				// 然后构造新节点。
				BinaryTreeNode parentNode = new BinaryTreeNode();
				// 分别将左右孩子的字符相加然后赋值给父节点
				parentNode.chars = leftNode.chars + rightNode.chars;
				// 将左右孩子的频数相加然后赋值给父节点的频数
				parentNode.frequency = leftNode.frequency + rightNode.frequency;
				// 一下进行确定彼此的关系
				parentNode.left = leftNode;
				parentNode.right = rightNode;
				leftNode.parent = parentNode;
				rightNode.parent = parentNode;
				queue.add(parentNode);
			}
			// 构建一个哈弗曼树，然后返回
			HuffmanTree tree = new HuffmanTree();
			tree.root = queue.poll();
			return tree;
		}
		return null;
	}

	/**
	 * 进行对一段字符串进行统计的操作
	 * 
	 * @param data
	 *            要进行统计的数据
	 * @return 返回的是统计后的结果，存储在map集合中
	 */
	public static Map<Character, Integer> processStatistic(String data) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] array = data.toCharArray();
		for (char c : array) {
			// 进行判断的操作如果该集合中已经存在该字符，则只需要在它的频数上加1否则进行添加该字符
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

	/**
	 * 对字符串进行编码，然后返回的是编码后的二进制数据
	 * 
	 * @param oriDataStr
	 *            原始的字符串
	 * @param statistics
	 *            统计的map集合
	 * @return 返回编码后的字符串
	 */
	public static String encode(String oriDataStr,
			Map<Character, Integer> statistics) {
		StringBuffer buffer = new StringBuffer();
		// 创建一个集合，用来下面构建创建哈弗曼树
		List<BinaryTreeNode> leafNodes = new ArrayList<BinaryTreeNode>();
		buildTree(statistics, leafNodes);
		// 获取每个字符的编码的码字
		Map<Character, String> info = encodeInfo(leafNodes);
		// 将该字符串转换为字符数组。
		char[] array = oriDataStr.toCharArray();
		// 不断的进行累加码字。
		for (char c : array) {
			buffer.append(info.get(c));
		}
		return buffer.toString();
	}

	/**
	 * 通过该方法进行对每个叶子节点进行编码。并存储到map集合中 进行编码的时候，尊熊的是自下而上的扫描。知道最后的一个根节点为止
	 * 
	 * @param leafNodes
	 * @return
	 */
	public static Map<Character, String> encodeInfo(
			List<BinaryTreeNode> leafNodes) {
		Map<Character, String> map = new HashMap<Character, String>();
		for (BinaryTreeNode node : leafNodes) {
			String codeWord = "";
			Character character = new Character(node.getChars().charAt(0));
			// 保存当前的节点值。
			BinaryTreeNode currentNode = node;
			// 下面进行自下而上的遍历
			do {
				// 如果该节点是左节点，则码字进行加0；否则进行加1
				if (currentNode.isLeftNode()) {
					codeWord = "0" + codeWord;
				} else {
					codeWord = "1" + codeWord;
				}
				currentNode = currentNode.parent;
			} while (currentNode.parent != null);
			map.put(character, codeWord);
		}
		return map;
	}

	/**
	 * 哈弗曼解码
	 * 
	 * @param binaryStr
	 *            对对应的二进制字符串进行解码。
	 * @param statistics
	 *            统计的集合
	 * @return 返回值为返回解码后的数据
	 */
	public static String decode(String binaryStr,
			Map<Character, Integer> statistics) {
		// 首先将二进制数据保存到linkedlist链表集合中
		char[] array = binaryStr.toCharArray();
		LinkedList<Character> binaryList = new LinkedList<>();
		for (int i = 0; i < array.length; i++) {
			binaryList.addLast(array[i]);
		}
		// 下面进行建立哈弗曼树
		List<BinaryTreeNode> leafNodes = new ArrayList<BinaryTreeNode>();
		HuffmanTree tree = buildTree(statistics, leafNodes);
		StringBuffer buffer = new StringBuffer();
		// 下面进行自上而下的遍历。知道该节点为叶子节点为止
		while (binaryList.size() > 0) {
			BinaryTreeNode node = tree.root;
			do {
				Character character = binaryList.removeFirst();
				if (character.charValue() == '0') {
					node = node.left;
				} else {
					node = node.right;
				}
			} while (!node.isLeaf());
			buffer.append(node.chars);
		}
		return buffer.toString();
	}

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}

}
