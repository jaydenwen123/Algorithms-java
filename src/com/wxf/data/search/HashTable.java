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
		// �Ȳ��ң�������ҵ�����result>0�����ʾû�в��ҵ�
		int result = find(x);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * �ڹ�ϣ���в���Ԫ��x
	 * 
	 * @param x
	 *            ���ҵ�Ԫ��
	 * @return ���ز��ҵ��Ĺ�ϣ��ַ
	 */
	public int find(int x) {
		// ���ó���������
		int i = x % tableSize;
		int j = i;
		if (items[j] == null)
			items[j] = new HashItem(0);
		// ���ڳ�ͻ
		while (items[j].info == 1 && items[j].data != x) {
			// ������̽��
			j = (j + 1) % tableSize;
			// ���j==i��ʾ�Ѿ������� ������ϣ�������
			if (j == i)
				return -tableSize;
		}
		// �����������������ʾѭ���˳��Ľ����items[j].data == x����ʾ�Ѿ��ҵ�
		if (items[j].info == 1)
			// ���ع�ϣ��ַ
			return j;
		// �����ʾû���ҵ������ع�ϣ��ַ�ĸ�ֵ
		else
			return -j;
	}

	/**
	 * ����һ������Ԫ�ص���ϣ����
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
		// result>=0��ʾ�ҵ��������ɾ��
		if (result >= 0) {
			System.out.println(result);
			items[result].info = 0;
			currentSize--;
		} else {
			throw new Exception("the data is not existed!!");
		}
	}

	/**
	 * ��ȡ��ϣ��ַΪi������Ԫ�ص�ֵ
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
	 * ��ϣ����
	 * 
	 * @author Administrator
	 * 
	 */
	class HashItem {
		// ����Ԫ��
		public int data;
		// ��־ֵ
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
		// ѭ����������
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
				System.out.println("430�ڹ�ϣ���У���");
			else {
				System.out.println("430���ڹ�ϣ����");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			hashTable.delete(430);
			if (hashTable.isIn(430)) {
				System.out.println("430�ڹ�ϣ���У���");
			} else {
				System.out.println("430���ڹ�ϣ���У���");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
