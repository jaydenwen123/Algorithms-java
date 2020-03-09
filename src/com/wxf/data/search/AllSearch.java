package com.wxf.data.search;

import java.util.Arrays;

/**
 * 
 * ���еĲ��ҷ��� 1.������˳����� 2.������˳����� 3.�����Ķ��ֲ��� 4.��������������
 * 
 * @author Administrator
 * 
 */
public class AllSearch {

	// �������ҵ�����
	public static int[] hostTable = new int[] { 101, 103, 105, 106, 0, 0, 0,
			201, 202, 205, 208, 209, 0, 0, 0, 0, 303, 307 };
	// �������ҵ�������
	public static IndexItem[] items = new IndexItem[] { new IndexItem(1, 0, 4),
			new IndexItem(2, 7, 5), new IndexItem(3, 16, 2) };

	/**
	 * 
	 * ������˳�����
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int OrderSearch(int[] data, int element) {
		int index = -1;
		// �������˳����ҵ�˼·���£�
		// 1.������ĵ�һ��Ԫ�ؿ�ʼ����
		int i = 0;
		while (i < data.length && data[i] < element)
			i++;
		// 2.��������ȸ�Ԫ��С��ֵ���ͼ�������
		if (i == data.length || data[i] > element)
			index = -1;
		else {
			index = i;
		}
		// 3.����ж��Ƿ��ҵ����㷨����
		return index;
	}

	/**
	 * 
	 * ������˳�����
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int SeqSearch(int[] data, int element) {
		int index = -1;
		// ������˳�����˼·���£�
		// 1.�Ӹ�����ĵ�һ��Ԫ����������ҵ���Ԫ�����˳�ѭ��
		int i = 0;
		while (i < data.length && data[i] != element) {
			i++;
		}
		// ���±����������д�����Ϊ�������������������i��ֵ����data������ĳ��ȡ�
		// �������data[i]==element
		// �ж�ʱ���ܳ��쳣
		if (i == data.length)
			index = -1;
		else {
			index = i;
		}

		// ���µ�forѭ���������whileѭ��Ч��һ��
		// for (int j = 0; j < data.length; j++) {
		// if (data[j] == element) {
		// index = j;
		// break;
		// }
		//
		// }
		// 2.�������������һ��Ԫ��֮����Ȼû���ҵ������㷨����������-1��Ԫ��δ�ҵ�
		return index;
	}

	/**
	 * �����Ķ��ֲ��� �ݹ�ṹ
	 * 
	 * @param data
	 * @param element
	 * @param low
	 * @param high
	 * @return
	 */
	public static int BinSearch1(int[] data, int element, int low, int high) {
		int index = -1;
		// �ݹ�ṹ�Ķ��ֲ���˼·���£�
		// 1.���ȶԲ��������ж��Ƿ��������
		if (low < high) {
			// 2.�ڲ�����������������£������low��high��ƽ��ֵmid
			int mid = (low + high) / 2;
			// 3��Ȼ�����element��data[mid]���бȽ�
			if (element == data[mid])
				return mid;
			// �����ȣ��򷵻�mid��������������������в���
			else if (element < data[mid]) {
				// �°��������
				return BinSearch1(data, element, low, mid - 1);
			} else {
				// �ϰ��������
				return BinSearch1(data, element, mid + 1, high);
			}
		}
		return index;
	}

	/**
	 * 
	 * �����Ķ��ֲ��� ѭ���ṹ
	 * 
	 * @param data
	 * @param element
	 * @param low
	 * @param high
	 * @return
	 */
	public static int BinSearch2(int[] data, int element, int low, int high) {
		// ѭ���ṹ�Ķ��ֲ������˼·���£�
		// 1.���Ƚ��в����ж��Ƿ����������
		// 2��������������������£��������м�ֵ
		// 3.Ȼ����к�element�Ƚϣ��������������������
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
	 * ��������������
	 * 
	 * @param data
	 * @param element
	 * @return
	 */
	public static int IndexSerach(int[] data, int element) {
		int index = -1;
		// �������ҵ�˼·���£�
		// 1.����Ҫ���������������
		IndexItem item = getIndexItem(element);
		if (item == null)
			return index;
		int j = item.start;
		// ͨ�������������
		for (; j < item.start + item.length; j++) {
			if (hostTable[j] == element) {
				index = j;
				break;
			}
		}
		// 2.����Ҫ���ҵ�����Ԫ�غ����������������Ԫ��λ���ĸ������ӱ�
		// 3.��������ӱ���,����������ӱ�����������в���
		// 4.�㷨����
		return index;
	}

	/**
	 * ��������Ԫ�ص�ֵ����ȡ��Ӧ�������ӱ���������ڣ��򷵻�Ϊ��
	 * 
	 * @param element
	 * @return
	 */
	private static IndexItem getIndexItem(int element) {
		int eIndex = element / 100;
		// ������¼�������ӱ�
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
	 * ��������
	 * 
	 * @param element
	 * @return
	 */
	public static boolean IndexInsert(int element) {
		boolean flag = false;
		// ���������˼·���£�
		// 1.���Ȳ��Ҹ�Ԫ�أ������Ԫ�ش��ڣ����޷����в���
		IndexItem item = getIndexItem(element);
		// 2.�����ҵ��ʵ���λ�ã��������
		if (item != null) {
			hostTable[item.start+item.length]=element;
			// 3.ͬʱ��Ӧ�������ӱ�ĳ��ȼ�һ
				item.length++;
			flag = true;
		}
		return flag;
	}

	/**
	 * �����������������
	 * 
	 * @author Administrator
	 * 
	 */
	public static class IndexItem {
		// ������
		public int index;
		// �������ʼ�±�
		public int start;
		// ���������к��м�������Ԫ��
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
	 * ͨ����������Ԫ���������е��±꣬���н���Ԫ���Ƿ��ҵ�
	 * 
	 * @param result
	 * @return ���ص��ǽ�����Ľ��
	 */
	public static String parseResult(int result) {
		String str = (result == -1) ? "û���ҵ���Ӧ��Ԫ�أ�����" : "�ҵ���Ԫ�أ���Ԫ���������еĵ�"
				+ (result + 1) + "��Ԫ��";
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
