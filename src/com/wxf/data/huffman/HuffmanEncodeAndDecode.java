package com.wxf.data.huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * ������Ҫ������ɹ������ı���� ������������˼·���£� 
 * 1.������Ҫ������������ 
 * 1�����ȶ���ڵ��࣬���а������ӣ��Һ��ӣ������ӣ���Ӧ���ַ���Ȩֵ
 * 2��Ȼ�󹹽���������ʱͨ�����ȼ����������й���
 * 
 * @author Administrator
 * 
 */
public class HuffmanEncodeAndDecode {

	// �������ĸ��ڵ�
	private binaryTreeNode root;

	public static class binaryTreeNode implements Comparable<binaryTreeNode> {
		// ����
		public binaryTreeNode leftchild;
		// �Һ���
		public binaryTreeNode rightchild;
		// ���׽ڵ�
		public binaryTreeNode parent;
		// �ýڵ���ַ�
		public String symbol;
		// Ȩֵ
		public int frequency;

		/**
		 * �ж��Ƿ�ΪҶ�ӽڵ�
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
		// �������ȼ�����
		PriorityQueue<binaryTreeNode> queue = new PriorityQueue<>();
		Character[] arrs = statistic.keySet().toArray(new Character[0]);
		for (Character c : arrs) {
			binaryTreeNode node = new binaryTreeNode();
			node.frequency = statistic.get(c);
			node.symbol = new Character(c).toString();
			nodes.add(node);
			// ����Ҷ�ӽڵ���ӵ����ȼ�������
			queue.add(node);
		}

		int size = queue.size();
		for (int i = 0; i < size - 1; i++) {
			// ��������Ȩֵ��С�������ڵ�
			binaryTreeNode node1 = queue.poll();
			binaryTreeNode node2 = queue.poll();
			binaryTreeNode parNode = new binaryTreeNode();
			parNode.frequency = node1.frequency + node2.frequency;
			parNode.symbol = node1.symbol + node2.symbol;
			// һ��ָ�����ߵĹ�ϵ
			parNode.leftchild = node1;
			parNode.rightchild = node2;
			node1.parent = parNode;
			node2.parent = parNode;
			// ������ӵ����ȼ�������
			queue.add(parNode);
		}
		HuffmanEncodeAndDecode huffman = new HuffmanEncodeAndDecode(
				queue.poll());
		return huffman;
	}

	/**
	 * 
	 * ����������
	 * 
	 * @param resStr
	 *            Դ�ַ���
	 * @param statistic
	 *            ͳ�ƽ��
	 * @return
	 */
	public static String encode(String resStr, Map<Character, Integer> statistic) {
		char[] arrs = resStr.toCharArray();
		List<binaryTreeNode> nodes = new ArrayList<binaryTreeNode>();
		// ������������
		buildHuffmanTree(statistic, nodes);
		// ��ȡ��������Ϣ
		Map<String, String> map = encodeInfo(nodes);
		// ͨ��buffer�����������Ľ��
		StringBuffer buffer = new StringBuffer();
		for (char c : arrs) {
			buffer.append(map.get(new Character(c).toString()));
			// System.out.println(c);
		}
		return buffer.toString();
	}

	/**
	 * ��ÿ��Ҷ�ӽڵ���б�ֵ ˼·�ǣ����¶��ϵ�ɨ��
	 * 
	 * @param nodes
	 * @return
	 */
	public static Map<String, String> encodeInfo(List<binaryTreeNode> nodes) {
		Map<String, String> map = new HashMap<String, String>();
		for (binaryTreeNode node : nodes) {
			String codeWord = new String();
			// ��ȡ��Ҷ�ӽڵ���ַ�
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
	 * ����������
	 * 
	 * @return
	 */
	public static String decode(String binaryStr,
			Map<Character, Integer> statistic) {

		LinkedList<binaryTreeNode> nodes = new LinkedList<>();
		LinkedList<Character> list = new LinkedList<>();
		// ������������
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
				// ���϶��µ�ɨ��
			}
			buffer.append(node.symbol);
		}
		return buffer.toString();
	}

	/**
	 * ��һ���ַ�������ͳ�ƣ�����ͳ�ƺ����Ϣ��ŵ�map��
	 * 
	 * @param data
	 * @return
	 */
	public static Map<Character, Integer> statsticInfo(String data) {
		// ����Ϣ���浽map��
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] arrs = data.toCharArray();
		// ����ͳ����Ϣ
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
