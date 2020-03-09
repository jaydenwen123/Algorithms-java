package com.wxf.data.search;

import java.util.Arrays;

public class IndexSearch {

	// ����
	public static int[] students = new int[] { 1101, 1102, 1103, 1104, 0, 0, 0,
			0, 2022, 2023, 2025, 2045, 2044, 0, 0, 0, 3011, 3019, 3045 };
	// ����������
	public static IndexItem[] indexItems = new IndexItem[] {
			new IndexItem(1, 0, 4), new IndexItem(2, 8, 5),
			new IndexItem(3, 16, 3) };

	/**
	 * �������ҷ���
	 * 
	 * @param key
	 *            Ҫ���ҵĶ���
	 * @return ���ؼ����������ݴ���������е��±�
	 */
	public static int indexSearch(int key) {
		// ���������������Ԫ���е����ݳ���1000������������
		int index = key / 1000;

		IndexItem item = null;
		// �������������������ң�
		for (int i = 0; i < indexItems.length; i++) {
			if (indexItems[i].index == index) {
				// ���浱ǰԪ�������ĸ�������
				item = indexItems[i];
				break;
			}
		}
		// ���itemΪ�ձ�ʾ����������û���ҵ���Ӧ��������ֱ�ӷ���
		if (item == null) {
			return -1;
		}
		// �ҵ�֮���ڶ�Ӧ����������һ�������п�ʼ����
		for (int i = item.start; i < item.start + item.length; i++) {
			if (students[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * ��̬�ļ��Բ�������Ԫ�ص������У�ͬʱ���������� ����Ϊ���裺 1.���ȸ���Ҫ�����Ԫ���ҵ���Ӧ�������� 2.�ڽ��и��������������
	 * 
	 * @param key
	 * @return
	 */
	public static boolean insertNode(int key) {
		// ������������
		int index = key / 1000;
		IndexItem item = null;
		int i = 0;
		// ���ȸ���ҪҪ���������Ԫ�أ�Ѱ�Ҹ�����Ӧ��������
		for (; i < indexItems.length; i++) {
			if (indexItems[i].index == index) {
				item = indexItems[i];
				break;
			}

		}
		// ���item��Ϊ�գ����ʾ�ҵ��˸�Ԫ�ض�Ӧ��������
		if (item != null) {
			// ������������Ԫ����ӵ�������
			students[item.start + item.length] = key;
			// ������ĳ��ȼ�1
			indexItems[i].length++;
			return true;
		}

		return false;
	}

	/**
	 * ������ʵ����
	 * 
	 * @author Administrator
	 * 
	 */
	public static class IndexItem {
		// ������Ӧ�������е�ֵ
		public int index;
		// �����¼����εĿ�ʼλ��
		public int start;
		// �����¼����ĳ���
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
		System.out.println("����ǰ��Ԫ�أ�");
		System.out.println(Arrays.toString(students));
		boolean result = insertNode(2034);
		if (result) {
			System.out.println("������Ԫ�أ�");
			System.out.println(Arrays.toString(students));
			int i = indexSearch(2025);
			System.out.println("���ҵ���Ԫ�ص�λ�ã�");
			System.out.println(i);
		}

	}
}
