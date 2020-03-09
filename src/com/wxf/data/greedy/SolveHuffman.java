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
 * ���ڴ�����������ı����Ĳ�����˼·���£� 1.���Ƚ��ж�Ҫ��������ݽ���ͳ�ƣ�ͳ�ƺ�Ľ����map�������洢
 * 2.ͨ��ͳ�ƺ����Ϣ�������������������ر�������Ϣ
 * 
 * @author Administrator
 * 
 */
public class SolveHuffman {

	// ���������ĸ��ڵ�
	public HuffmanBTreeNode root;

	public SolveHuffman() {
		// TODO Auto-generated constructor stub
	}

	public SolveHuffman(HuffmanBTreeNode root) {
		super();
		this.root = root;
	}

	/**
	 * ����������Ӧ�Ľڵ���
	 * 
	 * @author Administrator
	 * 
	 */
	public static class HuffmanBTreeNode implements
			Comparable<HuffmanBTreeNode> {
		// ���������ĸ��ڵ�
		public HuffmanBTreeNode parent;
		// �������������ӽڵ�
		public HuffmanBTreeNode leftChild;
		// �����������Һ��ӽڵ�
		public HuffmanBTreeNode rightChild;
		// ���������Ľڵ��Ӧ���ַ�
		public String symbol;
		// ���������Ľڵ��Ȩֵ
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
		 * ��������Ҷ�ӽڵ�
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
		 * �жϸýڵ�����ڵ�
		 * 
		 * @return
		 */
		public boolean isLeft() {
			return this.parent != null && parent.leftChild == this;
		}

		/**
		 * �жϸýڵ��Ƿ���Ҷ�ӽڵ�
		 * 
		 * @return
		 */
		public boolean isLeaf() {
			return this.leftChild == null && this.rightChild == null;
		}
	}

	/**
	 * ���ݴ�����ַ�����ͳ�Ʋ�ͬ���ַ����ֵĴ�����������ͳ�ƺ����Ϣ
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
	 * ������������
	 * 
	 * @param statistics
	 * @return
	 */
	public static SolveHuffman createHuffmanTree(
			Map<Character, Integer> statistics, List<HuffmanBTreeNode> nodes) {
		SolveHuffman tree = null;
		// ͨ�����ȼ�������ʵ��
		PriorityQueue<HuffmanBTreeNode> queue = new PriorityQueue<SolveHuffman.HuffmanBTreeNode>();
		// ������������˼·���£�
		// ��.���ȸ���ͳ�Ƶ���Ϣ���д���Ҷ�ӽڵ�
		// 2��Ȼ�����е�Ҷ�ӽڵ㲻�ϵ���Ӻͳ��ӣ����ֻ����һ�Žڵ㣬��Ϊ���ڵ�
		Character[] chars = statistics.keySet().toArray(new Character[0]);
		// ������������
		HuffmanBTreeNode leftChild, rightChild, parent = null;
		for (Character c : chars) {
			// ����Ҷ�ӽڵ�
			HuffmanBTreeNode node = new HuffmanBTreeNode(c + "",
					statistics.get(c));
			queue.add(node);
			// ������ӵ�list�����У��������
			nodes.add(node);

		}
		// ��¼��ǰ�Ķ����е�Ԫ�صĸ���
		int n = queue.size();
		for (int i = 1; i < n; i++) {
			// �����ȼ������е�������Ȩֵ��С�Ľڵ�
			leftChild = queue.poll();
			rightChild = queue.poll();
			// Ȼ������������ڵ��������µĸ��ڵ㣬��ȨֵΪ���Һ��ӽڵ��Ȩֵ֮�ͣ��ַ�ҲΪ���Һ����ַ�֮��
			parent = new HuffmanBTreeNode(leftChild.symbol + rightChild.symbol,
					leftChild.weigh + rightChild.weigh);
			// ���½���ָ�����ߵĹ�ϵ
			leftChild.parent = parent;
			rightChild.parent = parent;
			parent.leftChild = leftChild;
			parent.rightChild = rightChild;
			// ���´����ĸ��ڵ�Ҳ���뵽������
			queue.add(parent);
		}
		// �������һ���ڵ�
		HuffmanBTreeNode root = queue.poll();
		tree = new SolveHuffman(root);
		return tree;
	}

