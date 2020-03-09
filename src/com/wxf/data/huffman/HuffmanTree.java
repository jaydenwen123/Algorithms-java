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
 * ������������
 * 
 * @author Administrator
 * 
 */
public class HuffmanTree {
	// ���������ĸ��ڵ�
	private BinaryTreeNode root;

	/**
	 * ������������
	 * 
	 * @param statistics
	 *            �ò�������洢����ÿһ��Ҷ�ӽڵ���ַ��ͳ��ֵ�Ƶ�� �����ÿһ���ַ���Ӧһ��Ҷ�ӽڵ�
	 */
	public static HuffmanTree buildTree(Map<Character, Integer> statistics,
			List<BinaryTreeNode> leafNodes) {

		if (statistics != null) {
			PriorityQueue<BinaryTreeNode> queue = new PriorityQueue<BinaryTreeNode>();
			// ���Ƚ�map�����е��ַ�תΪһ���ַ����飬Ȼ����Ա�����
			// ���Ҵ���Ҷ�ӽڵ㲢����洢��PriorityQueue���ȶ�����
			Character[] chars = (Character[]) statistics.keySet().toArray(
					new Character[0]);
			// ÿһ����ĸ����һ��Ҷ�ӽڵ㡣
			for (Character ch : chars) {
				// ����Ҷ�ӽڵ㡣Ȼ������ӵ����ȶ�����
				BinaryTreeNode node = new BinaryTreeNode();
				node.chars = ch.toString();
				node.frequency = statistics.get(ch);
				queue.add(node);
				leafNodes.add(node);
			}
			int size = queue.size();
			for (int i = 1; i <= size - 1; i++) {
				// �Ӹö����У�ȡ��������С��Ҷ�ӽڵ㡣
				BinaryTreeNode leftNode = queue.poll();
				BinaryTreeNode rightNode = queue.poll();
				// Ȼ�����½ڵ㡣
				BinaryTreeNode parentNode = new BinaryTreeNode();
				// �ֱ����Һ��ӵ��ַ����Ȼ��ֵ�����ڵ�
				parentNode.chars = leftNode.chars + rightNode.chars;
				// �����Һ��ӵ�Ƶ�����Ȼ��ֵ�����ڵ��Ƶ��
				parentNode.frequency = leftNode.frequency + rightNode.frequency;
				// һ�½���ȷ���˴˵Ĺ�ϵ
				parentNode.left = leftNode;
				parentNode.right = rightNode;
				leftNode.parent = parentNode;
				rightNode.parent = parentNode;
				queue.add(parentNode);
			}
			// ����һ������������Ȼ�󷵻�
			HuffmanTree tree = new HuffmanTree();
			tree.root = queue.poll();
			return tree;
		}
		return null;
	}

	/**
	 * ���ж�һ���ַ�������ͳ�ƵĲ���
	 * 
	 * @param data
	 *            Ҫ����ͳ�Ƶ�����
	 * @return ���ص���ͳ�ƺ�Ľ�����洢��map������
	 */
	public static Map<Character, Integer> processStatistic(String data) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] array = data.toCharArray();
		for (char c : array) {
			// �����жϵĲ�������ü������Ѿ����ڸ��ַ�����ֻ��Ҫ������Ƶ���ϼ�1���������Ӹ��ַ�
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

	/**
	 * ���ַ������б��룬Ȼ�󷵻ص��Ǳ����Ķ���������
	 * 
	 * @param oriDataStr
	 *            ԭʼ���ַ���
	 * @param statistics
	 *            ͳ�Ƶ�map����
	 * @return ���ر������ַ���
	 */
	public static String encode(String oriDataStr,
			Map<Character, Integer> statistics) {
		StringBuffer buffer = new StringBuffer();
		// ����һ�����ϣ��������湹��������������
		List<BinaryTreeNode> leafNodes = new ArrayList<BinaryTreeNode>();
		buildTree(statistics, leafNodes);
		// ��ȡÿ���ַ��ı��������
		Map<Character, String> info = encodeInfo(leafNodes);
		// �����ַ���ת��Ϊ�ַ����顣
		char[] array = oriDataStr.toCharArray();
		// ���ϵĽ����ۼ����֡�
		for (char c : array) {
			buffer.append(info.get(c));
		}
		return buffer.toString();
	}

	/**
	 * ͨ���÷������ж�ÿ��Ҷ�ӽڵ���б��롣���洢��map������ ���б����ʱ�����ܵ������¶��ϵ�ɨ�衣֪������һ�����ڵ�Ϊֹ
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
			// ���浱ǰ�Ľڵ�ֵ��
			BinaryTreeNode currentNode = node;
			// ����������¶��ϵı���
			do {
				// ����ýڵ�����ڵ㣬�����ֽ��м�0��������м�1
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
	 * ����������
	 * 
	 * @param binaryStr
	 *            �Զ�Ӧ�Ķ������ַ������н��롣
	 * @param statistics
	 *            ͳ�Ƶļ���
	 * @return ����ֵΪ���ؽ���������
	 */
	public static String decode(String binaryStr,
			Map<Character, Integer> statistics) {
		// ���Ƚ����������ݱ��浽linkedlist��������
		char[] array = binaryStr.toCharArray();
		LinkedList<Character> binaryList = new LinkedList<>();
		for (int i = 0; i < array.length; i++) {
			binaryList.addLast(array[i]);
		}
		// ������н�����������
		List<BinaryTreeNode> leafNodes = new ArrayList<BinaryTreeNode>();
		HuffmanTree tree = buildTree(statistics, leafNodes);
		StringBuffer buffer = new StringBuffer();
		// ����������϶��µı�����֪���ýڵ�ΪҶ�ӽڵ�Ϊֹ
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
