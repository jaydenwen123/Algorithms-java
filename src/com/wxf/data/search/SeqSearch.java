package com.wxf.data.search;

public class SeqSearch {

	public SeqSearch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �������еĲ��ҷ���1��forѭ����ʽ��ʵ��
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
	 * �������еĲ��ҷ���2 whileѭ��ʵ�֡�
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
