package com.wxf.data.refer;

import java.util.Arrays;

public class IndexSearch {
//	com.wxf.data.refer.IndexSearch
	// ����
	static int[] students = { 101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202,
			203, 204, 0, 0, 0, 0, 0, 0, 301, 302, 303, 0, 0, 0, 0, 0, 0, 0 };
	// ������
	static IndexItem[] indexItem = { new IndexItem(1, 0, 5),
			new IndexItem(2, 10, 4), new IndexItem(3, 20, 3), };

	// ��������
	public static int indexSearch(int key) {
		IndexItem item = null;

		// ������������
		int index = key / 100;

		// ����ȥ������
		for (int i = 0; i < indexItem.length; i++) {
			if (indexItem[i].index == index) {
				item = new IndexItem(index, indexItem[i].start,
						indexItem[i].length);
				break;
			}
		}

		// ���itemΪnull����˵���������в���ʧ��
		if (item == null)
			return -1;

		for (int i = item.start; i < item.start + item.length; i++) {
			if (students[i] == key) {
				return i;
			}
		}
		return -1;
	}

	// / ��������
	public static int insert(int key) {
		IndexItem item = null;
		// ������������
		int index = key / 100;
		int i = 0;
		for (i = 0; i < indexItem.length; i++) {
			// ��ȡ��������
			if (indexItem[i].index == index) {
				item = new IndexItem(index, indexItem[i].start,
						indexItem[i].length);
				break;
			}
		}
		if (item == null)
			return -1;
		// ��������
		students[item.start + item.length] = key;
		// ����������
		indexItem[i].length++;
		return 1;
	}

	public static void main(String[] args) {
		int value = 205;
		// ��205���뼯���У�������
		System.out.println(Arrays.toString(students));
		int index = insert(value);
		insert(308);
		// �������ɹ�����ȡ205Ԫ�����ڵ�λ��
		if (index == 1) {
			System.out.println("\n��������ݣ�" + Arrays.toString(students));
			System.out.println("\n����Ԫ�أ�205�������е�λ��Ϊ " + indexSearch(205) + "λ");
		}

	}

}

// ������ʵ��
class IndexItem {
	// ��Ӧ�����ֵ
	public int index;
	// �����¼����εĿ�ʼλ��
	public int start;
	// �����¼����εĳ���
	public int length;

	public IndexItem() {
	}

	public IndexItem(int index, int start, int length) {
		this.index = index;
		this.start = start;
		this.length = length;
	}
}

//���н����
//ԭ����Ϊ��[101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202, 203, 204, 0, 0, 0, 0, 0, 0, 301, 302, 303, 0, 0, 0, 0, 0, 0, 0]

//��������ݣ�[101, 102, 103, 104, 105, 0, 0, 0, 0, 0, 201, 202, 203, 204, 205, 0, 0, 0, 0, 0, 301, 302, 303, 308, 0, 0, 0, 0, 0, 0]

//����Ԫ�أ�205�������е�λ��Ϊ 14λ
