package com.wxf.data.search;

import java.util.Arrays;

public class IndexSearch {

	// 主表
	public static int[] students = new int[] { 1101, 1102, 1103, 1104, 0, 0, 0,
			0, 2022, 2023, 2025, 2045, 2044, 0, 0, 0, 3011, 3019, 3045 };
	// 建立索引表
	public static IndexItem[] indexItems = new IndexItem[] {
			new IndexItem(1, 0, 4), new IndexItem(2, 8, 5),
			new IndexItem(3, 16, 3) };

	/**
	 * 索引查找方法
	 * 
	 * @param key
	 *            要查找的对象
	 * @return 返回检索到的数据存放在数组中的下标
	 */
	public static int indexSearch(int key) {
		// 建立索引规则根据元素中的数据除以1000来建立索引表
		int index = key / 1000;

		IndexItem item = null;
		// 首先在索引表中来查找，
		for (int i = 0; i < indexItems.length; i++) {
			if (indexItems[i].index == index) {
				// 保存当前元素属于哪个索引表
				item = indexItems[i];
				break;
			}
		}
		// 如果item为空表示在索引表中没有找到对应的数据则直接返回
		if (item == null) {
			return -1;
		}
		// 找到之后，在对应索引表中那一段数据中开始查找
		for (int i = item.start; i < item.start + item.length; i++) {
			if (students[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 动态的记性插入数据元素到主表中，同时更新索引表 以下为步骤： 1.首先根据要插入的元素找到对应的索引表 2.在进行更新索引表和主表
	 * 
	 * @param key
	 * @return
	 */
	public static boolean insertNode(int key) {
		// 建立索引规则
		int index = key / 1000;
		IndexItem item = null;
		int i = 0;
		// 首先根据要要插入的数据元素，寻找该数对应的索引表
		for (; i < indexItems.length; i++) {
			if (indexItems[i].index == index) {
				item = indexItems[i];
				break;
			}

		}
		// 如果item不为空，则表示找到了该元素对应的索引表
		if (item != null) {
			// 更新主表，将该元素添加到主表中
			students[item.start + item.length] = key;
			// 索引表的长度加1
			indexItems[i].length++;
			return true;
		}

		return false;
	}

	/**
	 * 索引项实体类
	 * 
	 * @author Administrator
	 * 
	 */
	public static class IndexItem {
		// 索引对应的主表中的值
		public int index;
		// 主表记录区间段的开始位置
		public int start;
		// 主表记录区间的长度
		public int length;

		public IndexItem() {
			// TODO Auto-generated constructor stub
		}

		public IndexItem(int index, int start, int length) {
			super();
			this.index = index;
			this.start = start;
			this.length = length;
		}

	}

	public static void main(String[] args) {
		System.out.println("插入前的元素：");
		System.out.println(Arrays.toString(students));
		boolean result = insertNode(2034);
		if (result) {
			System.out.println("插入后的元素：");
			System.out.println(Arrays.toString(students));
			int i = indexSearch(2025);
			System.out.println("查找到的元素的位置：");
			System.out.println(i);
		}

	}
}
