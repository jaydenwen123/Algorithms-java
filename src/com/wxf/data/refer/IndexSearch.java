package com.wxf.data.refer;

import java.util.Arrays;

public class IndexSearch {
//	com.wxf.data.refer.IndexSearch
	// 主表
	static int[] students = { 101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202,
			203, 204, 0, 0, 0, 0, 0, 0, 301, 302, 303, 0, 0, 0, 0, 0, 0, 0 };
	// 索引表
	static IndexItem[] indexItem = { new IndexItem(1, 0, 5),
			new IndexItem(2, 10, 4), new IndexItem(3, 20, 3), };

	// 查找数据
	public static int indexSearch(int key) {
		IndexItem item = null;

		// 建立索引规则
		int index = key / 100;

		// 首先去索引找
		for (int i = 0; i < indexItem.length; i++) {
			if (indexItem[i].index == index) {
				item = new IndexItem(index, indexItem[i].start,
						indexItem[i].length);
				break;
			}
		}

		// 如果item为null，则说明在索引中查找失败
		if (item == null)
			return -1;

		for (int i = item.start; i < item.start + item.length; i++) {
			if (students[i] == key) {
				return i;
			}
		}
		return -1;
	}

	// / 插入数据
	public static int insert(int key) {
		IndexItem item = null;
		// 建立索引规则
		int index = key / 100;
		int i = 0;
		for (i = 0; i < indexItem.length; i++) {
			// 获取到了索引
			if (indexItem[i].index == index) {
				item = new IndexItem(index, indexItem[i].start,
						indexItem[i].length);
				break;
			}
		}
		if (item == null)
			return -1;
		// 更新主表
		students[item.start + item.length] = key;
		// 更新索引表
		indexItem[i].length++;
		return 1;
	}

	public static void main(String[] args) {
		int value = 205;
		// 将205插入集合中，过索引
		System.out.println(Arrays.toString(students));
		int index = insert(value);
		insert(308);
		// 如果插入成功，获取205元素所在的位置
		if (index == 1) {
			System.out.println("\n插入后数据：" + Arrays.toString(students));
			System.out.println("\n数据元素：205在数组中的位置为 " + indexSearch(205) + "位");
		}

	}

}

// 索引项实体
class IndexItem {
	// 对应主表的值
	public int index;
	// 主表记录区间段的开始位置
	public int start;
	// 主表记录区间段的长度
	public int length;

	public IndexItem() {
	}

	public IndexItem(int index, int start, int length) {
		this.index = index;
		this.start = start;
		this.length = length;
	}
}

//运行结果：
//原数据为：[101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202, 203, 204, 0, 0, 0, 0, 0, 0, 301, 302, 303, 0, 0, 0, 0, 0, 0, 0]

//插入后数据：[101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202, 203, 204, 205, 0, 0, 0, 0, 0, 301, 302, 303, 308, 0, 0, 0, 0, 0, 0]

//数据元素：205在数组中的位置为 14位
