package com.wxf.data.search;


public class BinSearch {

	public BinSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ͨ��ѭ���ķ�ʽ�����
	 * 
	 * @param data
	 *            Ҫ���ҵ�����
	 * @param element
	 *            Ҫ���в��ҵ�Ԫ��
	 * @return ���ظ�Ԫ���������е��±���������Ϊ-1���ʾû���ҵ�������ʧ��
	 */
	public static int binSearch1(int[] data, int element) {
		int index = -1;
		int n = data.length;
		// ����Сֵdata[0]����low
		int low = 0;
		// �����ֵdata[n-1]��ֵ��high����
		int high = n - 1;
		while (low <= high) {
			// ��ö��ߵ��м�ֵ
			int mid = (low + high) / 2;
			if (data[mid] < element)
				low = mid + 1;
			else if (data[mid] > element)
				high = mid - 1;
			else {
				return mid;
			}
		}
		return index;
	}

	/**
	 * ͨ���ݹ��˼������ƶ��ֲ����㷨
	 * 
	 * @param data
	 *            Ҫ���ҵ�����
	 * @param element
	 *            ���ҵ�Ԫ��
	 * @param low
	 *            ��ʼʱ����Сֵ
	 * @param high
	 *            ��ʼʱ�����ֵ
	 * @return ���ز��ҵ���Ԫ�ص��±꣬����-1��ʾû���ҵ���Ԫ��
	 */
	public static int binSearch2(int[] data, int element, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high) / 2;
		// �����Ԫ�����õ��ڶ�Ӧ���м�Ԫ�ص�ֵ����ֱ�Ӽ��Է���
		if (data[mid] == element)
			return mid;
		// �����Ԫ�ش����м�Ԫ�ص�ֵ�������ϰ������в��ң��������°������в���
		if (element > data[mid]) {
			return binSearch2(data, element, mid + 1, high);
		} else {
			return binSearch2(data, element, low, mid - 1);
		}
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4, 6, 43, 56 };
		System.out
				.println(parseResult(binSearch2(data, 6, 0, data.length - 1)));
	}

	/**
	 * ͨ�����صĲ��ҵ��±꣬�����з������
	 * 
	 * @param i
	 * @return
	 */
	private static String parseResult(int i) {
		String result = i == -1 ? "����ʧ�ܣ�û���ҵ���Ԫ��" : "���ҳɹ�����Ԫ�ض�Ӧ�������е��±�Ϊ��" + i;
		return result;
	}

}
