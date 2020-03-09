package com.wxf.data.search;

public class SeqSearch {

	public SeqSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 无序序列的查找方法1。for循环方式来实现
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int seqSearch1(int[] data, int element) {
		int index = -1;
		for (int i = 0; i < data.length; i++) {
			if (element == data[i]) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * 无序序列的查找方法2 while循环实现。
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int seqSearch2(int[] data, int element) {
		int index = -1;
		int i = 0;
		while (i < data.length && data[i] != element) {
			i++;
		}
		if (i==data.length)
			index = -1;
		else {
			index=i;
		}
		return index;
	}

	public static void main(String[] args) {

		int[] data = { 12, 234, 44, 5, 76, 7, 2, 21 };
		int i = seqSearch2(data, 0);
		String result = parseResult(i);
		System.out.println(result);
	}

	/**
	 * 通过返回的查找的下标，来进行分析结果
	 * 
	 * @param i
	 * @return
	 */
	private static String parseResult(int i) {
		String result = i == -1 ? "查找失败，没有找到该元素" : "查找成功，该元素对应的数组中的下表为：" + i;
		return result;
	}
}
