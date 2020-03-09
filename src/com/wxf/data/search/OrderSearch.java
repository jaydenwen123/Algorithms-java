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
		// 通过while循环来进行遍历因为该数组已经有序，因此只需要设置条件为数组的元素是否小于该数据元素，当循环结束时
		// 再进行对结束循环的条件进行分之的判断
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
	 * 通过返回的查找的下标，来进行分析结果
	 * 
	 * @param i
	 * @return
	 */
	private static String parseResult(int i) {
		String result = i == -1 ? "查找失败，没有找到该元素" : "查找成功，该元素对应的数组中的下表为：" + i;
		return result;
	}
}
