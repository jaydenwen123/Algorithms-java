package com.wxf.data.search;

public class OrderSearch {

	public OrderSearch() {
		// TODO Auto-generated constructor stub
	}

	public static int orderSearch(int[] data, int element) {
		int index = -1;
		// int i=0;
		// for(;i<data.length && data[i]<element;i++);
		// if(data[i]==element){
		// index=i;
		// }
		int i = 0;
		// ͨ��whileѭ�������б�����Ϊ�������Ѿ��������ֻ��Ҫ��������Ϊ�����Ԫ���Ƿ�С�ڸ�����Ԫ�أ���ѭ������ʱ
		// �ٽ��жԽ���ѭ�����������з�֮���ж�
		while (i < data.length && data[i] < element) {
			i++;
		}
		if(i==data.length || data[i]>element)
			index=-1;
		else
			index = i;
		return index;
	}

	public static void main(String[] args) {
		int[] data = { 1, 2, 3, 4, 6, 43, 56 };
		System.out.println(parseResult(orderSearch(data, 43)));
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
