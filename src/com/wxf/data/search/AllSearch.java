package com.wxf.data.search;

import java.util.Arrays;

/**
 * 
 * 所有的查找方法 1.无序表的顺序查找 2.有序表的顺序查找 3.有序表的二分查找 4.有序表的索引查找
 * 
 * @author Administrator
 * 
 */
public class AllSearch {

	// 索引查找的主表
	public static int[] hostTable = new int[] { 101, 103, 105, 106, 0, 0, 0,
			201, 202, 205, 208, 209, 0, 0, 0, 0, 303, 307 };
	// 索引查找的索引表
	public static IndexItem[] items = new IndexItem[] { new IndexItem(1, 0, 4),
			new IndexItem(2, 7, 5), new IndexItem(3, 16, 2) };

	/**
	 * 
	 * 有序表的顺序查找
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int OrderSearch(int[] data, int element) {
		int index = -1;
		// 有序表中顺序查找的思路如下：
		// 1.从数组的第一个元素开始查找
		int i = 0;
		while (i < data.length && data[i] < element)
			i++;
		// 2.如果遇到比该元素小的值，就继续查找
		if (i == data.length || data[i] > element)
			index = -1;
		else {
			index = i;
		}
		// 3.最后判断是否找到，算法结束
		return index;
	}

	/**
	 * 
	 * 无序表的顺序查找
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int SeqSearch(int[] data, int element) {
		int index = -1;
		// 无序表的顺序查找思路如下：
		// 1.从该数组的第一个元素找起，如果找到该元素则退出循环
		int i = 0;
		while (i < data.length && data[i] != element) {
			i++;
		}
		// 以下必须这样进行处理，因为当最后遍历完整个数组后，i的值等于data的数组的长度。
		// 所以如果data[i]==element
		// 判断时会跑出异常
		if (i == data.length)
			index = -1;
		else {
			index = i;
		}

		// 以下的for循环和上面的while循环效果一样
		// for (int j = 0; j < data.length; j++) {
		// if (data[j] == element) {
		// index = j;
		// break;
		// }
		//
		// }
		// 2.如果到数组的最后一个元素之后，仍然没有找到，则算法结束，返回-1，元素未找到
		return index;
	}

	/**
	 * 有序表的二分查找 递归结构
	 * 
	 * @param data
	 * @param element
	 * @param low
	 * @param high
	 * @return
	 */
	public static int BinSearch1(int[] data, int element, int low, int high) {
		int index = -1;
		// 递归结构的二分查找思路如下：
		// 1.首先对参数进行判断是否符合条件
		if (low < high) {
			// 2.在参数符合条件的情况下，计算出low和high的平均值mid
			int mid = (low + high) / 2;
			// 3、然后对其element和data[mid]进行比较
			if (element == data[mid])
				return mid;
			// 如果相等，则返回mid，否则在其上下区间进行查找
			else if (element < data[mid]) {
				// 下半区间查找
				return BinSearch1(data, element, low, mid - 1);
			} else {
				// 上半区间查找
				return BinSearch1(data, element, mid + 1, high);
			}
		}
		return index;
	}

	/**
	 * 
	 * 有序表的二分查找 循环结构
	 * 
	 * @param data
	 * @param element
	 * @param low
	 * @param high
	 * @return
	 */
	public static int BinSearch2(int[] data, int element, int low, int high) {
		// 循环结构的二分查找设计思路如下：
		// 1.首先进行参数判断是否符合条件，
		// 2、参数符合条件的情况下，计算其中间值
		// 3.然后进行和element比较，决定在其上下区间查找
		int index = -1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (element == data[mid])
				return mid;
			else if (element < data[mid])
				high = mid - 1;
			else {
				low = mid + 1;
			}
		}
		return index;
	}

	/**
	 * 有序表的索引查找
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int IndexSerach(int[] data, int element) {
		int index = -1;
		// 索引查找的思路如下：
		// 1.首先要建立主表和索引表
		IndexItem item = getIndexItem(element);
		if (item == null)
			return index;
		int j = item.start;
		// 通过有序查找来找
		for (; j < item.start + item.length; j++) {
			if (hostTable[j] == element) {
				index = j;
				break;
			}
		}
		// 2.根据要查找的数据元素和索引规则来计算该元素位于哪个索引子表
		// 3.如果索引子表中,则根据索引子表进行再主表中查找
		// 4.算法结束
		return index;
	}

	/**
	 * 根据数据元素的值，获取对应的索引子表，如果不存在，则返回为空
	 * 
	 * @param element
	 * @return
	 */
	private static IndexItem getIndexItem(int element) {
		int eIndex = element / 100;
		// 用来记录该索引子表
		IndexItem item = null;
		for (int i = 0; i < items.length; i++) {
			if (items[i].index == eIndex) {
				item = items[i];
				break;
			}
		}
		return item;
	}

	/**
	 * 索引插入
	 * 
	 * @param element
	 * @return
	 */
	public static boolean IndexInsert(int element) {
		boolean flag = false;
		// 索引插入的思路如下：
		// 1.首先查找该元素，如果该元素存在，则无法进行插入
		IndexItem item = getIndexItem(element);
		// 2.否则找到适当的位置，将其插入
		if (item != null) {
			hostTable[item.start+item.length]=element;
			// 3.同时对应的索引子表的长度加一
				item.length++;
			flag = true;
		}
		return flag;
	}

	/**
	 * 建立索引表的索引项
	 * 
	 * @author Administrator
	 * 
	 */
	public static class IndexItem {
		// 索引号
		public int index;
		// 主表的起始下标
		public int start;
		// 该索引表中含有几个数据元素
		public int length;

		public IndexItem(int index, int start, int length) {
			super();
			this.index = index;
			this.start = start;
			this.length = length;
		}

		public IndexItem() {
			// TODO Auto-generated constructor stub
		}
	}

	/**
	 * 
	 * 通过返回来的元素在数组中的下标，进行解析元素是否找到
	 * 
	 * @param result
	 * @return 返回的是解析后的结果
	 */
	public static String parseResult(int result) {
		String str = (result == -1) ? "没有找到对应的元素！！！" : "找到该元素，该元素是数组中的第"
				+ (result + 1) + "个元素";
		return str;
	}

	public static void main(String[] args) {
		int[] data1 = new int[] { 34, 23, 1, 45, 56, 7, 3, 123, 32 };
		int[] data2 = new int[] { 1, 3, 5, 12, 34, 67, 89, 100 };
		System.out.println("the unOrder table seqSearch:");
		System.out.println(parseResult(SeqSearch(data1, 111)));
		System.out.println("the order table  orderSearch:");
		System.out.println(parseResult(OrderSearch.orderSearch(data2, 111)));
		System.out.println("the order table  digui  binSearch:");
		System.out.println(parseResult(BinSearch1(data2, 444, 0,
				data2.length - 1)));
		System.out.println("the order table cycle binSearch:");
		System.out.println(parseResult(BinSearch2(data2, 444, 0,
				data2.length - 1)));
		System.out.println("the order table indexSearch:");
		// { 101, 103, 105, 106, 0, 0, 0,
		// 201, 202, 205, 208, 209, 0, 0, 0, 0, 303, 307 };
		
		System.out.println(Arrays.toString(hostTable));
		System.out.println(IndexInsert(104));
		System.out.println(Arrays.toString(hostTable));
		System.out.println(parseResult(IndexSerach(data1, 104)));

	}

}