	/**
	 * ����������
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(String data, Map<Character, Integer> pathLength) {
		String encodeStr = "";
		Map<Character, Integer> statistics = statistic(data);
		List<HuffmanBTreeNode> nodes = new ArrayList<>();
		// ��ȡÿ��Ҷ�ӽڵ�ı�����Ϣ
		Map<Character, String> infos = getEachNodeInfo(statistics, nodes,
				pathLength);
		char[] chars = data.toCharArray();
		// �齨��������Ϣ
		for (char c : chars) {
			encodeStr += infos.get(c);
		}
		return encodeStr;
	}

	/**
	 * ������������Ĵ�Ȩ·������
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
	 * ����ÿ���ڵ�ı�����Ϣ��ͨ��map����
	 * 
	 * @param statistics
	 *            ͳ�Ƶ��ַ���Ϣ
	 * @param nodes
	 *            Ҷ�ӽڵ�ļ���
	 * @param pathLength
	 *            ÿ��Ҷ�ӽڵ��·������
	 * @return
	 */
	public static Map<Character, String> getEachNodeInfo(
			Map<Character, Integer> statistics, List<HuffmanBTreeNode> nodes,
			Map<Character, Integer> pathLength) {
		Map<Character, String> infos = new HashMap<>();
		// ������������
		createHuffmanTree(statistics, nodes);
		// �����Ǵ�������ɨ�裬���������ڵ�ʱ���Ž���
		for (int i = 0; i < nodes.size(); i++) {
			// ÿ��Ҷ�ӽڵ������
			String codeWord = "";
			// û��Ҷ�ӽڵ��·������
			int length = 0;
			HuffmanBTreeNode currParent = nodes.get(i);
			// ��ȡ��Ҷ�ӽڵ���ַ�
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
			// ͳ�����ֵ���Ϣ
			infos.put(character, codeWord);
			// ͳ�ƽڵ��·������
			if (pathLength != null) {
				pathLength.put(character, length);
			}
		}
		return infos;
	}

	/**
	 * ����������
	 * 
	 * @param binStr
	 * @return
	 */
	public static String decode(String binStr,
			Map<Character, Integer> statistics) {
		String decodeStr = "";
		// �����������˼·���£�
		// 1.������Ҫ������������
		// 2.��ν���Ĺ��������϶��µ�ɨ��Ĺ��̣�ÿ��ɨ��ֱ������Ҷ�ӽڵ��Ϊֹ
		List<HuffmanBTreeNode> nodes = new ArrayList<SolveHuffman.HuffmanBTreeNode>();
		// ������������
		SolveHuffman tree = createHuffmanTree(statistics, nodes);
		LinkedList<Character> chars = new LinkedList<>();
		char[] data = binStr.toCharArray();
		for (char c : data) {
			chars.addLast(c);
		}
		while (!chars.isEmpty()) {
			HuffmanBTreeNode node = tree.root;
			// ���϶��µĲ���Ҷ�ӽڵ�
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
		System.out.println("����һ����Ҫ�������ͬ�ַ��ı�������");
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
		System.out.println("���������Ĵ�Ȩ·������Ϊ��" + getWPL(pathLength, nodes));
		System.out.println("���Զ����ò�����Ҫ�Ƕ�һ�����ݵı���ͽ�����в��ԣ�");
		String data = "hello everyone today is a nice day int the century ,so we should to enjoy it all right?";
		Map<Character, Integer> statistics = statistic(data);
		System.out.println("ԭ��Ϣ��");
		System.out.println(data);
		String encodeStr = encode(data, null);
		System.out.println("��������Ϣ��");
		System.out.println(encodeStr);
		System.out.println("��������Ϣ��");
		System.out.println(decode(encodeStr, statistics));
	}

}
