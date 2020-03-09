package com.wxf.data.search;

public class HashTable {

	private HashItem[] items;
	private int tableSize;
	private int currentSize;

	public HashTable(int tableSize) {
		super();
		this.items = new HashItem[tableSize];
		this.tableSize = tableSize;
		this.currentSize = 0;    
	}

	public boolean isIn(int x) {
		// 先查找，如果查找到，则result>0否则表示没有查找到
		int result = find(x);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 在哈希表中查找元素x
	 * 
	 * @param x
	 *            查找的元素
	 * @return 返回查找到的哈希地址
	 */
	public int find(int x) {
		// 采用除留余数法
		int i = x % tableSize;
		int j = i;
		if (items[j] == null)
			items[j] = new HashItem(0);
		// 存在冲突
		while (items[j].info == 1 && items[j].data != x) {
			// 线性侦探法
			j = (j + 1) % tableSize;
			// 如果j==i表示已经查找完 整个哈希表的数组
			if (j == i)
				return -tableSize;
		}
		// 如果此条件成立，表示循环退出的结果是items[j].data == x，表示已经找到
		if (items[j].info == 1)
			// 返回哈希地址
			return j;
		// 否则表示没有找到，返回哈希地址的负值
		else
			return -j;
	}

	/**
	 * 插入一个数据元素到哈希表中
	 * 
	 * @param x
	 * @throws Exception
	 */
	public void insert(int x) throws Exception {
		int result = find(x);
		if (result > 0)
			throw new Exception("the data is existed");
		else if (result == -tableSize)
			throw new Exception("the hash table is full,cannot to insert.");
		else {
			items[-result] = new HashItem(x, 1);
			currentSize++;
		}
	}
	public void delete(int x) throws Exception {
		int result = find(x);
		// result>=0表示找到，则进行删除
		if (result >= 0) {
			System.out.println(result);
			items[result].info = 0;
			currentSize--;
		} else {
			throw new Exception("the data is not existed!!");
		}
	}

	/**
	 * 获取哈希地址为i的数据元素的值
	 * 
	 * @param i
	 * @return
	 */
	public int getValue(int i) {
		return items[i].data;
	}

	public HashTable() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 哈希表项
	 * 
	 * @author Administrator
	 * 
	 */
	class HashItem {
		// 数据元素
		public int data;
		// 标志值
		public int info;

		public HashItem() {

		}

		public HashItem(int data, int info) {
			super();
			this.data = data;
			this.info = info;
		}

		public HashItem(int info) {
			super();
			this.info = info;
		}

	}

	public static void main(String[] args) {
		HashTable hashTable = new HashTable(13);
		int[] array = new int[] { 180, 750, 600, 430, 541, 900, 4 };
		for (int a : array) {
			try {
				hashTable.insert(a);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 循环的输出结果
		for (int i = 0; i < array.length; i++) {
			int result = hashTable.find(array[i]);
			if (result > 0) {
				System.out.println("i=" + i + "\tthe value:"
						+ hashTable.getValue(result));
			}
		}
		try {
			boolean flag = hashTable.isIn(430);
			if (flag)
				System.out.println("430在哈希表中！！");
			else {
				System.out.println("430不在哈希表中");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hashTable.delete(430);
			if (hashTable.isIn(430)) {
				System.out.println("430在哈希表中！！");
			} else {
				System.out.println("430不在哈希表中！！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
